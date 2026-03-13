/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - MATERIAL DIDACTICO
 *  PRACTICA RESUELTA: SUELDOS CON TABLA DINAMICA
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  UNIDAD 8 | ARRAYS DINAMICOS Y RECORRIDOS
 * ==========================================================================================
 *  En esta practica trabajamos un caso muy comun:
 *  - Guardar datos cuando no sabemos cuantas personas van a participar.
 *  - Recorrer esos datos para calcular resultados.
 *  - Separar el problema en metodos pequenos y claros.
 *
 *  Idea clave:
 *  - Si no conocemos el tamano de antemano, una estructura dinamica como
 *    ArrayList suele ser mejor opcion que un array fijo.
 *
 *  Objetivo de la practica:
 *  - Leer sueldos hasta que el usuario escriba -1.
 *  - Mostrar los sueldos ordenados de mayor a menor.
 *  - Calcular el sueldo maximo, el minimo y la media.
 *
 *  Recomendacion:
 *  - Lee el codigo por bloques.
 *  - Ejecuta primero el programa tal como esta.
 *  - Despues intenta resolver las tareas propuestas dentro del codigo.
 * ==========================================================================================
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UT8_EjercicioResuelto_SueldosTablaDinamica {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // !IMPORTANT: El `main` coordina la practica y llama a cada metodo en orden.
        // *INFO: Separamos lectura, calculo y salida para que cada parte tenga una responsabilidad clara.
        // *INFO: Un error tipico es meter todo en `main` y luego no saber donde esta el fallo.
        ArrayList<Double> sueldos = leerSueldosHastaCentinela(sc);

        if (sueldos.isEmpty()) {
            System.out.println("No se han introducido sueldos validos. Fin del programa.");
        } else {
            ArrayList<Double> sueldosOrdenados = obtenerCopiaOrdenadaDescendente(sueldos);
            double sueldoMaximo = calcularMaximo(sueldos);
            double sueldoMinimo = calcularMinimo(sueldos);
            double mediaSueldos = calcularMedia(sueldos);

            mostrarResumen(sueldosOrdenados, sueldoMaximo, sueldoMinimo, mediaSueldos);
        }

        // !IMPORTANT: Cerramos el Scanner al final porque usamos el mismo objeto en toda la practica.
        // *INFO: Asi evitamos problemas al intentar leer despues de haber cerrado `System.in` antes de tiempo.
        // *INFO: Un error tipico es cerrar el Scanner dentro de un metodo auxiliar y bloquear futuras lecturas.
        sc.close();
    }

    // * TEORIA: Lectura con centinela y tabla dinamica
    // --------------------------------------------------------------------------------------
    // ? Cuando no conocemos cuantas entradas va a escribir el usuario, usamos `ArrayList`.
    // ? El valor `-1` funciona como centinela: no es un sueldo real, solo indica fin.
    // ? Esta tecnica es muy util en ejercicios donde el tamano cambia en tiempo de ejecucion.
    public static ArrayList<Double> leerSueldosHastaCentinela(Scanner sc) {
        ArrayList<Double> sueldos = new ArrayList<>();
        boolean finEntrada = false;

        System.out.println("ENCUESTA DE SUELDOS");
        System.out.println("Introduce cada sueldo. Escribe -1 para terminar.");

        while (!finEntrada) {
            try {
                System.out.print("Sueldo: ");
                double sueldoLeido = sc.nextDouble();

                // !IMPORTANT: Primero comprobamos el centinela para no guardarlo dentro de la lista.
                // *INFO: Solo se aceptan sueldos positivos o cero; cualquier negativo distinto de `-1` es invalido.
                // *INFO: Un error tipico es meter `-1` en la lista y estropear el minimo o la media.
                if (sueldoLeido == -1) {
                    finEntrada = true;
                } else if (sueldoLeido < 0) {
                    System.out.println("Error: el sueldo no puede ser negativo salvo -1 para terminar.");
                } else {
                    sueldos.add(sueldoLeido);
                    System.out.println("Sueldo guardado correctamente.");
                }
            } catch (InputMismatchException e) {
                // !IMPORTANT: Si el usuario escribe texto, limpiamos la entrada para evitar bucle infinito.
                // *INFO: `next()` consume el dato incorrecto y permite seguir pidiendo valores validos.
                // *INFO: Un error tipico es capturar la excepcion pero no limpiar el token erroneo.
                System.out.println("Error: debes introducir un numero valido.");
                sc.next();
            }
        }

        return sueldos;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Modifica este metodo para que tambien rechace sueldos iguales a 0 si el enunciado dijera que no son validos.
    // *INFO: Asi practicas la validacion sin tocar el resto del programa y evitas romper los calculos finales.
    // ?QUESTION: Que tendrias que cambiar exactamente en el `if` para distinguir entre 0 valido y 0 invalido?

    // * TEORIA: Ordenacion descendente
    // --------------------------------------------------------------------------------------
    // ? Muchas veces necesitamos ordenar los datos para presentarlos mejor.
    // ? Aqui hacemos una copia para no modificar la lista original.
    // ? Esa decision es buena practica porque mantiene los datos originales intactos.
    public static ArrayList<Double> obtenerCopiaOrdenadaDescendente(ArrayList<Double> sueldosOriginales) {
        ArrayList<Double> copiaSueldos = new ArrayList<>(sueldosOriginales);

        // !IMPORTANT: `Collections.reverseOrder()` permite ordenar de mayor a menor de forma directa.
        // *INFO: Trabajamos con una copia para poder seguir usando la lista original en otros calculos.
        // *INFO: Un error tipico es ordenar la lista original y luego perder el orden en que se introdujeron los datos.
        Collections.sort(copiaSueldos, Collections.reverseOrder());
        return copiaSueldos;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea otro metodo parecido llamado `obtenerCopiaOrdenadaAscendente`.
    // *INFO: El objetivo es comparar facilmente orden descendente y ascendente con el mismo conjunto de datos.
    // ?QUESTION: Que parte de este metodo tendrias que cambiar para ordenar al reves?

    // * TEORIA: Recorrido para encontrar el valor maximo
    // --------------------------------------------------------------------------------------
    // ? Para buscar el mayor valor, empezamos suponiendo que el primero es el maximo.
    // ? Despues recorremos el resto comparando uno a uno.
    // ? Este patron se reutiliza mucho en programacion.
    public static double calcularMaximo(ArrayList<Double> sueldos) {
        double maximo = sueldos.get(0);

        for (int i = 1; i < sueldos.size(); i++) {
            if (sueldos.get(i) > maximo) {
                maximo = sueldos.get(i);
            }
        }

        return maximo;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea un metodo `contarCuantosTienenElMaximo` que cuente cuantas personas cobran el sueldo maximo.
    // *INFO: Primero puedes llamar a `calcularMaximo` y despues hacer un segundo recorrido para contar coincidencias.
    // ?QUESTION: Que pasaria si varias personas tienen exactamente el mismo sueldo mas alto?

    // * TEORIA: Recorrido para encontrar el valor minimo
    // --------------------------------------------------------------------------------------
    // ? El minimo se calcula igual que el maximo, pero cambiando la comparacion.
    // ? Este tipo de ejercicios ayuda a ver patrones repetidos en el codigo.
    public static double calcularMinimo(ArrayList<Double> sueldos) {
        double minimo = sueldos.get(0);

        for (int i = 1; i < sueldos.size(); i++) {
            if (sueldos.get(i) < minimo) {
                minimo = sueldos.get(i);
            }
        }

        return minimo;
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Implementa un metodo `contarSueldosMenoresQueMil` que cuente cuantas personas cobran menos de 1000.
    // *INFO: Es una ampliacion sencilla porque solo necesitas un contador y una condicion dentro del recorrido.
    // ?QUESTION: Conviene poner 1000 fijo en el metodo o recibirlo como parametro? Piensa cual opcion reutiliza mejor el codigo.

    // * TEORIA: Media aritmetica
    // --------------------------------------------------------------------------------------
    // ? La media se obtiene sumando todos los valores y dividiendo entre la cantidad de elementos.
    // ? En un ejercicio dinamico hay que dividir entre `size()`, no entre un numero fijo.
    public static double calcularMedia(ArrayList<Double> sueldos) {
        double suma = 0;

        for (int i = 0; i < sueldos.size(); i++) {
            suma += sueldos.get(i);
        }

        return suma / sueldos.size();
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Crea un metodo `calcularSumaTotal` para mostrar tambien cuanto dinero suman todos los sueldos.
    // *INFO: La logica sera muy parecida a este metodo, pero sin hacer la division final.
    // ?QUESTION: Por que en la media dividimos entre `sueldos.size()` y no entre 10?

    // * TEORIA: Mostrar resultados finales
    // --------------------------------------------------------------------------------------
    // ? Una buena practica es dejar la impresion final en un metodo aparte.
    // ? Asi el programa queda mas limpio y es mas facil cambiar el formato de salida.
    public static void mostrarResumen(ArrayList<Double> sueldosOrdenados, double maximo, double minimo, double media) {
        System.out.println();
        System.out.println("RESULTADO FINAL");
        System.out.println("Sueldos ordenados de forma decreciente: " + sueldosOrdenados);
        System.out.printf("Sueldo maximo: %.2f%n", maximo);
        System.out.printf("Sueldo minimo: %.2f%n", minimo);
        System.out.printf("Media de los sueldos: %.2f%n", media);
    }

    // !IMPORTANT: TAREA PARA EL ALUMNO:
    // *INFO: Modifica la salida para que tambien muestre el numero total de personas encuestadas.
    // *INFO: Solo necesitas recibir la lista o su tamano y anadir una linea mas en este metodo.
    // ?QUESTION: Donde se calcula mas facilmente ese dato: en `main` o dentro de este metodo?

    // * TEORIA: Variante con array fijo
    // --------------------------------------------------------------------------------------
    // ? La segunda imagen del ejercicio parece una version con 10 habitantes exactos.
    // ? En ese caso, un array fijo `double[]` si tendria sentido.
    // ? Este metodo se deja como apoyo para comparar ambos enfoques.
    public static double[] leerSueldosVersionFija(Scanner sc, int cantidadHabitantes) {
        double[] sueldos = new double[cantidadHabitantes];

        for (int i = 0; i < sueldos.length; i++) {
            boolean datoValido = false;

            while (!datoValido) {
                try {
                    System.out.print("Introduce el sueldo de la persona " + (i + 1) + ": ");
                    double sueldoLeido = sc.nextDouble();

                    // !IMPORTANT: En esta version no usamos `-1` porque ya sabemos cuantas personas hay.
                    // *INFO: Si el tamano es fijo, el final de la entrada lo marca el propio bucle `for`.
                    // *INFO: Un error tipico es mezclar la version fija con la dinamica y usar dos criterios a la vez.
                    if (sueldoLeido < 0) {
                        System.out.println("Error: en la version fija no se admiten sueldos negativos.");
                    } else {
                        sueldos[i] = sueldoLeido;
                        datoValido = true;
                    }
                } catch (InputMismatchException e) {
                    // !IMPORTANT: Igual que antes, limpiamos el dato erroneo para poder seguir leyendo bien.
                    // *INFO: La validacion no depende de si usamos ArrayList o array fijo.
                    // *INFO: Un error tipico es pensar que el control de errores solo va en el ejercicio principal.
                    System.out.println("Error: debes introducir un numero valido.");
                    sc.next();
                }
            }
        }

        return sueldos;
    }

    // !IMPORTANT: TAREA FINAL PARA EL ALUMNO:
    // *INFO: Rehaz toda la practica usando la version fija de 10 personas y compara ventajas e inconvenientes.
    // *INFO: Asi entenderas cuando conviene usar `double[]` y cuando conviene usar `ArrayList<Double>`.
    // ?QUESTION: Que solucion te parece mas flexible si el numero de encuestados puede cambiar cada dia?
}
