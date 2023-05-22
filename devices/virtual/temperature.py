import pygame

d = pygame.display.set_mode((600, 600))
pygame.init()

#Takes rectangle's size, position and a point. Returns true if that
#point is inside the rectangle and false if it isnt.
def pointInRectanlge(px, py, rw, rh, rx, ry):
    if px > rx and px < rx  + rw:
        if py > ry and py < ry + rh:
            return True
    return False

#Blueprint to make sliders in the game
class Slider:
    def __init__(self, position:tuple, upperValue:int=10, sliderWidth:int = 30, text:str="Editing features for simulation",
                 outlineSize:tuple=(300, 100))->None:
        self.position = position
        self.outlineSize = outlineSize
        self.text = text
        self.sliderWidth = sliderWidth
        self.upperValue = upperValue
        
    #returns the current value of the slider
    def getValue(self)->float:
        return self.sliderWidth / (self.outlineSize[0] / self.upperValue)

    #renders slider and the text showing the value of the slider
    def render(self, display:pygame.display)->None:
        #draw outline and slider rectangles
        pygame.draw.rect(display, (0, 0, 0), (self.position[0], self.position[1], 
                                              self.outlineSize[0], self.outlineSize[1]), 3)
        
        pygame.draw.rect(display, (0, 0, 0), (self.position[0], self.position[1], 
                                              self.sliderWidth, self.outlineSize[1] - 10))

        #determite size of font
        self.font = pygame.font.Font(pygame.font.get_default_font(), int((15/100)*self.outlineSize[1]))

        #create text surface with value
        valueSurf = self.font.render(f"{self.text}: {round(self.getValue())}", True, (255, 0, 0))
        
        #centre text
        textx = self.position[0] + (self.outlineSize[0]/2) - (valueSurf.get_rect().width/2)
        texty = self.position[1] + (self.outlineSize[1]/2) - (valueSurf.get_rect().height/2)

        display.blit(valueSurf, (textx, texty))

    #allows users to change value of the slider by dragging it.
    def changeValue(self)->None:
        #If mouse is pressed and mouse is inside the slider
        mousePos = pygame.mouse.get_pos()
        if pointInRectanlge(mousePos[0], mousePos[1]
                            , self.outlineSize[0], self.outlineSize[1], self.position[0], self.position[1]):
            if pygame.mouse.get_pressed()[0]:
                #the size of the slider
                self.sliderWidth = mousePos[0] - self.position[0]

                #limit the size of the slider
                if self.sliderWidth < 1:
                    self.sliderWidth = 0
                if self.sliderWidth > self.outlineSize[0]:
                    self.sliderWidth = self.outlineSize[0]

slider = Slider((100, 100), upperValue=100)


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

value = "10"

def on_connect(client, userdata, flags, return_code):
    if return_code != 0:
        print("could not connect, return code:", return_code)
        sys.exit()

    print("connected")
    client.subscribe(DISCOVERABILITY)

def on_message(client, userdata, message):
    global value
    global value_intensity
    global STATE
    if (message.topic == DISCOVERABILITY):
        STATE = 1

client = mqtt.Client(MAC_ADDRESS)
# client.username_pw_set(username="user_name", password="password") # uncomment if you use password auth
client.on_connect=on_connect
client.on_message=on_message

client.connect(broker_hostname, port)
client.loop_start()

config_dic = {
    "mac":MAC_ADDRESS,
    "name":"Virtual Iota TempSense",
    "actions":[
        {"id":"1","deviceAction":"readonly-number","name":"temperature","displayName": "Temperature", "status": value},
    ]}

config = json.dumps(config_dic)

def parse_data():
    o = [
        {"id": "1", "status": str(value)}
    ]

    return json.dumps(o)

last = time.time()

while True:
    pygame.event.get()
    d.fill((255, 255, 255))

    slider.render(d)
    slider.changeValue()
    value = slider.getValue()

    pygame.display.update()

    if time.time() < last + 1:
        continue

    last = time.time()

    if STATE == 0:
        err = client.publish("discoverability", config)
        print(err)
    else:
        client.publish(str(VALUE), parse_data())    
        print("Publishing", parse_data())