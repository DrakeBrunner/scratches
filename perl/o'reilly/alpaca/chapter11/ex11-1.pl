#!/usr/bin/perl

use strict;
use warnings;

# This Perl script asks the user for the name of animal(s)
# and have the animal(s) make a sound

{
    package Animal;
    sub speak {
        my $class = shift;
        print "a $class goes ", $class->sound, "!\n";
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

print "Enter animal(s) separated by space: ";
my @backyard = split " ", <STDIN>;
for my $beast (@backyard){
    chomp $beast;
    $beast->speak;
}
