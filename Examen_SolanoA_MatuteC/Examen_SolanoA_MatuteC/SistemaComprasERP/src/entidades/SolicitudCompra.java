package entidades;

import java.util.HashMap;
import java.util.Map;

public class SolicitudCompra {
    private static int contador = 1;
    private int numero;
    private EstadoSolicitud estado;
    private Map<Producto, Integer> items;

    public SolicitudCompra() {
        this.numero = contador++;
        this.estado = EstadoSolicitud.SOLICITADA;
        this.items = new HashMap<>();
    }

    public int getNumero() { return numero; }
    public EstadoSolicitud getEstado() { return estado; }
    public void setEstado(EstadoSolicitud estado) { this.estado = estado; }
    public Map<Producto, Integer> getItems() { return items; }

    public void agregarProducto(Producto producto, int cantidad) {
        items.put(producto, cantidad);
    }

    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            total += entry.getKey().calcularCostoTotal(entry.getValue());
        }
        return total;
    }

    public void mostrarInfo() {
        System.out.println("Solicitud #" + numero + " | Estado: " + estado);
        for (Map.Entry<Producto, Integer> entry : items.entrySet()) {
            System.out.println("- " + entry.getKey().getNombre() + " x" + entry.getValue() +
                               " = $" + entry.getKey().calcularCostoTotal(entry.getValue()));
        }
        System.out.println("TOTAL: $" + calcularTotal());
    }
}
