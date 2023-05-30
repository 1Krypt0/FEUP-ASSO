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

## How to Use it
