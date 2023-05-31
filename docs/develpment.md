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

<!-- Nuno -->

### Mosquitto

<!-- Nuno -->

## Devices

### Real Devices

### Virtual Devices

<!-- @Dustini -->

# Design and architecture
<!-- Document design and architecture problems and solutions, described preferably using pattern instances. Justify all design and architectural choices. -->

## Interpreter

The interpreter software pattern was followed to helps us achieve the workflows system.

We have 4 different types of nodes, which represent different types of rules. The entry nodes are action nodes, which have a reference to a specific device action that triggers the activation of the action node. The action node has children that might be either condition or operator nodes. Every node triggers the child nodes if the node's conditions are verified. The workflows end with the Action node that updates a device's value with the specified value by the user.

![](Workflow.png)

In order to implement this functionality with the interpreter software pattern, we mainly used two different design patterns, the visitor and the composite.

### Composite

#### Context

The workflow graph needs to be traverse troughout the several types of node, but they have common behaviour regarding updating the children. To address this problem, we use the composite pattern which allows each node to store sucessors and update the graph values recursively, until an event node is found.

#### Mapping

Each node extends the base class `Node` which contains a set of sucessor nodes. With the help of the visitor, each node has an `update` method. Each node verifies the its condition and when the condition is met, it will pass the control to the sucessors. We consider the `ActionNode` to be the Leaf node, as it marks the end of the graph.
 
#### Consequences

It easily allows to create a data structure similar to a tree graph, where each node might have different behaviour and update the values of whole tree based on the state of the rules of the workflow.

### Visitor

#### Context

The worfklow graphs need to be traversed to determine what actions should be executed. However, these graphs have different types of nodes that require different interpretations such as boolean operators, conditions, and actions. To address this, the visitor pattern is a useful pattern for adding functionality to these nodes without requiring significant changes to their classes. The visitor pattern also allows for different implementations to be designed for different visitors.

#### Mapping

The nodes are the elements being visited. The class `NodeVisitor`The `update` method invokes each of the different update methods and each one verifies if the class is the correct node type, otherwise it does nothing.

#### Consequences

It allows functionality to be added to the nodes without requiring significant changes to their classes. The visitor pattern also enforces that all node classes have implementations, even if the implementation does nothing. This improves readability and separates the different functionalities into a single class, which can also improve readability. Additionally, the visitor pattern is useful for accumulating information when traversing the graph, which could be used for flowing state through the nodes.

Everytime we need to create a new type of node, we simply need to add the method to the `NodeVisitor` class for the new type of node and veriify if it is the correct node. 

## Entity component element
 <!--  Nuno -->
