#!/usr/bin/env perl

use strict;
use warnings;

use File::Spec;

# Simple utility script for managing exercise problems on "Learning Perl" and
# "Beyond the basics of Learning Perl" from O'Reilly.
#
# Given a directory name, this script looks for .pl files in that directory
# and moves them into directories named like "chapterXX" by looking at the
# file names. For example, "chapter2-4.pl" (practice problem 4 in chapter 2)
# will be moved to "chapter02". Directories are created if they don't exist.
#
# Nested directories are NOT supported. The script only looks at the given
# directory and no further (deeper).
#
# Author: Naoki Mizuno (nigorojr@gmail.com)
# Dec. 10, 2012 (Updated Aug 4, 2014)

our $base_dir;
if (@ARGV) {
    $base_dir = shift;
}
else {
    print "base_dir to process?\n";
    chomp($base_dir = <STDIN>);
}

# Only want file names && too lazy to use File::Find
opendir my $dir_handle, File::Spec->rel2abs($base_dir) or die $!;
for my $file_name (sort readdir $dir_handle) {
    next if $file_name eq "." or $file_name eq "..";
    move_to_dir($file_name);
}

sub move_to_dir {
    my $file_name = shift;

    # Check if that file really exists
    my $src_file_path = File::Spec->catfile($base_dir, $file_name);
    if (! -f $src_file_path) {
        warn "Couldn't find $src_file_path";
        return;
    }

    # Get chapter number from file name
    $file_name =~ m{
        ^
        (ex|chap) .*?

        (?<chap_num>
            \d +
        )
    }ixms;

    # Don't do anything if the filename is invalid
    if (! defined $+{"chap_num"}) {
        warn "Couldn't find chapter name from filename $file_name";
        return;
    }

    # Construct directory name that the file is moved to
    my $chapter_dir_name = sprintf "chapter%02d", $+{"chap_num"};

    # Create directory if it doesn't exist
    my $dest_dir = File::Spec->catdir($base_dir, $chapter_dir_name);
    if (! -d $dest_dir) {
        mkdir $dest_dir or die "Failed to create directory: $!";
    }

    # Construct paths of source and destination directories
    my $src_path = File::Spec->catfile($base_dir, $file_name);
    my $dest_path = File::Spec->catfile($dest_dir, $file_name);

    # Move file
    if (rename $src_path, $dest_path) {
        print "Moved $file_name to '$chapter_dir_name'\n"
    }
    # Failed to move
    else {
        warn "Could not move $file_name: $!" ;
    }
}
