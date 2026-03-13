/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - MATERIAL DIDACTICO
 *  PRACTICA RESUELTA: GESTION DE BIBLIOTECA
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  UNIDAD 5 | CLASES, OBJETOS Y ARRAYLIST
 * ==========================================================================================
 *  En esta practica trabajamos un caso muy comun en Programacion Orientada a Objetos:
 *  - Crear una clase propia (`Libro`) con atributos y metodos.
 *  - Guardar varios objetos dentro de un `ArrayList`.
 *  - Usar un menu repetitivo para gestionar los datos.
 *
 *  Idea clave:
 *  - La clase representa el molde.
 *  - Los objetos representan los datos concretos.
 *  - El `ArrayList<Libro>` guarda todos los libros de la biblioteca.
 *
 *  Objetivo de la practica:
 *  - Agregar libros.
 *  - Mostrar todos los libros.
 *  - Prestar libros.
 *  - Devolver libros.
 *  - Mostrar solo los libros disponibles.
 *
 *  Recomendacion:
 *  - Lee primero la clase `Libro`.
 *  - Despues revisa el menu del `main`.
 *  - Finalmente observa como se recorren los libros para buscar por titulo.
 * ==========================================================================================
 */

import java.util.ArrayList;
import java.util.Scanner;

public class UT5_EjercicioResuelto_GestionBiblioteca {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Libro> biblioteca = new ArrayList<>();
        int opcion;

        // !IMPORTANT: El programa funciona con un menu repetitivo para que el usuario pueda hacer varias operaciones.
        // *INFO: `ArrayList<Libro>` guarda todos los objetos `Libro` creados durante la ejecucion.
        // *INFO: Un error tipico es crear la lista dentro del bucle y perder los datos en cada vuelta.
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

