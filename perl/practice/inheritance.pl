#!/usr/bin/perl

use strict;
use warnings;

{
    package Animal;

    sub speak {
        my $class = shift;
        print "a $class goes @{[ $class->sound() ]}!\n";
    }

    sub DESTROY {
        my $self = shift;
        print "[ $$self has died in SUPER ]\n";
    }

    1;
}

{
    package Cow;

    @Cow::ISA = qw( Animal );

    sub new {
        my $self = shift;
        my $name = shift;

        bless \$name, $self;
    }

    sub name {
        my $self = shift;

        if (ref $self) {    # Is an instance
            return $$self;
        }
        else {              # Is a class
            return "unnamed $self";
        }
    }

    sub DESTROY {
        my $self = shift;
        print "[ $$self has died ]\n";

        # Call destructor in super class
        $self->SUPER::DESTROY;
    }

    sub sound {
        return "moooo";
    }

    1;
}

print Cow->name, "\n";
my $foo = Cow->new("foo");
print $foo->name(), "\n";
