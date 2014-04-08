#This program reverses input string's order.

print "Input strings: ";
$input = <STDIN>;
chomp($input);

print chop($input) while($input);
print "\n";
