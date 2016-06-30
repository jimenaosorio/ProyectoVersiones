package actividad14.unidad3.inacap.cl.proyectoversiones;

import org.junit.Test;

import clases.Cliente;
import clases.Vendedor;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class VendedorClienteUnitTest {

    @Test
    public void agregarCliente_esCorrecto() throws Exception{
        Cliente cliente=new Cliente();
        cliente.setNombre("Test");
        Vendedor vendedor=new Vendedor();
        vendedor.addCliente(cliente);
        int idCliente=cliente.getIdCliente();
        assertEquals(true,vendedor.getCliente(idCliente).isActivo());
    }

    @Test
    public void eliminarCliente_esCorrecto() throws Exception{
        Cliente cliente=new Cliente();
        cliente.setNombre("Test");
        Vendedor vendedor=new Vendedor();
        vendedor.addCliente(cliente);
        int idCliente=cliente.getIdCliente();
        vendedor.dropCliente(idCliente);
        assertEquals(false,vendedor.getCliente(idCliente).isActivo());
    }

}