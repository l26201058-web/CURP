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
        Scanner sc = new Scanner(System.in);

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido paterno: ");
        String paterno = sc.nextLine();
        System.out.print("Apellido materno: ");
        String materno = sc.nextLine();
        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
        String fecha = sc.nextLine();
        System.out.print("Sexo (H/M): ");
        char sexo = sc.next().toUpperCase().charAt(0);
        sc.nextLine(); // Limpiar buffer
        System.out.print("Entidad federativa: ");
        String estado = sc.nextLine();

        String curp = generarCURP(nombre, paterno, materno, fecha, sexo, estado);

        System.out.println("\nCURP Generada: " + curp);
    }


    public static char obtenerPrimeraLetra(String texto) {
        return texto.toUpperCase().charAt(0);
    }

    public static char obtenerPrimeraVocal(String texto) {
        String t = texto.toUpperCase();
        for (int i = 1; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return c;
            }
        }
        return 'X';
    }

    public static char obtenerPrimeraConsonante(String texto) {
        String t = texto.toUpperCase();
        for (int i = 1; i < t.length(); i++) {
            char c = t.charAt(i);
            if (c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && Character.isLetter(c)) {
                return c;
            }
        }
        return 'X';
    }

    public static String obtenerClaveEstado(String estado) {
        switch (estado.toUpperCase()) {
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

    public static String generarCURP(String nombre, String paterno, String materno, String fecha, char sexo, String estado) {
        // Extraer partes de la fecha (DD/MM/AAAA)
        String dia = fecha.substring(0, 2);
        String mes = fecha.substring(3, 5);
        String anio = fecha.substring(8, 10);

        char c1 = obtenerPrimeraLetra(paterno);
        char c2 = obtenerPrimeraVocal(paterno);
        char c3 = obtenerPrimeraLetra(materno);
        char c4 = obtenerPrimeraLetra(nombre);

        char c14 = obtenerPrimeraConsonante(paterno);
        char c15 = obtenerPrimeraConsonante(materno);
        char c16 = obtenerPrimeraConsonante(nombre);

        String claveEdo = obtenerClaveEstado(estado);
        Random rand = new Random();
        int rnd1 = rand.nextInt(10);
        int rnd2 = rand.nextInt(10);
        // Unir todo con concatenación simple (se agregan "74" como homoclave simulada)
        return "" + c1 + c2 + c3 + c4 + anio + mes + dia + sexo + claveEdo + c14 + c15 + c16 + rnd1 + rnd2;
    }
}