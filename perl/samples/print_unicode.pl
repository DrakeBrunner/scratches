#!/usr/bin/perl

use strict;
use warnings;

use utf8;
use Encode;

my $s = "Hello, 世界";

for my $l (split "", $s) {
    # To prevent 'wide character in print...'
    #print encode('utf8', $l), "\n";

    # OR
    binmode STDOUT, ":utf8";
    print "$l\n";
}
