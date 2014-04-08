#!/usr/bin/perl

use strict;
use warnings;

use URI::Escape qw(uri_escape);
use Digest::MD5 qw(md5_hex);

my $email = 'nm2372@gmail.com';
my $size = 40;

my $grav_url = "http://www.gravatar.com/avatar/" . md5_hex(lc $email) . "&s=" . $size;

print "$grav_url\n";
