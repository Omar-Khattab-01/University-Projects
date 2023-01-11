% --------
% book( Title, Authors, Publisher, Year, CallNumber )
% --------

book(
  'The craft of Prolog',
  'R. A. O''Keefe',
  'MIT Press',
  1990,
  'QA 76.73 .P76 O38 1990'
).
book(
  'Programming in Prolog',
  'W. F. Clocksin, C. S. Mellish',
  'Springer',
  2003,
  'QA 76.73 .P76 C57 2003'
).
book(
  'Prolog for programmers',
  'F. Kluzniak, S. Szpakowicz',
  'Academic Press',
  1985,
  'QA 76.73 .P76 K58 1985'
).
book(
  'Prolog programming for artificial intelligence',
  'I. Bratko',
  'Addison-Wesley',
  2001,
  'Q 336 .B74 2001'
).

% --------
% reader( CardNumber, Name )
% --------

reader( 1234567, 'James Brown' ).
reader( 2345678, 'Jacques Brun' ).
reader( 3456789, 'Giacomo Bruno' ).

% --------
% checkedOut( CardNumber, CallNumber )
% --------

checkedOut( 1234567, 'QA 76.73 .P76 C57 2003' ).
checkedOut( 3456789, 'Q 336 .B74 2001' ).


% (1) all books published by Springer,

%?- book(T,_,'Springer',_,_).
%T = 'Programming in Prolog'

%(2) all books published after 1990

%?- book(T,_,_,X,_),X>1990.
%T = 'Programming in Prolog',
%X = 2003 ;
%T = 'Prolog programming for artificial intelligence',
%X = 2001.

%(3) all books checked out by James Brown.

%?- reader(X,'James Brown'),checkedOut(X,Z),book(T,_,_,_,Z).
%X = 1234567,
%Z = 'QA 76.73 .P76 C57 2003',
%T = 'Programming in Prolog'.

find(A,B,C,D,E,F) :- book(A,B,C,D,E), D>F.

%?- find(T,_,_,_,_,1990).
%T = 'Programming in Prolog' ;
%T = 'Prolog programming for artificial intelligence'.

check(X,T) :- reader(Y,X), checkedOut(Y,SerialNumber),book(T,_,_,_,SerialNumber).

%?- check('James Brown',Answer).
%Answer = 'Programming in Prolog'.


