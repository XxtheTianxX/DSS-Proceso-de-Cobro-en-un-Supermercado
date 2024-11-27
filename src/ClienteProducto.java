import java.util.List;
import java.util.stream.Collectors;

public class ClienteProducto {
    private String nombre;
    private final List<Producto> productos;

    public ClienteProducto(String nombre, List<Producto> productos) {
        this.nombre = nombre;
        this.productos = productos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double totalCompra() {
        return productos.stream().mapToDouble(Producto::getPrecio).sum();
    }

    @Override
    public String toString() {
        String productosStr = productos.stream()
                                       .map(Producto::toString)
                                       .collect(Collectors.joining(", "));
        return "Cliente " + nombre + " compró: " + productosStr;
    }
}