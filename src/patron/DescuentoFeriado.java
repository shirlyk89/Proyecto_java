package patron;

public class DescuentoFeriado implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double subtotal) {
        return subtotal * 0.90; // Descuenta el 10%
    }
}
