#!/usr/bin/perl

use strict;
use warnings;

use 5.018;

print "Choose either FOO or BAR: " unless @ARGV;
my $label = shift // <>;

FOO:
for my $foo (0..2) {
    print "FOO $foo\n";

    BAR:
    for my $bar (0..2) {
        print "BAR $bar\n";

        print "JUMPING TO $label\n";
        next $label;
    }
}
