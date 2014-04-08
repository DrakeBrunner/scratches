#!/usr/bin/perl

use strict;
use warnings;

use DateTime;
use DateTime::TimeZone;

# my $tz = DateTime::TimeZone->new(name => "Japan");
my $tz = DateTime::TimeZone->new(name => "US/Eastern");
my $local = DateTime::TimeZone->new(name => "local");
my $gmt = DateTime::TimeZone->new(name => "Universal");
my $current = DateTime->now();

my $offset = $tz->offset_for_datetime($current);
my $local_offset = $local->offset_for_datetime($current);
my $gmt_offset = $gmt->offset_for_datetime($current);

print $offset / 3600, "\n";
print $local_offset / 3600, "\n";
print $gmt_offset / 3600, "\n";
