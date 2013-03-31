" This is my first vim script
" It's a simple "Hello, world" program!
echo "Hello, world!"

echo "Now, without a newline char"
echon "Hello, world without newline!"
echo " -> to a world WITH newline"

" Now, let's play around with variables
let a = 1
echo a
let a = 1.23
echo a + a

" You can only use += and -= (no /= or *=)
echo "before a += 4:" a
let a += 4
echo "after a += 4:" a
let a -= 2
echo "after a -= 2:" a

" Play around with strings
let str = "123" . "a"
echo str
echo "length of str is" strlen(str)
let str2 = "hello,world,I,love,vim"
" Just like the good old split in Perl
let result = split(str2, ',')
echo result
echo join(result, " and ")
" Shows l (the 2nd one)
echo "hello world"[3]

" Now, check to see how arrays work
let array = [4, 2, 5, 1, 3]
echo "length is " . len(array)
echo "index 4 is " . array[4]
echo "removing..." remove(array, 0)
echo "removed index 0:" array
" Use call to just call function
call remove(array, -1)
echo "removed index -1:" array
echo insert(array, "hello", 3)

echo add(array, "added to last")
" Remove the last element
call remove(array, -1)
" Will be inserted to the front if no arg
echo insert(array, "added again")

let list = [3, 1, 2]
echo "for the following list:" list
for a in list
    if a == 1
        echo "a is 1"
    elseif a == 2
        echo "a is 2"
    else
        echo "a is neither 1 nor 2"
    endif
    echon " when a is " . a
endfor

" Now, testing function
function! Sum(n1, n2)
    let b = a:n1 + a:n2
    echo b
    return b
endfunction

" Has to be after declaring function
call Sum(5, 2)

" Don't forget to add a: in front of the variable!
function! s:subt(n1, n2)
    if a:n1 > a:n2
        return a:n1 - a:n2
    else
        return a:n2 - a:n1
    endif
endfunction

" Don't forget to add s:
echo s:subt(1, 3)

" Variable arguments
function! Testing(var1, ...)
    echo "a:0" a:0
    echo "a:2" a:1
    echo "a:000" a:000
endfunction

call Testing(5, 2, 14)
