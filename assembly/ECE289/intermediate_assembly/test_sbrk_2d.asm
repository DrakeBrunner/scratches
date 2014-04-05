# test program for sbrk calls
# vim: ft=mips expandtab sw=4

# $s0 => num of rows
# $s1 => num of columns
# $s2 => address of first row

j main

create:
    move $t0, $ra   # Save returning address
    
    # Alloc rows
    mul $a0, $s0, 4
    li $v0, 9       # sbrk syscall
    syscall
    move $s2, $v0   # save address of first row

    li $t1, 0       # counter variable
    move $t2, $s2
    jal loop
    jr $t0          # go back to main

loop:
    move $t4, $ra
.L1:
    bge $t1, $s1, .L2

    # allocate rows
    mul $a0, $s1, 4 # prepare to allocate row length
    li $v0, 9
    syscall
    sw $v0, ($t2)

    addi $t1, $t1, 1
    addi $t2, $t2, 4
    j .L1
# exit loop
.L2:
    jr $t4

# create a 2x3 matrix
main:
    li $s0, 2   # 2 rows (i.e. column length of 2)
    li $s1, 3   # 3 columns (i.e. row length of 3)

    # Allocate
    mul $a0, $s0, $s1
    mul $a0, $a0, 4
    li $v0, 9
    syscall
    move $s2, $v0   # $s2 is the address of first element in matrix
    move $t2, $s2   # copy for going through the array when input

    li $t1, 0       # initialize counter variable
    # input data (rows * column)
    mul $t0, $s0, $s1
input:
    bge $t1, $t0, end_input

    li $v0, 5
    syscall
    # store input int
    sw $v0, ($t2)
    addi $t2, $t2, 4

    addi $t1, $t1, 1

    j input
end_input:

    jal create

    # exit
    li $v0, 10
    syscall
