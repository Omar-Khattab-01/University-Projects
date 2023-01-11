import:-
    csv_read_file('partition65.csv', Data65, [functor(partition)]),maplist(assert, Data65),
    csv_read_file('partition74.csv', Data74, [functor(partition)]),maplist(assert, Data74),
    csv_read_file('partition75.csv', Data75, [functor(partition)]),maplist(assert, Data75),
    csv_read_file('partition76.csv', Data76, [functor(partition)]),maplist(assert, Data76),
    csv_read_file('partition84.csv', Data84, [functor(partition)]),maplist(assert, Data84),
    csv_read_file('partition85.csv', Data85, [functor(partition)]),maplist(assert, Data85),
    csv_read_file('partition86.csv', Data86, [functor(partition)]),maplist(assert, Data86),listing(partition).

copy(L,R) :- accCp(L,R).
accCp([],[]).
accCp([H|T1],[H|T2]) :- accCp(T1,T2).

getClusterID(X,[X]).
getClusterID(X,[_|L]) :- getClusterID(X,L).

intersection([X|Y],M,[X|Z]):- list_member(X,M),intersection(Y,M,Z).
intersection([X|Y],M,Z):- \+ list_member(X,M),intersection(Y,M,Z).
intersection([],M,[]).

list_member(X,[X|_]).
list_member(X,[_|T]):-list_member(X,T).

append( [], X, X).                                  
append( [X | Y], Z, [X | W]) :- append( Y, Z, W).


relabel(I,[],E,T2).
relabel(I, [H|T], E, [H2|T2]) :-
    nth0(I, H, _, R),
    nth0(I, H2, E, R),
    relabel(I,T,E,T2).

mergeClusters([],[]).
mergeClusters(LL):- findall([X,Y,Z,C],partition(_,X,Y,Z,C),L) ,mergeClusters(LL,L). 
mergeClusters(LL, [H2|T2]):- check(H2,LL),mergeClusters(T,T2).


check(P,LL):- 
    getClusterID(ID,P),nth0(0, P, X2), findall([X,Y,Z,C2],(partition(_,X,Y,Z,C2),X = X2),L),intersection(L,LL,NewL),relabel(3,NewL,ID,NewLL),append(NewLL,LL,LL).

%tried to fix relabel but kept runnning into infinite loop everything else should be good.