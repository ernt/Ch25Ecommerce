package org.generation.ecommerce.service;

import java.util.List;

import org.generation.ecommerce.model.Producto;
import org.generation.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


	
	@Service
	public class ProductoService {
		private final ProductoRepository productoRepository;
		@Autowired
		public ProductoService(ProductoRepository  productoRepository) {
			this.productoRepository=productoRepository;

		}//constructor
		
		public List<Producto> getAllProductos(){
			return productoRepository.findAll() ;
		}

		public Producto getProducto(Long id) {
			return productoRepository.findById(id).orElseThrow(
					()-> new IllegalArgumentException("El producto con el id "
							+ id+"no existe")
					);
		}//getProducto

		public Producto deleteProducto(Long id) {
			Producto tmpProd=null;
			if (productoRepository.existsById(id)) {
				tmpProd=productoRepository.findById(id).get();
				productoRepository.deleteById(id);
			}
			
			return tmpProd;
		}//deleteProducto

		public Producto addProducto(Producto producto) {
			Producto tmpPro=null;
			if (productoRepository.findByNombre(producto.getNombre()).isEmpty()) {
				tmpPro=productoRepository.save(producto);
			}else {
				System.out.println("Ya existe un producto con el nombre "+producto.getNombre());
			}
		 
			return tmpPro;
		}

		public Producto updateProducto(Long id, String nombre, String descripcion, String imagen, Double precio) {
			Producto tmpProd=null;
			if (productoRepository.existsById(id)) {
				tmpProd=productoRepository.findById(id).get();
					if (nombre!=null) tmpProd.setNombre(nombre);
					if(descripcion!=null) tmpProd.setDescripcion(descripcion);
					if (imagen!=null) tmpProd.setImagen(imagen);
					if(precio!=null)tmpProd.setPrecio(precio.doubleValue());
					productoRepository.save(tmpProd);		
							
					
			}else {
				System.out.println("Update-El Producto con id "+id+" no existe");
			}
				

			return tmpProd;
		}//updateProducto
		
		
		
//		eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhcXVpbG9zQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNjg0MTc2NzEyLCJleHAiOjE2ODQyMTI3MTJ9.QFrnlp-TRp9VTB8-f5I6CQNpsOcE_9E9NWoiwqJSBts
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} //class ProductoService   
