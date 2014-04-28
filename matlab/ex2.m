function ex2 = ex2

% This is part A in exerecise 2
% This determines the amount of time for the
% cistern to overflow/empty according to the
% input of Ri, Ro, and d as a single vector
% Made by Naoki Mizuno

Ri = input('Input rate: ');
Ro = input('Output rate: ');
d = input('d: ');
data = [Ri, Ro, d];

% Change in height per minute (in inches)
heightChangePerMin = (data(1) - data(2)) / (12^2*pi);

% When it's increasing
if heightChangePerMin > 0
    time = (24 - data(3)) / heightChangePerMin;
% When it's decreasing
elseif heightChangePerMin < 0
    time = data(3) / -1 * heightChangePerMin;
% When there is no change in height
else
    disp('The height will stay constant!')
end


% This is part B in exercise 2
x = [];     % x axis represents the time
y = [];     % y axis represents the height
for i = 1:time+1
    x(i) = i - 1;
    y(i) = d + (i - 1) * heightChangePerMin;
end
% If the height increases, the endpoint is 24
plot(x, y)
title('Height versus Time')
xlabel('Time (min)')
ylabel('Height (in)')
