import paho.mqtt.client as mqtt
import time
import sys
import json

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

value = "7ec0ee"
value_intensity = "150"

def on_connect(client, userdata, flags, return_code):
    if return_code != 0:
        print("could not connect, return code:", return_code)
        sys.exit()

    print("connected")
    client.subscribe(ACTION)
    client.subscribe(DISCOVERABILITY)

def on_message(client, userdata, message):
    global value
    global value_intensity
    global STATE
    print(message.topic, DISCOVERABILITY)
    if (message.topic == DISCOVERABILITY):
        STATE = 1
    else:
        parsed = json.loads(str(message.payload.decode("utf-8")))
        print("Received message: ", parsed)
        if parsed["id"] == "1":
            value = parsed["status"]
        elif parsed["id"] == "2":
            value_intensity = parsed["status"]
        # value = parsed


client = mqtt.Client(MAC_ADDRESS)
# client.username_pw_set(username="user_name", password="password") # uncomment if you use password auth
client.on_connect=on_connect
client.on_message=on_message

client.connect(broker_hostname, port)
client.loop_start()

config_dic = {
    "mac":MAC_ADDRESS,
    "name":"Virtual Iota Colorful 1.0",
    "actions":[
        {"id":"1","deviceAction":"toggle","name":"toggle","displayName": "Toggle", "status": value},
        {"id":"2", "deviceAction": "range", "name": "intensity", "displayName": "Intensity", "status": value_intensity, "properties": {
            "min": "0", "max": "100", "step": "1"
        }}
    ]}

config = json.dumps(config_dic)


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

surface1 = pygame.Surface((600,600))
surface1.set_colorkey((0,0,0))
surface1.set_alpha(int(value_intensity))

# paint screen one time
pygame.display.flip()

def parse_data():
    o = [
        {"id": "1", "status": value},
        {"id": "2", "status": value_intensity},
    ]

    return json.dumps(o)

try:
    while True:
        time.sleep(1)

        if STATE == 0:
            err = client.publish("discoverability", config)
            print(err)
        else:
            client.publish(str(VALUE), parse_data())
            scrn.fill((255,255, 255))
            print("Sent", parse_data())
            color = pygame.Color(f"#{value}")
            pygame.draw.circle(surface1,color,(305, 276), 78)
            surface1.set_alpha(int(value_intensity))
            scrn.blit(imp, (0, 0))
            scrn.blit(surface1, (0,0))
            pygame.display.update()

finally:
    client.loop_stop()
