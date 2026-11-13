import java.util.Scanner;
import java.util.Random;
/*
INSTITUTO TECNOLOGICO DE PACHUCA
FUNDAMENTOS DE PROGRAMACION
EJERCICIO: GENERADOR DE CURP CON METODOS
ALUMNO: DIAZ CERON JOSE LUIS    26201058
13-11-2026
 */
public class GeneradorCurp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nombre, paterno, materno, fecha, entidad, curpFinal;
        char sexo;

        System.out.println("========================================");
        System.out.println("    SISTEMA GENERADOR DE CURP - JAVA    ");
        System.out.println("========================================");

        System.out.print("Nombre(s): ");
        nombre = scanner.nextLine();

        System.out.print("Apellido paterno: ");
        paterno = scanner.nextLine();

        System.out.print("Apellido materno: ");
        materno = scanner.nextLine();

        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
        fecha = scanner.nextLine();

        System.out.print("Sexo (H/M): ");
        sexo = scanner.next().toUpperCase().charAt(0);
        scanner.nextLine(); // Limpiar el buffer de entrada

        System.out.print("Entidad federativa de nacimiento (EN MAYUSCULAS): ");
        entidad = scanner.nextLine();

        curpFinal = generarCURP(nombre, paterno, materno, fecha, sexo, entidad);

        System.out.println("\n----------------------------------------");
        System.out.println("CURP Generada Exitosamente:");
        System.out.println("--> " + curpFinal);
        System.out.println("----------------------------------------");

    }

    // Metodo principal para integrar y generar la CURP
    public static String generarCURP(String nombre, String paterno, String materno, String fechaNac, char sexo, String estado) {
        // Procesar fecha formato DD/MM/AAAA
        String[] partesFecha = fechaNac.split("/");
        String dia = partesFecha[0];
        String mes = partesFecha[1];
        String anio = partesFecha[2].substring(2, 4); // Últimos 2 dígitos del año

        // Obtener caracteres según las reglas de posiciones
        char c1 = obtenerPrimeraLetra(paterno);
        char c2 = obtenerPrimeraVocal(paterno);
        char c3 = obtenerPrimeraLetra(materno);
        char c4 = obtenerPrimeraLetra(nombre);

        char c14 = obtenerPrimeraConsonante(paterno);
        char c15 = obtenerPrimeraConsonante(materno);
        char c16 = obtenerPrimeraConsonante(nombre);

        String claveEstado = obtenerClaveEstado(estado);

        // Generar dígitos aleatorios para posiciones 17 y 18 (homoclave básica)
        Random rand = new Random();
        int rnd1 = rand.nextInt(10);
        int rnd2 = rand.nextInt(10);

        // Construcción final de la cadena CURP
        StringBuilder curp = new StringBuilder();
        curp.append(c1).append(c2).append(c3).append(c4);
        curp.append(anio).append(mes).append(dia);
        curp.append(sexo);
        curp.append(claveEstado);
        curp.append(c14).append(c15).append(c16);
        curp.append(rnd1).append(rnd2);

        return curp.toString();
    }

    // Metodo para obtener la primera letra de una palabra
    public static char obtenerPrimeraLetra(String texto) {
        if (texto == null || texto.isEmpty()) return 'X';
        return Character.toUpperCase(texto.trim().charAt(0));
    }

    // Metodo para encontrar la primera vocal interna (a partir de la posición 1)
    public static char obtenerPrimeraVocal(String texto) {
        if (texto == null || texto.length() < 2) return 'X';
        String limpio = texto.toUpperCase().trim();
        for (int i = 1; i < limpio.length(); i++) {
            char c = limpio.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return c;
            }
        }
        return 'X';
    }

    // Metodo para encontrar la primera consonante interna (a partir de la posición 1)
    public static char obtenerPrimeraConsonante(String texto) {
        if (texto == null || texto.length() < 2) return 'X';
        String limpio = texto.toUpperCase().trim();
        for (int i = 1; i < limpio.length(); i++) {
            char c = limpio.charAt(i);
            // Validar que sea letra y no vocal
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && Character.isLetter(c)) {
                return c;
            }
        }
        return 'X';
    }

    // Metodo para obtener la clave de la entidad federativa
    public static String obtenerClaveEstado(String estado) {
        String est = estado.toUpperCase().trim();
        switch (est) {
            case "AGUASCALIENTES": return "AS";
            case "BAJA CALIFORNIA": return "BC";
            case "BAJA CALIFORNIA SUR": return "BS";
            case "CAMPECHE": return "CC";
            case "CHIAPAS": return "CS";
            case "CHIHUAHUA": return "CH";
            case "CIUDAD DE MEXICO": case "CDMX": return "DF";
            case "COAHUILA": return "CL";
            case "COLIMA": return "CM";
            case "DURANGO": return "DG";
            case "GUANAJUATO": return "GT";
            case "GUERRERO": return "GR";
            case "HIDALGO": return "HG";
            case "JALISCO": return "JC";
            case "ESTADO DE MEXICO": case "MEXICO": return "MC";
            case "MICHOACAN": return "MN";
            case "MORELOS": return "MS";
            case "NAYARIT": return "NT";
            case "NUEVO LEON": return "NL";
            case "OAXACA": return "OC";
            case "PUEBLA": return "PL";
            case "QUERETARO": return "QT";
            case "QUINTANA ROO": return "QR";
            case "SAN LUIS POTOSI": return "SP";
            case "SINALOA": return "SL";
            case "SONORA": return "SR";
            case "TABASCO": return "TC";
            case "TAMAULIPAS": return "TS";
            case "TLAXCALA": return "TL";
            case "VERACRUZ": return "VZ";
            case "YUCATAN": return "YN";
            case "ZACATECAS": return "ZS";
            default: return "NE"; // No especificado / Extranjero
        }
    }
}