#include <ESP8266WiFi.h>
#include <Ticker.h>
#include <PubSubClient.h>
#include <WiFiManager.h>
#include <Esp.h>
#include <ArduinoJson.h>


WiFiManager wifiManager;
WiFiManagerParameter custom_mqtt_server("server", "mqtt server", "192.168.1.74", 40);


#define MQTT_PORT 1883
#define PUBLISH_INTERVAL 1000

enum State {
  WAITING,
  ACTIVE
};

enum State state = WAITING;

int value = 0;
long lastMsg, previousMill = 0;

WiFiClient wifiClient;

PubSubClient client;
Ticker mqttReconnectTimer;

WiFiEventHandler wifiConnectHandler;
WiFiEventHandler wifiDisconnectHandler;
Ticker wifiReconnectTimer;

String readTopic = "";
String actionTopic = "";
String discoverabilityTopic = "";
IPAddress mqqtHost;

String user = "";
String pass = "";
String macAddress = "";

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    // If you do not want to use a username and password, change next line to
    // if (client.connect("ESP8266Client")) {
    if (client.connect(WiFi.macAddress().c_str())) {
      Serial.println("connected");
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
      delay(1000);
    }
  }

  if (client.subscribe(actionTopic.c_str())) {
     Serial.printf("Subscribe to topic %s \n", actionTopic.c_str());
  }
  if (client.subscribe(discoverabilityTopic.c_str())) {
     Serial.printf("Subscribe to topic %s \n", actionTopic.c_str());
  }
}

void connect() {
  // wifiManager.setTimeout(180);
  wifiManager.addParameter(&custom_mqtt_server);
  macAddress = WiFi.macAddress();
  wifiManager.autoConnect(macAddress.c_str());
  String mqttAddress = custom_mqtt_server.getValue();
  mqqtHost.fromString(mqttAddress);
  Serial.println(mqttAddress);

  client.setClient(wifiClient);
  client.setServer(mqqtHost, MQTT_PORT);
  client.setCallback(callback);

  readTopic = "DATA-" + macAddress;
  actionTopic = "ACTION-" + macAddress;
  discoverabilityTopic = "discoverability-" + macAddress;
}

void callback(char* topic, byte* payload, unsigned int length)
{
  if (String(topic) == discoverabilityTopic) {
    state = ACTIVE;    
  } else {
    payload[length] = '\0';
    value = String((char*) payload).toInt();
  }
}

String configs = "{\"mac\":\"mac-address\",\"name\":\"Iota Light 1.0\",\"actions\":[{\"id\":\"toggle\",\"name\":\"Toggle\",\"type\":\"BOOL\"}]}";

void setupSettings() {
  configs.replace("mac-address", macAddress);
}

void setup() {
  Serial.begin(115200);

  connect();

  Serial.println("Connected!");
  // initialize digital pin LED_BUILTIN as an output.
  pinMode(LED_BUILTIN, OUTPUT);

  setupSettings();
}


void handleWaiting() {
  uint16_t packet = client.publish("discoverability", configs.c_str());
  Serial.printf("Publishing on topic %s at QoS 1, packetId: %i", "discoverability", packet);
  Serial.printf("Message: %d \n", configs);
}

void handleActive() {
  digitalWrite(LED_BUILTIN, 1 - value);
  Serial.printf("Set light %d\n", value);

  uint16_t packet = client.publish(readTopic.c_str(), String(value).c_str());
  Serial.printf("Publishing on topic %s at QoS 1, packetId: %i", readTopic.c_str(), packet);
  Serial.printf("Message: %d \n", value);
}


void loop() {
  // Serial.println("Loooo");
  client.loop();
  // ESP.wdtFeed();
  long now = millis();
  if (now - lastMsg > PUBLISH_INTERVAL) {
    lastMsg = now;    

    if (WiFi.status() != WL_CONNECTED) {
      return;  
    }

    if (!client.connected()) {
      Serial.println("Enter reconnect phase!");
      reconnect();
    }

    if (state == WAITING) handleWaiting();
    else handleActive();


  }
}
