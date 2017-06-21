package crud;

import java.sql.*;
import java.util.*;

import excepciones.BaseDatosException;
import excepciones.ConexionException;

import tp.Conexion;
import tp.Empleado;

public class EmpleadoCRUD {
	private Connection conn;
	//constructor
	public EmpleadoCRUD() {
        try {
        	//se establece conexion con la base de datos empresa
            conn = Conexion.getConnection();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new ConexionException(ex.getMessage(),  ex);
        }
    }

	
//---------------------------------------------------
//|		LISTAR EMPLEADOS EN LA BASE DE DATOS		|
//---------------------------------------------------
    public List<Empleado> listarEmpleados() {
        List<Empleado> empleados = new ArrayList<Empleado>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM empleados ORDER BY legajo ASC");
            while (rs.next()) {
            	Empleado empleado = new Empleado(0, null, null, null, null, null, null, null, null);
            	empleado.setLegajo(rs.getInt("legajo"));
            	empleado.setDni(rs.getInt("dni"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                empleado.setGenero(rs.getString("genero"));
                empleado.setDomicilio(rs.getString("domicilio"));
                empleado.setLocalidad(rs.getString("localidad"));
                empleado.setSector(rs.getString("sector"));
                empleado.setPuesto(rs.getString("puesto"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }

        return empleados;
    }
	

//-------------------------------------------------------
//|		AGREGAR NUEVO EMPLEADO EN LA BASE DE DATOS		|
//-------------------------------------------------------
    public void agregarEmpleado(Empleado empleado) {
        try {
        	java.util.Date d= empleado.getFechaNacimiento();
        	java.sql.Date sqlDate = new java.sql.Date(d.getTime());

        	PreparedStatement preparedStatement = conn
            		.prepareStatement("INSERT INTO empleados(legajo,dni,apellido,nombre,fecha_nacimiento,genero,domicilio,localidad,sector,puesto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            
            preparedStatement.setInt(1, empleado.getLegajo());
            preparedStatement.setInt(2, empleado.getDni());
            preparedStatement.setString(3, empleado.getApellido());
            preparedStatement.setString(4, empleado.getNombre());
            preparedStatement.setDate(5, sqlDate);
            preparedStatement.setString(6,empleado.getGenero());
            preparedStatement.setString(7, empleado.getDomicilio());
            preparedStatement.setString(8, empleado.getLocalidad());
            preparedStatement.setString(9, empleado.getSector());
            preparedStatement.setString(10, empleado.getPuesto());
            
            preparedStatement.executeUpdate();
            System.out.println("Se agrego al empleado exitosamente");

        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }

    
    
//---------------------------------------------------
//|		MODIFICAR EMPLEADO EN LA BASE DE DATOS		|
//---------------------------------------------------
    public void modificarEmpleado(Empleado empleado) {
        try {
        	java.util.Date d= empleado.getFechaNacimiento();
        	java.sql.Date sqlDate = new java.sql.Date(d.getTime());

            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE empleados SET apellido=?, nombre=?, fecha_nacimiento=?, genero=?, domicilio=?, localidad=?, sector=?, puesto=? WHERE dni=? OR legajo=?");
        
            preparedStatement.setString(1, empleado.getApellido());
            preparedStatement.setString(2, empleado.getNombre());
            preparedStatement.setDate(3, sqlDate);
            preparedStatement.setString(4, empleado.getGenero());
            preparedStatement.setString(5, empleado.getDomicilio());
            preparedStatement.setString(6, empleado.getLocalidad());
            preparedStatement.setString(7, empleado.getSector());
            preparedStatement.setString(8, empleado.getPuesto());
            preparedStatement.setInt(9, empleado.getDni());
            preparedStatement.setInt(10, empleado.getLegajo());
            preparedStatement.executeUpdate();

            System.out.println("Se modifico al empleado exitosamente");
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }


//---------------------------------------------------
//|		ELIMINAR EMPLEADO EN LA BASE DE DATOS		|
//---------------------------------------------------

    public void borrarEmpleado(int dni) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM empleado WHERE dni=?");
           
            preparedStatement.setInt(1, dni);
            preparedStatement.executeUpdate();
            System.out.println("Se elmino al empleado "+dni);
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }

    public void borrarEmpleadoLegajo(int legajo) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM empleado WHERE legajo=?");
           
            preparedStatement.setInt(1, legajo);
            preparedStatement.executeUpdate();
            System.out.println("Se elmino al empleado "+legajo);

        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
    }
    
//---------------------------------------------------------------
//|		BUSCAR EMPLEADO POR DOCUMENTO EN LA BASE DE DATOS		|
//---------------------------------------------------------------
      
	public Empleado buscarEmpleadoDNI(int dni) {
    	Empleado empleado = new Empleado(dni, null, null, null, null, null, null, null, null);
    			
        try {
        	String query = "SELECT * FROM empleados WHERE dni=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt(1, dni);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	empleado.setLegajo(rs.getInt("legajo"));
                empleado.setDni(rs.getInt("dni"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                empleado.setGenero(rs.getString("genero"));
                empleado.setDomicilio(rs.getString("domicilio"));
                empleado.setLocalidad(rs.getString("localidad"));
                empleado.setSector(rs.getString("sector"));
                empleado.setPuesto(rs.getString("puesto"));
            }
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }
        
        return empleado;
    }

//-----------------------------------------------------------
//|		BUSCAR EMPLEADO POR LEGAJO EN LA BASE DE DATOS		|
//-----------------------------------------------------------
	      
	public Empleado buscarEmpleadoLegajo(int legajo) {
    	Empleado empleado = new Empleado(0, null, null, null, null, null, null, null, null);
	    			
        try {
        	String query = "SELECT * FROM empleados WHERE legajo=?";
            PreparedStatement preparedStatement = conn.prepareStatement( query );
            preparedStatement.setInt(1, legajo);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
            	empleado.setLegajo(rs.getInt("legajo"));
                empleado.setDni(rs.getInt("dni"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                empleado.setGenero(rs.getString("genero"));
                empleado.setDomicilio(rs.getString("domicilio"));
                empleado.setLocalidad(rs.getString("localidad"));
                empleado.setSector(rs.getString("sector"));
                empleado.setPuesto(rs.getString("puesto"));
            }
        } catch (SQLException e) {
        	throw new BaseDatosException(e.getMessage(), e);
        }

        return empleado;
    }
	
	
	
}
