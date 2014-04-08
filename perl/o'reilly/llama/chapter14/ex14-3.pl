#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

say "String?";
chomp(my $input = <STDIN>);
say "Search for?";
chomp(my $search = <STDIN>);

print index($input, $search), "\n";
