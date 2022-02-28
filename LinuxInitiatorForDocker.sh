#!/usr/bin/env bash

# Executes mvn clean package -Dmaven.test.skip=true for all sub directories containing a pom.xml

# How to Run:

# -> Run this commands in order:

# 1) chmod +x LinuxInitiatorForDocker.sh
# 2) ./LinuxInitiatorForDocker.sh

	
find . -name "pom.xml" -exec mvn clean package -Dmaven.test.skip=true -f '{}' \;