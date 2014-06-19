#!/usr/bin/perl

use strict;
use warnings;

# This is a script that basically does nothing.
# Random words go through the screen, making it look like
# you're doing some "hacking"
# Press Ctrl+c when you want to exit the script

# Turn Autoflush on
$| = 1;

# There are several types of action
# 1. compiling
# 2. Observing network traffic
# 3. Hacking password

my @actions = (\&compiling, \&hack_password, \&network_traffic);

while (1) {
    my $what_to_do = int(rand() * 10) % @actions;
    &{$actions[$what_to_do]}();
}

# THIS SUBROUTINE COULD BE BETTER!!!!
# Mocks compiling
sub compiling {

    # FORCE it to become a C source file!!!
    print "Compiling \"", &random_file(), ".c\"...\n";
    &wait(int(rand() * 3));
    print "Compiling has completed!\n\n";
}

# Show some network traffic (like the command "netstat")
sub network_traffic {
    print "Active Internet connections (w/o servers)\n";
    printf "%-6s %-20s%-5s %20s %s\n",
        "Proto", "Local Address", "Port", "Foreign Address", "State";

    # Yeah, I know some are not protocols in the transport layer
    # But what the heck! It makes it look cooler! :P
    my @proto = qw(tcp udp smtp icmp snmp igmp);
    # From 10 to 50 lines
        for (my $i = 0; $i < 10 + int(rand() * 40); $i++) {
        my $protocol = $proto[int(rand() * 10) % @proto];
        # Make a random local address
        my @tmp;
        for (1..4) {
            push @tmp, int(rand() * 256);
        }
        my $local_address = join ".", @tmp;
        my $port = int(rand() * 2**16);

        # for now, leave the foreign address like this
        my $foreign_address = "edge-star-ecmp-12:https";

        my @states = qw(ESTABLISHED DISCONNECTED LISTENING);
        my $state = $states[int(rand() * 10) % @states];

        printf "%-6s %-20s%-5d %20s %s\n",
            $protocol, $local_address, $port, $foreign_address, $state;

        &wait(int(rand() * 2));
    }
    print "\n";
}

# Mocks Hacking password
sub hack_password {
    print <<'EOF';
                _                        _           _ 
     /\        | |                      | |         | |
    /  \  _   _| |_ ___  _ __ ___   __ _| |_ ___  __| |
   / /\ \| | | | __/ _ \| '_ ` _ \ / _` | __/ _ \/ _` |
  / ____ \ |_| | || (_) | | | | | | (_| | ||  __/ (_| |
 /_/    \_\__,_|\__\___/|_| |_| |_|\__,_|\__\___|\__,_|
                                                       
                                                       
  _____                                    _ 
 |  __ \                                  | |
 | |__) |_ _ ___ _____      _____  _ __ __| |
 |  ___/ _` / __/ __\ \ /\ / / _ \| '__/ _` |
 | |  | (_| \__ \__ \\ V  V / (_) | | | (_| |
 |_|   \__,_|___/___/ \_/\_/ \___/|_|  \__,_|
                                             
                                             
                       _                    
     /\               | |                   
    /  \   _ __   __ _| |_   _ _______ _ __ 
   / /\ \ | '_ \ / _` | | | | |_  / _ \ '__|
  / ____ \| | | | (_| | | |_| |/ /  __/ |   
 /_/    \_\_| |_|\__,_|_|\__, /___\___|_|   
                          __/ |             
                         |___/              
EOF

    my $password = "";
    # Typical password is more than 8 and less than 18 letters
    my $length_of_password = 8 + int(rand() * 10);

    # We could change the subroutine to redo it if it's a directory
    # but there might be a case where there are no files in that directory
    print "Analyzing password for file \"", &random_file(), ".dat\"...\n";
    for (my $i = 0; $i < $length_of_password; $i++) {
        &wait(int(rand() * 4));
        # ord gets the ASCII code for that letter
        # chr gets the letter from char code
        # The following line prints a random letter from a-z and A-Z
        my $letter = &random_letter();
        print $letter;
        $password .= $letter;
    }
    print "\nPassword analysis completed.\nPassword: $password\n\n";
}

# This subroutine is for hack_password subroutine.
# Returns a randomly generated letter/number/sign
sub random_letter {
    my $letter;
    {   # Make a code block so we can redo it when a sign is picked
        # ASCII code for 0 => 38
        #                A => 65
        #                z => 122
        $letter = chr(ord('0') + int(rand() * (ord('z') - ord('0'))));

        # There are signs such as ; : < = betwee code 32 and 122
        # IMHO, it's cooler to have only numbers and letters for a password :P
        # (although I agree it would be more secure to have signs in it)
        redo unless $letter =~ /\d|[a-z]/i;
    }

    return $letter;
}

# Just waits for the specified time.
# Waits for 1 second when no duration is specified
sub wait {
    my $how_long = shift;
    # Set it to 1 when not specified
    $how_long = 1 unless defined $how_long;
    sleep $how_long;
}

# Grabs a random file and return it
sub random_file {
    my @files = glob "/*";
    # Descend into a child directory (if there is one)
    my $random_num = int(rand() * 10 % @files);
    @files = glob "$files[$random_num]/*";

    return -f $files[$random_num] ? $files[$random_num] :
        # Whatever file
        "/boot/vmlinuz-3.2.0-36-generic/kernel";
}
