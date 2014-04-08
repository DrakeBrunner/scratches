#!/usr/bin/perl

use strict;
use warnings;

while (chomp(my $input = <STDIN>)) {
    my $vowel     = qr/[aeiou]/;
    my $consonant = qr/[^aeiou]+?/;
    if ($input =~ m/$vowel (?: $consonant $vowel ){2}/x) {
        print "Yes\n"
    }
    else {
        print "No\n";
    }
}
