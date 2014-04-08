#!/usr/bin/perl

use strict;
use warnings;

#This program displays IP address in binary
print "Input IP address: ";
chomp(my $dec_ip = <STDIN>);

my @array = split /\.+/, $dec_ip;

foreach my $splited (@array){
	$packed = pack("I", $splited);
	$bin = unpack("B8", $packed);
	print "$bin ";
}
print "\n";
