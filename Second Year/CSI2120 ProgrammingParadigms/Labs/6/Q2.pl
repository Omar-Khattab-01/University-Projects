element(chlorine,'Cl').
element(helium,'He').
element(hydrogen,'H').
element(nitrogen,'N').
element(oxygen,'O').

lookup(X):- 
	element(X, Res),
	write(Res), write(" is the symbol for: "), writeln(X), 
	!.
lookup(X):-
	write("Don\'t know the symbol: "), writeln(X), 
	!, fail.
interact :- 
	writeln("Elements in the Periodic Table "),
	repeat,
	write("Symbol to look-up: "),
	read(X),
	not(lookup(X)),
	!,fail.
	