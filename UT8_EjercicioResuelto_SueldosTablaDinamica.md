# 💼 Practica Resuelta: Sueldos con Tabla Dinamica

---

## 🎯 Objetivo

Construir un programa en Java capaz de:

- pedir sueldos por teclado,
- guardar una cantidad variable de datos,
- terminar cuando el usuario escriba `-1`,
- mostrar los sueldos ordenados de mayor a menor,
- calcular el sueldo maximo, el minimo y la media.

La idea mas importante de esta practica es entender **por que aqui usamos `ArrayList<Double>`** y no un array fijo.

---

## 🗺️ Plan

1. Leer el enunciado y detectar que la cantidad de datos es variable.
2. Elegir `ArrayList<Double>` como estructura principal.
3. Crear un metodo que lea datos hasta encontrar el centinela `-1`.
4. Validar que no se guarden valores negativos incorrectos.
5. Crear una copia ordenada de forma decreciente.
6. Recorrer la lista para calcular el valor maximo.
7. Recorrer la lista para calcular el valor minimo.
8. Recorrer la lista para calcular la media.
9. Mostrar un resumen final claro.
10. Proponer pequeñas ampliaciones para practicar.

---

## 📘 Codigo o diff por archivos

### Archivo principal

`UT8_EjercicioResuelto_SueldosTablaDinamica.java`

### Que hace este archivo

- resuelve el ejercicio completo,
- separa la logica en metodos pequenos,
- controla errores de entrada,
- incluye una ampliacion ya resuelta,
- deja ejercicios concretos para que la alumna practique.

### Por que se implementa asi

- porque es mas facil entender un programa por partes,
- porque el alumno puede probar un cambio pequeño sin romper todo,
- porque permite reutilizar metodos en ejercicios parecidos.

### Caso limite importante

Si el primer dato es `-1`, el programa no intenta calcular media, maximo ni minimo sobre una lista vacia.

---

## 🧠 Idea clave del ejercicio

### Cuando usar tabla dinamica

Usamos una tabla dinamica cuando **no sabemos el numero exacto de datos antes de empezar**.

Ejemplo:

- Si el enunciado dijera: "vas a leer exactamente 10 sueldos", un array fijo tendria sentido.
- Si el enunciado dice: "lee sueldos hasta que el usuario escriba `-1`", entonces es mejor `ArrayList`.

---

## 🔍 Explicacion de los metodos

### `leerSueldosHastaCentinela(Scanner scanner)`

**Que hace**

Lee sueldos hasta que aparece `-1`.

**Por que se hace asi**

Porque el programa no sabe cuantas personas van a participar.

**Caso limite**

Si el usuario mete letras, se controla el error y el programa sigue funcionando.

### `obtenerCopiaOrdenadaDescendente(ArrayList<Double> sueldosOriginales)`

**Que hace**

Crea una copia y la ordena de mayor a menor.

**Por que se hace asi**

Para no modificar la lista original.

**Caso limite**

Si la lista tiene un solo elemento, la copia sigue siendo correcta.

### `calcularMaximo(ArrayList<Double> sueldos)`

**Que hace**

Busca el sueldo mas alto.

**Por que se hace asi**

Porque es la forma mas clara de practicar un recorrido con comparaciones.

**Caso limite**

Si todos los sueldos son iguales, el maximo sera ese mismo valor.

### `calcularMinimo(ArrayList<Double> sueldos)`

**Que hace**

Busca el sueldo mas bajo.

**Por que se hace asi**

Porque sigue el mismo patron que el maximo y eso ayuda a aprender.

**Caso limite**

Si solo hay un sueldo, ese valor es tambien el minimo.

### `calcularMedia(ArrayList<Double> sueldos)`

**Que hace**

Suma todos los sueldos y divide entre la cantidad real de datos.

**Por que se hace asi**

Porque en una tabla dinamica no debemos dividir entre un numero fijo.

**Caso limite**

No se ejecuta si la lista esta vacia.

### `contarSueldosMayoresQue(ArrayList<Double> sueldos, double limite)`

**Que hace**

Cuenta cuantos sueldos superan un valor indicado.

**Por que se hace asi**

Porque es una ampliacion natural del problema y prepara para ejercicios de examen.

**Caso limite**

Si nadie supera el limite, devuelve `0`.

### `mostrarEjemploDeExtension(int cantidadSueldosAltos)`

**Que hace**

Muestra una ampliacion ya resuelta.

**Por que se hace asi**

Para que la alumna vea como evolucionar el programa sin empezar desde cero.

**Caso limite**

Si el contador vale `0`, sigue siendo una salida correcta.

### `mostrarEjerciciosPropuestos()`

**Que hace**

Muestra retos pequenos para practicar.

**Por que se hace asi**

Porque aprender este tipo de ejercicios requiere tocar el codigo y observar que cambia.

**Caso limite**

Aunque no se haga ningun ejercicio extra, el programa principal sigue funcionando igual.

---

## 🧪 Como probar

### Compilar

```bash
javac UT8_EjercicioResuelto_SueldosTablaDinamica.java
```

### Ejecutar

```bash
java UT8_EjercicioResuelto_SueldosTablaDinamica
```

### Entrada de ejemplo 1

```text
1500
2100
1800
900
-1
```

### Salida esperada aproximada

```text
RESULTADO FINAL
Sueldos ordenados de forma decreciente: [2100.0, 1800.0, 1500.0, 900.0]
Sueldo maximo: 2100.00
Sueldo minimo: 900.00
Media de los sueldos: 1575.00

AMPLIACION YA RESUELTA
Cantidad de sueldos mayores que 1500: 2
```

### Entrada de ejemplo 2

```text
-1
```

### Salida esperada aproximada

```text
No se han introducido sueldos validos. Fin del programa.
```

### Entrada de ejemplo 3

```text
1200
hola
1800
-5
950
-1
```

### Que debe pasar

- `hola` debe dar error de entrada,
- `-5` no debe guardarse,
- el programa debe seguir funcionando,
- solo se deben procesar `1200`, `1800` y `950`.

---

## ⚠️ Errores tipicos del alumno

- Guardar `-1` dentro de la lista.
- Dividir la media entre `10` aunque el ejercicio sea dinamico.
- Cerrar el `Scanner` dentro de un metodo auxiliar.
- No controlar la lista vacia antes de calcular maximo o minimo.
- Ordenar la lista original cuando luego se quiere seguir usando.
- Confundir "mayor que" con "mayor o igual que".

---

## 🧩 Ejercicios extra

### Ejercicio 1

Cambia la ampliacion ya resuelta para contar cuantos sueldos son **menores que 1000**.

### Ejercicio 2

Crea un metodo que calcule la **suma total** de todos los sueldos.

### Ejercicio 3

Crea un metodo que cuente cuantas personas cobran **exactamente el sueldo maximo**.

### Ejercicio 4

Haz una version del programa con **10 habitantes exactos** usando `double[]`.

### Ejercicio 5

Muestra al final un mensaje que diga:

- `"Hay mas sueldos altos que bajos"` o
- `"Hay mas sueldos bajos que altos"`

usando el limite que tu elijas.

---

## ✅ Checklist final

- [x] Ejercicio principal resuelto.
- [x] Uso de tabla dinamica con `ArrayList`.
- [x] Sueldos ordenados de forma decreciente.
- [x] Calculo de maximo, minimo y media.
- [x] Control de errores de entrada.
- [x] Una ampliacion ya resuelta dentro del `.java`.
- [x] Ejercicios extra para seguir practicando.
- [x] Unico archivo `.md` para apoyar la practica.
- [ ] Version alternativa completa con array fijo de 10 personas.

