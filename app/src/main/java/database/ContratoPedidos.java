package database;

import java.util.UUID;

/**
 * Created by Jimena on 05-07-2016.
 */
public class ContratoPedidos {
    interface ColumnasPedido {
        String ID = "id";
        String ID_CLIENTE = "id_cliente";
        String FECHA = "fecha_entrega";
        String PRECIO="precio";
        String ENTREGADO = "entregado";
        String ID_VENDEDOR="id_vendedor";
    }
    interface ColumnasDetallePedido {
        String ID = "id";
        String SECUENCIA = "secuencia";
        String ID_PRODUCTO = "id_producto";
        String CANTIDAD = "cantidad";
        String PRECIO = "precio";
    }
    interface ColumnasProducto {
        String ID = "id";
        String NOMBRE = "nombre";
        String PRECIO = "precio";

    }
    interface ColumnasCliente {
        String ID = "id";
        String NOMBRE = "nombre";
        String DIRECCION = "direccion";
        String TELEFONO = "telefono";
        String ID_VENDEDOR="id_vendedor";
        String ACTIVO="activo";
    }
    interface ColumnasVendedor {
        String ID="id";
        String NOMBRE="nombre";
        String LOGIN="login";
        String PASSWORD="password";
        String MONTO_PEDIDO="monto_pedido";
        String MONTO_COBRADO="monto_cobrado";
        String SALDO="saldo";
    }
    public static class Pedidos implements ColumnasPedido {
        public static String generarIdPedido() {
            return "CP-" + UUID.randomUUID().toString();
        }
    }
    public static class DetallesPedido implements ColumnasDetallePedido {
        // Métodos auxiliares
    }
    public static class Productos implements ColumnasProducto{
        public static String generarIdProducto() {
            return "PRO-" + UUID.randomUUID().toString();
        }
    }
    public static class Clientes implements ColumnasCliente{
        public static String generarIdCliente() {
            return "CLI-" + UUID.randomUUID().toString();
        }
    }
    public static class Vendedores implements ColumnasVendedor{
        public static String generarIdVendedor(){
            return "VEN-" + UUID.randomUUID().toString();
        }
    }
    private ContratoPedidos() {
    }


}
