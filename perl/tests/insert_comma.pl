#!/usr/bin/perl

use strict;
use warnings;

my $text = "The population of 298444215 is growing";

$text =~ s/(?<=\d)(?=(\d\d\d)+(?!\d))/,/g;
print $text, "\n";

$text = "The population of 298444215 is growing";
$text =~ s/(?<=\d)(?=(\d\d\d)+\b)/,/g;
print $text, "\n";
