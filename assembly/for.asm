# int j = 3;
# for (i = $a0; i < $a1; i += $a2) {
#     j++;
# }

j main

for:
addi $t0, $a0, 0		# Initialize
jal eval

eval:
bge $t0, $a1, end		# i < $a1 (end if i >= $a1)

jal proc

add $t0, $t0, $a2		# i += $a2
jal eval

# What to do in the for loop
proc:
addi $s0, $s0, 1		# j++
jr $ra

main:
addi $s0, $zero, 3		# j = 3
addi $a0, $zero, 0		# i = 0
addi $a1, $zero, 10		# i < 10
addi $a2, $zero, 1		# i++
jal for

end: