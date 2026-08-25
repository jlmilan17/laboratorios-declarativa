personaje(leon).
personaje(ashley).
personaje(ada).
personaje(luis).

agente(leon).
estudiante(ashley).
espia(ada).
investigador(luis).

edad(leon, 27).
edad(ashley, 20).
edad(ada, 26).
edad(luis, 32).

arma(leon, pistola).
arma(leon, cuchillo).
arma(ada, pistola).
arma(luis, cuchillo).

enemigo(ganado).
enemigo(regeneradores).

infectado_por(ganado, las_plagas).
infectado_por(regeneradores, las_plagas).

aparece_en(ganado, pueblo).
aparece_en(ganado, castillo).
aparece_en(regeneradores, isla).

zona(pueblo).
zona(castillo).
zona(isla).

dificultad(pueblo, alta).
dificultad(castillo, alta).
dificultad(isla, muy_alta).

se_encuentra(luis, pueblo).
se_encuentra(leon, pueblo).
se_encuentra(ada, pueblo).
se_encuentra(ada, castillo).