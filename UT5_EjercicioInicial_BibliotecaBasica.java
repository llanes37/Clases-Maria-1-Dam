/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - MATERIAL DIDACTICO
 *  EJERCICIO INICIAL RESUELTO: BIBLIOTECA BASICA
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  ENUNCIADO
 * ==========================================================================================
 *  Se desea desarrollar una aplicacion en Java para gestionar una pequena biblioteca.
 *
 *  De cada libro se almacenara:
 *  - Titulo
 *  - Autor
 *  - Estado (disponible o prestado)
 *
 *  El programa usara un ArrayList para guardar objetos de tipo Libro y mostrara un menu con
 *  las siguientes opciones:
 *  1. Agregar libro
 *  2. Mostrar todos los libros
 *  3. Prestar libro
 *  4. Devolver libro
 *  5. Salir
 *
 *  Clase Libro:
 *  - Atributos privados: titulo, autor, disponible
 *  - Constructor parametrizado
 *  - Getters
 *  - Metodos prestar() y devolver()
 *  - Metodo toString() sobrescrito
 * ==========================================================================================
 */

import java.util.ArrayList;
import java.util.Scanner;

public class UT5_EjercicioInicial_BibliotecaBasica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<LibroBasico> biblioteca = new ArrayList<>();
        int opcion;

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

            System.out.println();
        } while (opcion != 5);

        sc.close();
    }

    // * TEORIA: Menu principal
    // ? Un menu permite elegir acciones distintas dentro del mismo programa.
    public static void mostrarMenu() {
        System.out.println("===== BIBLIOTECA BASICA =====");
        System.out.println("1. Agregar libro");
        System.out.println("2. Mostrar todos los libros");
        System.out.println("3. Prestar libro");
        System.out.println("4. Devolver libro");
        System.out.println("5. Salir");
        System.out.print("Elige una opcion: ");
    }

    // * TEORIA: Leer numeros desde teclado
    // ? Este metodo evita que el programa se rompa si el usuario no escribe un numero.
    public static int leerOpcionMenu(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // * TEORIA: Crear objetos y guardarlos en un ArrayList
    // ? Cada vez que agregamos un libro, creamos un objeto nuevo y lo guardamos en la lista.
    public static void agregarLibro(Scanner sc, ArrayList<LibroBasico> biblioteca) {
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();

        System.out.print("Autor: ");
        String autor = sc.nextLine();

        // !IMPORTANT: El libro se crea disponible por defecto dentro del constructor.
        // *INFO: Asi el alta de libros es mas simple y no hace falta pedir ese dato al usuario.
        // *INFO: Un error tipico es olvidar inicializar el estado y no saber si el libro esta disponible.
        LibroBasico libro = new LibroBasico(titulo, autor);
        biblioteca.add(libro);
        System.out.println("Libro agregado correctamente.");
    }

    // * TEORIA: Recorrer una lista de objetos
    // ? Para mostrar los libros, recorremos todo el ArrayList y usamos `toString()`.
    public static void mostrarLibros(ArrayList<LibroBasico> biblioteca) {
        if (biblioteca.isEmpty()) {
            System.out.println("No hay libros guardados.");
            return;
        }

        for (int i = 0; i < biblioteca.size(); i++) {
            System.out.println((i + 1) + ". " + biblioteca.get(i));
        }
    }

    // * TEORIA: Buscar un objeto por un dato
    // ? Para prestar o devolver, primero necesitamos localizar el libro por su titulo.
    public static LibroBasico buscarLibro(ArrayList<LibroBasico> biblioteca, String tituloBuscado) {
        for (int i = 0; i < biblioteca.size(); i++) {
            if (biblioteca.get(i).getTitulo().equalsIgnoreCase(tituloBuscado)) {
                return biblioteca.get(i);
            }
        }

        return null;
    }

    // * TEORIA: Cambiar el estado del objeto
    // ? Si el libro existe y esta disponible, se puede prestar.
    public static void prestarLibro(Scanner sc, ArrayList<LibroBasico> biblioteca) {
        System.out.print("Titulo del libro a prestar: ");
        String titulo = sc.nextLine();

        LibroBasico libro = buscarLibro(biblioteca, titulo);

        if (libro == null) {
            System.out.println("El libro no existe.");
        } else if (!libro.isDisponible()) {
            System.out.println("El libro ya estaba prestado.");
        } else {
            libro.prestar();
            System.out.println("Libro prestado correctamente.");
        }
    }

    // * TEORIA: Reutilizar logica parecida
    // ? Devolver un libro es parecido a prestar, pero con la condicion al reves.
    public static void devolverLibro(Scanner sc, ArrayList<LibroBasico> biblioteca) {
        System.out.print("Titulo del libro a devolver: ");
        String titulo = sc.nextLine();

        LibroBasico libro = buscarLibro(biblioteca, titulo);

        if (libro == null) {
            System.out.println("El libro no existe.");
        } else if (libro.isDisponible()) {
            System.out.println("El libro ya estaba disponible.");
        } else {
            libro.devolver();
            System.out.println("Libro devuelto correctamente.");
        }
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea una opcion nueva para mostrar solo los libros disponibles.
    // *INFO: La idea es recorrer la lista y mostrar solo los que tengan `disponible = true`.
    // ?QUESTION: En que parte del menu tendrias que hacer el cambio para anadir esa opcion?

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Modifica el programa para impedir libros repetidos por titulo.
    // *INFO: Antes de hacer `add`, deberias buscar si ya existe un libro con ese mismo titulo.
    // ?QUESTION: Te basta comparar solo el titulo o compararias tambien el autor?

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea un metodo `mostrarLibrosPrestados` usando la misma idea que los disponibles.
    // *INFO: Asi practicas filtros con ArrayList y booleanos de estado.
    // ?QUESTION: Que condicion deberias usar para detectar si un libro esta prestado?
}

class LibroBasico {
    private String titulo;
    private String autor;
    private boolean disponible;

    public LibroBasico(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void prestar() {
        disponible = false;
    }

    public void devolver() {
        disponible = true;
    }

    @Override
    public String toString() {
        String estado = disponible ? "Disponible" : "Prestado";
        return "Titulo: " + titulo + " | Autor: " + autor + " | Estado: " + estado;
    }
}
