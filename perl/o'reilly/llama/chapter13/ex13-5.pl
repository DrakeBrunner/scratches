#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my ($from, $to) = @ARGV;
if (-d $to) {
    rename $from, "$to/$from" or die $!;
}
else {
    rename $from, $to or die $!;
}
