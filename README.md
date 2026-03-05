# serenity-project-test
Ejercicio de Automatizacion E2E 
PROYECTO: Automatizacion E2E - Demoblaze
Framework: Serenity BDD
Lenguaje: Java
Build Tool: Gradle

----------------------------------------
REQUISITOS PREVIOS
----------------------------------------

1. Tener instalado:
   - Java 16
   - Google Chrome

2. Verificar version de Java:
   java -version

----------------------------------------
ESTRUCTURA DEL PROYECTO
----------------------------------------

- src/test/java  Step Definitions
- src/test/resources/features Archivo feature
- build.gradle  Dependencias y configuracion
- serenity.conf  Configuracion de Serenity

----------------------------------------
PASOS PARA EJECUTAR
----------------------------------------

1. Abrir una terminal en la raiz del proyecto
   (donde se encuentra el archivo gradlew)

2. Ejecutar el siguiente comando en Windows PowerShell:

   .\gradlew clean test aggregate

   En caso de usar CMD:
   gradlew clean test aggregate

3. Esperar a que finalice la ejecucion.

----------------------------------------
ESCENARIO AUTOMATIZADO
----------------------------------------

Se automatizo el siguiente flujo E2E:

1. Ingreso a la pagina principal
2. Agregar dos productos al carrito
3. Visualizar el carrito
4. Completar formulario de compra
5. Finalizar compra

----------------------------------------
NOTAS
----------------------------------------

- El driver es gestionado automaticamente por Serenity.
- Se utilizaron esperas explicitas para estabilidad.
- La prueba valida la confirmacion final de compra exitosa.
