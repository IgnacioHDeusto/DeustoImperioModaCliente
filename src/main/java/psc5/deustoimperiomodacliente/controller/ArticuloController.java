package psc5.deustoimperiomodacliente.controller;

import java.rmi.RemoteException;

import psc5.deustoimperiomodacliente.remote.ServiceLocator;

/* 
public class ArticuloController {
    
    private ServiceLocator serviceLocator;

    public ArticuloController(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator; 
    }
}
*/

public class ArticuloController {
	
	//Reference to the Service Locator
		private ServiceLocator serviceLocator;
		
		public ArticuloController(ServiceLocator serviceLocator) {
			this.serviceLocator = serviceLocator; 
		}
		
		public void agregarArticulo(String categoria, String descripcion, String nombre, double precio,
        String tamano, long token) throws RemoteException {
			
			//this.serviceLocator.getService().crearEntrenamiento(categoria, descripcion, nombre, precio, tamano, token);
			try {
				
			} catch (Exception e) {
				 System.out.println("\t#Error: crearEntrenamiento( " + categoria + ", " + descripcion + ", "  + nombre + ", "  + precio + ", "  + tamano + ", "   + ") ha fallado: " + e);
			}
		}
		/* 
		public List<ArticuloDTO> getEntrenamientos(long token) throws RemoteException {
            try {
                return this.serviceLocator.getService().getArticulos(token);
            } catch (Exception e) {
                System.out.println("# Error getting all entrenamientos: " + e);
                return null;
            }

        }
*/

	
}