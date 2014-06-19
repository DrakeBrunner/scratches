#!/usr/bin/perl

use strict;
use warnings;

# Script that gets rid of white spaces that appears in empty lines
# when using Eclipse (and maybe other IDEs)
# Also gets rid of the newline character in DOS formats
# Converts TAB to 4 spaces (turn off by changing the following
# variable to something other than "true")

my $expandtab = "true";

my @file_to_process;
if (@ARGV) {
    @file_to_process = map {$_} @ARGV;
}
else {
    print "File name: ";
    chomp(my $filename = <STDIN>);
    @file_to_process = map {$_} split / |,|:/, $filename;
}

foreach my $filename (@file_to_process) {
    # Don't read it if it's a directory
    next if -d $filename;

    my @content;
    open FILE, $filename or die "Could not open $filename: $!";
    $^I = ".bak";

    # Read file content to array
    while (<FILE>) {
        chomp;
        my $line = $_;
        $line =~ s/^\s*$//;
        # Get rid of the darn new line character in DOS format
        $line =~ s/\r//;
        $line =~ s/\t/    /g if $expandtab eq "true";
        push @content, $line;
    }
    close FILE;

    # Write that to the file
    open FILE, ">", $filename or die "Could not write to $filename: $!";
    print FILE join("\n", @content);

    print STDOUT "$filename: Process Complete!\n";
}
