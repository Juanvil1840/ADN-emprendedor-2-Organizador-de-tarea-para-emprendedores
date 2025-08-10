import java.util.*;

public class Subbloque {
    String nombre_de_bloque;
    int diaSemana; // 1=Domingo, 2=Lunes, ..., 7=Sábado (Calendar)
    int horaInicio;
    int minutoInicio;
    int horaFin;
    int minutoFin;

    ArrayList<tarea> tareas = new ArrayList<>();

    public Subbloque(String nombre_de_bloque, int diaSemana, int horaInicio, int minutoInicio, int horaFin, int minutoFin) {
        this.nombre_de_bloque = nombre_de_bloque;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.minutoInicio = minutoInicio;
        this.horaFin = horaFin;
        this.minutoFin = minutoFin;
    }

    public void addTarea(tarea tarea) {
        tareas.add(tarea);
    }

    public ArrayList<tarea> getTareas() {
        return tareas;
    }

    public String getNombre_de_bloque() {
        return nombre_de_bloque;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public int getMinutoInicio() {
        return minutoInicio;
    }

    public int getHoraFin() {
        return horaFin;
    }

    public int getMinutoFin() {
        return minutoFin;
    }
}
