#!/bin/bash

sudo $HOME/stop_tomcat.sh

sudo rm -rf /Users/steve/Library/Tomcat/7.0/logs/*

sudo rm -rf /Users/steve/Library/Tomcat/7.0/webapps/*

export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_06.jdk/Contents/Home/

ant war

mv ercot-file-collector.war /Users/steve/Library/Tomcat/7.0/webapps/ercot-file-collector.war

sudo $HOME/start_tomcat.sh &
