#!/usr/bin/perl

use strict;
use warnings;

# Script for counting lines in a source code.
# Takes command line parameters
# Ignores empty lines and lines that only has a "}"
# Also ignores comment lines (lines starting with #, ", //, and /* --- */)
# The /* --- */ can be among multiple lines

# Change the following to false (or something other than "true")
# if you don't want to ignore comments
my $ignore_comments = "true";

# Author: Naoki Mizuno
# Version 1.0
# February 26th, 2013

my @files_to_process;
if (@ARGV) {
    @files_to_process = map {$_} @ARGV;
}
else {
    print "File(s) to check: ";
    chomp(my $stdin = <STDIN>);
    @files_to_process = map {$_} split / |,|:/, $stdin;
}

# Hash to store results
my %results;
my $comment_flag = 0;
foreach my $file (@files_to_process) {
    open FILE, $file or die "Could not open $file: $!";

    $results{$file} = 0;

    # Read from file
    while (<FILE>) {
        # Skip line if it's empty or has only "}"
        next if /^\s*\}?\s*$/;

        # Ignore comments
        if ($ignore_comments eq "true") {
            # Turn flag on when detecting /*
            $comment_flag = 1 if /\/\*/;
            # Turn flag off when the /* --- */ style comment ends
            if (/\*\//) {
                $comment_flag = 0;
                next;
            }
            next if $comment_flag == 1;

            # Ignore #, ", //
            next if /^\s*[#|"|\/\/|]/;
        }

        $results{$file}++;
    }
}

# Show results
foreach my $file (sort keys %results) {
    printf "%-20s %d lines\n", $file, $results{$file};
}
