# Practica Resuelta | Gestion de Biblioteca

## Objetivo

Desarrollar una aplicacion en Java que permita gestionar una lista de libros usando:

- una clase propia llamada `Libro`,
- un `ArrayList<Libro>` para almacenar los objetos,
- un menu repetitivo para interactuar con el usuario.

Al final de la practica, el alumno debe entender:

- como se crea una clase con atributos y metodos,
- como se guardan objetos en una coleccion,
- como se buscan elementos en un `ArrayList`,
- como se modifica el estado de un objeto segun la opcion elegida.

---

## Plan

1. Crear la clase `Libro` con sus atributos privados.
2. Hacer un constructor que deje el libro disponible por defecto.
3. Crear getters para acceder a los datos sin romper la encapsulacion.
4. Sobrescribir `toString()` para mostrar la informacion del libro.
5. Crear un `ArrayList<Libro>` en el programa principal.
6. Construir un menu repetitivo con varias opciones.
7. Implementar el alta de libros en la biblioteca.
8. Implementar la busqueda de libros por titulo.
9. Programar las operaciones de prestar, devolver y filtrar disponibles.
10. Probar el programa con varios casos, incluidos errores y situaciones limite.

---

## Codigo o diff por archivos

### Archivo principal

[UT5_EjercicioResuelto_GestionBiblioteca.java](C:/Users/MediaMarktVillaverde/Desktop/Clases%20particulares/Clases%20Maria%201%20Dam/UT5_EjercicioResuelto_GestionBiblioteca.java)

### Que hace

Este archivo contiene:

- la clase principal con el menu,
- todos los metodos de gestion de la biblioteca,
- la clase `Libro` al final del archivo.

### Por que se implementa asi

Porque para una practica docente es muy util que el alumno vea en un solo archivo:

- la clase que representa el objeto,
- la lista que guarda los objetos,
- y la logica que opera sobre ellos.

### Caso limite importante

Si no hay libros en la biblioteca, el programa no falla: muestra mensajes informativos en lugar de intentar recorrer una lista vacia sin sentido.

---

## Modelado del problema

### Clase `Libro`

Cada libro tiene tres datos:

```java
private String titulo;
private String autor;
private boolean disponible;
```

### Significado de cada atributo

- `titulo`: nombre del libro.
- `autor`: nombre del autor.
- `disponible`: indica si el libro se puede prestar o no.

### Por que `disponible` es `boolean`

Porque solo hay dos estados posibles en este ejercicio:

- `true` -> disponible
- `false` -> prestado

### Caso limite

Si mas adelante el ejercicio creciera y hubiera mas estados como "reservado" o "perdido", entonces un `boolean` ya no seria suficiente y convendria otro enfoque.

---

## Explicacion guiada por bloques

