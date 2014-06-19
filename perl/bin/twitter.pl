#!/usr/bin/perl

# A script that send tweets to Twitter
# You need to have Net::Twitter installed.
# If you have it installed but can't get it to work,
# try installing Mozilla::CA also.
# Author: Naoki Mizuno

use Net::Twitter;
use Encode qw(decode encode);
use utf8;   # Just in case

use strict;
use warnings;

# Set the file in which the access token and
# access token secret is stored or will be stored.
my $token_file = "$ENV{HOME}/.myconfig/tokens.txt";

my $nt = Net::Twitter->new(
    traits          => ['API::RESTv1_1', 'OAuth'],
    consumer_key    => "Pba1DKJ9rKQRb3wYhh0dw",
    consumer_secret  => "xFfJ3sLe21Ebtr9pCqRnFKxWlBzvh4Rew2UwBzv2gsA",
);

# Prepare for a Tweet
&prepare();

my $status = "";
if (@ARGV) {
    $status = decode('UTF-8', join(" ", @ARGV));
}
else {
    while (<STDIN>) {
        $status .= decode('UTF-8', $_);
    }
}

unless ($status eq "") {
    # Trim status if the length is over 140 letters
    $status = substr $status, 0, 140 if length $status > 140;
    $status = "Commit message: \"$status\"";
    $nt->update($status);
    print "Successfully tweeted\n$status\n";
}
else {
    print "Not sending tweet because the content is empty!";
}

sub prepare {
    my ($access_token, $access_token_secret) = &restore_tokens();
    if ($access_token and $access_token_secret) {
        $nt->access_token($access_token);
        $nt->access_token_secret($access_token_secret);
    }

    unless ($nt->authorized) {
        print "Authorize app at\n", $nt->get_authorization_url, "\nand enter PIN number!\n";
        chomp(my $pin = <STDIN>);
        my ($access_token, $access_token_secret, $user_id, $screen_name) =
            $nt->request_access_token(verifier => $pin);
        &store_tokens($access_token, $access_token_secret);
    }
}

sub restore_tokens {
    return unless -f $token_file;
    open FILE, $token_file or die "Couldn't open token file! $!";
    my ($access_token, $access_token_secret);
    while (<FILE>) {
        chomp;
        if (/access_token:/) {
            $access_token = $';
        }
        elsif (/access_token_secret:/) {
            $access_token_secret = $';
        }
    }
    close FILE;

    return unless $access_token and $access_token_secret;
    return ($access_token, $access_token_secret);
}

sub store_tokens {
    my $access_token = shift;
    my $access_token_secret = shift;

    return unless $access_token and $access_token_secret;

    # Using file handle reference
    open my $file_handle, ">", $token_file or die "Couldn't open token file for writing! $!";
    print {$file_handle} "access_token:$access_token\n";
    print {$file_handle} "access_token_secret:$access_token_secret\n";

    return;
}
