package actividad14.unidad3.inacap.cl.proyectoversiones;

import org.junit.Test;

import clases.Cliente;
import clases.ListaVendedores;
import clases.Vendedor;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class VendedorClienteUnitTest {

    @Test
    public void agregarCliente_esCorrecto() throws Exception{

        Vendedor vendedor=new Vendedor();
        Cliente cliente=new Cliente("Test","Direccion","112233",vendedor);
       // vendedor.addCliente(cliente);
        ListaVendedores listaVendedores=ListaVendedores.getInstancia();
        listaVendedores.addCliente(cliente,vendedor);
        String idCliente=cliente.getIdCliente();
        assertEquals(true,vendedor.getCliente(idCliente).isActivo());

    }

    @Test
    public void eliminarCliente_esCorrecto() throws Exception{

        Vendedor vendedor=new Vendedor();
        Cliente cliente=new Cliente("Test","Direccion","111",vendedor);
        cliente.setNombre("Test");
        vendedor.addCliente(cliente);
        String idCliente=cliente.getIdCliente();
        vendedor.dropCliente(idCliente);
        assertEquals(false,vendedor.getCliente(idCliente).isActivo());
    }

}