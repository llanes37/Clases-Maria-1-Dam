# Ejercicio Inicial Resuelto | Biblioteca Basica

## Objetivo

Empezar a trabajar con:

- clases y objetos,
- `ArrayList`,
- busquedas simples,
- y menús en Java.

Esta practica es una version mas sencilla que la de gestion de biblioteca completa. La idea es que el alumno entienda primero lo basico y luego pueda pasar a una version mas grande.

---

## Plan

1. Leer el enunciado.
2. Crear la clase `LibroBasico`.
3. Definir sus atributos y su constructor.
4. Crear un `ArrayList<LibroBasico>`.
5. Mostrar un menu repetitivo.
6. Agregar libros a la lista.
7. Mostrar todos los libros.
8. Buscar un libro por titulo.
9. Prestar o devolver el libro encontrado.
10. Proponer mejoras como tarea del alumno.

---

## Codigo o diff por archivos

### Archivo principal

[UT5_EjercicioInicial_BibliotecaBasica.java](C:/Users/MediaMarktVillaverde/Desktop/Clases%20particulares/Clases%20Maria%201%20Dam/UT5_EjercicioInicial_BibliotecaBasica.java)

### Que hace

Este archivo incluye:

- el enunciado al principio,
- una version mas simple del sistema de biblioteca,
- la clase `LibroBasico`,
- tareas del alumno al final, al estilo de `UT4_Funciones.java`.

### Por que se implementa asi

Porque esta version busca ser mas amigable para empezar:

- menos opciones,
- menos teoria repartida,
- y una estructura facil de seguir.

### Caso limite importante

Si el usuario escribe una opcion no valida en el menu, el programa no se rompe y muestra un mensaje de error.

---

## Explicacion de la idea principal

La clave de este ejercicio es entender que:

- una clase representa un tipo de dato propio,
- un objeto es una instancia real de esa clase,
- un `ArrayList` permite guardar muchos objetos del mismo tipo.

En este caso:

```java
ArrayList<LibroBasico> biblioteca = new ArrayList<>();
```

Eso significa que `biblioteca` guardara muchos objetos `LibroBasico`.

---

## Explicacion guiada por bloques

### 1. Clase `LibroBasico`

Fragmento importante:

```java
class LibroBasico {
    private String titulo;
    private String autor;
    private boolean disponible;
}
```

#### Que hace

Define la estructura de cada libro.

#### Por que se implementa asi

Porque cada libro necesita guardar sus propios datos.

#### Caso limite

Si los atributos no fueran `private`, se perderia la encapsulacion.

---

### 2. Constructor

Fragmento importante:

```java
public LibroBasico(String titulo, String autor) {
    this.titulo = titulo;
    this.autor = autor;
    this.disponible = true;
}
```

#### Que hace

Crea un libro nuevo y lo deja disponible por defecto.

#### Por que se implementa asi

Porque el enunciado dice que al agregar un libro debe estar disponible inicialmente.

#### Caso limite

Si no se inicializa `disponible`, el comportamiento queda menos claro para el alumno.

---

### 3. Menu principal

Fragmento importante:

```java
do {
    mostrarMenu();
    opcion = leerOpcionMenu(sc);

    switch (opcion) {
        case 1:
            agregarLibro(sc, biblioteca);
            break;
        case 2:
            mostrarLibros(biblioteca);
            break;
        case 3:
            prestarLibro(sc, biblioteca);
            break;
        case 4:
            devolverLibro(sc, biblioteca);
            break;
        case 5:
            System.out.println("Fin del programa.");
            break;
        default:
            System.out.println("Opcion no valida.");
    }
} while (opcion != 5);
```

#### Que hace

Permite repetir acciones hasta que el usuario decida salir.

#### Por que se implementa asi

Porque un menu repetitivo es la forma mas clara de organizar un programa interactivo.

#### Caso limite

Si el usuario mete una opcion fuera de rango, entra en `default`.

---

### 4. Agregar libro

Fragmento importante:

```java
LibroBasico libro = new LibroBasico(titulo, autor);
biblioteca.add(libro);
```

#### Que hace

Crea el objeto y lo guarda en el `ArrayList`.

#### Por que se implementa asi

Porque el alumno ve de forma directa la relacion entre clase, objeto y lista.

#### Caso limite

Si luego quieres evitar duplicados, antes del `add` tendrias que buscar si el titulo ya existe.

---

### 5. Buscar por titulo

Fragmento importante:

```java
public static LibroBasico buscarLibro(ArrayList<LibroBasico> biblioteca, String tituloBuscado) {
    for (int i = 0; i < biblioteca.size(); i++) {
        if (biblioteca.get(i).getTitulo().equalsIgnoreCase(tituloBuscado)) {
            return biblioteca.get(i);
        }
    }

    return null;
}
```

#### Que hace

Recorre la lista hasta encontrar el libro con ese titulo.

#### Por que se implementa asi

Porque prestar y devolver necesitan localizar primero el objeto correcto.

#### Caso limite

Si no encuentra el libro, devuelve `null`.

---

### 6. Prestar y devolver

Fragmento importante:

```java
if (libro == null) {
    System.out.println("El libro no existe.");
} else if (!libro.isDisponible()) {
    System.out.println("El libro ya estaba prestado.");
} else {
    libro.prestar();
    System.out.println("Libro prestado correctamente.");
}
```

#### Que hace

Comprueba si el libro existe y si su estado permite hacer la operacion.

#### Por que se implementa asi

Porque no basta con encontrar el libro: tambien hay que revisar su estado.

#### Caso limite

Si el libro ya estaba prestado o ya estaba disponible, el programa informa y no cambia nada.

---

## Como probar

### Compilar

```bash
javac UT5_EjercicioInicial_BibliotecaBasica.java
```

### Ejecutar

```bash
java UT5_EjercicioInicial_BibliotecaBasica
```

### Ejemplo de uso

Entrada aproximada:

```text
1
El Principito
Antoine de Saint-Exupery
1
1984
George Orwell
2
3
1984
2
5
```

### Salida esperada aproximada

```text
1. Titulo: El Principito | Autor: Antoine de Saint-Exupery | Estado: Disponible
2. Titulo: 1984 | Autor: George Orwell | Estado: Disponible

Libro prestado correctamente.

1. Titulo: El Principito | Autor: Antoine de Saint-Exupery | Estado: Disponible
2. Titulo: 1984 | Autor: George Orwell | Estado: Prestado
```

---

## Errores tipicos

- Pensar que un `ArrayList<LibroBasico>` guarda texto en lugar de objetos.
- Usar `==` para comparar titulos.
- No comprobar si el libro existe antes de prestarlo.
- No diferenciar entre disponible y prestado.
- No sobrescribir `toString()`.

---

## Checklist final

- [x] Ejercicio mas simple que la biblioteca completa.
- [x] Enunciado colocado al principio del `.java`.
- [x] Uso de `ArrayList` con objetos.
- [x] Clase `LibroBasico` creada.
- [x] Menu repetitivo funcionando.
- [x] Prestamo y devolucion implementados.
- [x] Tareas del alumno colocadas al final, como en `UT4_Funciones.java`.
- [x] Markdown alineado con esta version sencilla.
- [ ] Si quieres, el siguiente paso es hacer una version intermedia entre esta y la biblioteca completa.
