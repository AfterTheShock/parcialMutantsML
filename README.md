# Parcial Mutantes Mercado Libre

## Introducción

Examen de Mercado Libre dado como parcial en la materia Desarrollo de Software de la Universidad Tecnológica Nacional FRM. Por Gabriel Badui.

Desarrollar un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.

Para eso te ha pedido crear un programa con un método o función con la siguiente firma:

```java
boolean isMutant(String[] dna);
```

Una tira de adn mutante contiene, al menos, dos secuencias de cuatro letra iguales.

## Funcionamiento y Algoritmo

### Verificación

No todas las cadenas son válidas. Se debe ingresar un arreglo de cadenas de tal forma que se forme 
una matriz cuadrada, es decir, se debe ingresar n cadenas de n letras cada una. Las letras que deben
componer cada cadena no deben ser otras que _"A", "T", "C"_ o _"G"_.

### Algoritmo

Este algoritmo, ubicado en la capa de servicios, implementa búsqueda binaria a lo largo de una matriz 
bidimensional de caracteres. Utiliza dos métodos: el primero realiza verificaciones de forma horizontal,
el segundo en diagonal. Para las verificaciones en vertical se pasa como parámetro la matriz de caracteres
transpuesta al método `horizontalVer`.

```java
private int horizontalVer(char[][] doubleCharArray, int totalSeq) { }

private int diagonalVer(char[][] doubleCharArray, int totalSeq) { }
```
En caso de encontrar una secuencia, se verifica si es necesario seguir en búsqueda de otra, o podemos retornar
directamente.

`totalSeq` se encarga de hacer un seguimiento de la cantidad total de secuencias.

### Solicitudes y Servicios

El proyecto ha sido subido a Render. Link de acceso:

https://parcialmutantsml.onrender.com


* POST `/mutant`
Este servicio recibe un _JSON_ con la matriz de ADN a verificar.
```yaml
"dna": [
  "ATGCGA",
  "CAGTGC",
  "TTATGT",
  "AGAAGG",
  "CCCCTA",
  "TCACTG"
]
```

Este servicio particular no se puede utilizar en Render debido a la falta de interfaz de usuario en
la web. Para hacer la solicitud podemos usar Postman.

https://parcialmutantsml.onrender.com/mutant


* GET `/stats`
Este servicio consulta y devuelve un _JSON_ con la cantidad de adn humanos y mutantes verificados.
```yaml
{
    "count_mutant_dna": 0,
    "count_human_dna": 0,
    "ratio": 0
}
```

https://parcialmutantsml.onrender.com/stats

### Ejemplos de Matrices de ADN

Matriz de ADN mutante:
```yaml
"dna": [
  "ATGCG",
  "CAGTG",
  "TTATG",
  "AGAAG",
  "CCCCT",
  "TCACT"
]
```

Matriz de ADN humano:
```yaml
"dna": [
  "GTGCG",
  "CAGTG",
  "TTATG",
  "AGAAG",
  "CTCCT",
  "TCACT"
]
```

### Testing

En la carpeta _parcialMutantsML\src\test_ se incluyen casos de pruebas unitarias.