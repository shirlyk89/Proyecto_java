package patron;

public class SinDescuento implements EstrategiaDescuento {
    @Override
    public double aplicarDescuento(double subtotal) {
        return subtotal; // Devuelve el mismo valor, no aplica rebaja
    }
}
