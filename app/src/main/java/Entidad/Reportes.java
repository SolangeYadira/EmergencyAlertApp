package Entidad;

public class Reportes {

    private String idReporte;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String descripcion;
    private double latitud;
    private double longitud;

    public Reportes(){
        idReporte="0";
        nombre="nn";
        apellidos="nn";
        telefono="nn";
        descripcion="nn";
        latitud=0.0;
        longitud=0.0;
    }

    public Reportes(String idReporte, String nombre, String apellidos, String telefono, String descripcion, double latitud, double longitud) {
        this.idReporte = idReporte;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(String idReporte) {
        this.idReporte = idReporte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Reporte{" +
                "idReporte='" + idReporte + '\'' +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", telefono='" + telefono + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                '}';
    }
}
