package clases;

import java.util.ArrayList;

/**
 * Created by Jimena on 24-06-2016.
 */
public class Vendedor {
    private int idVendedor;
    private String nombre;
    private String login;
    private String password;
    private ArrayList<Cliente> clientes;

    //Getter y Setter de los atributos


    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    //Lista de los vendedores
    public ArrayList<Vendedor> listaVendedores(){
        Vendedor vendedor=new Vendedor();
        ArrayList<Vendedor> listaVendedores=new ArrayList<Vendedor>();
        //Vendedor 1
        vendedor.setIdVendedor(1);
        vendedor.setNombre("Ana Rosales");
        vendedor.setLogin("arosales");
        vendedor.setPassword("1234");
        //Clientes para el vendedor 1
        ArrayList<Cliente> listaClientes=new ArrayList<Cliente>();
        Cliente cliente=new Cliente();
        cliente.setIdCliente(100);
        cliente.setNombre("Kiosco Barria");
        cliente.setDireccion("Prat 333");
        cliente.setTelefono("22334455");
        listaClientes.add(cliente);
        cliente=new Cliente();
        cliente.setIdCliente(101);
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
        cliente.setIdCliente(200);
        cliente.setNombre("Almacen Bulnes");
        cliente.setDireccion("Bulnes 567");
        cliente.setTelefono("22004400");
        listaClientes.add(cliente);
        cliente=new Cliente();
        cliente.setIdCliente(201);
        cliente.setNombre("Kiosco Carlitos");
        cliente.setDireccion("Portugal 987");
        cliente.setTelefono("22446688");
        listaClientes.add(cliente);
        //Agrego la lista de clientes al vendedor
        vendedor.setClientes(listaClientes);
        //Agrego el vendedor a la lista de vendedores
        listaVendedores.add(vendedor);

        return listaVendedores;


    }
    //Validar login y password
    public int validarLogin(String login, String password){
        Vendedor vendedor;
        ArrayList<Vendedor> vendedores=listaVendedores();
        int tam=vendedores.size();
        for(int i=0;i<tam;i++){     //recorrer la lista de vendedores
            vendedor=vendedores.get(i);
            if(vendedor.getLogin().equals(login) && vendedor.getPassword().equals(password)){ //El login y la password coinciden
                return vendedor.getIdVendedor();
            }
        }


        return 0; //Si no lo encuentra
    }
    //Devolver un vendedor
    public Vendedor getVendedor(int id){
        Vendedor vendedor=null;
        ArrayList<Vendedor> vendedores=listaVendedores();
        int tam=vendedores.size();
        for(int i=0;i<tam;i++) {     //recorrer la lista de vendedores
            vendedor = vendedores.get(i);
            if(vendedor.getIdVendedor()==id)
                return vendedor;
        }
        return null;
    }


    // Sobreescribir toString


    @Override
    public String toString() {
        return "Vendedor [" + idVendedor + "]: " + nombre + "(" + login + ")";
    }


}
