# Compiler

## Specifications
### Language
The language is defined to be similar to LISP. I defined so in order for the
parsing to be less tedious.

    <code>
        <loop type="do-while">
            <cond>
                <o>&lt;</o>
                    <v>a</v>
                    <c>20</c>
            </cond>

            <body>
                <setq>
                    <v>a</v>
                    <o>+</o>
                        <v>a</v>
                        <c>2</c>
                </setq>
            </body>
        </loop>
    </code>

This code is the translation of the following C code snippet.

    do {
        a = a + 2;
    } while (a < 20);

### Variables
* You can't have more than 8 variables

### Assignments
* Use `<setq>` tag for assignments
* Left operand must be a variable
* Constants must all be integers

### Operators
* +, -, ==, !=, >, >=, <, <= are the operators that you can use
* Operands must be either constant or variable
* Combination of constant and variable is valid (`a + 2`)
* Variables must come first (`a < 20` instead of `20 > a`)
* You can't subtract variables. Only constants

### Loops
* do-while loops only
* Loop conditions must be either operators or variables (no constants)

### Conditionals
The following code illustrates the if-else statement.

    <if>
        <cond>
            <o>==</o>
                <v>a</v>
                <c>2</c>
        </cond>

        <!-- true -->
        <body>
            <setq>
                <v>a</v>
                <c>1</c>
            </setq>
        </body>
        <!-- false -->
        <body>
            <setq>
                <v>a</v>
                <c>25</c>
            </setq>
        </body>
    </if>

The first `<body>` is executed when the condition is true and the second is
executed when the condition is false.
