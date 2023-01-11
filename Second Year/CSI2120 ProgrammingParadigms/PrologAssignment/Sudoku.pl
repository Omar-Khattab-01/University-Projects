sudoku([[2,1,4,3],[4,3,2,1],[1,2,3,4],[3,4,1,2]]).
sudoku([[2,1,4,3],[4,3,2,1],[1,2,3,3],[3,4,1,2]]).

verifyAnswer(N1,N2):-
    N1 == N2,!.


%checks if a list have unique numbers.
different(L) :-
   %using setof to get a new list without the deplicates if any,
   %and comparing lenght of original list and new list
    setof(X, member(X, L), NewL),
    length(L, N1),length(NewL, N2),
    verifyAnswer(N1,N2),!.

%checks if a 2d list have unique numbers.
allDifferent(M):-
    maplist(different(),M),!.

%extracts the 4 coulumns from the DB.
extract4Columns(M,L):-  transpose(M,L),!.


transpose([[]|_], []).
transpose(M, [R|Rs]) :-
    transposeList(M, R, LL),
    transpose(LL, Rs).
transposeList([], [], []).
transposeList([[H|T]|Rs], [H|Hs], [T|Ts]) :- transposeList(Rs, Hs, Ts).

%returns element at index X on a list
element_at(X,[X|_],0).
element_at(X,[_|L],K) :- K > 0, K1 is K - 1, element_at(X,L,K1).


%tried using nth0/4 but kept getting an error that I could not debug.
quadrants(A,B,C,D, L) :-
    element_at(A0,A,0),element_at(A1,A,1),element_at(A2,A,2),element_at(A3,A,3),
    element_at(B0,B,0),element_at(B1,B,1),element_at(B2,B,2),element_at(B3,B,3),
    element_at(C0,C,0),element_at(C1,C,1),element_at(C2,C,2),element_at(C3,C,3),
    element_at(D0,D,0),element_at(D1,D,1),element_at(D2,D,2),element_at(D3,D,3),
     L = [[A0,A1,B0,B1],[A2,A3,B2,B3],[C0,C1,D0,D1],[C2,C3,D2,D3]].

%copies a list L into R.
copyList(L,R) :- accCp(L,R).
accCp([],[]).
accCp([H|T1],[H|T2]) :- accCp(T1,T2).

%extracts the 4 quadrants
extract4Quadrants(M,L):- element_at(A,M,0),element_at(B,M,1),element_at(C,M,2),element_at(D,M,3),quadrants(A,B,C,D,L1),copyList(L1,L),!.

%checks if a sudoku is valid.
checkSudoku(M):-
      (extract4Quadrants(M,L1), allDifferent(L1),extract4Columns(M,L2),allDifferent(_L2),allDifferent(M))->write('yes');write('No').
