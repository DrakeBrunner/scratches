#!/usr/bin/perl
# This script will delete all files that has _test.pl
# in its filename inside specified directory.

use 5.010;
use strict;
use warnings;

say "Dir to process?";
chomp(my $dir_to_process = <STDIN>);
for my $file (glob "$dir_to_process/*_test* $dir_to_process/.*_test*") {
    next if $0 =~ /$file/;
    unlink $file if -f $file or die "Failed to remove $file: $!";
    print "$file deleted!\n";
}
