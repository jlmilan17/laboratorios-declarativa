:- consult('hechos.pl').

zona_segura(Z) :- 
    zona(Z),
    \+ (enemigo(E), aparece_en(E, Z)).

zona_alto_riesgo(Z) :-
    zona(Z),
    (nivel_peligro(Z, alto); nivel_peligro(Z, alto, _)).

puede_sobrevivir(X) :-
    necesita(X, refugio),
    necesita(X, comida),
    necesita(X, agua).           