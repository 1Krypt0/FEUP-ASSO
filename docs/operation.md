# Operation Guide

This document shows how to properly set up an environment, how to build the system and how to deploy it successfully.

## Setting up a production environment

For Home, our frontend, all that is necessary to start creating a production environment is to have all the appropriate packages installed. This can be easily done with npm by running `npm ci`, which will install all packages as if it was a first time installation.

## Building the system

Building Home is simple, since Svelte has scripts set up for it. All that is necessary is to run `npm run build` on the root of the project, and an output folder should appear inside a `.svelte-kit` folder.

## Deploying the system

### Home

To deploy Home, the best option would be to use a service like Vercel or Netlify. With this, it is only necessary to associate a git repository, and the service will usually detect the precise commands to deploy the system and automate the remaining steps.

Something that should need to change in the frontend is the fetch URLs. Since this was only tested on development purposes, it is necessary to take into account that most items were fetched from `localhost`. This means that, in production, the URLs should be changed to fetch from wherever the backend is being hosted.

### Core

#### Mosquitto Broker

In order to run the mosquitto broker in the core machine, we can simply run the command `docker compose up -d`.

#### Core System

Depending on the operating system, we can either use `gradle` or `./gradlew` for linux operating systems, and `.\gradlew.bat` for windows operating system.

First, we need to build the application using either `gradle assemble`. Then we can run the application using `gradle bootRun`. We can only run the application after initializing the mosquitto broker.
