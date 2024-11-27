import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HilosMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Crear lista de productos disponibles con precios en pesos colombianos
        List<Producto> productosDisponibles = new ArrayList<>();
        productosDisponibles.add(new Producto("Arroz", 5000));
        productosDisponibles.add(new Producto("Leche", 3000));
        productosDisponibles.add(new Producto("Huevos", 1200));
        productosDisponibles.add(new Producto("Aceite", 7000));
        productosDisponibles.add(new Producto("Pan", 2500));
        productosDisponibles.add(new Producto("Azúcar", 2200));
        productosDisponibles.add(new Producto("Café", 4500));
        productosDisponibles.add(new Producto("Pollo", 10000));
        productosDisponibles.add(new Producto("Papa", 1800));
        productosDisponibles.add(new Producto("Tomate", 3000));
        productosDisponibles.add(new Producto("Cebolla", 2000));
        productosDisponibles.add(new Producto("Manzana", 4000));
        productosDisponibles.add(new Producto("Plátano", 1500));
        productosDisponibles.add(new Producto("Carne", 8000));
        productosDisponibles.add(new Producto("Frijoles", 3500));
        productosDisponibles.add(new Producto("Salsa", 1800));

        // Mostrar lista de productos disponibles
        System.out.println("Lista de productos disponibles:");
        for (int i = 0; i < productosDisponibles.size(); i++) {
            Producto producto = productosDisponibles.get(i);
            System.out.println((i + 1) + ". " + producto.getNombre() + " - $" + producto.getPrecio());
        }

        // Crear lista de clientes
        List<ClienteProducto> clientes = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            System.out.println("Ingrese los productos para el Cliente " + i + " (números separados por coma, una línea por cliente):");
            List<Producto> productosCliente = new ArrayList<>();
            boolean entradaValida = false;
            while (!entradaValida) {
                String entrada = scanner.nextLine();
                String[] partes = entrada.split(",");
                for (String parte : partes) {
                    try {
                        int indiceProducto = Integer.parseInt(parte.trim()) - 1;
                        if (indiceProducto >= 0 && indiceProducto < productosDisponibles.size()) {
                            Producto productoSeleccionado = productosDisponibles.get(indiceProducto);
                            productosCliente.add(new Producto(productoSeleccionado.getNombre(), productoSeleccionado.getPrecio()));
                            entradaValida = true;
                        } else {
                            System.out.println("¡Producto no válido! Por favor, seleccione un número de producto de la lista.");
                            entradaValida = false;
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("¡Entrada inválida! Por favor, ingrese solo números separados por comas.");
                        entradaValida = false;
                        break;
                    }
                }
            }
            clientes.add(new ClienteProducto("Cliente " + i, productosCliente));
        }

        // Crear y asignar cajeras
        Cajera1 cajera1 = new Cajera1("Cajera 1", clientes.get(0));
        Cajera1 cajera2 = new Cajera1("Cajera 2", clientes.get(1));
        Cajera1 cajera3 = new Cajera1("Cajera 3", clientes.get(2));

        // Iniciar el proceso de cobro
        cajera1.start();
        cajera2.start();
        cajera3.start();

        // Esperar a que todas las cajeras terminen
        try {
            cajera1.join();
            cajera2.join();
            cajera3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Todas las cajeras han terminado de procesar las compras.");
        scanner.close();
    }
}
