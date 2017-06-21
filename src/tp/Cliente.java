package tp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crud.ClienteCRUD;
import excepciones.ClienteInvalidoException;
import excepciones.DatoInvalidoException;
import excepciones.DocumentoInvalidoException;
import excepciones.ListadoException;
import excepciones.OpcionInvalidaException;

public class Cliente implements Persona{
	
	protected int dni;
	protected String apellido;
	protected String nombre;
	protected Date fechaNacimiento;
	protected String genero;
	protected String domicilio;
	protected String localidad;
	
//contructor clase Cliente	
	public Cliente(int dni, String apellido, String nombre, Date fechaNacimiento, String genero, String domicilio, String localidad) {
		
		//hacer try catch para:
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.genero=genero;
		this.domicilio = domicilio;
		this.localidad = localidad;
		
	}

//Getters y Setters
	public int getDni() {
		return dni;
	}

	public void setDni(int dni) throws DocumentoInvalidoException  {
		if (dni < 1000000 || dni > 99999999){
			throw new DocumentoInvalidoException("El numero de documento " + dni + " es inv�lido");
		}
		
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) throws DatoInvalidoException{
		if(apellido== null||apellido.trim().isEmpty()){
			throw new DatoInvalidoException("El apellido ' "+apellido+" ' no es valido");
		}
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if(nombre== null||nombre.trim().isEmpty()){
			throw new DatoInvalidoException("El genero ' "+nombre+" ' no es valido");
		}
		this.nombre = nombre;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		if(fechaNacimiento== null){
			throw new DatoInvalidoException("La fecha de nacimiento no tiene que ser nulo");
		}
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) throws DatoInvalidoException{
		if(genero== null||genero.trim().isEmpty()||genero!="M"||genero!="F"){
			throw new DatoInvalidoException("El genero ' "+genero+" ' no es valido");
		}
		this.genero = genero;
	}
	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) throws DatoInvalidoException{
		if(domicilio== null||domicilio.trim().isEmpty()){
			throw new DatoInvalidoException("El domicilio ' "+domicilio+" ' no es valido");
		}
		this.domicilio = domicilio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) throws DatoInvalidoException{
		if(localidad== null||localidad.trim().isEmpty()){
			throw new DatoInvalidoException("La localidad ' "+localidad+" ' no es valida");
		}
		this.localidad = localidad;
	}

//LISTAR LOS CLIENTES
	public void listar()throws ListadoException{
		ClienteCRUD cl= new ClienteCRUD();
		List<Cliente> clientes = new ArrayList<Cliente>();
		//obtiene a los clientes de la base de datos
		clientes=cl.listarClientes();
		//si no hay clientes en la base de datos lanza una excepcion
		if(clientes.size()==0){
			throw new ListadoException("No hay clientes guardados");
		}
		System.out.println("|     Numero de Documento     |		Apellido/s		|		Nombre/s		|  Fecha de Nacimiento  |  Genero  |		Domicilio		|		Localidad		|");
		//recorre la lista de clientes
		for(int i=0;i<clientes.size();i++){
			Cliente c=clientes.get(i);
			//Imprime al cliente
			System.out.println("|\t\t "+c.dni+" \t\t|\t\t "+c.apellido+" \t\t|\t\t "+c.nombre+" \t\t|\t\t "+c.fechaNacimiento+" \t\t|\t\t "+c.genero+" \t\t|\t\t "+c.domicilio+" \t\t|\t\t "+c.localidad+"\t\t|");
		}
		
	}

//INGRESAR NUEVO EMPLEADO
	public void ingresar() throws DatoInvalidoException,ClienteInvalidoException{
			try{
				
			String cNombre;
			String cApellido;
			int cDNI;
			Date cFNacimiento=null;
			String cGenero;
			String cDomicilio;
			String cLocalidad;
			String f=null;
			ClienteCRUD cn=new ClienteCRUD();
	
			
			//obtener el documento del cliente
			cDNI=Integer.parseInt(Menu.obtenerDatos("Ingresar numero de documento"));
			//obtener apellido del cliente
			cApellido=Menu.obtenerDatos("Ingresar Apellido/s");
			//obtener nombre del cliente
			cNombre=Menu.obtenerDatos("Ingresar Nombre/s");
			//obtener fecha nacimiento del cliente
			f=Menu.obtenerDatos("Ingresar Fecha de Nacimiento(aaaa-mm-dd)");
			//obtener el genero
			cGenero=Menu.obtenerDatos("Ingresar genero");
			//obtener domicilio del cliente
			cDomicilio=Menu.obtenerDatos("Ingresar Domicilio");
			//obtener localidad del cliente
			cLocalidad=Menu.obtenerDatos("Ingresar Localidad");
			
	//----------------------------------------------------------		
	//obtener la fecha de nacimiento
			
			//Pasar de un String a Date la fecha de nacimiento
			SimpleDateFormat fecha=new SimpleDateFormat("yyyy-MM-dd");
			
			
			try{
				cFNacimiento=fecha.parse(f);
			}catch(ParseException ex){
				throw new DatoInvalidoException("La fecha ingresada no es valida", ex);
			}
			
	//----------------------------------------------------------		
			
	//Crea un nuevo objeto tipo Cliente con los datos del cliente ingresado
			Cliente a=new Cliente(cDNI, cApellido, cNombre, cFNacimiento ,cGenero, cDomicilio, cLocalidad);
			System.out.println("DNI: "+a.dni+"\tApellido: "+a.apellido+"\tNombre: "+a.nombre+"\tFecha de Nacimiento: "+a.fechaNacimiento+"\tGenero: "+a.genero+"\tDomicilio: "+a.domicilio+"\tLocalidad: "+a.localidad);
			
			
			//verifica si el cliente con ese numero de dni no exista
			Cliente bCliente=cn.buscarCliente(cDNI);
			if(bCliente.apellido!=null){//si existe lanza una excepcion
					throw new ClienteInvalidoException("El cliente con el numero de documento: "+cDNI+"ya esta cargado");
			}
			
			//Carga los datos del objeto cliente en la base de datos 
			cn.agregarCliente(a);
			}catch(NumberFormatException ex){
				throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero",ex);
			}		

		
	}

