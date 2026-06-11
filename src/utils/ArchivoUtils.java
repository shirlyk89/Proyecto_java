package utils;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class ArchivoUtils {
  public static boolean exportarReporteTxt(String nombreArchivo, String contenidoReporte) {

        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo))) {

            escritor.print(contenidoReporte);
            return true;

        } catch (IOException e) {
            System.out.println(" Error al guardar el archivo: " + e.getMessage());
            return false;
        }
    }
public static void limpiarConsola() {
    System.out.println("\n----------------------------------------------------------------------");
    System.out.println(">>>>>>>>>>>>>>>>>>>> NAVEGACION: % TECNOSTORE % <<<<<<<<<<<<<<<<<<<<<<");
    System.out.println("----------------------------------------------------------------------\n");
}
}
