% Assignment 1-1
% Write a matlab code to find the probability of drawing a matching pair (in
% the first try) out of a drawer containing five identical pairs of gloves.

NUM_PAIRS = 5;
NUM_TRIALS = 1000000;

match_cnt = 0;
total_cnt = 0;

for i = 1:NUM_TRIALS
    % Even is left, odd is right
    first_glove_ID = floor(rand() * 2 * NUM_PAIRS);

    no_dup = true;
    while no_dup
        second_glove_ID = floor(rand() * 2 * NUM_PAIRS);

        if first_glove_ID != second_glove_ID
            no_dup = false;
        end
    end

    % One is left, and the other is right
    if mod(first_glove_ID, 2) != mod(second_glove_ID, 2)
        match_cnt++;
    end
    total_cnt++;
end

disp(strcat('MATCH/TOTAL=', num2str(match_cnt), '/', num2str(total_cnt)))
disp(strcat(num2str(100 * match_cnt / total_cnt), '%'))
