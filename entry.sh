#!/bin/bash
set -e
if [[ "$1" == "backend" ]];then
  echo "Start mvn backend"
  while inotifywait -r -e modify /app/src/main/;
  do
    mvn compile -o -DskipTests;
  done >/dev/null 2>&1 &

  mvn spring-boot:run
fi