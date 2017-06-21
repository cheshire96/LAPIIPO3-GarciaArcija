package tp;

import java.util.Scanner;

import excepciones.DatoInvalidoException;
import excepciones.OpcionInvalidaException;


public class Menu {
	
	
//-----------------------------------
//|			MENU PRINCIPAL			|
//-----------------------------------
	
	public static void menu() throws OpcionInvalidaException,DatoInvalidoException{
		int cerrar=0;
		while(cerrar==0){
			try{
				int opc = Integer.parseInt(obtenerDatos("--------------------------------------------\n|\t\tMENU\t\t|\n--------------------------------------------\n1 Operaciones con Clientes\t2 Operaciones con Empleados\t3 Cerrar el menu\t")); 
				switch (opc) {
					//Operaciones con Clientes
					case 1:
						menuCliente();
						break;
					//Operaciones con Empleados
					case 2:
						menuEmpleado();
						break;
					case 3:
						cerrar=1;
						break;
					default:
						throw new OpcionInvalidaException("La opcion ingresada no es valida");
				}
			}catch (NumberFormatException e){
				throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", e);
			}
		}
	}	     
	

		
		
		

//-----------------------------------------------
//|			MENU OPCIONES DE CLIENTE			|
//-----------------------------------------------
				
	public static void menuCliente() throws OpcionInvalidaException,DatoInvalidoException{
		Cliente c=new Cliente(0, null, null, null, null, null, null);
		int continuar=0;
		while (continuar<1) {			
		try{
			int opc=Integer.parseInt(obtenerDatos("--------------------------------------------\n|\t\tMENU CLIENTE\t\t|\n--------------------------------------------\n1 Ver todos los Clientes Existentes\t2 Ingresar nuevo Cliente\t3 Modificar un Cliente\t4 Eliminar un Cliente\t5 Volver al menu principal")); //Invocamos un m�todo sobre un objeto Scanner
		
			switch (opc) {
				//Ver todos los Clientes Existentes
				case 1:
					c.listar();
					break;
				//Ingresar nuevo Cliente
				case 2:
					c.ingresar();
					break;
				//Modificar un Cliente
				case 3:
					c.modificar();
					break;
				//Eliminar un Cliente
				case 4:
					c.eliminar();
					break;
				case 5:
					continuar=1;
					break;
				default:
					throw new OpcionInvalidaException("La opcion ingresada no es valida");
			}
		}catch (NumberFormatException e){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", e);
		}
		}
	}

		
//-----------------------------------------------
//|			MENU OPCIONES DE EMPLEADO			|
//-----------------------------------------------
		
	public static void menuEmpleado() throws OpcionInvalidaException,DatoInvalidoException{
		int continuar=0;
		Empleado e=new Empleado(0, null, null, null, null, null, null, null, null);
		while (continuar<1) {			
			try{
			int opc =Integer.parseInt(obtenerDatos("--------------------------------------------\n|\t\tMENU\t\t|\n--------------------------------------------\n1 Ver todos los Empleados Existentes\t2 Ingresar nuevo Empleado\t3 Modificar un Empleado\t4 Eliminar un Empleado\t5 Volver al menu principal"));
				
			switch (opc) {
				//Ver todos los Empleados Existentes
				case 1:
					e.listar();
					break;
				//Ingresar nuevo Empleado
				case 2:
					e.ingresar();
					break;
				//Modificar un Empleado
				case 3:
					e.modificar();
					break;
				//Eliminar un Empleado
				case 4:
					e.eliminar();
					break;
				//Se vuelve al menu principal
				case 5:
					continuar=1;
					break;
				
				default:
					throw new OpcionInvalidaException("La opcion ingresada no es valida");
			}
		}catch (NumberFormatException ex){
			throw new DatoInvalidoException("Se ingreso un caracter en lugar de un numero", ex);
		}
		}
	}
	
	
//-----------------------------------------------------------
//|			OBTENER DATOS INGRESADOS POR TECLADO			|
//-----------------------------------------------------------
					
	public static String obtenerDatos(String mensaje){
		System.out.println (mensaje);
		String entradaTeclado = "";
		Scanner entradaEscaner = new Scanner (System.in); //Creacion de un objeto Scanner
		entradaTeclado = entradaEscaner.nextLine (); //Invocamos un motodo sobre un objeto Scanner
		return entradaTeclado;
	}
			


	
}
