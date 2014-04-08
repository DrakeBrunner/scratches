#!/usr/bin/perl

use strict;
use warnings;

my @members = qw(1 2 4 5 6);
my %case;
foreach my $member (@members) {
    $case{$member} = ($member % 2 == 0) ? 1 : 0;
}

print scalar grep $case{$_} == 1, keys %case;
