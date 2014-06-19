#!/usr/bin/perl

use strict;
use warnings;

# This script checks for the declaration of prototype in C++
# Author: Naoki Mizuno
# July 8th, 2013
# Version 2.1
# TODO:
#   Insert #include "filename.h" to the beginning of the file
#   Insert prototypes after public: if there is a class.
#   Split script into smaller subroutines

my $ADD_COMMENT = "True";
my $CREATE_HEADER_FILE = "False";

my @files_to_process;
if (@ARGV) {
    @files_to_process = @ARGV;
}
else {
    print "File to process? ";
    chomp(my $file = <STDIN>);
    push @files_to_process, $file;
}

for my $file (@files_to_process) {
    insert_prototypes($file);
}

sub insert_prototypes {
    my $file = shift;

    my @whole_file;
    open FILE, "$file" or die "Can't open file for reading: $!";
    # Read whole file into an array
    while (<FILE>) {
        push @whole_file, $_;
    }
    close FILE;

    # Array to store all functions
    my @functions;
    # Array to store existing prototypes
    my @prototypes;
    my $line_number = 0;
    my $main_line = 0;
    my $prototype_comment = 0;
    my ($type, $func_name, $arg);

    for (@whole_file) {
        $line_number++;

        my $line = $_;
        # No need for a prototype for main
        if ($line =~ /int\s+main\s*\(/) {
            $main_line = $line_number;
            next;
        }
        elsif ($line =~ m#//\s*Prototype#i) {
            $prototype_comment = $line_number;
            next;
        }

        #Example: int max(int a, int b);
        if (/(\w+)\s+(\w+)\s*\((.*)\)/) {

            # Count the prototypes already written after the comment
            $prototype_comment++ if $prototype_comment != 0
                and $main_line == 0;
            # Or even if there' no comment
            $prototype_comment = $line_number if $prototype_comment == 0
                and $main_line == 0;

            $type = $1;
            $func_name = $2;
            # TODO?: Write just the type without the var name (add subroutine)
            $arg = $3;
            # Had problems with "else if"
            next if $type eq "else" and $func_name eq "if";

            my $match = sprintf "%s %s(%s)", $type, $func_name, $arg;

            # Sort by prototype or function
            # It's a prototype if there's a semicolon in the line
            $line =~ /\;/ ? push @prototypes, $match : push @functions, $match;
        }
    }

    # Insert prototype to file
    my $prototypes_in_one_line = "";
    # Add comment indicating these's are prototypes
    if ($prototype_comment == 0) {
        $prototypes_in_one_line = "\n";
        $prototypes_in_one_line .= @prototypes == 1
            ? "// Prototype\n"
            : "// Prototypes\n" if $ADD_COMMENT eq "True";
    }

    # Put them into one line of string
    for my $function (@functions) {
        # Add only the functions that don't have their prototypes
        next if $function ~~ @prototypes;
        $prototypes_in_one_line .= "$function\;\n";
    }

    # Print what will be added
    printf length $prototypes_in_one_line == 0
        ? "No new prototype was added\n"
        : ("\nAdding the following prototypes to %s:\n%s\n",
            $file, $prototypes_in_one_line);


    # Add prototypes to the array of the file
    my $current_line_number = 0;
    for my $line (@whole_file) {

        next if $current_line_number++ < ($prototype_comment == 0 ?
            $main_line - 2 : $prototype_comment);

        # Add to the end of the line
        $line =~ s/$/$prototypes_in_one_line/;

        last;
    }

    # Print to file
    # Write to a header file if the option says to do so
    $file = generate_header($file) if $CREATE_HEADER_FILE eq "True";
    print "$file\n";
    open FILE, ">$file" or die "Can't open file for writing: $!";

    print FILE @whole_file;

    close FILE;
}

# Returns the name of the header file.
# For example, when a file with the name "hello.cpp" was passed,
# this subroutine returns "hello.h" by changing the extension.
# Returns the original file name when an invalid file is passed (e.g. ".vimrc")
sub generate_header {
    my $file = shift;
    if ($file =~ m/(.*)\..*/) {
        my $base_name = $1;
        return $base_name . ".h";
    }
    return $file;
}
