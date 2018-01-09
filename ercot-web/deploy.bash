#!/bin/bash

sudo $HOME/stop_tomcat.sh

sudo rm -rf /Users/steve/Library/Tomcat/7.0/logs/*

sudo rm -rf /Users/steve/Library/Tomcat/7.0/webapps/*

ant war

mv ercot.war /Users/steve/Library/Tomcat/7.0/webapps/ROOT.war

sudo $HOME/start_tomcat.sh &
