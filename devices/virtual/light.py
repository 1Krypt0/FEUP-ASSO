import json
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
DISCOVERABILITY = "discoverability-" + MAC_ADDRESS

STATE = 0

value = "0"

def on_connect(client, userdata, flags, return_code):
    if return_code != 0:
        print("could not connect, return code:", return_code)
        sys.exit()

    print("connected")
    client.subscribe(ACTION)
    client.subscribe(DISCOVERABILITY)

def on_message(client, userdata, message):
    global value
    global STATE
    print(message.topic, DISCOVERABILITY)
    if (message.topic == DISCOVERABILITY):
        STATE = 1
    else:
        parsed = json.loads(str(message.payload.decode("utf-8")))
        print("Received message: ", parsed)
        if parsed["id"] == "1":
            value = parsed["status"]


client = mqtt.Client(MAC_ADDRESS)
# client.username_pw_set(username="user_name", password="password") # uncomment if you use password auth
client.on_connect=on_connect
client.on_message=on_message

client.connect(broker_hostname, port)
client.loop_start()

config = "{\"mac\":\"<mac-address>\",\"name\":\"Virtual Iota Light 1.0\",\"actions\":[{\"id\":\"1\",\"deviceAction\":\"toggle\", \"displayName\": \"Toggle\",\"name\":\"toggle\", \"status\":\"<status>\"}]}"
config = config.replace("<mac-address>", MAC_ADDRESS)
config = config.replace("<status>", value)

def parse_data():
    o = [
        {"id": "1", "status": value},
    ]

    return json.dumps(o)

import pygame
 
# activate the pygame library .
pygame.init()
X = 600
Y = 600

# create the display surface object
# of specific dimension..e(X, Y).
scrn = pygame.display.set_mode((X, Y))
 
# set the pygame window name
pygame.display.set_caption('image')

# create a surface object, image is drawn on it.
imp = pygame.image.load("lamp.jpg").convert()

# Using blit to copy content from one surface to other
scrn.blit(imp, (0, 0))

# paint screen one time
pygame.display.flip()

try:
    while True:
        time.sleep(1)

        if STATE == 0:
            err = client.publish("discoverability", config)
            print(err)
        else:
            client.publish(str(VALUE), parse_data())
            print("Sent", parse_data())
            pygame.draw.circle(scrn,(255, 255, (1 - int(value))*255),(305, 276), 78)
            pygame.display.update()

finally:
    client.loop_stop()
