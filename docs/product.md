# Product Documentation

This document highlights some of the main features our product offers, both concerning the user-centered ones that make it an easy to use product, as well as the features that make it easy to extend and improve our application.

## Product Vision

Here we highlight some of the ideas that we envisioned for the project, and its most standout features.

### Abstraction of devices

One of the key decisions for our product was how should a device be represented. There were many paths we thought of taking, from creating individual adapters for each one or to just create a class to interface with each one.

Since that would be a hassle and scale terribly, what we went with was a more general solution. We decided to abstract each device to the actions that it can perform. In our solution, an action is just a bit of state, for example an integer, a boolean or a string, and some restrictions on how the state can vary.

For example, a simple light with just an on/off switch has only one action, it manipulates one state of the light (whether it is on or off) and there are no restrictions.

A more complex example that should make this clear is that of an RGB light that has brightness levels. Here, we are dealing with three actions: one for the on/off switch, one for the brightness level and one for the color the light emits.

The on/off switch operates in the same way the one described before does. The brightness control is simply a range operation; this means that it is an integer value that can vary in a certain range (minimum and maximum), and with a defined step (for example, if we want to limit the brightness in increments of 5%). Finally, the RGB portion can be controled with a string specifying the hex value of the color, with the format restrictions associated with it.

With this simple concept we can define any action for any device, and the best part is that they are reusable. For example, if we want to alter the temperature of a thermostat, it also varies between well defined ranges, so the same range operation can be used, and only the restrictions have to be updated.

This design is far more scalable and makes it easy to operate with any kind of device, needing only to instantiate a method to work with the device's specific protocol.

### Simple to use

The best products are the ones that abstract away all the hard work and present to the user only a simple, albeit powerful interface. This was our goal with the frontend application that was devised, called Home.

One idea was to easily group related devices together, not only spacially but also by their capabilities. That's why the user can filter the devices shown by room or by category. One example of a category would be media devices, or climate devices.

Also, every action should feel natural and responsive, so not only should the devices update rapidly, but also the UI itself should respond to events quickly.

### Automation

Workflows, also referred as triggers and actions, play a crucial role in the automation of the project. They enable users to define specific conditions and corresponding actions that should be executed when those conditions are met. Automation is essential in the project as it allows users to streamline their daily routines, improve efficiency, and enhance the overall experience of managing their home automation devices. 

This functionality really ads value to our product, for different reasons:

- Customization: workflows allow users to customize and personalize their system according to their specific needs and preferences. By defining triggers and corresponding actions, users can tailor their devices' behavior to align with their daily routines and lifestyle, thus adapting to individual requirements and enhancing user satisfaction and convenience.

- Efficiency/time-saving: automation (workflows) eliminates the need for manual intervention in controlling various devices, this way saving time and effort for users, as they no longer have to individually operate each device or monitor their status continuously. Instead, the system handles routine tasks, allowing users to focus on other activities or enjoy a more streamlined living experience.

- Energy conservation: the ability to define workflows based on environmental conditions or occupancy patterns also contributes to energy conservation. For instance, users can set triggers to turn off lights, adjust thermostats, or power down appliances when certain conditions are met, such as when a room is unoccupied or when daylight is sufficient. This proactive approach to energy management promotes sustainability and reduces unnecessary energy consumption, leading to cost savings and a greener living environment.

- Safety and security: workflows also play a vital role in enhancing safety and security within the home. By integrating sensors and other security devices, users can set triggers to detect specific events, such as unauthorized access or the presence of smoke or carbon monoxide. The corresponding actions can include sending notifications, activating alarms, or initiating emergency protocols. This automation provides real-time monitoring and immediate responses, improving overall safety and security measures.

- Scalability and flexibility: the ability to easily add new kinds of devices and support various communication infrastructures ensures the scalability and flexibility of the application.

In summary, automation, achieved in the form of the so-called workflows, brings numerous benefits. By empowering users to define specific conditions and corresponding actions, this functionality enhances the overall experience, simplifies daily routines, and enables the system to operate autonomously, promoting a more intelligent, efficient, and convenient living environment.

## How to Use it

Before starting, do make sure that the hub is running, and that mosquitto container is also active, otherwise Home won't be able to fetch the necessary data.

In Home, you start by creating an account which holds all your data. Then, it is necessary to create some Rooms. A Room is just a way of grouping devices together. When you have at least one room set up, you can start adding devices!

To add a device, you simply select the room you want to add it in later, and hit the plus sign.

Automatically, all the devices nearby are discovered and presented for pairing, much like with Bluetooth. The types of actions allowed are inferred directly from the device itself, so the user can configure it straight away.

In the device page, the actions show up with a intuitive UI, and you can start updating it straight away.
