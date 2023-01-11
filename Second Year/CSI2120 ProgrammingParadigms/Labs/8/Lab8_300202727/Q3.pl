bunkbeds(L):- L=[[N1,C1],[N2,C2],[kayla,C3],[N4,C4],[N5,C5],[N6,C6]],
    ((N1=reeva,N2=haley);(N2=reeva,N1=haley)),  /* Constraint 1.*/
    member(C1,[brown,black,blue]),
    member(C3,[brown,black,blue]),
    member(C5,[brown,black,blue]),
    member([beth,C],L),member(C,[yellow,green,red]),
    member([blue,red],[[C1,C2],[C3,C4],[C5,C6]]),
    member(liza,[N1,N5]), %Kayla already specified
    member(zoe,[N1,N2,N5,N6]), %exclude N4
    brown = C5,
    member([black,yellow], [[C1,C2],[C3,C4],[C5,C6]]),
    member(green,[C1,C2,C3,C4,C5,C6]).


%?- bunkbeds(X).
% X = [[reeva, black], [haley, yellow], [kayla, blue], [beth,
% red],[liza, brown], [zoe, green]]