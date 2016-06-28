package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 27-06-2016.
 */
public class ListaVendedores {
    private static ListaVendedores instancia=new ListaVendedores();
    private ArrayList<Vendedor> listaVendedores;

    public static ListaVendedores getInstancia(){
        return instancia;
    }

    //Constructor
    private ListaVendedores(){
        listaVendedores=new ArrayList<Vendedor>();
        //Vendedor 1
        Vendedor vendedor=new Vendedor();
        vendedor.setIdVendedor(1);
        vendedor.setNombre("Ana Rosales");
        vendedor.setLogin("arosales");
        vendedor.setPassword("1234");
        //Clientes para el vendedor 1
        ArrayList<Cliente> listaClientes=new ArrayList<Cliente>();
        Cliente cliente=new Cliente();
        cliente.setNombre("Kiosco Barria");
        cliente.setDireccion("Prat 333");
        cliente.setTelefono("22334455");
        listaClientes.add(cliente);
        cliente=new Cliente();
        cliente.setNombre("Almacen Flores");
        cliente.setDireccion("San Diego 444");
        cliente.setTelefono("22112211");
        listaClientes.add(cliente);
        //Agrego la lista de clientes al vendedor
        vendedor.setClientes(listaClientes);
        //Agrego el vendedor a la lista de vendedores
        listaVendedores.add(vendedor);

        //Vendedor 2
        vendedor=new Vendedor();
        vendedor.setIdVendedor(2);
        vendedor.setNombre("Pedro Soto");
        vendedor.setLogin("psoto");
        vendedor.setPassword("1234");
        //Clientes para el vendedor 2
        listaClientes=new ArrayList<Cliente>();
        cliente=new Cliente();
        cliente.setNombre("Almacen Bulnes");
        cliente.setDireccion("Bulnes 567");
        cliente.setTelefono("22004400");
        listaClientes.add(cliente);
        cliente=new Cliente();
        cliente.setNombre("Kiosco Carlitos");
        cliente.setDireccion("Portugal 987");
        cliente.setTelefono("22446688");
        listaClientes.add(cliente);
        //Agrego la lista de clientes al vendedor
        vendedor.setClientes(listaClientes);
        //Agrego el vendedor a la lista de vendedores
        listaVendedores.add(vendedor);


    }
    //Getter y Setter
    public ArrayList<Vendedor> getListaVendedores() {
        return listaVendedores;
    }

    public void setListaVendedores(ArrayList<Vendedor> vendedores) {
        this.listaVendedores = vendedores;
    }

    //Validar login y password
    public int validarLogin(String login, String password){
        Vendedor vendedor;
        int tam=listaVendedores.size();
        for(int i=0;i<tam;i++){     //recorrer la lista de vendedores
            vendedor=listaVendedores.get(i);
            if(vendedor.getLogin().equals(login) && vendedor.getPassword().equals(password)){ //El login y la password coinciden
                return vendedor.getIdVendedor();
            }
        }


        return 0; //Si no lo encuentra
    }
    //Devolver un vendedor
    public Vendedor getVendedor(int id){
        Vendedor vendedor=null;
        int tam=listaVendedores.size();
        for(int i=0;i<tam;i++) {     //recorrer la lista de vendedores
            vendedor = listaVendedores.get(i);
            if(vendedor.getIdVendedor()==id)
                return vendedor;
        }
        return null;
    }


}
