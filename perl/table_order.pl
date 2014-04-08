#!/usr/bin/perl

use strict;
use warnings;

# This script displays the product table
# with the number showing it's largeness

print "Max: ";
chomp(my $max = <STDIN>);

my %numbers;
my ($i, $j);

for ($i = 1; $i <= $max; $i++) {
    for ($j = $i; $j <= $max; $j++) {
        $numbers{$i * $j} = [$i];
    }
}

my $count = 1;
for my $product (sort {$b <=> $a} keys %numbers) {
    push @{$numbers{$product}}, $count;
    $count++;
}

# Show $j
printf "%4d ", $_ for (1 .. $max);
print "\n";
printf "-----" for (1 .. $max);
print "\n";

for ($i = 1; $i <= $max; $i++) {
    for ($j = 1; $j < $i; $j++) {
        print "     ";
    }
    for ($j = $i; $j <= $max; $j++) {
        printf "%4d ", $numbers{$i * $j}->[1];
    }
    # Shor $i
    print "| $i\n";
}
print "\n";
