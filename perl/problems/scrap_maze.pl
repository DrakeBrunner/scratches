#!/usr/bin/perl

use strict;
use warnings;
use 5.010;

my $input;
if (@ARGV) {
    $input = $ARGV[0];
}
else {
    print "File: ";
    chomp($input = <STDIN>);
}

open FILE, $input or die "Could not open file: $!";

# Store input file into 2-dimensional array
my @maze;
my ($i, $j);
$i = 0;
my ($start_ref, $goal_ref);
while (<FILE>) {
    chomp(my $line = $_);

    $start_ref = [$i, index($line, "S")] if /S/;
    $goal_ref = [$i, index($line, "G")] if /G/;

    $maze[$i++] = [split //, $line];
}

print "Initial check\n";
&check(\@maze);

($i, $j) = ($$start_ref[0], $$start_ref[1]);
my @break_point;
my $counter = 0;
my %min;
# Keep moving while the cursor is not on goal
while (!($i == $$goal_ref[0] and $j == $$goal_ref[1])) {
    my $moveable = 0;
    $moveable += 1 unless $maze[$i-1][$j] =~ /\*|\$|S/;
    $moveable += 2 unless $maze[$i][$j-1] =~ /\*|\$|S/;
    $moveable += 4 unless $maze[$i][$j+1] =~ /\*|\$|S/;
    $moveable += 8 unless $maze[$i+1][$j] =~ /\*|\$|S/;
    #  1
    # 2C4
    #  8

    {   # Code block to redo the given-when statement
        given ($moveable) {
            when ($_ == 0) {
                # Roll back to previous break point
                # (array stores {$i, $j, [available direction]})
                my @previous_point = @{$break_point[@break_point - 1]};
                # Go back the path by following the $ sign
                while (!($i == $previous_point[0] and $j == $previous_point[1])) {
                    $maze[$i][$j] =~ s/\$/ /;
                    $counter--;
                    if    ($maze[$i-1][$j] eq "\$") { $i--; }
                    elsif ($maze[$i+1][$j] eq "\$") { $i++; }
                    elsif ($maze[$i][$j-1] eq "\$") { $j--; }
                    elsif ($maze[$i][$j+1] eq "\$") { $j++; }
                }
                $moveable = shift @{$previous_point[2]};
                # Pop it if there's no available direction in the last
                # element of @break_point after shifting
                pop @break_point if @{$previous_point[2]} == 0;
                redo;
            }
            when ($_ == 1) { $maze[--$i][$j] =~ s/ /\$/; $counter++; break; }
            when ($_ == 2) { $maze[$i][--$j] =~ s/ /\$/; $counter++; break;  }
            when ($_ == 4) { $maze[$i][++$j] =~ s/ /\$/; $counter++; break;  }
            when ($_ == 8) { $maze[++$i][$j] =~ s/ /\$/; $counter++; break;  }

            default {
                # Temporary array
                my @available_direction;
                # Interpret the direction bit (just like persmission bit)
                for (qw(8 4 2 1)) {
                    next if $moveable < $_;
                    push @available_direction, $_ if $moveable - $_ >= 0;
                    last if $moveable - $_ == 0;
                    $moveable -= $_;
                }
                $moveable = shift @available_direction;
                push @break_point, [$i, $j, \@available_direction]; # Add current $i, $j to last
                redo;   # Move to any available direction
            }
        }
        # If it reached the goal, check for the shortes route
#         if ($i == $$goal_ref[0] and $j == $$goal_ref[1]) {
#             if (defined $min{counter}) {
#                 if ($min{counter} > $counter) {
#                     $min{counter} = $counter;
#                 }
#             }
#             else {
#                 $min{counter} = $counter;
#             }
#             pop @break_point if @{${$break_point[@break_point - 1]}[2]} == 0;
#             print "length of bp: ", length(@break_point), "\n";
# &check_break_points(\@break_point);
#             last if @break_point == 0;
#             $i = ${$break_point[@break_point - 1]}[0];
#             $j = ${$break_point[@break_point - 1]}[1];
#             redo;
#         }
    }   # End of code block
}

print "Last\n";
&check(\@maze);
# &check_break_points(\@break_point);

sub check {
    my $maze = shift;
    for (my $i = 0; $i < @$maze; $i++) {
        print join("", @{$$maze[$i]}), "\n";
    }
}

sub check_break_points {
    my $break_point = shift;
    if (@$break_point == 0) {
        print "No break points\n";
        return;
    }
    for (my $i = 0; $i < @$break_point; $i++) {
        print "Point $i\n";
        print "i: ${$$break_point[$i]}[0] i: ${$$break_point[$i]}[1]\n";
        print join(" ", @{${$break_point[$i]}[2]}), "\n";
    }
}
