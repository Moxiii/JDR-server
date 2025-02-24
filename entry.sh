#!/bin/bash

if [ "$1" = "backend" ]; then
    echo "Starting backend with Maven..."
    mvn spring-boot:run
else
    echo "Command not recognized"
    exit 1
fi
