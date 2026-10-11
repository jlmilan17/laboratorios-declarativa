%Descomponer un numero en una lista de sus digitos

%Caso base
digits(0, []) :- !.

%Caso general
digits(N, [D|R]) :-
    N > 0,
    D is N mod 10, %Operacion para extraer el ultimo digito
   	N1 is N // 10, %Operacion para eliminar el ultimo digito
    digits(N1, R). %Recursion que el numero nuevo luego de la extraccion