#!/usr/bin/perl

use strict;
use warnings;

# Setting this to a different value will change
# the separating character when printing an array
$" = " space ";

my @array;
my $i;

my $n = 0;
while (1) {
    chomp(my $input = <STDIN>);

    last if $input eq "quit";

    $array[$n] = $input;

    $n++;
}
print "Joining with ','\n";
my $out = join ", ", @array;
print "$out\n";

print "Just using print\n";
print "@array\n";


$" = " ";
print "Changed separater to space\n\n";

my @other = qw(10 11 12 13 14);
print "Other was:\n@other\n";
@other = @array;
print "Other became:\n@other\n";

if (defined $array[0] and defined $array[1]) {
    print "Swapping\n";
    ($array[0], $array[1]) = ($array[1], $array[0]);
    print "array: @array\n";
    print "other: @other\n";
}
