#!/bin/sh
cd /usr/local/src/vim
hg pull
hg update
#if [ ! `hg update | grep "no changes found"` = "" ]; then
./configure \
--with-features=huge \
--enable-multibyte \
--enable-rubyinterp \
--enable-pythoninterp \
--enable-python3interp \
--enable-luainterp \
--enable-perlinterp \
--enable-fail-if-missing
make
make install
#fi
