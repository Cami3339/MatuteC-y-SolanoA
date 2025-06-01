import entidades.*;
import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Proveedor> proveedores = new ArrayList<>();
    static ArrayList<Producto> productos = new ArrayList<>();
    static ArrayList<SolicitudCompra> solicitudes = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    static void guardarProveedores() {
        try (PrintWriter pw = new PrintWriter("proveedores.txt")) {
            for (Proveedor p : proveedores) {
                pw.println(p.getId() + "|" + p.getNombre() + "|" + p.getEmpresa());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar proveedores: " + e.getMessage());
        }
    }

    static void cargarProveedores() {
        File archivo = new File("proveedores.txt");
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    proveedores.add(new Proveedor(partes[0], partes[1], partes[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar proveedores: " + e.getMessage());
        }
    }

    static void guardarProductos() {
        try (PrintWriter pw = new PrintWriter("productos.txt")) {
            for (Producto p : productos) {
                pw.println(p.getNombre() + "|" + p.getPrecioUnitario() + "|" + p.getPrecioPorMayor());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    static void cargarProductos() {
        File archivo = new File("productos.txt");
        if (!archivo.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    double precioU = Double.parseDouble(partes[1]);
                    double precioM = Double.parseDouble(partes[2]);
                    productos.add(new Producto(nombre, precioU, precioM));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar productos: " + e.getMessage());
        }
    }


    public static void main(String[] args) {
        cargarProveedores();
        cargarProductos();
        int opcion;
        do {
            System.out.println("\n===== SISTEMA DE GESTION DE COMPRAS ERP =====");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Registrar producto");
            System.out.println("3. Registrar solicitud de compra");
            System.out.println("4. Listar proveedores");
            System.out.println("5. Listar productos");
            System.out.println("6. Listar solicitudes de compra");
            System.out.println("7. Buscar proveedor por ID");
            System.out.println("8. Buscar producto por nombre");
            System.out.println("9. Buscar solicitud por número");
            System.out.println("13. Aprobar / Rechazar solicitud de compra");
            System.out.println("14. Calcular total de una solicitud");
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> {registrarProveedor();guardarProveedores();}
                case 2 -> {registrarProducto();guardarProductos();}
                case 3 -> registrarSolicitud();
                case 4 -> proveedores.forEach(Proveedor::mostrarInfo);
                case 5 -> productos.forEach(Producto::mostrarInfo);
                case 6 -> solicitudes.forEach(SolicitudCompra::mostrarInfo);
                case 7 -> buscarProveedorPorID();
                case 8 -> buscarProductoPorNombre();
                case 9 -> buscarSolicitudPorNumero();
                case 13 -> aprobarRechazarSolicitud();
                case 14 -> calcularTotalSolicitud();
            }
        } while (opcion != 15);
        
    }

    static void registrarProveedor() {
        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Empresa: ");
        String empresa = sc.nextLine();
        proveedores.add(new Proveedor(id, nombre, empresa));
    }

   static void registrarProducto() {
    System.out.print("Nombre del producto: ");
    String nombre = sc.nextLine();
    System.out.print("Precio unitario: ");
    double precioU = sc.nextDouble(); sc.nextLine();
    System.out.print("Precio por mayor (desde 10 unidades): ");
    double precioM = sc.nextDouble(); sc.nextLine();
    productos.add(new Producto(nombre, precioU, precioM));
}


    static void registrarSolicitud() {
        SolicitudCompra solicitud = new SolicitudCompra();
        String continuar;
        do {
            System.out.print("Nombre del producto: ");
            String nombre = sc.nextLine();
            Producto prod = productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst().orElse(null);
            if (prod != null) {
                System.out.print("Cantidad: ");
                int cant = sc.nextInt(); sc.nextLine();
                solicitud.agregarProducto(prod, cant);
            } else {
                System.out.println("Producto no encontrado.");
            }
            System.out.print("¿Agregar otro producto? (s/n): ");
            continuar = sc.nextLine();
        } while (continuar.equalsIgnoreCase("s"));
        solicitudes.add(solicitud);
    }

    static void buscarProveedorPorID() {
        System.out.print("Ingrese ID del proveedor: ");
        String id = sc.nextLine();
        proveedores.stream().filter(p -> p.getId().equalsIgnoreCase(id)).findFirst()
            .ifPresentOrElse(Proveedor::mostrarInfo, () -> System.out.println("No encontrado."));
    }

    static void buscarProductoPorNombre() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = sc.nextLine();
        productos.stream().filter(p -> p.getNombre().equalsIgnoreCase(nombre)).findFirst()
            .ifPresentOrElse(Producto::mostrarInfo, () -> System.out.println("No encontrado."));
    }

    static void buscarSolicitudPorNumero() {
        System.out.print("Ingrese número de solicitud: ");
        int num = sc.nextInt(); sc.nextLine();
        solicitudes.stream().filter(s -> s.getNumero() == num).findFirst()
            .ifPresentOrElse(SolicitudCompra::mostrarInfo, () -> System.out.println("No encontrada."));
    }

    static void aprobarRechazarSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        int num = sc.nextInt(); sc.nextLine();
        var solicitud = solicitudes.stream().filter(s -> s.getNumero() == num).findFirst().orElse(null);
        if (solicitud != null) {
            System.out.print("¿Aprobar (a) o Rechazar (r)? ");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("a"))
                solicitud.setEstado(EstadoSolicitud.APROBADA);
            else if (respuesta.equalsIgnoreCase("r"))
                solicitud.setEstado(EstadoSolicitud.RECHAZADA);
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    static void calcularTotalSolicitud() {
        System.out.print("Ingrese número de solicitud: ");
        int num = sc.nextInt(); sc.nextLine();
        var solicitud = solicitudes.stream().filter(s -> s.getNumero() == num).findFirst().orElse(null);
        if (solicitud != null) {
            System.out.println("Total de la solicitud: $" + solicitud.calcularTotal());
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }
}