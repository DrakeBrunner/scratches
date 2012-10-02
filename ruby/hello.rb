#!/usr/bin/ruby

name = "user"
puts name

array = [1, 2, 3, 'hello', 'world']

array.each do |var|
    puts var
end

hash = {"perl" => "best"}

hash.each do |var, what|
    print "#{var} is #{what}\n"
end
