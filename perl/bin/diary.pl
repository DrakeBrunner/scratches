#!/usr/bin/perl

use strict;
use warnings;

# This program is for editing diary txt files
# This will create a file named in the format of
# MM-DD-YY.txt in a certain directory (~/Dropbox/diary by default)
# If the file already exists, it will edit that file
# Also, you don't have to worry about writing your diary after midnight
# since this script is wise enough to know that you wanted to write
# the diary for yesterday (if you run this script at Oct. 15th at 1am,
# you can write the diary of Oct. 14th)
# Let's face it; you can't do much in 4 hours...in the middle of the night!

# Made by Naoki Mizuno
# Version 1.3.2

# Change these variables to where you want to store your txt files
# The default directory to store files: ~/Dropbox/diary
my $diary_directory = "$ENV{HOME}/Dropbox/diary";
#######################################################

my ($month, $date, $year) = (localtime)[4, 3, 5];
# If it's before 4am, then you probably wanted to write
# the diary for the previous date!
$date-- if (localtime)[2] < 4;

my $filename = sprintf("%02d-%02d-%02d.txt", $month + 1, $date, $year % 100);

system "editor $diary_directory/$filename"
