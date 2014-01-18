function! s:srand(seed)
    let self = {}
    let self.seed = a:seed
    function self.apply()
        let self.seed = self.seed * 214013 + 2531011
        return (self.seed < 0 ? self.seed - 0x80000000 : self.seed) / 0x10000 % 0x8000
    endfunction
    return self
endfunction

function! s:uniform_int_distribution(min, max)
    let self = {}
    let self.min = a:min
    let self.max = a:max
    function! self.apply(engine)
        let num = a:engine.apply()
        return num % ((self.max+1)-self.min) + self.min
    endfunction
    return self
endfunction

function! Sort_by_value(a, b)
    return a:a == a:b ? 0
                \: a:a > a:b ? 1 : -1
endfunction

function! s:main()
    " Don't put 0
    let favorites = {
                \ 'desert' : 3,
                \ 'gentooish' : 7,
                \ 'jellybeans' : 5,
                \ 'molokai' : 9,
                \ 'moria' : 8,
                \ 'solarazed' : 9,
                \ 'wombat' : 3,
                \ 'zenburn' : 4,
                \ }

" Changing the ratio to increasing numbers makes
" it easier to pick a random number.
" 0   3       10    15            24
" +--------------------------------+
" ^   ^        ^     ^             ^
" <-3-><-- 7 --><-5-> <--    9   -->
    let prev = 0
    let total = 0
    for key in keys(favorites)
        let total += favorites[key]

        let favorites[key] += prev
        let prev = favorites[key]
    endfor

    let engine = s:srand(localtime())

    let distribution = s:uniform_int_distribution(1, 48)
    let rand = distribution.apply(engine)

    let first = 1
    for key in reverse(sort(keys(favorites), "Sort_by_value"))
        if favorites[key] <= rand || (rand >= total - favorites[key] && first == 1)
            " echo rand
            echo key
            execute 'colorscheme ' . key
            break
        else
            first = 0
        endif
    endfor
endfunction
call s:main()
