import java.util.Date;

public class tarea {
    String nombre;
    Date fecha;
    String prioridad;
    
    public tarea() {
    }

    public tarea(String nombre, Date fecha, String prioridad) {
    this.nombre = nombre;
    this.fecha = fecha;
    this.prioridad = prioridad;
}

    public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public Date getFecha() {
    return fecha;
}

public void setFecha(Date fecha) {
    this.fecha = fecha;
}

public String getPrioridad() {
    return prioridad;
}

public void setPrioridad(String prioridad) {
    this.prioridad = prioridad;
}

    
}
