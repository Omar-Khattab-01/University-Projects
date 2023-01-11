sum_int(1,1).

sum_int(N,X):-
    N > 1,
    NewN is N-1, 
    sum_int(NewN, Y),
    X is Y + N.



%?- sum_int(2,X).
%X = 3 .
%?- sum_int(5,Y).
%Y = 15 .
