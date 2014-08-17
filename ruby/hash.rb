#!/usr/bin/ruby

hash1 = {
  'foo' => 1,
  'bar' => 2,
}
# Outputs from hash2 and hash3 are identical
hash2 = {
  foo: 1,
  bar: 2,
}
hash3 = {
  :foo => 1,
  :bar => 2,
}

# Syntax errors
#hash4 = {
#  :foo, 1,
#  :bar, 2,
#}
#hash5 = {
#  'foo', 1,
#  'bar', 2,
#}

print "Hashes\n"
p hash1
p hash2
p hash3
print "\n"

print "['foo']\n"
p hash1['foo']
p hash2['foo']
p hash3['foo']
print "\n"

print "[:foo]\n"
p hash1[:foo]
p hash2[:foo]
p hash3[:foo]
print "\n"
