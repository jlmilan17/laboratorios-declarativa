% Suma de dos numeros
suma(A, B, R) :-
number(A), number(B), is(R, A + B).
% Resta de dos numeros
resta(A, B, R) :-
number(A), number(B), is(R, A - B).
% Multiplicacion de dos numeros
multiplicacion(A, B, R) :-
number(A), number(B), is(R, A * B).
% Division exacta de dos enteros (el divisor no puede ser cero)
division(A, B, R) :-
integer(A), integer(B), B =\= 0, is(0, A mod B), !, is(R, A // B).
% Division con decimales (el divisor no puede ser cero)
division(A, B, R) :-
number(A), number(B), B =\= 0, is(R, A * 1.0 / B).
