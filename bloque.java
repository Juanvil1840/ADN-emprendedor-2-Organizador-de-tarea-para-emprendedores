import java.util.*;

public class bloque {
    private Date fecha_inicio;
    private Date fecha_fin;
    private String nombre_de_bloque;
    private ArrayList<Subbloque> subbloques = new ArrayList<>();

    public bloque(String nombre_de_bloque, Date fecha_inicio, Date fecha_fin) {
        this.nombre_de_bloque = nombre_de_bloque;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public void addSubbloque(Subbloque sb) {
        subbloques.add(sb);
    }

    public List<String> generarRepeticiones() {
        List<String> calendario = new ArrayList<>();

        for (Subbloque sb : subbloques) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha_inicio);

            // Avanzar hasta el primer día de la semana del subbloque
            while (cal.get(Calendar.DAY_OF_WEEK) != sb.getDiaSemana()) {
                cal.add(Calendar.DAY_OF_MONTH, 1);
            }

            // Ir sumando semanas mientras no se pase de la fecha final
            while (!cal.getTime().after(fecha_fin)) {
                String fechaStr = cal.getTime().toString();
                for (tarea t : sb.getTareas()) {
                    calendario.add(
                        fechaStr + " " +
                        sb.getHoraInicio() + ":" + String.format("%02d", sb.getMinutoInicio()) +
                        " - " +
                        sb.getHoraFin() + ":" + String.format("%02d", sb.getMinutoFin()) +
                        " | Subbloque: " + sb.getNombre_de_bloque() +
                        " | Tarea: " + t.getNombre()
                    );
                }
                cal.add(Calendar.WEEK_OF_YEAR, 1);
            }
        }

        return calendario;
    }

    // Getters
    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public String getNombre_de_bloque() {
        return nombre_de_bloque;
    }

    public ArrayList<Subbloque> getSubbloques() {
        return subbloques;
    }

    // Setters
    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public void setNombre_de_bloque(String nombre_de_bloque) {
        this.nombre_de_bloque = nombre_de_bloque;
    }

    public void setSubbloques(ArrayList<Subbloque> subbloques) {
        this.subbloques = subbloques;
    }
}