//MODIFICAR CLIENTE
	public void modificar() throws DatoInvalidoException,OpcionInvalidaException{
		try{
			ClienteCRUD cn=new ClienteCRUD();
			//buscar al cliente
			Cliente cmb =buscar(cn, "Ingrese el numero de documento del cliente a modificar");
			
			//Imprimir los datos del cliente que se recupero
			System.out.println("DNI: "+cmb.dni+"\tApellido: "+cmb.apellido+"\tNombre: "+cmb.nombre+"\tFecha de Nacimiento: "+cmb.fechaNacimiento+"\tGenero: "+cmb.genero+"\tDomicilio: "+cmb.domicilio+"\tLocalidad: "+cmb.localidad);
			
			//Menu de que atributo se busca modificar
			int opcion=1;
			String modificado;
			while((opcion!=0) && (opcion<7)){
				System.out.println ("--------------------------------------------\n|\t\tMODIFICAR:\t\t|\n--------------------------------------------\n");
				opcion=Integer.parseInt(Menu.obtenerDatos("1 Apellido/s\t2 Nombre/s\t3 Fecha de Nacimiento\t4 Genero\t5 Domicilio\t6 Localidad"));
				//llama al set de cada atributo segun el caso
				switch (opcion) {
				case 1:
					modificado=Menu.obtenerDatos("Ingrese el/los nuevo/s Apellido/s");
					//carga el nuevo Apellido del cliente
					cmb.setApellido(modificado);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				case 2:
					modificado=Menu.obtenerDatos("Ingrese el/los nuevo/s  Nombre/s");
					//carga el nuevo Nombre del cliente
					cmb.setNombre(modificado);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				case 3:
					Date emFNacimiento=null;
					modificado=Menu.obtenerDatos("Ingrese la nueva fecha de nacimiento(aaaa-mm-dd)");
					SimpleDateFormat fecha=new SimpleDateFormat("yyyy-MM-dd");
					try{
						emFNacimiento=fecha.parse(modificado);
					}catch(ParseException ex){
						throw new DatoInvalidoException("La fecha ingresada no es valida", ex);
					}
					//carga la nueva fecha de nacimiento del cliente
					cmb.setFechaNacimiento(emFNacimiento);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				case 4:
					modificado=Menu.obtenerDatos("Ingrese el nuevo genero");
					//carga el nuevo Genero del cliente
					cmb.setGenero(modificado);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				case 5:
					modificado=Menu.obtenerDatos("Ingrese el nuevo domicilio");
					//carga el nuevo Domicilio del cliente
					cmb.setDomicilio(modificado);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				case 6:
					modificado=Menu.obtenerDatos("Ingrese el nuevo localidad");
					//carga el nuevo Localidad del cliente
					cmb.setLocalidad(modificado);
					opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del cliente?\n1 Si\t2 No"));
					//Si elige que no desea modificar algun dato
					if (opcion==2){
						opcion=0;//se pone la opcion en 0 para indicar que ya no hay que modificar ningun dato
					}
					break;
				default:
					throw new OpcionInvalidaException("La opcion ingresada no es valida");
				}
			}
			//se modifica al cliente
			cn.modificarCliente(cmb);
		}catch(NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}
	}

//---------------------------
//|		BUSCAR CLIENTE		|
//---------------------------
		public static Cliente buscar(ClienteCRUD c, String mensaje)throws DatoInvalidoException,ClienteInvalidoException{
			try{	
				int dato;
				//obtiene el numero de dni o de legajo del empleado
				dato=Integer.parseInt(Menu.obtenerDatos(mensaje));
				//busca al empleado en la base de datos
				Cliente cb =c.buscarCliente(dato);
				//verifica si el cliente existe
				if(cb.apellido==null){
					throw new ClienteInvalidoException("El cliente con el numero de documento: "+cb.dni+" no existe");
				}
				//devuelve al empleado que se encontro
				return cb;
			}catch(NumberFormatException ex){
				throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
			}
		}

//-------------------------------
//|		ELIMINAR CLIENTE		|
//-------------------------------

	public void eliminar() throws DatoInvalidoException,ClienteInvalidoException{
		try{
			ClienteCRUD cd=new ClienteCRUD();
			int cDNI;
			cDNI=Integer.parseInt(Menu.obtenerDatos("Ingresar Numero de Documento del Cliente"));
			Cliente c=cd.buscarCliente(cDNI);
			if(c.apellido==null){
				throw new ClienteInvalidoException("El cliente con el numero de documento: "+c.dni+" no existe");
			}
			cd.borrarCliente(cDNI);
		}catch(NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero",ex);
		}
	}

}
