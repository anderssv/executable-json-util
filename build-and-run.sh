#!/bin/bash -eu

mvn clean install
clear
./target/executable-json-util-1.0-SNAPSHOT.jar

