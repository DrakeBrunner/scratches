#!/bin/sh

echo "Cron job running at" `date`

TYPEBEST_DIR=~/src/java/typebest
SOURCE=`find $TYPEBEST_DIR -name 'MainWindow.java'`
DERBY_DIR=~/lib/derby

if [ ! -d $TYPEBEST_DIR ]; then
    git clone https://github.com/NigoroJr/typebest $TYPEBEST_DIR
fi

cd $TYPEBEST_DIR
git pull origin master

if [ ! -d $TYPEBEST_DIR/bin ]; then
    mkdir $TYPEBEST_DIR/bin
fi

cd $TYPEBEST_DIR/src
javac -cp $TYPEBEST_DIR/src:$DERBY_DIR -d $TYPEBEST_DIR/bin $SOURCE

cd $TYPEBEST_DIR/bin
# Copy derby libraries to bin
cp $DERBY_DIR/derby*.jar .
CLASS_DIR=`find . -name 'MainWindow.class' | xargs dirname`
mkdir $TYPEBEST_DIR/bin/TypeBest
jar cvfe TypeBest/TypeBest.jar MainWindow $CLASS_DIR/*.class derby*.jar

cp -r $TYPEBEST_DIR/dic $TYPEBEST_DIR/bin/TypeBest
cp $TYPEBEST_DIR/LICENSE $TYPEBEST_DIR/bin/TypeBest
cp $TYPEBEST_DIR/README.md $TYPEBEST_DIR/bin/TypeBest

# Create a tarball
tar czvf TypeBest.tar.gz TypeBest

mv TypeBest.tar.gz /home/ftp/naoki/typebest/

# Remove bin directory
rm -r $TYPEBEST_DIR/bin
