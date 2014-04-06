# just another bubble sorting program
# vim: ft=mips expandtab sw=4

# $s0 => address of the array
# $s1 => length of the array

j main

# $t0 => return address
# $t1 => i
# $t2 => j
# $t3 => temp
BubbleSort:
    # save return address
    move $t0, $ra

# outer for loop
.L1:
    li $t1, 0
.L2:
    sub $t4, $s1, 1
    bge $t1, $t4, exit

# inner for loop
.L3:
    li $t2, 0
.L4:
    sub $t4, $s1, 1
    sub $t4, $t4, $t1
    bge $t2, $t4, .L6

# if statement
    addi $t4, $t2, 0    # j
    mul $t4, $t4, 4     # (j + 0) * 4 bytes
    add $t4, $t4, $s0  # address of a[j]
    addi $t5, $t2, 1    # j + 1
    mul $t5, $t5, 4     # (j + 1) * 4 bytes
    add $t5, $t5, $s0  # address of a[j + 1]

    lw $t6, ($t4)       # a[j]
    lw $t3, ($t5)       # a[j + 1] (= temp)
    ble $t6, $t3, .L5   # jump if false

# true
    # Note: temp is already set to $t3
    sw $t6, ($t5)       # a[j + 1] = a[j]
    sw $t3, ($t4)       # a[j] = temp

# false
.L5:
# end if statement

    addi $t2, $t2, 1
    j .L4
# end inner for loop

.L6:

    addi $t1, $t1, 1
    j .L2
# end outer for loop

exit:
    jr $t0

main:
    li $s1, 8

    mul $a0, $s1, 4     # allocate ($s1 * 4) bytes
    li $v0, 9
    syscall
    move $s0, $v0

# for loop to input numbers
.L7:
    li $t0, 0
    move $t1, $s0
.L8:
    bge $t0, $s1, .L9

    li $v0, 5
    syscall
    sw $v0, ($t1)

    addi $t0, $t0, 1
    addi $t1, $t1, 4
    j .L8
.L9:
# end loop

    jal BubbleSort

    # exit
    li $v0, 10
    syscall
