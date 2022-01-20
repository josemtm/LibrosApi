## Code documentation

Este proyecto de spring fue realizado por Jose Miguel Torrealba
el dia 1/15/2022

# v1.2 1/20/2022

cambios realizados en version:

1. GET de libros por id se recibe ahora por url con el siguiente contexto ?id=.
2. Se agrego que el autor y el libro en combinacion no puedan estar repetidos en la BD.
3. Se agrego validacion en entidad libro no pueda ser nula.
4. Bad request en los campos faltantes de put y post.
5. Las solicitudes GET de libro ahora devuelven el estado OK.
6. se elimino la peticion Get que encontraba todos los registros del sistema.
7. se refactorizo el controlador para no implementar la interfaz crud original y se movio la logica a su clase.
8. Se agrego la libreria Swagger para la documentacion de la api rest y se agregaron sus anotaciones pertienentes en la clase controlador.
9. Se agrego un paginador para la peticion GET
10. Se implemento un filtro para los libros.
11. se actualizo la documentacion.


12. se implementaron todos los cambios que pude anotar en nuestra conversacion fueron muy interesantes a nivel de conocimiento me llevo algo bueno de ellos, pido disculpas adelantadas si alguna se me paso espero sea de su AGRADO!!!


## Requisitos


- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)



## Sobre el proyecto

Este proyecto realiza funciones de un crud pensado para gestionar peticiones a una base de datos que mantenga datos
sobre sus libros,precios autores y publicaciones.

Se trato de trabajar un codigo limpio siguiendo las mejores practicas y se tuvo en cuenta
la funcionabilidad y escabilidad, por sobre el codigo muy complejo y breve de implementar.

Lo que dio como resultado un codigo que se considera junto a los comentarios que se dejaron
siguiendo la implementacion del estandar "Javadoc" sera facil para cualquier programador
entender su funcionamiento.

## Correr la aplicacion localmente


El proyecto puede iniciarse como un proyecto simple de springboot y empezar a usar sus funciones pues como se implemento una base de datos embebida h2 no hay mayor necesidad de configuracion.

para iniciar el proyecto se puede correr en la mayoria de los idle mas populares solamente dando un inicio normal a la clase que se encuentra CodechallengeApplication.java en la siguiente locacion src\main\java\com\sondeos\codechallenge\CodechallengeApplication.java el proyecto levantara de manera local por lo que su url base para realizar peticiones estara apuntando a http://localhost:8080 pues corre por defecto en el puerto 8080

si se necesita revisar la BD embebida de la aplicacion se puede consultar en el siguiente path 

   - /h2-console/
   - ex: http://localhost:8080/h2-console/

 pues se dejo la consola de la BD encendida para sus verficaciones y los datos para el login son:

   - username: sa
   - password: password



## Estructura del proyecto

El proyecto es un proyecto simple de spring realizado con la ultima version estable para la fecha de ejecucion del challenge las librerias mas importantes para el proyecto son

1. spring-boot-starter-data-jpa
2. spring-boot-starter-data-rest
3. spring-boot-starter-validation
4. spring-boot-starter-web
5. h2
6. lombok
7. spring-search

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

## Peticiones

se implemento swagger para generar la documentacion de las peticiones al crud luego de levantar el proyecto se puede solicitar la informacion detallada de los endpoint en la siguiente url en el apartado de libro-controller-impl

   - /swagger-ui
   - ex: http://localhost:8080/swagger-ui

los dos endpoint que requieren una explicacion un poco mas extendida son los siguientes

1. get por paginador

   - url: GET sondeos/api/challenge/v1/libros/paginator?page=2&size=2

   - Al ser una aplicacion que por la naturaleza del challenge se solicito que fuera escalable y pensada en manejar muchos datos se agrego un paginador para manejar la alta concurrencia de datos que se puedan gestionar 

   - los parametros para solicitar los datos del paginador se implementaron para ser recibidos por medio de la url y es tan simple como solicitarlos de la siguiente manera:
      -  sondeos/api/challenge/v1/libros/paginator?page=X&size=Y
      - donde x es la pagina solicitada, Y el tamaño de las paginas que se solicita

2. filtro de libros
   - url: GET sondeos/api/challenge/v1/libros/search

   - Igualmente pensado que se pueden implementar gran cantidad de libros y al ser un volumen tan alto de ellos se implemento un filtro de libros bastante simple que permite ejecutar funciones en su busqueda dinamicamente haciendo uso de la biblioteca spring-sipios

   - su funcionamiento es bastante simple pues aceptar peticiones por medio de url para filtrar los datos dinamicamente

   - su retorno como resultado sera de una lista simple de libros donde cumpla con los datos a solicitar

   - la libreria acepta los siguientes parametros como busqueda en la url
      - equal : 
      - not equal operator !
      -  greater and less than operator > <
      - *
         - al principio del valor es: contiene y al final es: termina con el string que encierre
      - la libreria tiene bastante mas casos de operador para verlos al detalle consultar 
      -[Sipios spring-search](https://github.com/codecentric/springboot-sample-app/blob/master/README.md?plain=1)

   - Por ejemplo para buscar libros por autor y titulo donde las string sean similares la consulta seria esta
      - sondeos/api/challenge/v1/libros/search?search=titulo:'*XXX*' OR autor:'*XXX*' donde xxx es el titulo y autor a buscar
      - o un precio especifico sondeos/api/challenge/v1/libros/search?search=precio:'*1000*'


## Testing

Se realizaron Integration testing y Unit testing para el controlador
y los servicios respectivamente estos se encuentran en las siguientes carpeta

- src/test/java/com/sondeos/codechallenge/controller/libro/LibroControllerImplTest.java
- src/test/java/com/sondeos/codechallenge/service/libro/LibroServiceImplTest.java


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
    
