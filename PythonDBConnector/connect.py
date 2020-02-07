import paho.mqtt.client as paho
from database import sensor_data_handler


def on_subscribe(client, userdata, mid, granted_qos):
    print("Subscribed: " + str(mid) + "" + str(granted_qos))


def on_message(client, userdata, msg):
    print(msg.topic + " " + str(msg.qos) + " " + str(msg.payload))
    sensor_data_handler(msg.topic, msg.payload)


client = paho.Client()
client.on_subscribe = on_subscribe
client.on_message = on_message
client.connect("broker.mqttdashboard.com", 1883)
client.subscribe("mqtt_topic", qos=1)

client.loop_forever()
