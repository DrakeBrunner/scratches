#!/usr/bin/perl

use strict;
use warnings;

my %last_name = qw(
    fred flintstone Wilma Flintstone Barney Rubble
    betty rubble Bamm-Bamm Rubble PEBBLES FLINTSTONE
);

my @sorted = sort {
    "\u\L%last_name{$a}" cmp "\u\L%last_name{$b}"
        or
    "\u\L$a" cmp "\u\L$b"
    } keys %last_name;

for (@sorted) {
    print "\u\L$last_name{$_}, \E\u\L$_\n";
}
