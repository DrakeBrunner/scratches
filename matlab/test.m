function test = test

% Script for testing if-elseif-else statement,
% for loop, and while loop
% Made by Naoki Mizuno on March 25, 2013

% Testing standard input
var = input('Enter a number: ');
disp('The number you entered is: ')
disp(var)

disp('This number is larger than 5')
if var > 5
    disp('true')
% Cannot use 'else if'
elseif var < 5
    disp('false')
else
    disp('It is 5')
end

% The escape character '\n' can't be used in MATLAB
disp('testing for loop (and new line char)')
% Equivalent to Python's
% for i in xrange(10):
for i = 0:1:10
    % equivalent to 0:10 (default step is 1)
    disp('Currently in ')
    disp(i)
end

% Testing while loop
disp('testing while loop')
while 'true'
    stdin = input('Enter a number: ');
    if stdin == 50
        break
    end
end
