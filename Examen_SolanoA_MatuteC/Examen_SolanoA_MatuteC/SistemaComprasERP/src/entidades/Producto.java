package entidades;

import interfaces.Calculable;

public class Producto implements Calculable {
    private String nombre;
    private double precioUnitario;
    private double precioPorMayor;

    public Producto(String nombre, double precioUnitario, double precioPorMayor) {
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.precioPorMayor = precioPorMayor;
    }

    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getPrecioPorMayor() { return precioPorMayor; }

    @Override
    public double calcularCostoTotal(int cantidad) {
        if (cantidad >= 10) {
            return precioPorMayor * cantidad;
        } else {
            return precioUnitario * cantidad;
        }
    }

    public void mostrarInfo() {
        System.out.println("Producto: " + nombre + 
            " | Precio Unitario: $" + precioUnitario + 
            " | Precio Por Mayor: $" + precioPorMayor);
    }
}
