#!/usr/bin/env bash

# Executes mvn spring-boot:run for all sub directories containing a pom.xml

# How to Run:

# -> Run this commands in order:

# 1) chmod +x LinuxRunProjectViaMaven.sh
# 2) ./LinuxRunProjectViaMaven.sh

	
find . -name "pom.xml" -exec gnome-terminal -x mvn spring-boot:run -f '{}' \;