# SimuladorArchivos
Simulador Virtual de Sistema de Archivos con Gestión de Permisos y Asignación de Bloques

Planteamiento del Problema
El objetivo de este proyecto es que los estudiantes desarrollen un simulador de sistema de archivos, en el que puedan comprender y aplicar conceptos fundamentales como la gestión de archivos y directorios, la asignación de bloques de almacenamiento, la administración de permisos y la fragmentación del espacio en disco.
Para ello, los estudiantes deberán implementar un sistema de archivos simulado en Java utilizando NetBeans, con una interfaz gráfica que represente visualmente la estructura jerárquica de directorios y archivos mediante un JTree, así como la distribución de bloques en una Simulación de un Disco (SD), y una tabla de asignación de archivos.
El sistema debe operar en dos modos de usuario:
Modo Administrador: Permite realizar todas las operaciones, incluyendo crear, modificar y eliminar archivos y directorios y visualizar información completa del SD.


Modo Usuario: Restringe las acciones a solo lectura, impidiendo modificar archivos o la estructura del sistema de archivos.
Los archivos creados deberán tener un tamaño en bloques, los cuales serán asignados utilizando el método de asignación encadenada, en donde cada archivo se representa como una lista enlazada de bloques en el SD.
Además, el sistema deberá actualizarse en tiempo real cada vez que se realice una operación CRUD (Crear, Leer, Actualizar, Eliminar), reflejando los cambios en la estructura de directorios, en el estado del disco SD y en la tabla de asignación de archivos.
Requerimientos Funcionales
Visualización de la estructura del sistema de archivos:
Implementar un JTree para representar la estructura jerárquica de directorios y archivos. 
Referencia de aprendizaje: Pueden echarle un ojo a este video que describe cómo utilizar este componente https://www.youtube.com/watch?v=SfljfVnLbc4 
Mostrar información del archivo o directorio seleccionado (nombre, tamaño, permisos y fecha de creación).
Simulación del SD y asignación de bloques:
Representar visualmente el SD como un conjunto de bloques, indicando cuáles están ocupados y cuáles están libres.
Simular la asignación encadenada, donde cada archivo se almacena como una lista enlazada de bloques.
Manejar la liberación de bloques cuando se eliminan archivos.
Definir un tamaño limitado de almacenamiento, evitando la creación de archivos si no hay espacio disponible. (Una cantidad máxima de bloques razonable)
Gestión de archivos y directorios (CRUD):
Crear:
Los administradores podrán crear archivos y directorios.
Se especificará el tamaño del archivo en bloques, los cuales serán asignados en el SD.
Leer:
Todos los usuarios podrán visualizar la estructura del sistema y sus propiedades.
Actualizar:
Solo los administradores podrán modificar el nombre
Eliminar:
Al borrar un archivo, se liberarán los bloques asignados en el SD.
Al eliminar un directorio, también se deben eliminar todos sus archivos y subdirectorios.
Modo Administrador vs. Modo Usuario:
Implementar dos modos de uso:
Administrador: Permite realizar todas las operaciones.
Usuario: Restringido a solo lectura.
El modo se seleccionará mediante la interfaz.
Tabla de asignación de archivos:
Implementar un JTable que muestre:
El nombre del archivo.
La cantidad de bloques asignados.
La dirección del primer bloque.
La cadena de enlaces de la asignación encadenada.
La tabla debe actualizarse en tiempo real con cada operación CRUD.
Almacenar el estado de los archivos en el sistema:
Los estudiantes podrán elegir almacenar la información del sistema de archivos en un archivo de texto, JSON o cualquier otro formato que les resulte conveniente para que los datos puedan ser cargados en futuras ejecuciones.
Bono Adicional (10% de la Nota)
Se otorgará un bono del 10% de la nota final a los equipos o estudiantes que implementen una funcionalidad adicional. Algunas opciones incluyen:
Sistema de versionado o backup: Permitir la restauración de versiones anteriores de un archivo.
Registro de auditoría: Implementar un log en tiempo real que registre todas las operaciones realizadas, con marcas de tiempo y el usuario que ejecutó la acción.
Simulación de fallos y recuperación: Implementar fallos en el SD, con mecanismos de recuperación de archivos eliminados o reubicación eficiente de bloques.
Consideraciones
Fecha de entrega: Jueves 20 de marzo de 2025 (Semana 11).
Fecha de defensa: Viernes 21 de marzo de 2025 (Semana 11).
Integrantes: El proyecto puede realizarse en equipos de hasta 2 personas (3 solo si alguien queda sin equipo).
Uso de librerías:
Se permite el uso de librerías para JTree y otras necesarias para la funcionalidad de la interfaz gráfica, pero no para las estructuras de datos como listas. 
Se pueden usar librerías para la lectura/escritura de archivos (JSON, TXT, etc.).
No se permite el uso de librerías que implementan estructuras de datos como ArrayList, Queue, etc.
Uso obligatorio de GitHub:
Cada equipo debe mantener su proyecto en un repositorio de GitHub, que deberá ser enviado junto con un informe en formato PDF.
Los proyectos sin repositorio en GitHub serán calificados con 0 (cero).
Requisitos de implementación:
El proyecto debe realizarse en Java y NetBeans (versiones posteriores a Java 21).
Los proyectos sin interfaz gráfica serán calificados con 0 (cero).
Los proyectos que no compilen o no funcionen serán calificados con 0 (cero).
Defensa del Proyecto:
La defensa se realizará en turnos asignados en el salón habitual.
Todos los integrantes deben estar presentes.
Se evaluará el conocimiento de cada miembro sobre la implementación del proyecto.
Información Teórica
SD: Espacio de almacenamiento simulado en memoria, donde se asignan bloques para representar la estructura de archivos y directorios. Este no es un concepto real.
Asignación encadenada: Usa asignación dinámica con bloques de tamaño fijo, al final de cada bloque se coloca un apuntador hacia el siguiente bloque del fichero, por lo que no tienen que estar guardados de forma contigua, pero puede provocar que se tengan que acceder a diferentes sectores del disco para leer un mismo fichero. No existe fragmentación externa, La tabla de asignación de ficheros solo tiene una entrada por fichero, la posición del bloque inicial y la longitud del fichero

![Screenshot 2025-03-01 095903](https://github.com/user-attachments/assets/9359cec9-77f1-456b-a239-4dbf93ce235f)


JTree: Componente de Java Swing utilizado para representar estructuras jerárquicas de datos. Para aprender más, pueden consultar: https://www.youtube.com/watch?v=SfljfVnLbc4 
