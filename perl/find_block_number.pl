#!/usr/bin/perl

use strict;
use warnings;

# The comments assume that the bad sector is in /dev/sda3

my $byte_size = 512;
# The number you get from `tune2fs -l /dev/sda3`
my $block_size = 4096;
# The LBA_of_first_error
my $lba_bad_sector;
if (@ARGV) {
    $lba_bad_sector = shift @ARGV if @ARGV;
}
else {
    print "Enter bad sector: ";
    chomp($lba_bad_sector = <STDIN>);
}
# From `fdisk /dev/sda3`
my $starting_sector_of_partition = 1116160;

my $block_number = ($lba_bad_sector - $starting_sector_of_partition) * $byte_size / $block_size;

print "Block number: $block_number\n";
print "After rounded: ", int $block_number, "\n";
