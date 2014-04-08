#!/usr/bin/perl

use strict;
use warnings;

chomp(my $input = <STDIN>);

my $status = "Not started";
my $date;

if ($input =~ / on /) {
    $date = $';
    $status = "Submitted";
}

print "Status: $status\n";
if (defined $date) {
    print "Date: $date\n";
}
