# test with 1D array
# vim: ft=mips expandtab sw=4

# $s0 => length of array
# $s1 => address of first element in array
# $s2 => address of second sbrk

li $s0, 3

mul $t0, $s0, 4
move $a0, $t0
li $v0, 9
syscall
move $s1, $v0

# use $t1 to go through array
move $t1, $s1

# read first int
li $v0, 5
syscall
sw $v0, ($t1)

addi $t1, $t1, 4

# read second int
li $v0, 5
syscall
sw $v0, ($t1)

addi $t1, $t1, 4

# read third int
li $v0, 5
syscall
sw $v0, ($t1)

# use sbrk for second time
li $a0, 5
li $v0, 9
syscall
move $s2, $v0

# exit
li $v0, 10
syscall
