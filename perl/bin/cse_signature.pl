#!/usr/bin/perl

use strict;
use warnings;

# Perl script for inserting signature in source codes
# Java, C, C++ (languages that use /* --- */ comment style

# To-do
# skip process when signature is already there

open DAT, "$ENV{HOME}/.myconfig/cse_signature.dat"
    or die "Could not open dat file: $!";
print "File to process: ";
chomp(my $file = <STDIN>);
open WRITE, "$file" or die "Could not open file to process: $!";

my @signature;
my $i = 0;
while (<DAT>) {
    chomp;
    my $line = $_;
    $signature[$i] = $line;
    $i++;
}

print "Description of the file's contents:\n";
my $description = <STDIN>;
print "Identify code segments that were developed by another person if any:\n";
my $another_person = <STDIN>;

my $to_print;
while (1) {
    $to_print .= "/*\n";
    $to_print .= "    $signature[0]\n";
    $to_print .= "    $signature[1]\n";
    $to_print .= "    $signature[2]\n";
    $to_print .= "    Description:\n";
    $to_print .= "    $description\n";
    $to_print .= "    Code segment(s) done by another person:\n$another_person\n"
        unless $another_person =~ /\s*/;
    $to_print .= "*/\n";

    print "$to_print\n";

    print "\nIs this correct? (Y/n)\n";
    if (<STDIN> =~ /n/i) {
        print "What do you want to change?\n";
        chomp(my $choice = <STDIN>);

        if ($choice =~ /(my|your)\s+name/i) {
            print "=> ";
            chomp($signature[0] = <STDIN>);
        }
        elsif ($choice =~ /instruct.*\s+name/i) {
            print "=> ";
            chomp($signature[1] = <STDIN>);
        }
        elsif ($choice =~ /section.*/i) {
            print "=> ";
            chomp($signature[2] = <STDIN>);
        }
        elsif ($choice =~ /descri.*/i) {
            print "=> ";
            chomp($description = <STDIN>);
        }
    }
    else {
        my $content;
        $content .= $_ while (<WRITE>);
        close WRITE;
        # Reopen
        open WRITE, ">$file" or die "Could not open file to process: $!";
        print WRITE "$to_print\n$content";
        # Exit loop
        last;
    }
}
