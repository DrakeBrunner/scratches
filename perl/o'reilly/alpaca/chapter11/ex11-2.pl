#!/usr/bin/perl

use strict;
use warnings;

# This Perl script asks the user for the name of animal(s)
# and have the animal(s) make a sound
# 11-2 includes human

{
    package LivingCreature;
    sub speak {
        my $class = shift;
        if (@_) {
            print "a $class goes '@_'\n";
        }
        else {
            print "a $class goes ", $class->sound, "!\n";
        }
    }
}

{
    package Person;
    @Person::ISA = qw(LivingCreature);
    sub sound {
        "hmmmm";
    }
    sub speak {
        my $class = shift;
        $class->SUPER::speak(@_);
    }
}

{
    package Animal;
    @Animal::ISA = qw(LivingCreature);
    sub sound {
        die "all Animals should define a sound!";
    }
    sub speak {
        my $class = shift;
        die "animals can't talk!" if @_;
        $class->SUPER::speak;
    }
}

{
    package Cow;
    @Cow::ISA = qw(Animal);
    sub sound {
        "moooo";
    }
}

{
    package Horse;
    @Horse::ISA = qw(Animal);
    sub sound {
        "neigh";
    }
}

{
    package Sheep;
    @Sheep::ISA = qw(Animal);
    sub sound {
        "baaaah";
    }
}

{
    package Mouse;
    @Mouse::ISA = qw(Animal);
    sub sound {
        "squeak";
    }
    sub speak {
        my $class = shift;
        $class->SUPER::speak;
        # Animal::speak($class);
        print "[but you can barely hear it!]\n";
    }
}

Person->speak("hello");
Person->speak;
Sheep->speak;
