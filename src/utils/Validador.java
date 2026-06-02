package utils;

import java.util.regex.Pattern;

public class Validador {

    // Validar precio y stock positivos

    public static boolean validarStock(int stock){
        return stock > 0;
    }

    public static boolean validarPrecio(double precio){
        return precio > 0;
    }

    // validar formato del correo

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static boolean validarCorreo(String correo){
        if (correo==null) return false;
        return Pattern.matches(EMAIL_REGEX, correo);
    }

    // validar numero de identificacion unico

    public static boolean validarNumero(String texto){
        if (texto == null)return false;
        return texto.matches("\\d+");
    }
}
