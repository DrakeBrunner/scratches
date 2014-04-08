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
open UNSCRANBLE, "to_unscranble.txt";
# Import from "wordlist" file
open WORDLIST, "wordlist.txt";
# Open file to output
open OUTPUT, ">output.txt";

# Sort words that have to be unscranbled
my $i = 0;
my @sorted_words_to_unscranble;
while (<UNSCRANBLE>) {
    chomp;
    $sorted_words_to_unscranble[$i] = &rearrange_to_alphabetical($_);

    $i++;
}

# Sort words in wordlist.txt but still make a copy of the original
my $lines_from_top = 0;
my @words_original;
my @words_alphabetical;
while (<WORDLIST>) {
    chomp;
    # For the original words,
    $words_original[$lines_from_top] = $_;

    # For words rearraged
    $words_alphabetical[$lines_from_top] = &rearrange_to_alphabetical($_);

    $lines_from_top++;
}


# Compare words to unscranble with the wordlist
my $matched_elements = 0;
my @matched;
foreach (@sorted_words_to_unscranble) {
    for (my $n = 0; $n < @words_alphabetical; $n++) {
        if ($_ eq $words_alphabetical[$n]) {
            $matched[$matched_elements] = $words_original[$n];
            $matched_elements++;
        }
    }
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
