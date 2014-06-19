#!/usr/bin/perl

use strict;
use warnings;

# This is a method-counting script.
# Currently only works for C, C++, and Java
# The script takes one command-line parameter (but since it's
# so easy to allow more than one parameter, it will soon be modified).
#
# Made by Naoki Mizuno

my @files;
my $file;
# From command line option
@ARGV ? @files = @ARGV : push @files, chomp($file = <STDIN>);

for $file (@files) {
    # Skip directories
    next if -d $file;

    print "$file\n";
    my $list;
    if ($file =~ /java$/) {
        $list = &get($file, "java");
    }
    elsif ($file =~ /c(?:..)?$/) {
        $list = &get($file, "c");
    }

    # Print the result
    foreach my $func (@$list) {
        print "  $func->{return} $func->{name}($func->{param})\n";
    }
    print "\n";
}

sub get {
    chomp(my $file = shift);
    chomp(my $language = shift);
    # If no language was specified, try to determine from the extension
    unless (defined $language) {
        if ($file =~ /java/) {
            $language = "java";
        }
        elsif ($file =~ /c(..)?/) {
            $language = $&;
        }
    }
    my @valid_functions;
    my $block_comment = "false";

    open FILE, $file or die "Could not open $file: $!";

    while (<FILE>) {
        chomp(my $line = $_);
        my $result_of_check;

        # Check double-slash comment
        if ($line =~ /\/\//) {
            $result_of_check = &check_func($`, $language);
            push @valid_functions, $result_of_check if $result_of_check != -1;
            next;
        }

        # Check starting of the comment block
        if ($line =~ /\/\*/) {
            $block_comment = "true";
            $result_of_check = &check_func($`, $language);
            push @valid_functions, $result_of_check if $result_of_check != -1;
        }

        # Check ending of the comment block
        if ($line =~ /\*\//) {
            $block_comment = "false";
            $result_of_check = &check_func($', $language);
            push @valid_functions, $result_of_check if $result_of_check != -1;
            # Doesn't have to continue further on because it already checked
            next;
        }

        # Skip if if it's inside the comment block
        next if $block_comment eq "true";

        # Finally, check for that line
        $result_of_check = &check_func($line, $language);
        push @valid_functions, $result_of_check if $result_of_check != -1;
    }

    return \@valid_functions;
}

sub check_func {
    my $what = shift;
    my $language = shift;
    my %methods;

    # For C, C++
    if ($language =~ /c/i and $what =~ /(\w+)\s+(\S+)\s*\((.*)\)(.*;)?/) {
        my $ret = $1;
        my $name = $2;
        my $param = $3;
        # Ignore it if it's a prototype or some statement
        return -1 if defined $4;
        # There should not be more than 1 pair of parentheses in a proper function
        return -1 if $what =~ /\(.*\(/;
        # else if (cond) is confusing
        return -1 if $what =~ /else\s+if/;
        return {
            "return"    => $ret,
            "name"      => $name,
            "param"     => $param,
        };
    }
    # For Java
    elsif ($language eq "java" and $what =~
        /(\w+)\s+(?:(\w+)\s+)?(\w+)\s+(\S+)\s*\((.*)\)(.*;)?/) {
        $methods{name} = $4;
        $methods{access} = $1 if defined $1;     # private, public
        $methods{static} = $2 if defined $2;     # static? non-static?
        $methods{return} = $3 if defined $3;
        my $param = $5;
        # Ignore it if it's a prototype or some statement
        return -1 if defined $6;

        # Could be void
        $param eq "" ? $methods{param} = "void" :
            $methods{param} = $param;

        # else if (cond) is confusing (but have to be done after storing
        # because it changes the matched variables
        return -1 if $what =~ /else\s+if/;
        # There should not be more than 1 pair of parentheses in a proper function
        return -1 if $what =~ /\(.*\(/;

        return \%methods;
    }
    else {
        return -1;
    }
}
