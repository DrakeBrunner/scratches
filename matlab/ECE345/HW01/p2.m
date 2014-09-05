% Assignment 1-2
% In rolling a die four times, what is the
% odds that at least one 6 would appear? 

NUM_TRIALS = 1000000;

success_cnt = 0;
total_cnt = 0;

for i = 1:NUM_TRIALS
    for j = 1:4
        roll = ceil(rand() * 6);

        if roll == 6
            success_cnt++;
            break
        end
    end

    total_cnt++;
end

disp(strcat('SUCCESS/TOTAL=', num2str(success_cnt), '/', num2str(total_cnt)))
disp(strcat(num2str(100 * success_cnt / total_cnt), '%'))
