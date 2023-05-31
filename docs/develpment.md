# High-level architecture 
<!-- Provide higher-level views over these three types of elements using Package diagrams, if appropriate -->

## Components

![component-diagram](./component-diagram.png)

## Activities

<!-- use activity diagram  Ricky-->

## Infrastructure

<!-- use deployment diagram Ricky -->

# Technologies

<!-- Tools and rationale for choosing them (programming languages, frameworks, libraries, database engines, message queues). -->

## Front-end Technologies

### SvelteKit

One of the main reasons SvelteKit was chosen is its intuitive nature. SvelteKit embraces a component-based approach, making it easier to work with. 
Furthermore, SvelteKit's extensive documentation is a big help when starting to work with the framework. Finally, integrating TypeScript into SvelteKit was very easy, thanks to its compatibility and straightforward configuration process.

### Tailwind CSS

The utilization of Tailwind CSS significantly enhanced the frontend experience, offering a modern and efficient approach to building user interfaces. Tailwind CSS is a framework that provides a comprehensive set of pre-designed utility classes, which allowed to rapidly prototype and style the application. It is highly extensible and customizable and was used because some team members had already experience working with it.

## Back-end Technologies

### Spring Boot

Spring Boot, an extension of the Spring Framework, offers a toolkit for developing web applications. It supports both Java and Kotlin programming languages, with Kotlin being selected for this project due to its modernity and interoperability with Java.

Spring Boot was chosen because it is a framework that is easy to use, and has a well of documentation and community resources. It implements many features out of the box, like database access, relational mapping, and data validation. This allows us to focus on the business logic of the application, rather than spending time implementing these base components from scratch. Additionally, since some team members already had experience with Java and Kotlin, adopting Spring Boot provided a valuable opportunity to learn something new.

### MQTT

MQTT is a lightweight, publish-subscribe, machine to machine network protocol for message queue/message queuing service [Wikipedia](https://en.wikipedia.org/wiki/MQTT). 

MQTT was chosen due to its lightweight nature, which makes it ideal for IoT devices that don't have a lot of processing power or memory, and because it is a publish-subscribe protocol, which means that it is easy to add new devices to the system. It is also a widely used protocol, which means that there is a lot of documentation and community resources available, like client implementations for many programming languages.

For the MQTT broker, we chose the [Eclipse Mosquitto](https://mosquitto.org/) broker, which is an open-source broker that can be easily deployed on a server, as it is available as a Docker image.

We also used the [Eclipse Paho](https://www.eclipse.org/paho/) client library for both the Kotlin backend and the Python virtual devices, and the [PubSubClient](https://pubsubclient.knolleary.net/) library for the ESP32 devices.

## Devices

<!-- @Dustini -->

# Design and architecture
<!-- Document design and architecture problems and solutions, described preferably using pattern instances. Justify all design and architectural choices. -->

## Interpreter

<!-- Dustini -->

### Visitor

#### Context

The worfklow graphs need to be traversed to determine what actions should be executed. However, these graphs have different types of nodes that require different interpretations such as boolean operators, conditions, and actions. To address this, the visitor pattern is a useful tool for adding functionality to these nodes without requiring significant changes to their classes. The visitor pattern also allows for different implementations to be designed for different visitors.

#### Mapping

The nodes are the elements being visited. Each node type is a concrete implementation of the element interface and will have a method that accepts the visitor interface. The visitor interface defines the function signatures for the different node types.

One of the concrete visitors will be the workflow traversal visitor. This visitor is responsible for traversing the workflow graphs, verifying conditions, performing boolean logic, and executing actions depending on the node types encountered.

#### Consequences

One approach is to use a switch case expression to check the class or type of the node, and then execute the appropriate action. This approach is simpler to implement and more permissive. However, every time a new node type is created, the switch case expression needs to be updated. While this is not expected to happen frequently, it can be a maintenance burden.

Another approach is to implement this functionality on the node subclasses themselves. This is the simplest implementation of all, and it allows access to private elements. However, this approach can make the code more complex and harder to read as the logic for different node types will be scattered across different classes.

The visitor pattern provides a useful alternative. It allows functionality to be added to the nodes without requiring significant changes to their classes. The visitor pattern also enforces that all node classes have implementations, even if the implementation does nothing. This improves readability and separates the different functionalities into a single class, which can also improve readability. Additionally, the visitor pattern is useful for accumulating information when traversing the graph, which could be used for flowing state through the nodes. The visitor pattern has another advantage in that it allows for multiple visitor implementations to add different functionalities. For example, one implementation could count the number of node types in the workflow.

Despite this, it is more complex to implement and, similar to the switch case expression, the visitor interface and implementations also need to be updated whenever a new node type is created.

## Entity-Component System
 <!--  Nuno -->
 <!-- Talk about extensibilty using custom adapters-->
