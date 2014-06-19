#!/usr/bin/perl

use strict;
use warnings;

use Data::Dumper;

my @array = (
    1,
    2,
    3,
    2,
);
print "Array\n";
print Dumper(@array);
print "\n";

my %hash = (
    "normal", 1,
    "hash", 2,
);
print "Hash\n";
print Dumper(%hash);
print "\n";

my $array_ref = [
    2,
    3,
    4,
    1,
];
print "Array ref\n";
print Dumper($array_ref);
print "\n";

my $hash_ref = {
    "bar" => 0,
    "foo" => 3,
};
print "Hash ref\n";
print Dumper($hash_ref);
print "\n";

