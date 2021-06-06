# Spring ApiRest - Angualr Frontend & MongoDb
# Virtualizado en Docker
<br>

Se ha realizado un sistema web MVC donde se pueden dar de alta usuarios, que se pueden listar posteriormente al hacer login. Se puede utilizar en modo local (volatil) donde los datos se almacenan en las variables de sesión o en modo persistente donde se guardan en una base de datos de Mongo virtualizada con Docker. También se ha añadido la funcionalidad de borrar los usuarios que se listan.

<br>

Para la gestión de la base de datos se ha realizado un ApiRest con Spring que administra todas las llamadas y gestiona las sesiones. Para mantener los datos de session de ha utilizado HttpSession. Se ha utilizado la __BCryptPasswordEncoder__ para encriptar las contraseñas.

<br>

Con lo que respecta al frontend se ha utilizado Angular/cli@12.0.2, modelando las distintas páginas con el **RouterModule**. Para que un usuario no acceda a una página sin registrar se ha utilizado un **Guard** que verificar que la sesión es válida.

<br><br>
### Compilación y ejecución del backend
````
mvn clean install
java -jar target/backendoee-0.0.1-SNAPSHOT.jar
````
Para compilar el backend para Docker poner el valor **spring.data.mongodb.host=mongodb** en el proyecto de Spring.

### Ejecución del sistema completo mediante Docker
```
docker-compose up
```

<br><br>

# Login
<img src="/capturas/login.png" ></img>

# Alta
<img src="/capturas/alta.png" ></img>

# Lista de usuarios
<img src="/capturas/listaUsuarios.png" ></img>
