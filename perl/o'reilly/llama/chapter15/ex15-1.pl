#!/usr/bin/perl

use 5.010;
use strict;
use warnings;

my $secret_number = int(rand(100) + 1);

while (chomp(my $users_guess = <STDIN>) != 0) {
    given ($users_guess) {
        chomp;
        when (/^\s*$|quit|exit/)    { say "Sorry, you gave up!"; exit }
        when (/\D/)                 { say "Really?" }
        when ($_ == $secret_number) { say "That's it!"; exit }
        when ($_ > $secret_number)  { say "Too high!" }
        when ($_ < $secret_number)  { say "Too low!" }
    }
}
