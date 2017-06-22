package tp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crud.EmpleadoCRUD;
import excepciones.DatoInvalidoException;
import excepciones.DocumentoInvalidoException;
import excepciones.EmpleadoInvalidoException;
import excepciones.ListadoException;
import excepciones.OpcionInvalidaException;

public class Empleado implements Persona{
	protected static final int MIN=1000;
	protected int dni;
	protected String apellido;
	protected String nombre;
	protected Date fechaNacimiento;
	protected String genero;
	protected String domicilio;
	protected String localidad;
	protected static int legajos=MIN;
	protected String sector;
	protected String puesto;
	protected int legajo;


//--------------------------------------------------------	
//CONSTRUCTOR CLASE EMPLEADO
	public Empleado(int dni, String apellido, String nombre, Date fechaNacimiento, String genero, String domicilio, String localidad,String sector, String puesto) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.genero=genero;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.sector=sector;
		this.puesto=puesto;
		if(dni!=0){
			this.legajo=this.legajos;
			legajos++;
		}
		
	}

	
//----------------------------------------------------------------------------
//GETTERS Y SETTERS
	
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


	public String getSector() {
		return sector;
	}

	public void setSector(String sector) throws DatoInvalidoException {
		this.sector = sector;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) throws DatoInvalidoException{
		this.puesto = puesto;
	}

	public int getLegajo() {
		return legajo;
	}

	public void setLegajo(int legajo) throws DatoInvalidoException{
		if(legajo<MIN){
			throw new DatoInvalidoException("El numero de legajo no es valido");
		}else if(legajo==MIN){
			this.legajo=this.legajos;
			legajos++;
		}
		this.legajo = legajo;
	}

	
	
//----------------------------------------------------------------------------
//METODOS DE LISTAR/INGRESAR/MODIFICAR/ELIMINAR A EMPLEADO	

	
//-------------------------------
//|		LISTAR EMPLEADOS		|
//-------------------------------
	
	public void listar()throws ListadoException{
		EmpleadoCRUD el= new EmpleadoCRUD();
		List<Empleado> empleados = new ArrayList<Empleado>();
		//Se obtienen todos los empleados guardados en la base de datos
		empleados=el.listarEmpleados();
		//Si no hay empleados en la base de datos lanza una excepcion
		if(empleados.size()==0){
			throw new ListadoException("No hay empleados guardados");
		}
		
		System.out.println("|     Legajo     |     Numero de Documento     |		Apellido/s		|		Nombre/s		|  Fecha de Nacimiento  |  Genero  |		Domicilio		|		Localidad		|   Sector   |        Puesto        |");
		//Lista a los empleados de la base de datos
		for(int i=0;i<empleados.size();i++){     
			Empleado e=empleados.get(i);
			System.out.println("|\t\t "+e.legajo+" \t\t|\t\t "+e.dni+" \t\t|\t\t "+e.apellido+" \t\t|\t\t "+e.nombre+" \t\t|\t\t "+e.fechaNacimiento+" \t\t|\t\t "+e.genero+" \t\t|\t\t "+e.domicilio+" \t\t|\t\t "+e.localidad+" \t\t|\t\t "+e.sector+" \t\t|\t\t"+e.puesto+"\t\t|");
		}
	}

//---------------------------------------
//|		INGRESAR UN NUEVO EMPLEADO		|
//---------------------------------------
	
	public void ingresar()throws EmpleadoInvalidoException {
		try{
			EmpleadoCRUD en=new EmpleadoCRUD();
			String eNombre;
			String eApellido;
			int eDNI;
			Date eFNacimiento=null;
			String eGenero;
			String eDomicilio;
			String eLocalidad;
			String eF=null;
			String eSector;
			String ePuesto;
			
			Empleado e=new Empleado(0, null, null, null, null, null, null, null, null);	
			
			//obtener el documento del empleado
			eDNI=Integer.parseInt(Menu.obtenerDatos("Ingresar numero de documento"));
			e.setDni(eDNI);
			//obtener apellido del empleado
			eApellido=Menu.obtenerDatos("Ingresar Apellido/s");
			e.setApellido(eApellido);
			//obtener nombre del empleado
			eNombre=Menu.obtenerDatos("Ingresar Nombre/s");
			e.setNombre(eNombre);
			//obtener fecha nacimiento del empleado
			eF=Menu.obtenerDatos("Ingresar Fecha de Nacimiento(aaaa-mm-dd)");

			//Pasar de un String a Date la fecha de nacimiento
			SimpleDateFormat fecha=new SimpleDateFormat("yyyy-MM-dd");
			try{
				eFNacimiento=fecha.parse(eF);
			}catch(ParseException ex){
				throw new DatoInvalidoException("La fecha ingresada no es valida", ex);
			}
			
			e.setFechaNacimiento(eFNacimiento);
			
			//obtener el genero del empleado
			eGenero=Menu.obtenerDatos("Ingresar genero");
			e.setGenero(eGenero);
			//obtener domicilio del empleado
			eDomicilio=Menu.obtenerDatos("Ingresar Domicilio");
			e.setDomicilio(eDomicilio);
			//obtener localidad del empleado
			eLocalidad=Menu.obtenerDatos("Ingresar Localidad");
			e.setLocalidad(eLocalidad);
			//obtener sector al que esta asignado el empleado
			eSector=Menu.obtenerDatos("Ingresar Sector Designado");
			e.setSector(eSector);
			//obtener el puesto del empleado
			ePuesto=Menu.obtenerDatos("Ingresar Puesto del empleado");
			e.setPuesto(ePuesto);
			
			//Crea un nuevo objeto tipo Cliente con los datos del cliente ingresado
			System.out.println("DNI: "+e.dni+"\tApellido: "+e.apellido+"\tNombre: "+e.nombre+"\tFecha de Nacimiento: "+e.fechaNacimiento+"\tGenero: "+e.genero+"\tDomicilio: "+e.domicilio+"\tLocalidad: "+e.localidad+"\tSector: "+e.sector+"\tPuesto: "+e.puesto);
			//Verifica si el empleado con ese numero de documento ya existe
			Empleado bEmpleado=en.buscarEmpleadoDNI(e.dni);
			if(bEmpleado.apellido!=null){
					throw new EmpleadoInvalidoException("El empleado con el numero de documento: "+e.dni+"ya esta cargado");
			}
			e.setLegajo(MIN);
			//Carga los datos del objeto cliente en la base de datos 
			en.agregarEmpleado(e);
		}catch(NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}

	}

	
	
