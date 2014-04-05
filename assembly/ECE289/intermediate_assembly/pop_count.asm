# counts the number of 1's in it's binary representation
# vim: ft=mips expandtab sw=4

# $s0 => the number
# $s1 => total number of 1

j main

# $t0 => return address
# $t1 => loop variable
# $t2 => initially same as $s0 (so that $s0 don't get modified)
popCnt:
    move $t0, $ra   # save return address

    li $t1, 0       # init loop variable
    li $s1, 0       # set total count to 0

    move $t2, $s0   # don't modify $s0

.L1:
    bge $t1, 32, exit

    # AND it with 1
    andi $t3, $t2, 1
    add $s1, $s1, $t3

    srl $t2, $t2, 1 # shift right logically (insert 0 to right)
    #div $t2, $t2, 2

    addi $t1, $t1, 1
    j .L1

exit:
    jr $t0
# end loop

main:
    li $s0, 5

    jal popCnt

    # exit
    li $v0, 10
    syscall
