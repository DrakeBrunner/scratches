package NigoroJr::CGI::Receive;

use strict;
use warnings;
use URI::Escape;
use Encode qw(decode encode);
use utf8;

sub get_hash {
    my $received = shift;;

    my %form;
    my ($name, $value);
    my @pairs = split /&/, $received;
    foreach my $pair (@pairs) {
        ($name, $value) = split /=/, $pair;

        # Replace the '+' with ' ' (spaces)
        $name =~ s/\+/ /g;
        $value =~ s/\+/ /g;
        # Decode hex string
        $name = uri_unescape($name);
        $value = uri_unescape($value);
        # Decode to utf-8
        $name = decode('UTF-8', $name);
        $value = decode('UTF-8', $value);

        $form{$name} = $value;
    }

    return \%form;
}

1;