//---------------------------------------------------------------
//|		MODIFICAR EMPLEADO POR DNI O POR NUMERO DE LEGAJO		|
//---------------------------------------------------------------

	public void modificar() {
		try{
			EmpleadoCRUD en=new EmpleadoCRUD();
			int opcion=0;
			System.out.println ("--------------------------------------------\n|\t\tMENU MODIFICAR EMPLEADO\t\t|\n--------------------------------------------\n");
			
			opcion=Integer.parseInt(Menu.obtenerDatos("1 Modificar empleado por numero de DNI \t2 Modificar empleado por numero de legajo"));
			switch (opcion) {
				case 1:
					//buscar al empleado por su numero de documento
					Empleado emb =buscarDNI(en, "Ingrese el numero de documento del empleado a modificar");
					
					if(emb.apellido==null){
							throw new EmpleadoInvalidoException("El empleado con el numero de documento: "+emb.dni+"no esta cargado");
					}
					modificarE(en, emb);		
					break;
		
				case 2:
					//buscar al empleado en la base de datos por su legajo
					Empleado eb =buscarLegajo(en, "Ingrese el numero del legajo del empleado a modificar");
					if(eb.apellido==null){
						throw new EmpleadoInvalidoException("El empleado con el legajo: "+eb.legajo+" no existe");
					}
					modificarE(en,eb);
					break;
				default:
					throw new OpcionInvalidaException("La opcion ingresada no es valida");
			}
		}catch(NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}

	}
	
	
