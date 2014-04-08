#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

say "Your file that can be read and written are...";
for  (@ARGV){
    print "$_\n" if -w -r -o $_;
}
