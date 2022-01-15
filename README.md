## Code documentation

Este proyecto de spring fue realizado por Jose Miguel Torrealba
el dia 1/15/2022


Se trato de trabajar un codigo limpio siguiendo las mejores practicas y se tuvo en cuenta
la funcionabilidad y escabilidad, por sobre el codigo muy complejo y breve de implementar.

Lo que dio como resultado un codigo que considero junto a los comentarios que deje
siguiendo la implementacion del estandar "Javadoc" sera facil para cualquier programador
entender su funcionamiento.

El proyecto puede iniciarse como un proyecto simple de springboot y empezar a usar sus funciones la ruta principal para las api es:

   -/sondeos/api/challenge/v1/libros

"para consultar especificamente los enpoint y su funcionamiento se debe revisar el archivo que se nombra acontinuacion del controlador"



La principal capa para ver desde donde se puede ver la cascada de la logica que siguen
los servicios dados por el sistema se pueden encontrar en

1. Libros Controler
   - /controller/libro/LibroControllerImpl.java
2. Libros Service
   - /service/libros/LibrosServiceImpl.java

## Estructura del proyecto

La estructura que sigue el proyecto se hizo siguiendo un Repository Pattern

y se divide en las siguientes capaz de logicas

3. Controlador
   - Para manejar las peticiones rest que lleguen al sistemas
2. Service
   - Para manejar la logica que sucede entre la llegada de la peticion y su interaccion con la BD
3. Repository
   - Clase que interactua con la base de datos y sus peticiones
4. Entity
   - Clases que son las representaciones de las tablas en la base de datos
5. Reusable convertion currency system not tied only to taxes.

## Comentarios adicionales

Se agrego una base de datos embebida en memoria del tipo H2 para persistir los datos de las pruebas



## Demo

Los siguientes datos se encuentran agregados de manera automatica al sistema para realizar pruebas

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c1",
        "titulo": "Las aventuras de Sherlock Holmes",
        "autor": "Doyle, Arthur Conan",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c2",
        "titulo": "El Señor de los Anillos",
        "autor": "J. R. R. Tolkien",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c3",
        "titulo": "Harry Potter y la piedra filosofal ",
        "autor": "J. K. Rowling",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"
 
        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c4",
        "titulo": "Yo antes de ti",
        "autor": "Jojo Moyes",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c5",
        "titulo": "El psicoanalista",
        "autor": " John Katzenbach",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c6",
        "titulo": "Los miserables",
        "autor": "Victor Hugo",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"

        "id": "5a73d405-c69f-4e2c-8516-d5770c6bcf2c7",
        "titulo": "Don Quijote de la Mancha",
        "autor": "Miguel de Cervantes Saavedra ",
        "precio": 100.0,
        "fechaLanzamiento": "2020-05-28T04:00:00.000+00:00"
    
