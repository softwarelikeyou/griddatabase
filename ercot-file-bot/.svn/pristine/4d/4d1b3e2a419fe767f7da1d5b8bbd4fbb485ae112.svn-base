#!/bin/bash

DATE=`date +%Y%m%d`

SOURCE=/home/ercot
TARGET=/media/usb

if [ ! -d "$TARGET/rtlmp" ]; then
   mkdir $TARGET/rtlmp
fi
cp -R $SOURCE/rtlmp/$DATE $TARGET/rtlmp 

if [ ! -d "$TARGET/rtspp" ]; then
   mkdir $TARGET/rtspp
fi
cp -R $SOURCE/rtspp/$DATE $TARGET/rtspp

if [ ! -d "$TARGET/ascpc" ]; then
   mkdir $TARGET/ascpc
fi
cp -R $SOURCE/ascpc/$DATE $TARGET/ascpc

if [ ! -d "$TARGET/h48damas" ]; then
   mkdir $TARGET/h48damas
fi
cp -R $SOURCE/h48damas/$DATE $TARGET/h48damas

if [ ! -d "$TARGET/h48damhp" ]; then
   mkdir $TARGET/h48damhp
fi
cp -R $SOURCE/h48damhp/$DATE $TARGET/h48damhp

if [ ! -d "$TARGET/rtdam" ]; then
   mkdir $TARGET/rtdam
fi
cp -R $SOURCE/rtdam/$DATE $TARGET/rtdam
