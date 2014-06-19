#!/usr/bin/perl

use strict;
use warnings;

# This is a method-counting script for Java
# Currently only works for Java only
# The script takes one command-line parameter

my $file;
# From command line option
if (@ARGV) {
    $file = $ARGV[0];
}
else {
    chomp($file = <STDIN>);
}

open FILE, $file or die "Could not open $file: $!";
my $count = 0;
my %methods;
my @constructors;
my $class = "";

while (<FILE>) {
    chomp;
    my $line = $_;

    # class name
    if ($class eq "" and $line =~ /class\s+(\w+)/) {
        $class = $1;
    }

    # get constructors
    if ($line =~ /$class\s*\((.*)\)/) {
        next if $line =~ /\;/;
        push @constructors, $1;
        next;
    }

    next if /\;/;

    # get methods
    if ($line =~ /(\w+)\s+(?:(\w+)\s+)?(\w+)\s+(\S+)\s*\((.*)\)/) {
        # In case of use for C/C++ codes
        next if ($1 eq "else");

        $count++;
        my $name = $4;
        my $param = $5;
        $methods{$name}{access} = $1 if defined $1;     # private, public
        $methods{$name}{static} = $2 if defined $2;     # static? non-static?
        $methods{$name}{return_type} = $3 if defined $3;

        if ($param eq "") {
            $methods{$name}{parameter} = "void";
        }
        else {
            $methods{$name}{parameter} = $param;
        }
    }
}

print "Methods:\n";
for my $key (keys %methods) {
    print "$key\n";
    for my $method (keys $methods{$key}) {
        print "  $method => $methods{$key}{$method}\n";
    }
}

print "\nConstructors:\n", join("\n", @constructors), "\n";

print "\nTotal count for class $class is: $count\n";
