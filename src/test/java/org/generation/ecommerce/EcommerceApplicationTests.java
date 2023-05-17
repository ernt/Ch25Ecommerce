package org.generation.ecommerce;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.generation.ecommerce.model.Producto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
	@Autowired
	private MockMvc mockMvc;
//	Actualizar el token con uno valido
	private final String token="Bearer: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJvdGlzQGdtYWlsLmNvbSIsInJvbGUiOiJ1c2VyIiwiaWF0IjoxNjg0Mjc1MjIxLCJleHAiOjE2ODQzMTEyMjF9.ImCjuIR2eEjtv_H9EzilbwaTphbNqMQxHdDboDWBx18";
	

	@Test
	@DisplayName("Prueba para obtener (GET) la lista de productos")
	void pruebaGET() throws Exception {
		this.mockMvc.perform(get("/api/productos/1")).andDo(print())
		.andExpect(status().isOk() )
		.andExpect(
				content().string(
						containsString("norma1.gif")
						)
				);
	}
	
	@Test
	@Disabled("Probado la primera vez, deshabilitado.")
	@DisplayName("Prueba para borrar el producto con id 7 (DELETE), esta prueba solo se puede hacaer una vez")
	void pruebaDELETE()throws Exception{
		this.mockMvc.perform(delete("/api/productos/8").header("Authorization", token)).andDo(print())
		.andExpect(status().isOk() )
		.andExpect(
				content().string(
						containsString("bic2.jpg")
						)
				);
	}
		
		@Test
		@Disabled("Probado la primera vez, deshabilitado")
		@DisplayName ("Prueba para crear un nuevo Producto(POST)")
		void PruebaPOST() throws Exception{
			Producto p = new Producto();
			p.setNombre("Cuadernos Profesionales Norma My little pony");
			p.setDescripcion("Cuaderno profesional Norma, muy loco");
			p.setImagen("Imagen.jpg");
			p.setPrecio(76.3);
			
			this.mockMvc.perform(post("/api/productos/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(p)).header("Authorization", token)).andDo(print())
			.andExpect(status().isOk() )
			.andExpect(
					content().string(
							containsString("Imagen.jpg")
							)
					);
		}
		
		private static String asJsonString(final Object obj) {
		    try {
		      return new ObjectMapper().writeValueAsString(obj);
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }//catch
		 } // asJsonString

		@Test 
		@DisplayName("Se modifica el producto 5 con el nueov precio(PUT)")
		void pruebaPUT() throws Exception {
			this.mockMvc.perform(put("/api/productos/5")//Se puede mandar varios queryparam
					.queryParam("precio", "27.38")
					.header("Authorization", token)).andDo(print())
			.andExpect(status().isOk() )
			.andExpect(
					content().string(
							containsString("27.38")
							)
					);
			
		}
	

	
}
