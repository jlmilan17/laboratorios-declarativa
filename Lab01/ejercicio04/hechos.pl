sobreviviente(eric).
sobreviviente(timmy).
sobreviviente(kelvin).
sobreviviente(virginia).

rol(eric, protagonista).
rol(kelvin, aliado).
rol(virginia, mutante).

edad(eric, 30).

no_puede_hablar(kelvin).
puede_volverse_aliada(virginia).

tiene(eric, hacha).
tiene(eric, encendedor).

puede_cargar_troncos(kelvin).
puede_construir(kelvin, orden).

zona(superficie).
zona(cuevas).
zona(bunkeres).

enemigo(canibales).
enemigo(mutantes).

aparece_en(canibales, superficie).
aparece_en(mutantes, superficie).
aparece_en(mutantes, cuevas).

abren_con(bunkeres, llaves).

nivel_peligro(cuevas, alto).
nivel_peligro(superficie, medio, dia).
nivel_peligro(superficie, alto, noche).

necesita(eric, refugio).
necesita(eric, comida).
necesita(eric, agua).

material(troncos).
material(piedras).

se_encuentra_en(troncos, superficie).
se_encuentra_en(piedras, superficie).