            System.out.println();
        } while (opcion != 6);

        // !IMPORTANT: El Scanner se cierra una sola vez al final para no bloquear futuras lecturas.
        // *INFO: Se comparte el mismo `Scanner` entre todos los metodos que piden datos.
        // *INFO: Un error tipico es cerrarlo dentro de un metodo auxiliar y romper el menu.
        sc.close();
    }

    // * TEORIA: Menu repetitivo
    // --------------------------------------------------------------------------------------
    // ? Un menu permite reutilizar el programa varias veces sin reiniciarlo.
    // ? Cada opcion del menu llama a un metodo distinto.
    // ? Esto ayuda a dividir el problema en tareas pequenas y faciles de entender.
    public static void mostrarMenu() {
        System.out.println("===== GESTION DE BIBLIOTECA =====");
        System.out.println("1. Agregar libro");
        System.out.println("2. Mostrar todos los libros");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Mostrar libros disponibles");
        System.out.println("6. Salir");
        System.out.print("Elige una opcion: ");
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Anade una opcion nueva al menu para mostrar unicamente los libros prestados.
    // *INFO: Asi practicas como ampliar un menu sin cambiar la estructura principal del programa.
    // ?QUESTION: Que numero de opcion le pondrias y que metodo nuevo necesitarias crear?

    // * TEORIA: Lectura segura de opciones del menu
    // --------------------------------------------------------------------------------------
    // ? Al leer por teclado, el usuario puede escribir algo incorrecto.
    // ? Para simplificar la practica, aqui leemos la linea completa y la convertimos a entero.
    // ? Si falla la conversion, devolvemos un valor no valido para que el `switch` lo controle.
    public static int leerOpcionMenu(Scanner sc) {
        String linea = sc.nextLine();

        // !IMPORTANT: `Integer.parseInt` transforma texto en entero si el contenido es correcto.
        // *INFO: Devolver `-1` en caso de error nos permite centralizar el mensaje en el `switch`.
        // *INFO: Un error tipico es usar `nextInt()` y dejar el salto de linea pendiente para la siguiente lectura.
        try {
            return Integer.parseInt(linea);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Modifica este metodo para que repita la pregunta hasta que el usuario introduzca un numero valido.
    // *INFO: Esa mejora hace el programa mas robusto y te ayuda a practicar bucles con validacion.
    // ?QUESTION: Que estructura te conviene mas aqui: `while`, `do while` o recursion?

    // * TEORIA: Crear y guardar objetos en un ArrayList
    // --------------------------------------------------------------------------------------
    // ? Agregar un libro significa pedir datos, crear un objeto y guardarlo en la lista.
    // ? Cada objeto `Libro` nace disponible por defecto gracias al constructor.
    public static void agregarLibro(Scanner sc, ArrayList<Libro> biblioteca) {
        System.out.print("Introduce el titulo del libro: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Introduce el autor del libro: ");
        String autor = sc.nextLine().trim();

        // !IMPORTANT: Validamos datos vacios para no crear objetos incompletos.
        // *INFO: Si falta titulo o autor, el libro no se guarda porque seria un registro poco util.
        // *INFO: Un error tipico es aceptar cadenas vacias y luego no poder distinguir libros bien.
        if (titulo.isEmpty() || autor.isEmpty()) {
            System.out.println("Error: el titulo y el autor son obligatorios.");
            return;
        }

        Libro nuevoLibro = new Libro(titulo, autor);
        biblioteca.add(nuevoLibro);
        System.out.println("Libro agregado correctamente.");
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Impide que se puedan agregar dos libros con el mismo titulo si quieres tratar el titulo como identificador unico.
    // *INFO: Para hacerlo, antes de anadir tendrias que buscar si ya existe un libro con ese titulo.
    // ?QUESTION: Crees que es mejor comparar solo por titulo o por titulo y autor? Justifica la decision.

    // * TEORIA: Mostrar listas de objetos
    // --------------------------------------------------------------------------------------
    // ? Recorrer un `ArrayList` es una operacion muy frecuente.
    // ? Aqui mostramos todos los libros usando el metodo `toString` de cada objeto.
    public static void mostrarTodosLosLibros(ArrayList<Libro> biblioteca) {
        if (biblioteca.isEmpty()) {
            System.out.println("No hay libros registrados en la biblioteca.");
            return;
        }

        System.out.println("LISTADO COMPLETO DE LIBROS");

        // !IMPORTANT: Recorremos la lista completa para mostrar todos los objetos almacenados.
        // *INFO: El indice `i + 1` es solo visual para que el alumno vea una numeracion mas natural.
        // *INFO: Un error tipico es imprimir la referencia del objeto sin haber sobrescrito `toString()`.
        for (int i = 0; i < biblioteca.size(); i++) {
            System.out.println((i + 1) + ". " + biblioteca.get(i));
        }
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Cambia la salida para que muestre tambien el numero total de libros al principio del listado.
    // *INFO: Esa mejora es pequena, pero ayuda a practicar el uso de `size()` en un contexto real.
    // ?QUESTION: Donde queda mejor mostrar esa informacion: antes o despues del recorrido?

    // * TEORIA: Buscar un objeto por un dato concreto
    // --------------------------------------------------------------------------------------
    // ? Para prestar o devolver un libro, primero necesitamos encontrarlo.
    // ? Esta busqueda se hace recorriendo la lista y comparando el titulo.
    public static Libro buscarLibroPorTitulo(ArrayList<Libro> biblioteca, String tituloBuscado) {
        for (int i = 0; i < biblioteca.size(); i++) {
            Libro libroActual = biblioteca.get(i);

            // !IMPORTANT: `equalsIgnoreCase` permite comparar sin importar mayusculas o minusculas.
            // *INFO: Esto hace la busqueda mas comoda para el usuario, que puede escribir el titulo con otro formato.
            // *INFO: Un error tipico es usar `==` con Strings, lo cual compara referencias y no contenido.
            if (libroActual.getTitulo().equalsIgnoreCase(tituloBuscado.trim())) {
                return libroActual;
            }
        }

        return null;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea otro metodo para buscar libros por autor y mostrar todos los que coincidan.
    // *INFO: A diferencia de este metodo, esa version podria devolver varios resultados y no solo uno.
    // ?QUESTION: En ese caso devolverias un solo `Libro` o un `ArrayList<Libro>`? Por que?

    // * TEORIA: Cambiar el estado de un libro a prestado
    // --------------------------------------------------------------------------------------
    // ? Prestar un libro consiste en buscarlo y comprobar si esta disponible.
    // ? Si se puede prestar, llamamos al metodo `prestar()` del objeto.
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

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Muestra ademas el autor del libro en el mensaje de confirmacion del prestamo.
    // *INFO: Asi practicas como reutilizar getters del objeto una vez encontrado.
    // ?QUESTION: Que informacion minima necesita el usuario para saber que el prestamo se ha hecho bien?

    // * TEORIA: Cambiar el estado de un libro a disponible
    // --------------------------------------------------------------------------------------
    // ? Devolver un libro sigue la misma logica que prestar, pero invirtiendo la condicion.
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

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Refactoriza `prestarLibro` y `devolverLibro` para reducir codigo repetido si se te ocurre una solucion comun.
    // *INFO: Este tipo de mejora ayuda a detectar duplicidad y a escribir codigo mas mantenible.
    // ?QUESTION: Que parte exacta de ambos metodos es parecida y podria reutilizarse?

    // * TEORIA: Filtrar objetos segun una condicion
    // --------------------------------------------------------------------------------------
    // ? Mostrar solo los disponibles significa recorrer la lista y quedarnos con los que cumplen una condicion.
    public static void mostrarLibrosDisponibles(ArrayList<Libro> biblioteca) {
        boolean hayDisponibles = false;

        System.out.println("LIBROS DISPONIBLES");

        for (int i = 0; i < biblioteca.size(); i++) {
            Libro libroActual = biblioteca.get(i);

            // !IMPORTANT: Solo mostramos los libros cuyo estado sea `disponible = true`.
            // *INFO: La variable `hayDisponibles` sirve para saber si se ha impreso alguno.
            // *INFO: Un error tipico es no controlar este caso y dejar al usuario sin saber si la lista estaba vacia.
            if (libroActual.isDisponible()) {
                System.out.println("- " + libroActual);
                hayDisponibles = true;
            }
        }

        if (!hayDisponibles) {
            System.out.println("No hay libros disponibles en este momento.");
        }
    }

    // !IMPORTANT: TAREA FINAL PARA EL ALUMNO:
    // *INFO: Crea un metodo `mostrarLibrosPrestados` reutilizando la misma idea de este filtro.
    // *INFO: Asi compararas dos recorridos casi iguales con condiciones opuestas.
    // ?QUESTION: Que condicion tendrias que invertir exactamente para mostrar solo los prestados?
}

// * TEORIA: Clase Libro
// ------------------------------------------------------------------------------------------
// ? Esta clase representa un libro individual de la biblioteca.
// ? Cada objeto guarda su propio titulo, autor y estado.
class Libro {
    private String titulo;
    private String autor;
    private boolean disponible;

    // !IMPORTANT: El constructor inicializa titulo y autor, y deja el libro disponible por defecto.
    // *INFO: El enunciado indica que todos los libros nuevos deben empezar disponibles.
    // *INFO: Un error tipico es olvidar inicializar `disponible` y depender del valor por defecto sin explicarlo.
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    // * TEORIA: Getters
    // --------------------------------------------------------------------------------------
    // ? Los getters permiten leer los atributos privados desde fuera de la clase.
    // ? Se usan porque los atributos estan encapsulados y no deben tocarse directamente.
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Explica con tus palabras por que aqui usamos getters y no accedemos directamente a los atributos.
    // *INFO: Esta pregunta no cambia codigo, pero refuerza un concepto basico de encapsulacion.
    // ?QUESTION: Que ventaja tiene proteger los atributos con `private`?

    // * TEORIA: Metodos que cambian el estado del objeto
    // --------------------------------------------------------------------------------------
    // ? Un libro se presta cambiando `disponible` a `false`.
    // ? Un libro se devuelve cambiando `disponible` a `true`.
    public void prestar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Intenta crear un metodo `estaPrestado()` que devuelva justo lo contrario de `isDisponible()`.
    // *INFO: Es una mejora pequena, pero ayuda a pensar como expresar mejor el estado del objeto.
    // ?QUESTION: Que devolveria ese metodo cuando `disponible` vale `false`?

    // * TEORIA: toString sobrescrito
    // --------------------------------------------------------------------------------------
    // ? `toString()` sirve para mostrar el objeto de forma legible.
    // ? Si no lo sobrescribimos, Java imprime una referencia poco util para el usuario.
    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return "Titulo: " + titulo + " | Autor: " + autor + " | Estado: " + estado;
    }
}
