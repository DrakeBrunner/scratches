function exam = exam

% Exam problem. Prompts the user for an input and
% change show the x-value according to the input.
% Author: Naoki Mizuno

t = input('Enter value for t: ');
if (t > 1)
    x = 5 * t
else
    x = 5
end
