public class Cajera1 extends Thread {
    private String nombre;
    private ClienteProducto cliente;

    public Cajera1(String nombre, ClienteProducto cliente) {
        this.nombre = nombre;
        this.cliente = cliente;
    }

    @Override
    public void run() {
        System.out.println(nombre + " comienza a procesar la compra de " + cliente.getNombre());
        for (Producto producto : cliente.getProductos()) {
            System.out.println(nombre + " procesando producto " + producto.getNombre() + " - $" + producto.getPrecio());
            try {
                Thread.sleep(1000); // Simula el tiempo de procesamiento de cada producto
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        double total = cliente.totalCompra();
        System.out.println(nombre + " terminó de procesar la compra de " + cliente.getNombre() + ". Total a pagar: $" + total);
    }
}

