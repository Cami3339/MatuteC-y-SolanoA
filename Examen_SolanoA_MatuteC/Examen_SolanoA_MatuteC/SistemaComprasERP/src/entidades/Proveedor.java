package entidades;

public class Proveedor extends Persona {
    private String empresa;

    public Proveedor(String id, String nombre, String empresa) {
        super(id, nombre);
        this.empresa = empresa;
    }

    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }

    @Override
    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Proveedor: " + nombre + " | Empresa: " + empresa);
    }
}
