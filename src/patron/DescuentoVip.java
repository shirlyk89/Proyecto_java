package patron;

public class DescuentoVIP implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double subtotal) {
        return subtotal * 0.85; // Descuenta el 15%
    }
}