//-------------------------------------------
//|		MODIFICAR DATOS DEL EMPLEADO		|
//-------------------------------------------
	
	public static void modificarE(EmpleadoCRUD e, Empleado em) throws OpcionInvalidaException, DatoInvalidoException{
		try{
			//imprimir los datos del empleado
			System.out.println("Legajo: "+em.legajo+"\tDNI: "+em.dni+"\tApellido: "+em.apellido+"\tNombre: "+em.nombre+"\tFecha de Nacimiento: "+em.fechaNacimiento+"\tSexo: "+em.genero+"\tDomicilio: "+em.domicilio+"\tLocalidad: "+em.localidad+"\tSector: "+em.sector+"\tPuesto: "+em.puesto);
			//Menu de que atributo se busca modificar
			int opcion=1;
			String modificado;
			while((opcion!=0) && (opcion<9)){
				System.out.println ("--------------------------------------------\n|\t\tMODIFICAR:\t\t|\n--------------------------------------------\n");
				opcion=Integer.parseInt(Menu.obtenerDatos("1 Apellido/s\t2 Nombre/s\t3 Fecha de Nacimiento\t4 Genero\t5 Domicilio\t6 Localidad\t7 Sector\t8 Puesto"));
				//llamar a set de cada atributo segun el caso
				switch (opcion) {
					//modificar apellido del empleado
					case 1:
						modificado=Menu.obtenerDatos("Ingrese el/los nuevo/s Apellido/s");
						em.setApellido(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//modificar nombre del empleado	
					case 2:
						modificado=Menu.obtenerDatos("Ingrese el/los nuevo/s  Nombre/s");
						em.setNombre(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
						//modificar fecha de nacimiento del empleado
					case 3:
						Date emFNacimiento=null;
						modificado=Menu.obtenerDatos("Ingrese la nueva fecha de nacimiento(aaaa-mm-dd)");
						SimpleDateFormat fecha=new SimpleDateFormat("yyyy-MM-dd");
						try{
							emFNacimiento=fecha.parse(modificado);
						}catch(ParseException ex){
							throw new DatoInvalidoException("La fecha ingresada no es valida", ex);
						}
						em.setFechaNacimiento(emFNacimiento);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//modificar genero del empleado
					case 4:
						modificado=Menu.obtenerDatos("Ingrese el nuevo genero");
						em.setGenero(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//modificar domicilio del empleado
					case 5:
						modificado=Menu.obtenerDatos("Ingrese el nuevo domicilio");
						em.setDomicilio(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}			
						break;
					//modificar localidad del empleado
					case 6:
						modificado=Menu.obtenerDatos("Ingrese el nuevo localidad");
						em.setLocalidad(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//modificar el sector al que esta asignado el empleado
					case 7:
						modificado=Menu.obtenerDatos("Ingrese el nuevo sector al que esta asignado el empleado");
						em.setSector(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//modificar el puesto del empleado
					case 8:
						modificado=Menu.obtenerDatos("Ingrese el nuevo puesto del empleado");
						em.setPuesto(modificado);
						opcion=Integer.parseInt(Menu.obtenerDatos("�Desea modificar algun otro dato del empleado?\n1 Si\t2 No"));
						if (opcion==2){
							opcion=0;
						}
						break;
					//si la opcion ingresada no es valida
					default:
						throw new OpcionInvalidaException("La opcion Ingresada no es valida");
				}
				
		}
		//Se modifica al empleado	
		e.modificarEmpleado(em);
		}catch (NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}
	}
	
	
//---------------------------
//|		BUSCAR EMPLEADO		|
//---------------------------
	public Empleado buscarDNI(EmpleadoCRUD e, String mensaje) throws DatoInvalidoException{
		try{
			int dato;
			//obtiene el numero de dni o de legajo del empleado
			dato=Integer.parseInt(Menu.obtenerDatos(mensaje));
			//busca al empleado en la base de datos
			Empleado eb =e.buscarEmpleadoDNI(dato);
			//verificar al empleado
			if(eb.apellido==null){
					throw new EmpleadoInvalidoException("El empleado con el numero de documento: "+eb.dni+"no existe");
			}
			
			//devuelve al empleado que se encontro
			return eb;
		}catch (NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}
	}
	
	public Empleado buscarLegajo(EmpleadoCRUD e, String mensaje) throws DatoInvalidoException{
		try{
			int dato;
			//obtiene el numero de dni o de legajo del empleado
			dato=Integer.parseInt(Menu.obtenerDatos(mensaje));
			//busca al empleado en la base de datos
			Empleado eb =e.buscarEmpleadoLegajo(dato);
			//verificar al empleado
			if(eb.apellido==null){
					throw new EmpleadoInvalidoException("El empleado con el numero de legajo: "+eb.legajo+"no existe");
			}
			
			//devuelve al empleado que se encontro
			return eb;
		}catch (NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}
	}
	
//-------------------------------
//|		ELIMINAR EMPLEADO		|
//-------------------------------
	
	public void eliminar() throws OpcionInvalidaException,DatoInvalidoException{
		int eDNI;
		int eLegajo;
		int opc;
		EmpleadoCRUD ed=new EmpleadoCRUD();
		

		try{
			opc=Integer.parseInt(Menu.obtenerDatos("Eliminar Empleado por:\n1 Numero de Documento\t2 Numero de Legajo"));
			switch (opc) {
			case 1:
				eDNI=Integer.parseInt(Menu.obtenerDatos("Ingresar Numero de Documento del Empleado"));
				//buscar al empleado por numero de dni
				Empleado emb =ed.buscarEmpleadoDNI(eDNI);
				//si el empleado con ese numero de documento no existe
				if(emb.apellido==null){
						throw new EmpleadoInvalidoException("El empleado con el numero de documento: "+emb.dni+"no existe");
				}
				//elimina al empleado de la base de datos
				ed.borrarEmpleado(eDNI);
				break;
			case 2:
				eLegajo=Integer.parseInt(Menu.obtenerDatos("Ingresar Numero de Documento del Empleado"));
				//buscar al empleado por numero de legajo
				Empleado eb =ed.buscarEmpleadoLegajo(eLegajo);
				//verifica que el empleado se encuentre en la base de datos
				if(eb.apellido==null){//si no se encuentra manda una excepcion
						throw new EmpleadoInvalidoException("El empleado con el numero de legajo: "+eb.legajo+"no existe");
				}
				//elimina al empleado de  la base de datos
				ed.borrarEmpleadoLegajo(eLegajo);
				break;
			default:
				throw new OpcionInvalidaException("La opcion ingresada no es valida");
				
			}
		}catch (NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}	
	}

}
