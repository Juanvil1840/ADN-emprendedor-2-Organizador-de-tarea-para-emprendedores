import java.io.*;
import java.text.*;
import java.util.*;

public class Principal {
    static Scanner sc = new Scanner(System.in);
    static List<tarea> tareasIndependientes = new ArrayList<>();
    static List<Subbloque> proyectos = new ArrayList<>();
    static List<bloque> bloques = new ArrayList<>();
    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Crear tarea individual");
            System.out.println("2. Crear proyecto (Subbloque)");
            System.out.println("3. Crear bloque de tiempo");
            System.out.println("4. Cargar desde archivo");
            System.out.println("5. Consultar disponibilidad");
            System.out.println("6. Guardar y salir");
            System.out.println("7. Desplegar tareas"); // Nueva opción
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> crearTarea();
                case 2 -> crearProyecto();
                case 3 -> crearBloque();
                case 4 -> cargarDesdeArchivo();
                case 5 -> consultarDisponibilidad();
                case 6 -> {
                    guardarEnArchivo();
                    System.out.println("Datos guardados. Saliendo...");
                    return;
                }
                case 7 -> desplegarTareas(); // Nuevo método
                default -> System.out.println("Opción no válida");
            }
        }
    }

    static void crearTarea() {
        try {
            System.out.print("Nombre de la tarea: ");
            String nombre = sc.nextLine();
            System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
            Date fecha = sdf.parse(sc.nextLine());
            System.out.print("Prioridad: ");
            String prioridad = sc.nextLine();

            tarea t = new tarea(nombre, fecha, prioridad);
            tareasIndependientes.add(t);
            System.out.println("Tarea creada.");
        } catch (Exception e) {
            System.out.println("Error creando tarea: " + e.getMessage());
        }
    }

    static void crearProyecto() {
        System.out.print("Nombre del proyecto: ");
        String nombre = sc.nextLine();
        Subbloque sb = new Subbloque(nombre, Calendar.MONDAY, 0, 0, 0, 0);
        while (true) {
            System.out.print("¿Agregar tarea al proyecto? (s/n): ");
            if (!sc.nextLine().equalsIgnoreCase("s")) break;
            try {
                System.out.print("Nombre de la tarea: ");
                String tn = sc.nextLine();
                System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
                Date fecha = sdf.parse(sc.nextLine());
                System.out.print("Prioridad: ");
                String pr = sc.nextLine();
                sb.addTarea(new tarea(tn, fecha, pr));
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        proyectos.add(sb);
        System.out.println("Proyecto creado.");
    }

    static void crearBloque() {
        try {
            System.out.print("Nombre del bloque: ");
            String nombre = sc.nextLine();
            System.out.print("Fecha de inicio (yyyy-MM-dd): ");
            Date inicio = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
            System.out.print("Fecha de fin (yyyy-MM-dd): ");
            Date fin = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
            bloque b = new bloque(nombre, inicio, fin);

            while (true) {
                System.out.print("¿Agregar subbloque a este bloque? (s/n): ");
                if (!sc.nextLine().equalsIgnoreCase("s")) break;
                System.out.print("Nombre del subbloque: ");
                String nsb = sc.nextLine();
                System.out.print("Día de la semana (1=Domingo..7=Sábado): ");
                int dia = Integer.parseInt(sc.nextLine());
                System.out.print("Hora inicio: ");
                int hi = Integer.parseInt(sc.nextLine());
                System.out.print("Minuto inicio: ");
                int mi = Integer.parseInt(sc.nextLine());
                System.out.print("Hora fin: ");
                int hf = Integer.parseInt(sc.nextLine());
                System.out.print("Minuto fin: ");
                int mf = Integer.parseInt(sc.nextLine());

                Subbloque sb = new Subbloque(nsb, dia, hi, mi, hf, mf);

                while (true) {
                    System.out.print("¿Agregar tarea al subbloque? (s/n): ");
                    if (!sc.nextLine().equalsIgnoreCase("s")) break;
                    System.out.print("Nombre tarea: ");
                    String tn = sc.nextLine();
                    System.out.print("Prioridad: ");
                    String pr = sc.nextLine();
                    sb.addTarea(new tarea(tn, inicio, pr));
                }

                b.addSubbloque(sb);
            }
            bloques.add(b);
            System.out.println("Bloque creado.");
        } catch (Exception e) {
            System.out.println("Error creando bloque: " + e.getMessage());
        }
    }

    static void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("agenda.txt"))) {
            for (tarea t : tareasIndependientes) {
                pw.println("TAREA|" + t.getNombre() + "|" + sdf.format(t.getFecha()) + "|" + t.getPrioridad());
            }
            for (Subbloque sb : proyectos) {
                pw.println("PROYECTO|" + sb.getNombre_de_bloque());
                for (tarea t : sb.getTareas()) {
                    pw.println("TAREA-PROYECTO|" + sb.getNombre_de_bloque() + "|" + t.getNombre() + "|" + sdf.format(t.getFecha()) + "|" + t.getPrioridad());
                }
            }
            for (bloque b : bloques) {
                pw.println("BLOQUE|" + b.getNombre_de_bloque() + "|" +
                        new SimpleDateFormat("yyyy-MM-dd").format(b.getFecha_inicio()) + "|" +
                        new SimpleDateFormat("yyyy-MM-dd").format(b.getFecha_fin()));
                for (Subbloque sb : b.getSubbloques()) {
                    pw.println("SUBBLOQUE|" + b.getNombre_de_bloque() + "|" + sb.getNombre_de_bloque() + "|" +
                            sb.getDiaSemana() + "|" + sb.getHoraInicio() + "|" + sb.getMinutoInicio() + "|" +
                            sb.getHoraFin() + "|" + sb.getMinutoFin());
                    for (tarea t : sb.getTareas()) {
                        pw.println("TAREA-SUBBLOQUE|" + sb.getNombre_de_bloque() + "|" + t.getNombre() + "|" +
                                sdf.format(t.getFecha()) + "|" + t.getPrioridad());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error guardando: " + e.getMessage());
        }
    }

    static void cargarDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader("agenda.txt"))) {
            String linea;
            Map<String, Subbloque> proyectosMap = new HashMap<>();
            Map<String, bloque> bloquesMap = new HashMap<>();

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                switch (partes[0]) {
                    case "TAREA" -> tareasIndependientes.add(new tarea(partes[1], sdf.parse(partes[2]), partes[3]));
                    case "PROYECTO" -> {
                        Subbloque sb = new Subbloque(partes[1], Calendar.MONDAY, 0, 0, 0, 0);
                        proyectos.add(sb);
                        proyectosMap.put(partes[1], sb);
                    }
                    case "TAREA-PROYECTO" -> proyectosMap.get(partes[1]).addTarea(new tarea(partes[2], sdf.parse(partes[3]), partes[4]));
                    case "BLOQUE" -> {
                        bloque b = new bloque(partes[1],
                                new SimpleDateFormat("yyyy-MM-dd").parse(partes[2]),
                                new SimpleDateFormat("yyyy-MM-dd").parse(partes[3]));
                        bloques.add(b);
                        bloquesMap.put(partes[1], b);
                    }
                    case "SUBBLOQUE" -> {
                        Subbloque sb = new Subbloque(partes[2],
                                Integer.parseInt(partes[3]),
                                Integer.parseInt(partes[4]),
                                Integer.parseInt(partes[5]),
                                Integer.parseInt(partes[6]),
                                Integer.parseInt(partes[7]));
                        bloquesMap.get(partes[1]).addSubbloque(sb);
                        proyectosMap.put(partes[2], sb);
                    }
                    case "TAREA-SUBBLOQUE" -> proyectosMap.get(partes[1]).addTarea(new tarea(partes[2], sdf.parse(partes[3]), partes[4]));
                }
            }
            System.out.println("Datos cargados desde archivo.");
        } catch (Exception e) {
            System.out.println("Error cargando: " + e.getMessage());
        }
    }

    static void consultarDisponibilidad() {
        try {
            System.out.print("Fecha y hora a consultar (yyyy-MM-dd HH:mm): ");
            Date consulta = sdf.parse(sc.nextLine());

            boolean ocupado = false;

            for (tarea t : tareasIndependientes) {
                if (t.getFecha().equals(consulta)) {
                    ocupado = true;
                    System.out.println("Ocupado: " + t.getNombre());
                }
            }
            for (Subbloque sb : proyectos) {
                for (tarea t : sb.getTareas()) {
                    if (t.getFecha().equals(consulta)) {
                        ocupado = true;
                        System.out.println("Ocupado en proyecto: " + t.getNombre());
                    }
                }
            }
            for (bloque b : bloques) {
                for (String rep : b.generarRepeticiones()) {
                    if (rep.contains(sdf.format(consulta))) {
                        ocupado = true;
                        System.out.println("Ocupado en bloque: " + rep);
                    }
                }
            }
            if (!ocupado) System.out.println("Disponible.");
        } catch (Exception e) {
            System.out.println("Error en consulta: " + e.getMessage());
        }
    }

    // NUEVO MÉTODO
    static void desplegarTareas() {
        System.out.println("\n--- LISTA DE TODAS LAS TAREAS ---");

        // Tareas independientes
        for (tarea t : tareasIndependientes) {
            System.out.println(t.getNombre());
        }

        // Proyectos
        for (Subbloque sb : proyectos) {
            for (tarea t : sb.getTareas()) {
                System.out.println(sb.getNombre_de_bloque() + "/" + t.getNombre());
            }
        }

        // Bloques
        for (bloque b : bloques) {
            for (Subbloque sb : b.getSubbloques()) {
                for (tarea t : sb.getTareas()) {
                    System.out.println(b.getNombre_de_bloque() + "/" + sb.getNombre_de_bloque() + "/" + t.getNombre());
                }
            }
        }
    }
}
