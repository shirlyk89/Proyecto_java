package utils;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
}
