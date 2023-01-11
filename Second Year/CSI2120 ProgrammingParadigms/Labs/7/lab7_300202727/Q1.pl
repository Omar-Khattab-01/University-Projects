maxmin([H|T],Max,Min) :- maxmin(T,H,H,Max,Min),!.

%new max
maxmin([H|T],MX,MN,Max,Min):-


