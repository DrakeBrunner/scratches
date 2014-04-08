#!/usr/bin/perl

use strict;
use warnings;

# This script unscranbles the words from a file
# and outputs them in the format separated with commas
# Example: word1,word2,word3,...word10
#
# Script was originally made for the mission at
# http://www.hackthissite.org/missions/prog/1/
#
# Made by Naoki Mizuno


# Import from "unscranble" file
open UNSCRANBLE, "to_unscranble.txt" or die "Could not open file: $!";
# Import from "wordlist" file
open WORDLIST, "wordlist.txt" or die "Could not open file: $!";
# Open file to output
open OUTPUT, ">output.txt" or die "Could not open file: $!";

# Sort words that have to be unscranbled
my %words;
my $i;
while (<UNSCRANBLE>) {
    chomp;
    # hash consists of:       alphabetical => order
    $words{&rearrange_to_alphabetical($_)} = $i++;
}

# Sort words in wordlist.txt but still make a copy of the original
my %original;
while (<WORDLIST>) {
    chomp;
    # hash consists of:          alphabetical => original
    $original{&rearrange_to_alphabetical($_)} = $_;
}

# Compare words to unscranble with the wordlist
my @matched;
foreach my $key (sort {$words{$a} <=> $words{$b}} keys %words) {
    push @matched, $original{$key} if (exists $original{$key});
}
# Finally, join the array with commas and output to a file
print OUTPUT (join ",", @matched);



# Subroutines
sub rearrange_to_alphabetical {
    my $target = shift;

    # Join returned list and return it to main
    my $string = join ( "", &array_ize($target) );
    chomp $string;
    return $string;

}

# Return *list* of chars rearranged in alphabetical orders
sub array_ize {
    my $target = shift;
    return (sort (split //, $target) );
}
