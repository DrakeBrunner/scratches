#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

for (@ARGV){
    unlink $_ or die "Could not unlink $_: $!";
}
