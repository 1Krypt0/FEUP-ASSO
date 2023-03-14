#include <ESP8266WiFi.h>
#include <Ticker.h>
#include <PubSubClient.h>

#define WIFI_SSID "HUB-WIFI"
#define WIFI_PASSWORD "netdopovo"

// Raspberry Pi Mosquitto MQTT Broker
#define MQTT_HOST IPAddress(192, 168, 88, 201)
// For a cloud MQTT broker, type the domain name
//#define MQTT_HOST "example.com"
#define MQTT_PORT 1883

#define PUBLISH_INTERVAL 1000

WiFiClient wifiClient;

int value = 0;
long lastMsg, previousMill = 0;


PubSubClient client;
Ticker mqttReconnectTimer;

WiFiEventHandler wifiConnectHandler;
WiFiEventHandler wifiDisconnectHandler;
Ticker wifiReconnectTimer;

String readTopic = "";
String actionTopic = "";

String user = "";
String pass = "";

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
      delay(5000);
    }
  }

  if (client.subscribe(actionTopic.c_str())) {
     Serial.printf("Subscribe to topic %s \n", actionTopic.c_str());
  }
}


void connectToWifi() {
  Serial.println("Connecting to Wi-Fi...");
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
}

void onWifiConnect(const WiFiEventStationModeGotIP& event) {
  String macAddress = WiFi.macAddress();

  Serial.println("Connected to Wi-Fi. Mac Address " + macAddress);

  readTopic = "DATA-" + macAddress;
  actionTopic = "ACTION-" + macAddress;
}

void onWifiDisconnect(const WiFiEventStationModeDisconnected& event) {
  Serial.println("Disconnected from Wi-Fi.");
  wifiReconnectTimer.once(2, connectToWifi);
}

void callback(char* topic, byte* payload, unsigned int length)
{
    payload[length] = '\0';
    value = String((char*) payload).toInt();

    Serial.printf("Received %s %d\n", topic, value);
}

void setup() {
  Serial.begin(115200);
  Serial.println();

  wifiConnectHandler = WiFi.onStationModeGotIP(onWifiConnect);
  wifiDisconnectHandler = WiFi.onStationModeDisconnected(onWifiDisconnect);

  client.setClient(wifiClient);
  client.setServer(MQTT_HOST, MQTT_PORT);
  client.setCallback(callback);

  connectToWifi();

  // initialize digital pin LED_BUILTIN as an output.
  pinMode(LED_BUILTIN, OUTPUT);
}


void loop() {
    client.loop();
  
  long now = millis();
  if (now - lastMsg > PUBLISH_INTERVAL) {
    
    lastMsg = now;    

    if (WiFi.status() != WL_CONNECTED) {
      return;  
    }

    if (!client.connected()) {
      reconnect();
    }

    digitalWrite(LED_BUILTIN, 1 - value);
    Serial.printf("Set light %d\n", value);

    uint16_t packet = client.publish(readTopic.c_str(), String(value).c_str());
    Serial.printf("Publishing on topic %s at QoS 1, packetId: %i", readTopic.c_str(), packet);
    Serial.printf("Message: %d \n", value);
  }
}
