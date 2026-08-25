:- consult('hechos.pl').

armado_con_fuego(X) :- 
    personaje(X), 
    arma(X, pistola).

armado_con_cuchillo(X) :- 
    personaje(X), 
    arma(X, cuchillo).

sin_arma(X) :- 
    personaje(X), 
    \+ arma(X, pistola).

coinciden_en_zona(X, Y) :- 
    se_encuentra(X, E), 
    se_encuentra(Y, E),
    X \= Y.
