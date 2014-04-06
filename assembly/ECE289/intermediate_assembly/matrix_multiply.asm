# do a matrix multiplication
# vim: ft=mips expandtab sw=4 foldmethod=marker

# $s0 => address of m
# $s1 => number of rows in m (mrow)
# $s2 => number of columns in m (mcol)
# $s3 => address of n
# $s4 => number of rows in n (nrow)
# $s5 => number of columns in n (ncol)
# $s6 => address of result

j main

# $t0 => i
# $t1 => j
# $t2 => k
# $t3 => address of r_m
# $t4 => address of r_m[i]
matrix_multiply:    # {{{
    bne $s2, $s4, .TL9  # quit if nrow != mcol

    # allocate result matrix
    mul $a0, $s1, 4
    li $v0, 9
    syscall
    move $s6, $v0

# for loop to allocate rows
# init
.TL10:
    li $t0, 0
    move $t3, $s6
.TL11:
    bge $t0, $s1, .TL12

    mul $a0, $s5, 4
    li $v0, 9
    syscall
    sw $v0, ($t3)

    addi $t3, $t3, 4
    addi $t0, $t0, 1
    j .TL11
.TL12:
# end for loop to allocate rows

# outer for loop
# init
    li $t0, 0
    move $t3, $s6
    lw $t4, ($t3)       # load a row
.TL13:
    bge $t0, $s1, .TL14

# middle for loop
# init
    li $t1, 0
.TL15:
    bge $t1, $s5, .TL16

    sw $zero, ($t4)     # r_m[i][j] = 0

# inner for loop
# init
    li $t2, 0
.TL17:
    bge $t2, $s4, .TL18

    # Calculate the product

    # get m[i][k]
    mul $t5, $t0, 4
    add $t5, $t5, $s0   # address of m[i]
    lw $t6, ($t5)       # address of row stored at m[i]
    mul $t5, $t2, 4
    add $t5, $t5, $t6   # address of m[i][k]
    lw $t7, ($t5)       # $t7 = value in m[i][k]

    # get n[k][j]
    mul $t5, $t2, 4
    add $t5, $t5, $s3   # address of n[k]
    lw $t6, ($t5)       # address of row stored at n[k]
    mul $t5, $t1, 4
    add $t5, $t5, $t6   # address of n[k][j]
    lw $t8, ($t5)       # $t8 = value in n[k][j]

    mul $t5, $t7, $t8   # $t5 = m[k][j] * n[i][k]
    lw $t6, ($t4)       # $t6 = r_m[i][j]
    add $t6, $t6, $t5   # $t6 = r_m[i][j] + m[k][j] * n[i][k]
    sw $t6, ($t4)

    addi $t2, $t2, 1
    j .TL17
.TL18:
# end inner for loop

    addi $t1, $t1, 1
    addi $t4, $t4, 4
    j .TL15
.TL16:
# end middle for loop

    addi $t0, $t0, 1
    addi $t3, $t3, 4
    j .TL13
.TL14:
# end outer for loop

.TL9:
    jr $ra
# end function matrix_multiply }}}

# Allocates memory and prompts for input
# $t0 => number of columns
# $t1 => number of rows
# $t2 => address of allocated array
# Temporary vars
# $t3 => i
# $t4 => j
# $t5 => address of a
# $t6 => address of a[i]
array:  # {{{
    # first allocate column
    mul $a0, $t1, 4
    li $v0, 9
    syscall
    move $t2, $v0

# for loop to allocate rows
# init
.TL1:
    li $t3, 0
    move $t5, $t2
.TL2:
    bge $t3, $t1, .TL3

    # allocate a columns
    mul $a0, $t0, 4
    li $v0, 9
    syscall
    sw $v0, ($t5)

    # increment
    addi $t5, $t5, 4
    addi $t3, $t3, 1
    j .TL2
.TL3:
# end for loop to allocate rows

# outer for loop to input integers
# init
    li $t3, 0
    move $t5, $t2
.TL4:
    bge $t3, $t1, .TL6

# inner for loop to input integers
# init
.TL5:
    li $t4, 0
    lw $t6, ($t5)
.TL7:
    bge $t4, $t0, .TL8

    # read in an integer
    li $v0, 5
    syscall
    sw $v0, ($t6)

    # increment
    addi $t4, $t4, 1
    addi $t6, $t6, 4
    j .TL7
.TL8:
# end inner for loop

    # increment
    addi $t3, $t3, 1
    addi $t5, $t5, 4
    j .TL4
.TL6:
# end outer for loop

    jr $ra
# end function array }}}

main:

    li $s1, 3
    li $s2, 4
    li $s4, 4
    li $s5, 2

    # allocate m
    move $t0, $s2
    move $t1, $s1
    jal array
    move $s0, $t2

    # allocate n
    move $t0, $s5
    move $t1, $s4
    jal array
    move $s3, $t2

    jal matrix_multiply

    # exit
    li $v0, 10
    syscall
