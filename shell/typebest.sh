#!/bin/sh

echo "Cron job running at" `date`

TYPEBEST_DIR=~/src/java/typebest
SOURCE=`find $TYPEBEST_DIR -name 'MainWindow.java'`
TYPEBEST_LIB=~/lib/typebest
DEST_DIR=/home/ftp/typebest/

# Functions
compile() {
    cd $TYPEBEST_DIR/src
    if [ ! -d $TYPEBEST_DIR/bin ];then
        mkdir $TYPEBEST_DIR/bin
    fi
    javac -cp $TYPEBEST_DIR/src:$TYPEBEST_LIB -d $TYPEBEST_DIR/bin $SOURCE
}

create_jar() {
    if [ ! -d $TYPEBEST_DIR/bin ]; then
        mkdir $TYPEBEST_DIR/bin
    fi

    cd $TYPEBEST_DIR/bin
    # Copy derby libraries to bin
    cp -r $TYPEBEST_LIB/* .
    CLASS_DIR=`find . -name 'MainWindow.class' | xargs dirname`
    mkdir $TYPEBEST_DIR/bin/TypeBest
    jar cvfm TypeBest/TypeBest.jar META-INF/MANIFEST.MF $CLASS_DIR/*.class derby*.jar org/
}

create_tar() {

    cp -r $TYPEBEST_DIR/dic $TYPEBEST_DIR/bin/TypeBest
    cp $TYPEBEST_DIR/LICENSE $TYPEBEST_DIR/bin/TypeBest
    cp $TYPEBEST_DIR/README.md $TYPEBEST_DIR/bin/TypeBest

    # Create a tarball
    tar czvf TypeBest.tar.gz TypeBest
}

clean() {
    # Remove bin directory
    if [ -d $TYPEBEST_DIR/bin ];then
        rm -r $TYPEBEST_DIR/bin
    fi
}

# end functions

if [ ! -d $TYPEBEST_DIR ]; then
    git clone https://github.com/NigoroJr/typebest $TYPEBEST_DIR
fi

cd $TYPEBEST_DIR


PULL_RESULT=`git pull origin master 2>&1 | grep "Already"`
# Check for update
if [ "$PULL_RESULT" = "Already up-to-date." ]; then
    echo "No changes"
    exit 0
fi

compile

create_jar
create_tar

mv TypeBest.tar.gz $DEST_DIR

clean
