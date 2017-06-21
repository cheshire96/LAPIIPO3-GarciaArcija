package tp;

import java.util.Date;

public interface Persona {
		
	public int getDni();

	public void setDni(int dni);

	public String getApellido();

	public void setApellido(String apellido);

	public String getNombre();

	public void setNombre(String nombre);

	public String getDomicilio();

	public Date getFechaNacimiento();

	public void setFechaNacimiento(Date fechaNacimiento);
	
	public void setDomicilio(String domicilio);

	public String getLocalidad();

	public void setLocalidad(String localidad);
		
	public String getGenero();
	
	public void setGenero(String genero);
	
	public void ingresar();
	
	public void modificar();
	
	public void eliminar();
	
	public void listar();
}
