#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

for (sort glob "* .*"){
    printf "%s -> %s\n", readlink $_, $_ if -l $_;
}
