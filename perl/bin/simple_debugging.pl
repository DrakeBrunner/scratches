#!/usr/bin/perl

use strict;
use warnings;

# This script will find whether the specified file,
# which should be a source code, has semicolon errors.
# I made this because I once wasted half an hour
# just to find out there was no semicolon at the end of a line.
# 
# Made by Naoki Mizuno (nm2372@gmail.com)

# Check if the files specified really exist
my @files_to_check = @ARGV;
my @files_to_check_that_exist;
for (@files_to_check) {
    -f $_ ? push @files_to_check_that_exist, $_ : print "Ignoring $_ because it couldn't be found!\n";
}
print join(" & ", @files_to_check_that_exist), "\n";

# Add files to process if there were no arguments
unless (@files_to_check) {
    print "What's the file you want to process?\n";
    print "Empty line will start the checking.\n";
    while (<STDIN>) {
        chomp;
        last if /^\s*$/;

        unless (-f $_) {
            print "That file doesn't exist!\n";
            next;
        }
        push @files_to_check_that_exist, $_;
    }
}

# Don't do anything if there was nothing in @files_to_check_that_exist
unless (@files_to_check_that_exist) {
    print "No files in queue!\n";
    exit;
}


for my $processing_file (@files_to_check_that_exist) {
    print "Currently processing $processing_file\n\n";

    # The subroutine will return the number of errors found
    my $error_count = &check_semi_collon_for($processing_file);

    print "Finished checking for $processing_file!\n";
    print "Total of $error_count missing semicolon(s) found!\n\n";
}

sub check_semi_collon_for {
    my $processing_file = shift;
    my $line_count = 0;
    my $error_count = 0;

    open FH, $processing_file or die "Could not open $processing_file: $!";
    while (<FH>) {
        $line_count++;

        next if /^\s*\#|^\s*$|\{\s*$|\}\s*$|\;\s*$/;
        
        print "Seems to be a missing semicolon on line $line_count!\n";
        print;
        $error_count++;

        # Add automatic repairing function?
    }
    close FH;

    return $error_count;
}
