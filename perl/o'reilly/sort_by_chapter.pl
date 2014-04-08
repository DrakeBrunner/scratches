#!/usr/bin/perl

use strict;
use warnings;


# This program will sort files that are named after chapter numbers for example,
# ex2-1.pl
# Chapter 2_1.pl
#
# It can be recognized if it has "chap" or "ex" in the beginning of a file
# with the number of the chapter followed.
#
# The script will ask for the directory to process
# and it will make directories named, for example, "chapter02"
# For now, plese edit the command by yourself
# if you want to have different directory name.
#
# Made by Naoki Mizuno (nm2372@gmail.com)
# Dec. 10, 2012


print "dir to process?\n";
chomp(my $dir = <STDIN>);
$dir = "." if $dir =~ /\s*/;

opendir DH, $dir or die "Could not open directory $dir: $!";

for my $things_inside (sort readdir DH) {
    next if $things_inside eq "." or $things_inside eq "..";
    &move_to_dir($things_inside);
}

sub move_to_dir {
    my $processing_file = shift;

    # Recognize chapter number and appropriate file to process
    $processing_file =~ m/^(?:ex.*?|chap.*?)(?:\s*)(\d+)/i;

    # Don't do anything if the filename is invalid
    return unless defined $1;
    my $chapter_number = $1;

    # Format dir name to be something like chapter02
    $chapter_number = sprintf "%02d", $chapter_number;

    # Create directory if it doesn't exist
    mkdir "$dir/chapter$chapter_number"
        or die "Failed to create directory: $!" unless -d "$dir/chapter$chapter_number";

    # Move the file to the appropriate directory
    if (-f "$dir/$processing_file") {
        rename "$dir/$processing_file", "$dir/chapter$chapter_number/$processing_file"
            or die "Could not move $processing_file: $!" ;
        print "Moved $processing_file to \"chapter$chapter_number\"\n"
    }
}
