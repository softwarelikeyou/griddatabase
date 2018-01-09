#!/bin/bash

JARS=../lib/ercot-om.jar
JARS=$JARS:../lib/poi-ooxml-3.9-20121203.jar
JARS=$JARS:../lib/poi.jar
JARS=$JARS:../lib/xbean.jar
JARS=$JARS:../lib/poi-ooxml-schemas-3.9-20121203.jar 
JARS=$JARS:../lib/dom4j-1.6.1.jar 
JARS=$JARS:../lib/i18nlog-1.0.10.jar
JARS=$JARS:../lib/jersey-core-1.6.jar 
JARS=$JARS:../lib/jersey-server-1.6.jar 
JARS=$JARS:../lib/hibernate-jpa-2.0-api-1.0.0.Final.jar 
JARS=$JARS:../lib/hibernate3.jar 
JARS=$JARS:../lib/slf4j-api-1.6.1.jar 
JARS=$JARS:../lib/slf4j-log4j12-1.6.1.jar 
JARS=$JARS:../lib/log4j-1.2.16.jar 
JARS=$JARS:../lib/mysql-connector-java-5.1.13-bin.jar 
JARS=$JARS:../lib/commons-collections-3.2.1.jar 
JARS=$JARS:../lib/javassist-3.12.0.GA.jar 
JARS=$JARS:../lib/jta-1.1.jar 

# /usr/bin/java -classpath $JARS:. com.griddatabase.ascpc.LoadFile ascpc "/Users/steve/Desktop/Grid Database/ASCPC Data/ASCPC2004-2010.xlsx"

/usr/bin/java -classpath $JARS:. com.griddatabase.ascpc.LoadFile ascpc2011 "/Users/steve/Desktop/Grid Database/ASCPC Data/ASCPC2012.xlsx"

