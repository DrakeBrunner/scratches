function ex3 = ex3

% This script calculates the approximate sin value for a certain angle.
% It asks for the user's input of x, the angle,
% and n, the desired number of terms. It then determines
% the approximate value of the sine function
% Made by Naoki Mizuno

% Part A
x = input('Enter x: ');
n = input('Enter n: ');

answer = 0;
sign = 1;

% Part B
% The code displays the approximation error versus
% the number of terms in the approximation series.

error = [];

for i = 1:2:n * 2 - 1
    answer = answer + sign * (power(x, i) / factorial(i));
    % We have to do the opposite (add/subtract) in the next loop
    sign = sign * -1;

    % Modified for Part B
    % Stores the error to an array. Since i changes like
    % 1, 3, 5, 7... array's index must be (i + 1) / 2
    error((i + 1) / 2) = 100 * ((sin(x) - answer) / sin(x));
end

disp(answer)

% Plots the data
plot(1:n, error)
xlabel('Number of terms')
ylabel('Approximation error (%)')
