countDown(0):- writeln(0),
                !.
countDown(N):-  
    writeln(N), 
    NN is N-1, 
    countDown(NN).
