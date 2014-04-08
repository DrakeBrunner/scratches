#Program that swaps 2 strings.
print "Enter strings seperated by a space: ";
$string = <STDIN>;
chomp($string);

$string =~ /\s+/;
print "Reversed string: $' $`\n"