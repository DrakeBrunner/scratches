# program that calculates the GCD of two numbers
# vim: ft=mips expandtab sw=4

# $s0 => first int
# $s1 => second int
# $s2 => result

j main

# $t0 => return address
# $t1 => how much to shift (variable "shift")
# $t2 => copy of $s0 (in order not to modify it)
# $t3 => copy of $s1 (in order not to modify it)
gcd:
    # save return address
    move $t0, $ra

    # copy of $s0 and $s1
    move $t2, $s0
    move $t3, $s1

    or $t6, $s0, $s1
    bnez $t6, .L1

    # return 0 when u == 0 || v == 0
    li $s2, 0
    jr $t0

# for loop
.L1:
    li $t1, 0
.L2:
    # (u | v) & 1
    or $t6, $t2, $t3
    and $t6, $t6, 1
    bnez $t6, .L3

    # shift
    srl $t2, $t2, 1
    srl $t3, $t3, 1

    addi $t1, $t1, 1
    j .L2
# end for loop

# while loop
.L3:
    and $t6, $t2, 1
    bnez $t6, .L4

    srl $t2, $t2, 1     # shift 1 right logically

    j .L3
# end while loop

# do-while loop
# while loop
.L4:
    and $t6, $t3, 1
    bnez $t6, .L5

    srl $t3, $t3, 1     # shift 1 right logically

    j .L4
# end while loop

# if statement
.L5:
    ble $t2, $t3, .L6
# false
    subu $t6, $t2, $t3  # $t6 = diff = u - v = $t2 - $t3
    move $t2, $t3       # u = v
    move $t3, $t6       # v = diff
    j .L7
# true
.L6:
    subu $t3, $t3, $t2  # v -= u
# end if statement

.L7:
    srl $t3, $t3, 1

    bnez $t3, .L4       # do-while (v != 0)
# end do-while loop

    sllv $s2, $t2, $t1  # u << shift
    jr $t0              # return

main:
    li $s0, 21
    li $s1, 15

    jal gcd

    # exit
    li $v0, 10
    syscall
