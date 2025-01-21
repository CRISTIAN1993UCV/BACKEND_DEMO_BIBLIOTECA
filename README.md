# Microservicio Biblioteca

Este microservicio permite gestionar operaciones relacionadas con autores, libros y préstamos. Está configurado para funcionar tanto con una base de datos Oracle como con H2 (ideal para pruebas locales y rápidas). Además, se ha integrado Swagger UI y OpenAPI para documentar y probar la API de manera interactiva.

> **Nota Técnica:**  
> Este proyecto utiliza **Java 21 (JDK 21)**, por lo que asegúrese de tenerlo instalado en su entorno de desarrollo.

## Tabla de Contenidos

- [Requisitos Previos](#requisitos-previos)
- [Datos Data.sql](#datos-datasql)
- [Configuración del Microservicio](#configuración-del-microservicio)
    - [Puerto y Nombre de la Aplicación](#puerto-y-nombre-de-la-aplicación)
    - [Configuración para Oracle](#configuración-para-oracle)
    - [Configuración para H2 (Pruebas Locales)](#configuración-para-h2-pruebas-locales)
    - [Inicialización de la Base de Datos](#inicialización-de-la-base-de-datos)
  - [Documentación con Swagger](#documentación-con-swagger)


## Requisitos Previos

- **Java 21 (JDK 21)**.
- **Maven** para la gestión del proyecto.
- Base de datos **Oracle** o **H2** (según el perfil que se desee utilizar).
- Contenedor Docker para la base de datos Oracle. Tenga en cuenta que en Docker el puerto utilizado es el **1552**.

## Datos Data.sql

El proyecto está preparado para que, al iniciar la aplicación, JPA cree las tablas y se inserten los datos predefinidos desde el archivo `data.sql` ubicado en el directorio `src/main/resources`.  
Esto significa que, tanto si utiliza H2 o Oracle, las tablas se crearán y se poblarán automáticamente con los datos especificados en dicho archivo.

## Configuración del Microservicio

La configuración se realiza mediante el archivo `application.properties` ubicado en el directorio de recursos (`src/main/resources`).

## Puerto , Nombre de la Aplicación y configuracion Base datos

La aplicación se ejecutará en el puerto **9090** y se identifica como `bliblioteca`:

```properties
server.port=9090
spring.application.name=bliblioteca 

## Configuración para Oracle
Para utilizar Oracle como base de datos, descomente y ajuste los siguientes valores:

## Perfil de base de datos Oracle
spring.datasource.url=jdbc:oracle:thin:@localhost:1522:free
spring.datasource.username=system
spring.datasource.password=Ora1234
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=create-drop

## Configuración para H2 (Pruebas Locales)
Para pruebas rápidas o desarrollo local, se recomienda utilizar H2. En ese caso, comente la configuración de Oracle y active la siguiente:

# Perfil de base de datos H2 para pruebas locales
spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_ON_EXIT=FALSE;DB_CLOSE_DELAY=-1
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
# Permite que Hibernate cree las tablas
spring.jpa.hibernate.ddl-auto=create-drop

## Documentación con Swagger
Swagger se ha configurado para generar una documentación interactiva de la API y facilitar la prueba de los endpoints. La configuración en application.properties es la siguiente:

# Habilitar Swagger UI y OpenAPI
springdoc.api-docs.enabled=true
springdoc.swagger-ui.enabled=true
springdoc.swagger-ui.path=/doc/swagger-ui.html

springdoc.paths-to-match=/autor/**, /libro/**, /prestamo/**

## Para acceder a Swagger:
Ejecute el microservicio de Spring Boot. Con la configuración actual, la aplicación se ejecuta en el puerto 9090.
Abra su navegador web y visite la siguiente URL:
http://localhost:9090/doc/swagger-ui.html

-- Desde esta interfaz podrá:

Explorar la documentación de la API, en donde se listan y describen todos los endpoints disponibles, tales como /autor, /libro y /prestamo.
Probar los endpoints de manera interactiva, ingresando parámetros y viendo en tiempo real las respuestas de la API.
Revisar los modelos, esquemas y detalles de cada operación documentada. 
