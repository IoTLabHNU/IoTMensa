# About
Dieser Prototyp wurde für das Seminar "Performance Management" im Studiengang "Information Management Automotive" an der Hochschule Neu-Ulm entwickelt.
Ziel war es, mehrere Datenquellen innerhalb der Hochschul-Mensa zu bestimmen, über eine Android Applikation zu erfassen und in einer Datenbank zu speichern.
Die abgespeicherten Daten können mit dem Visualizierungstool [Grafana](https://grafana.com/) in anpassbaren Dashboards visuell dargestellt werden.

## Die App
Die App besteht aus zwei Fenstern:
- **Startbildschirm** dient als Eingangs- und Ausgangsposition. Durch einen Knopfdruck kann die Umfrage gestartet werden.
- **Nutzereingabe** dient als Datenerfassung. Dem Nutzer werden zwei Fragen gestellt, die über einen *Numberpicker* und *RadioButtons* ausgewertet werden können. Nach der Bestätigung geht die App wieder automatisch auf den Startbildschirm zurück.

<p align="center">
  <img src="https://github.com/IoTLabHNU/IoTMensa/blob/master/images/gui_startscreen.PNG" width="300" alt="Startbildschirm">
  <img src="https://github.com/IoTLabHNU/IoTMensa/blob/master/images/survey_new.PNG" width="300" alt="Nutzereingabe">
</p>

## Kommunikation
Als Kommunikationschnittstelle zwischen App und Datenbank dient das MQTT-Protokoll. MQTT ermöglicht leichtgewichtige M2M-Kommunikation über das Publish/Subscribe-Prinzip.

### Android Studio
Für Android Studio wurde der [HiveMq-Client](https://www.hivemq.com/blog/hivemq-mqtt-client-features/android-support/) implementiert.
Die publish-Methode über das Topic `mensaiot`:
```java
Mqtt5BlockingClient client = Mqtt5Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost("broker.mqttdashboard.com")
                .buildBlocking();
                
client.connect();                
                
client.publishWith().topic("mensaiot")
                .qos(MqttQos.AT_LEAST_ONCE)
                .payload(message.getBytes()).send();    
                
client.disconnect();                
```

### Python-Skript

Die Nachricht wird an einen [Python-Client](https://pypi.org/project/paho-mqtt/) weitergeleitet, der die empfangenen Daten verarbeiten kann.
Die Implementierung des Clients ist wie folgt:
```python
def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: " + str(mid) + "" + str(granted_qos))

def on_message(client, userdata, msg):
    print(msg.topic + "" + str(msg.qos) + "" + str(msg.payload))
    
client = paho.Client()
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("broker.mqttdashboard.com", 1883)
client.subscribe("mensaiot", qos=1)

client.loop_forever()
```

## Datenpersistenz

Eingehende Nachrichten werden in einer MySQL-Datenbanktabelle abgespeichert. Dazu wird ein Python-Connector in der Klasse `DatenbankManager` implementiert:

```python
class DatabaseManager:
    def __init__(self):
        # Connect to MySQL-database
        self.cnx = mysql.connector.connect(user='IMA',
                                           password='ima',
                                           host='192.168.141.46',
                                           port='3306',
                                           database='MensaDB')
        self.cnx.commit()
        self.cursor = self.cnx.cursor()

    def on_update_db(self, query, value=()):
        self.cursor.execute(query, value)
        self.cnx.commit()
        return

    def __del__(self):
        self.cursor.close()
        self.cnx.close()
 ```
 
 ## Entwurfsunterlage Prototyp
 
 <p align="center">
  <img src="" width="500" alt="Entwurfsunterlage"



