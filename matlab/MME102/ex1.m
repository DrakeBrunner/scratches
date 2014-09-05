function ex1 = ex1

% This script generates a square matrix of any
% size, n, with the following pattern
% 1 0 1 0 1
% 0 1 0 1 0
% 1 0 1 0 1
% 0 1 0 1 0
% 1 0 1 0 1
% Made by Naoki Mizuno


n = input('Enter size of matrix: ');

matrix = zeros(n);
start = 2;
for i = 1:n
    % Change starting index according to the previous
    % starting index
    if start == 1
        start = 2;
    else
        start = 1;
    end
    
    % Do the for loop
    for j = start:2:n
        matrix(i, j) = 1;
    end
end

disp(matrix)
