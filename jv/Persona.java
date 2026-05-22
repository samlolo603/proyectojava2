package proyectojava2.jv;


    public abstract class Persona {
    protected String nombre;
    protected String telefono;
    protected String direccion;
    protected String correo;

    public Persona(String nombre, String telefono, String direccion, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }
}

