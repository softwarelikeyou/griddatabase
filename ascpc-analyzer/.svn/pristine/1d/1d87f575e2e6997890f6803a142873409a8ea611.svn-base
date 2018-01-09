#!/bin/bash

JAVA=/home/jdk1.7.0_07/bin/java

cd /home/ercot

BASE_DIR=/home/ercot

LIB_DIR=$BASE_DIR/lib

CLASSPATH=$LIB_DIR/rtlmp-analyzer.jar:$LIB_DIR/rtspp-analyzer.jar:$LIB_DIR/ascpc-analyzer.jar:$LIB_DIR/ercot-om.jar:$LIB_DIR/jta-1.1.jar:$LIB_DIR/javassist-3.12.0.GA.jar:$LIB_DIR/commons-lang-2.4.jar:$LIB_DIR/commons-collections-3.2.1.jar:$LIB_DIR/mysql-connector-java-5.1.13-bin.jar:$LIB_DIR/jersey-core-1.6.jar:$LIB_DIR/jersey-server-1.6.jar:$LIB_DIR/slf4j-api-1.6.1.jar:$LIB_DIR/slf4j-log4j12-1.6.1.jar:$LIB_DIR/dom4j-1.6.1.jar:$LIB_DIR/i18nlog-1.0.10.jar:$LIB_DIR/quartz-1.8.0.jar:$LIB_DIR/log4j-1.2.16.jar:$LIB_DIR/opencsv-2.3.jar:$LIB_DIR/hibernate-jpa-2.0-api-1.0.0.Final.jar:$LIB_DIR/hibernate3.jar:$LIB_DIR/xercesImpl.jar:.

$JAVA -classpath $CLASSPATH com.softwarelikeyou.ercot.analyzer.ascpc.DailyWorker
