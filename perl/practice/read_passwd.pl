#!/usr/bin/perl

use strict;
use warnings;

open PASSWD, "/etc/passwd" or die "$!";

my @usernames;
my @fullnames;

while (<PASSWD>) {
    chomp;
    # First entry is username, 5th entry is full name if exists.
    push @usernames, sprintf "\L%s", (split ":", $_)[0];
    my $fullname = sprintf "\L%s", (split ":", $_)[4];
    push @fullnames, $fullname if $fullname ne "";
}

my %found_usernames;
foreach my $username (@usernames) {
    foreach my $fullname (@fullnames) {
        if ($fullname =~ m/$username/i) {
            # Push to the array if full name was already found before.
            # Otherwise, create an anonymous array
            if (defined $found_usernames{$fullname}) {
                push $found_usernames{$fullname}, $username
            }
            else {
                $found_usernames{$fullname} = [$username];
            }
        }
    }
}

# Print
foreach my $key (keys %found_usernames) {
    print "Users: ", join(",", @{$found_usernames{$key}}), " appear in fullname $key\n";
}
close PASSWD;
