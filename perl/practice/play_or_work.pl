#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

# This script will kindly tell you to go to work or play

if (`date` =~ m/^S/) {
    say "Go play";
}
else {
    say "Go to work";
}
