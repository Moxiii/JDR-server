#!/bin/bash
set -e

case "$1" in
  backend)
    echo "Starting backend with Maven..."
    mvnw spring-boot:run
    ;;
  *)
    echo "Invalid argument. Use  'backend'."
    exit 1
    ;;
esac