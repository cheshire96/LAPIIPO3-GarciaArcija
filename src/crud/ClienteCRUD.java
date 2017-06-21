package crud;


import java.sql.*;
import java.util.*;

import excepciones.BaseDatosException;
import excepciones.ConexionException;
import excepciones.DatoInvalidoException;

import tp.Cliente;
import tp.Conexion;

public class ClienteCRUD {
	private Connection conn;
	//constructor
	public ClienteCRUD() throws ConexionException{
        try {
        	//se establece conexion con la base de datos empresa
            conn = Conexion.getConnection();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ConexionException("No se pudo conectar al servidor", ex);
        }
    }

	
//-----------------------------------------------
//|		LISTAR CLIENTES EN LA BASE DE DATOS		|
//-----------------------------------------------
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
    		Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM clientes ORDER BY dni ASC");
            while (rs.next()) {
            	
                Cliente cliente = new Cliente(0, null, null, null, null, null, null);
                cliente.setDni(rs.getInt("dni"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setLocalidad(rs.getString("localidad"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new BaseDatosException(e.getMessage(), e);
        }

        return clientes;
    }
	

//-------------------------------------------------------
//|		AGREGAR NUEVO CLIENTE EN LA BASE DE DATOS		|
//-------------------------------------------------------
    public void agregarCliente(Cliente cliente) throws DatoInvalidoException{
        try {
        	java.util.Date d= cliente.getFechaNacimiento();
        	 java.sql.Date sqlDate = new java.sql.Date(d.getTime());

            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO clientes(dni,apellido,nombre,fecha_nacimiento,genero,domicilio,localidad) VALUES (?, ?, ?, ?, ?, ?, ? )");
            // Parametros empizan en 1
            preparedStatement.setInt(1, cliente.getDni());
            preparedStatement.setString(2, cliente.getApellido());
            preparedStatement.setString(3, cliente.getNombre());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setString(5,cliente.getGenero());
            preparedStatement.setString(6, cliente.getDomicilio());
            preparedStatement.setString(7, cliente.getLocalidad());
            
            preparedStatement.executeUpdate();
            System.out.println("Se agrego al cliente exitosamente");
        } catch(NullPointerException ex){
        	throw new DatoInvalidoException("Dato ingresado nulo", ex);
        }catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }

    
    
//---------------------------------------------------
//|		MODIFICAR CLIENTES EN LA BASE DE DATOS		|
//---------------------------------------------------
    public void modificarCliente(Cliente cliente) throws DatoInvalidoException{
        try {
        	java.util.Date d= cliente.getFechaNacimiento();
        	java.sql.Date sqlDate = new java.sql.Date(d.getTime());

            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE clientes SET apellido=?, nombre=?, fecha_nacimiento=?, genero=?, domicilio=?, localidad=? WHERE dni=?");
            // Parameters start with 1
            preparedStatement.setString(1, cliente.getApellido());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4,cliente.getGenero());
            preparedStatement.setString(5, cliente.getDomicilio());
            preparedStatement.setString(6, cliente.getLocalidad());
            preparedStatement.setInt(7, cliente.getDni());
            preparedStatement.executeUpdate();
            System.out.println("Se modifico al cliente exitosamente");
        }catch(NullPointerException ex){
        	throw new DatoInvalidoException("Dato ingresado nulo", ex);
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }


//---------------------------------------------------
//|		ELIMINAR CLIENTE EN LA BASE DE DATOS		|
//---------------------------------------------------

    public void borrarCliente(int dni) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM clientes WHERE dni=?");
            // Parameters start with 1
            preparedStatement.setInt(1, dni);
            preparedStatement.executeUpdate();
            System.out.println("Se elmino al cliente "+dni);
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }

    
    
//-----------------------------------------------
//|		BUSCAR CLIENTE EN LA BASE DE DATOS		|
//-----------------------------------------------
    
    public Cliente buscarCliente(int dni) {
        Cliente cliente = new Cliente(dni, null, null, null, null, null, null);
        try {
            String query = "SELECT * FROM clientes WHERE dni=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt(1, dni);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                cliente.setDni(rs.getInt("dni"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                cliente.setGenero(rs.getString("genero"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setLocalidad(rs.getString("localidad"));
            }
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }

        return cliente;
    }

}
