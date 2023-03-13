import paho.mqtt.client as mqtt
import time
import sys

if len(sys.argv) != 2:
    print("Invalid number of arguments, please provide a mac address for the device", sys.argv)
    exit(1)



broker_hostname = "localhost"
port = 1883

MAC_ADDRESS = sys.argv[1]
ACTION = "ACTION-" + MAC_ADDRESS
VALUE = "DATA-" + MAC_ADDRESS

status = "0"

def on_connect(client, userdata, flags, return_code):
    if return_code != 0:
        print("could not connect, return code:", return_code)
        sys.exit()

    print("connected")
    client.subscribe(ACTION)

def on_message(client, userdata, message):
    global status
    parsed = str(message.payload.decode("utf-8"))
    print("Received message: ", parsed)

    status = parsed


client = mqtt.Client("Client1")
# client.username_pw_set(username="user_name", password="password") # uncomment if you use password auth
client.on_connect=on_connect
client.on_message=on_message

client.connect(broker_hostname, port)
client.loop_start()


try:
    while True:
        time.sleep(1)
        result = client.publish(str(VALUE), status)
        if result[0] != 0:
            print("Failed to send message to broker")
            continue

        print("Written value", status, "to broker", str(VALUE))

finally:
    client.loop_stop()