### 1. Menu repetitivo

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
            mostrarTodosLosLibros(biblioteca);
            break;
        case 3:
            prestarLibro(sc, biblioteca);
            break;
        case 4:
            devolverLibro(sc, biblioteca);
            break;
        case 5:
            mostrarLibrosDisponibles(biblioteca);
            break;
        case 6:
            System.out.println("Saliendo del programa...");
            break;
        default:
            System.out.println("Opcion no valida. Debes elegir un numero del 1 al 6.");
    }
} while (opcion != 6);
```

#### Que hace

Permite que el usuario siga usando la aplicacion hasta elegir la opcion de salir.

#### Por que se implementa asi

Porque un menu es una forma natural de practicar:

- bucles,
- metodos,
- y separacion de responsabilidades.

#### Caso limite

Si el usuario introduce una opcion fuera del rango, el `default` informa del error.

#### Tarea para el alumno relacionada

Anadir una opcion para mostrar solo los libros prestados.

---

### 2. Lectura de la opcion del menu

Fragmento importante:

```java
public static int leerOpcionMenu(Scanner sc) {
    String linea = sc.nextLine();

    try {
        return Integer.parseInt(linea);
    } catch (NumberFormatException e) {
        return -1;
    }
}
```

#### Que hace

Lee la opcion del menu como texto y luego intenta convertirla a entero.

#### Por que se implementa asi

Porque evita problemas tipicos de mezclar `nextInt()` con `nextLine()`.

#### Caso limite

Si el usuario escribe `hola`, el programa no se rompe: devuelve `-1` y el `switch` lo trata como opcion no valida.

#### Tarea para el alumno relacionada

Mejorar la validacion para repetir la pregunta hasta que el usuario introduzca un numero correcto.

---

### 3. Crear y guardar un libro

Fragmento importante:

```java
public static void agregarLibro(Scanner sc, ArrayList<Libro> biblioteca) {
    System.out.print("Introduce el titulo del libro: ");
    String titulo = sc.nextLine().trim();

    System.out.print("Introduce el autor del libro: ");
    String autor = sc.nextLine().trim();

    if (titulo.isEmpty() || autor.isEmpty()) {
        System.out.println("Error: el titulo y el autor son obligatorios.");
        return;
    }

    Libro nuevoLibro = new Libro(titulo, autor);
    biblioteca.add(nuevoLibro);
    System.out.println("Libro agregado correctamente.");
}
```

#### Que hace

Pide titulo y autor, crea un objeto `Libro` y lo anade al `ArrayList`.

#### Por que se implementa asi

Porque asi se ve claramente el proceso completo:

1. pedir datos,
2. crear objeto,
3. guardar objeto.

#### Caso limite

Si el titulo o el autor estan vacios, el libro no se crea.

#### Tarea para el alumno relacionada

Evitar que se agreguen libros repetidos.

---

### 4. Mostrar todos los libros

Fragmento importante:

```java
public static void mostrarTodosLosLibros(ArrayList<Libro> biblioteca) {
    if (biblioteca.isEmpty()) {
        System.out.println("No hay libros registrados en la biblioteca.");
        return;
    }

    for (int i = 0; i < biblioteca.size(); i++) {
        System.out.println((i + 1) + ". " + biblioteca.get(i));
    }
}
```

#### Que hace

Recorre la lista y muestra todos los libros.

#### Por que se implementa asi

Porque recorrer una coleccion de objetos es una habilidad basica en Java.

#### Caso limite

Si la lista esta vacia, se muestra un mensaje y no se intenta recorrer nada.

#### Tarea para el alumno relacionada

Mostrar al principio cuantos libros hay en total.

---

### 5. Buscar un libro por titulo

Fragmento importante:

```java
public static Libro buscarLibroPorTitulo(ArrayList<Libro> biblioteca, String tituloBuscado) {
    for (int i = 0; i < biblioteca.size(); i++) {
        Libro libroActual = biblioteca.get(i);

        if (libroActual.getTitulo().equalsIgnoreCase(tituloBuscado.trim())) {
            return libroActual;
        }
    }

    return null;
}
```

#### Que hace

Busca un libro concreto comparando el titulo introducido con el titulo de cada objeto.

#### Por que se implementa asi

Porque prestar y devolver necesitan localizar antes el objeto correcto.

#### Caso limite

Si el libro no existe, el metodo devuelve `null`.

#### Tarea para el alumno relacionada

Crear una busqueda por autor que pueda devolver varios libros.

---

### 6. Prestar un libro

Fragmento importante:

```java
public static void prestarLibro(Scanner sc, ArrayList<Libro> biblioteca) {
    System.out.print("Introduce el titulo del libro a prestar: ");
    String titulo = sc.nextLine();

    Libro libro = buscarLibroPorTitulo(biblioteca, titulo);

    if (libro == null) {
        System.out.println("El libro no existe en la biblioteca.");
    } else if (!libro.isDisponible()) {
        System.out.println("El libro ya esta prestado.");
    } else {
        libro.prestar();
        System.out.println("Libro prestado correctamente.");
    }
}
```

#### Que hace

Localiza el libro y cambia su estado a prestado si estaba disponible.

#### Por que se implementa asi

Porque la logica debe comprobar dos cosas antes de actuar:

- si el libro existe,
- y si realmente puede prestarse.

#### Caso limite

Si el libro ya estaba prestado, el estado no se vuelve a cambiar y se informa al usuario.

#### Tarea para el alumno relacionada

Mejorar el mensaje final mostrando tambien el autor del libro prestado.

---

### 7. Devolver un libro

Fragmento importante:

```java
public static void devolverLibro(Scanner sc, ArrayList<Libro> biblioteca) {
    System.out.print("Introduce el titulo del libro a devolver: ");
    String titulo = sc.nextLine();

    Libro libro = buscarLibroPorTitulo(biblioteca, titulo);

    if (libro == null) {
        System.out.println("El libro no existe en la biblioteca.");
    } else if (libro.isDisponible()) {
        System.out.println("El libro ya estaba disponible.");
    } else {
        libro.devolver();
        System.out.println("Libro devuelto correctamente.");
    }
}
```

#### Que hace

Busca el libro y lo marca como disponible si estaba prestado.

#### Por que se implementa asi

Porque es el proceso inverso al prestamo y ayuda a comparar dos metodos muy parecidos.

#### Caso limite

Si el libro ya estaba disponible, se informa y no se cambia nada.

#### Tarea para el alumno relacionada

Refactorizar `prestarLibro` y `devolverLibro` para detectar codigo repetido.

---

### 8. Filtrar libros disponibles

Fragmento importante:

```java
public static void mostrarLibrosDisponibles(ArrayList<Libro> biblioteca) {
    boolean hayDisponibles = false;

    for (int i = 0; i < biblioteca.size(); i++) {
        Libro libroActual = biblioteca.get(i);

        if (libroActual.isDisponible()) {
            System.out.println("- " + libroActual);
            hayDisponibles = true;
        }
    }

    if (!hayDisponibles) {
        System.out.println("No hay libros disponibles en este momento.");
    }
}
```

#### Que hace

Recorre la lista y muestra solo los libros cuyo estado es disponible.

#### Por que se implementa asi

Porque filtrar por una condicion concreta es una operacion muy comun con colecciones.

#### Caso limite

Si no hay ninguno disponible, se muestra un mensaje claro.

#### Tarea para el alumno relacionada

Crear `mostrarLibrosPrestados(...)`.

---

## Explicacion de la clase `Libro`

### Constructor

Fragmento importante:

```java
public Libro(String titulo, String autor) {
    this.titulo = titulo;
    this.autor = autor;
    this.disponible = true;
}
```

#### Que hace

Inicializa el titulo, el autor y deja el libro disponible por defecto.

#### Por que se implementa asi

Porque el enunciado dice que al agregar un libro debe estar disponible inicialmente.

#### Caso limite

Si el constructor no pusiera `disponible = true`, el comportamiento del programa quedaria menos claro para el alumno.

---

### Getters

Fragmento importante:

```java
public String getTitulo() {
    return titulo;
}

