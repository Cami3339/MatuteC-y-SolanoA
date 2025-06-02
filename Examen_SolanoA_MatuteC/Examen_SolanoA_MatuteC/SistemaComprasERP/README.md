
#  Sistema de Gestión de Compras ERP

##  Descripción

Este proyecto implementa un **módulo de compras** para un sistema ERP (Enterprise Resource Planning), permitiendo gestionar proveedores, productos y solicitudes de compra a través de una interfaz por consola. Fue desarrollado como parte del **examen práctico de Programación Orientada a Objetos**, utilizando principios como:

- Clases abstractas e interfaces
- Herencia y polimorfismo
- Enums y manejo de archivos
- Menú interactivo por consola

---

##  Integrantes

- Camila Noemí Matute Orellana  
- Andrés Solano

---

##  Requisitos

- Java JDK 17 o superior
- Consola o terminal
- Editor de texto o entorno de desarrollo como VS Code, IntelliJ o NetBeans

---

##  Instrucciones de Ejecución

### 1. Abrir una terminal en la carpeta `SistemaComprasERP`

```bash
cd SistemaComprasERP
```

### 2. Compilar el proyecto

```bash
javac -d out src/**/*.java
```

### 3. Ejecutar el programa

```bash
java -cp out Main
```


##  Cómo Funciona el Programa

Al ejecutar el programa, se muestra un **menú interactivo** con varias opciones. A continuación se explica lo que hace cada una:

1. **Registrar proveedor**  
   Permite ingresar el nombre, cédula y tipo de proveedor. Se guarda en la lista de proveedores.

2. **Registrar producto**  
   Se ingresan los datos del producto: nombre, código, precio y cantidad. Se guarda en la lista de productos.

3. **Mostrar proveedores**  
   Imprime en consola la lista completa de proveedores registrados.

4. **Mostrar productos**  
   Muestra todos los productos almacenados en memoria.

5. **Generar solicitud de compra**  
   - Se selecciona un proveedor.
   - Se seleccionan productos existentes y cantidades.
   - El sistema genera una solicitud con todos los productos seleccionados.
   - Se calcula el total automáticamente.

6. **Mostrar solicitudes de compra**  
   Imprime todas las solicitudes realizadas, con detalles de productos y totales.

7. **Guardar en archivos**  
   Guarda los proveedores y productos en archivos de texto plano (`proveedores.txt` y `productos.txt`).

8. **Salir**  
   Termina el programa.

---

##  Estructura del Proyecto

```
Examen_SolanoA_MatuteC/
├── productos.txt
├── proveedores.txt
└── SistemaComprasERP/
    ├── src/
    │   ├── Main.java
    │   ├── entidades/
    │   ├── interfaces/
    │   └── servicios/
    └── out/ (se genera al compilar)
```

---

##  Observaciones

Este proyecto fue desarrollado como parte del **examen práctico de la asignatura Programación Orientada a Objetos**, correspondiente a la carrera de **Electrónica y Automatización**, durante el periodo **marzo - agosto 2025**.
