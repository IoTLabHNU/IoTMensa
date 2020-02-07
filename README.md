# About
Dieser Prototyp wurde für das Seminar "Performance Management" entwickelt im Studiengang "Information Management Automotive".
Ziel war es, mehrere Datenquellen innerhalb der Hochschul-Mensa zu bestimmen, über eine Android Applikation zu erfassen und in einer Datenbank zu speichern.
Die abgespeicherten Daten können mit dem Visualizerungstool [Grafana](https://grafana.com/) in anpassbaren Dashboards visuell dargestellt werden.

## Die App
Die App besteht aus zwei Fenstern:
- **Startbildschirm** dient als Eingangs- und Ausgangsposition. Durch einen Knopfdruck kann die Umfrage gestartet werden.
- **Nutzereingabe** dient als Datenerfassung. Dem Nutzer werden zwei Fragen gestellt, die über einen *Numberpicker* und *RadioButtons* ausgewertet werden können. Nach der Bestätigung geht die App wieder automatisch auf den Startbildschirm zurück.

<p align="center">
  <img src="https://github.com/IoTLabHNU/IoTMensa/blob/master/images/gui_startscreen.PNG" width="300" alt="Startbildschirm">
  <img src="https://github.com/IoTLabHNU/IoTMensa/blob/master/images/survey_new.PNG" width="300" alt="Nutzereingabe">
</p>

## Kommunikation
Als Kommunikationschnittstelle zwischen App und Datenbank dient das MQTT-Protokoll. Die App fungiert dabei als Client, der Nachrichten über einen Broker an einen weitern Client im Backend sendet. Für Android Studio wurde der (HiveMq-Client)[https://www.hivemq.com/blog/hivemq-mqtt-client-features/android-support/] implementiert.

Die publish-Methode:
```java
Mqtt5BlockingClient client = Mqtt5Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost("broker.mqttdashboard.com")
                .buildBlocking();
```




