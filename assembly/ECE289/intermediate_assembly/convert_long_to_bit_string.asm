# Accepts a number and returns the binary representation in string
# vim: ft=mips expandtab sw=4

# $s0 => original number
# $s1 => number of bits
# $s2 => address of first letter in string

j main

# $t0 => return address
# $t1 => mask
# $t2 => loop var i
# $t3 => pointer to a letter in string (same as $s2 + $t2)
convert_long_to_bit_string:
    move $t0, $ra

    # alloc string
    # Note: although char is 1 byte, SPIM simulator doesn't seem to like it unless
    #       we use a "chunk" of 4 bytes
    mul $a0, $s1, 4     # allocate ($s1 * 4) bytes
    li $v0, 9
    syscall
    move $s2, $v0       # save allocated address

    li $t1, 1           # mask

# start for loop
loop:
    addi $t2, $s1, -1   # setup i
    # set up pointer
    mul $t4, $t2, 4
    add $t3, $s2, $t4   # $s2 + (i * 4) because memory is 4 bytes each (?)

.L1:
    blt $t2, $zero, exit

    # Is (mask & orig_long > 0) ?
    and $t4, $t1, $s0
    bgt $t4, $zero, .L2

# false
    li $t5, 48          # '0'
    sw $t5, ($t3)
    j .L3

# true
.L2:
    li $t5, 49          # '1'
    sw $t5, ($t3)

.L3:
    sll $t1, $t1, 1     # shift logically left 1

    # rerun loop
    addi $t2, $t2, -1   # i--
    addi $t3, $t3, -4   # reverse pointer
    j .L1
# end of loop

exit:
    # add a null terminator '\0' to string
    mul $t7, $s1, 4
    add $t7, $s2, $t7
    sw $zero, ($t7)     # ASCII code for '\0' is 0

    jr $t0

main:
    li $s0, 5
    li $s1, 4

    jal convert_long_to_bit_string

    # exit program
    li $v0, 10
    syscall