public String getAutor() {
    return autor;
}

public boolean isDisponible() {
    return disponible;
}
```

#### Que hacen

Permiten leer los datos del objeto desde fuera de la clase.

#### Por que se implementan asi

Porque los atributos son privados y debemos respetar la encapsulacion.

#### Caso limite

Si los atributos fueran publicos, cualquier parte del programa podria modificarlos sin control.

---

### Metodos `prestar()` y `devolver()`

Fragmento importante:

```java
public void prestar() {
    disponible = false;
}

public void devolver() {
    disponible = true;
}
```

#### Que hacen

Cambian el estado interno del objeto.

#### Por que se implementan asi

Porque el propio objeto debe saber cambiar su estado sin depender de accesos directos al atributo.

#### Caso limite

Aunque el cambio sea simple, encapsularlo en metodos deja el diseno mas limpio.

---

### `toString()`

Fragmento importante:

```java
@Override
public String toString() {
    String estado = disponible ? "Disponible" : "Prestado";
    return "Titulo: " + titulo + " | Autor: " + autor + " | Estado: " + estado;
}
```

#### Que hace

Devuelve una representacion legible del libro.

#### Por que se implementa asi

Porque mostrar objetos con un `toString()` claro mejora mucho la salida del programa.

#### Caso limite

Sin sobrescribir `toString()`, Java mostraria algo como `Libro@1a2b3c`, que no sirve al usuario.

---

## Como probar

### Compilar

```bash
javac UT5_EjercicioResuelto_GestionBiblioteca.java
```

### Ejecutar

```bash
java UT5_EjercicioResuelto_GestionBiblioteca
```

### Ejemplo de uso 1

Entrada aproximada:

```text
1
Don Quijote
Miguel de Cervantes
1
La sombra del viento
Carlos Ruiz Zafon
2
6
```

Salida esperada aproximada:

```text
LISTADO COMPLETO DE LIBROS
1. Titulo: Don Quijote | Autor: Miguel de Cervantes | Estado: Disponible
2. Titulo: La sombra del viento | Autor: Carlos Ruiz Zafon | Estado: Disponible
```

### Ejemplo de uso 2

Entrada aproximada:

```text
1
1984
George Orwell
3
1984
2
6
```

Salida esperada aproximada:

```text
Libro prestado correctamente.
LISTADO COMPLETO DE LIBROS
1. Titulo: 1984 | Autor: George Orwell | Estado: Prestado
```

### Ejemplo de uso 3

Entrada aproximada:

```text
5
6
```

Salida esperada aproximada:

```text
LIBROS DISPONIBLES
No hay libros disponibles en este momento.
```

---

## Errores tipicos del alumno

- Crear la lista dentro del bucle del menu.
- Usar `==` para comparar titulos en lugar de `equals` o `equalsIgnoreCase`.
- No controlar si la biblioteca esta vacia.
- Intentar prestar un libro ya prestado.
- Intentar devolver un libro que ya estaba disponible.
- No sobrescribir `toString()` y mostrar referencias poco utiles.
- Mezclar `nextInt()` y `nextLine()` sin controlar el salto de linea.

---

## Ejercicios extra

1. Crear una opcion para mostrar solo los libros prestados.
2. Impedir libros duplicados por titulo o por titulo y autor.
3. Crear una busqueda por autor que devuelva varios resultados.
4. Mostrar el numero total de libros registrados.
5. Crear un metodo `estaPrestado()` dentro de `Libro`.
6. Guardar tambien el anio de publicacion del libro.

---

## Checklist final

- [x] Clase `Libro` creada con atributos privados.
- [x] Constructor parametrizado con `disponible = true`.
- [x] Getters implementados.
- [x] Metodos `prestar()` y `devolver()` implementados.
- [x] `toString()` sobrescrito.
- [x] Uso de `ArrayList<Libro>`.
- [x] Menu repetitivo implementado.
- [x] Alta, listado, prestamo, devolucion y filtro de disponibles.
- [x] Markdown alineado con el Java y con fragmentos reales de codigo.
- [ ] Si quieres, el siguiente paso puede ser hacer una version mas avanzada con borrar libro y buscar por autor.
