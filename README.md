#  Gestor de Tareas para Emprendedores

##  Descripción
Esta aplicación creada en **Java** ayuda a emprendedores a **organizar, visualizar y priorizar** sus tareas semanales.  
Está diseñada para ejecutarse en consola, siendo ligera, rápida y multiplataforma.

Con este sistema, el usuario puede:
- **Agregar tareas** con nombre, fecha y prioridad.
- **Agrupar tareas** por proyectos y subbloques.
- **Visualizar todas las tareas** en formato jerárquico `Proyecto/Subbloque/Tarea`.
- Llevar un **control simple** de sus actividades semanales sin depender de aplicaciones costosas.

---

## Stack y Herramientas
- **Lenguaje:** Java 17  
- **IDE sugeridos:** IntelliJ IDEA, NetBeans o VS Code  
- **Ejecución:** Consola de comandos  
- **Dependencias externas:** Ninguna

---

##  Requisitos
- Tener instalado **Java JDK 11** o superior.
- Consola de comandos o un IDE compatible con Java.

---

## Ejecución
1. Clona o descarga este repositorio/proyecto.
2. Abre la carpeta del proyecto en tu IDE o terminal.
3. Compila el archivo principal:
   ```
   javac Principal.java
4. Ejecuta el programa:
   ```
   java Principal
5 Usa el menu para:
  - 1. Agregar tarea
       Permite agregar una tarea individual que se debe realizar.

  - 2. Crear proyecto
       Permite agrupar una lista de tareas bajo un proyecto común, o tener un bloque de
       con un conjunto de tareas.
  - 3. Crear bloque de tiempo
       Permite tener un conjunto de proyectos que se van a repetir semanalmente en un
       periodo de tiempo.
   - 4. Cargar desde archivo
       permite cargar desde un archivo de texto diferentes tareas que ya se han hecho anteriormente.
   - 5. Consultar disponibilidad
       permite ingresar una fecha y verificar si ya existe otra asignación en ese momento.
   - 6. Guardar y salir
        guarda las tareas registradas y se sale del programa.
   - 7. Desplegar tareas
        despliega todas las tareas existentes en el momento.

6 Ejemplo de uso 

```
--- MENÚ ---
1. Crear tarea individual
2. Crear proyecto (Subbloque)
3. Crear bloque de tiempo
4. Cargar desde archivo
5. Consultar disponibilidad
6. Guardar y salir
7. Desplegar tareas
Seleccione una opción: 1
Ingrese el nombre de la tarea: Redactar propuesta
Ingrese la fecha (dd/MM/yyyy): 15/08/2025
Ingrese la prioridad (Alta, Media, Baja): Alta
Tarea agregada correctamente.
Visualizando las tareas:
--- LISTA DE TODAS LAS TAREAS ---
Terminar trabajo
StartUp idea/buscar inversionistas
universidad/calculo diferencial /hacer derivadas
universidad/calculo diferencial /derivar


