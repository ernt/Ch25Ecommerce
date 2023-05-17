package org.generation.ecommerce.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;

import org.generation.ecommerce.config.JwtFilter;
import org.generation.ecommerce.model.Token;
import org.generation.ecommerce.model.Usuario;
import org.generation.ecommerce.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(path="/api/login/")
public class LoginController {
	private final UsuarioService usuarioService ;
	
	@Autowired
	public LoginController (UsuarioService usuarioService ) {
		this.usuarioService=usuarioService;
		
	}
	
	
	
	@PostMapping
	public Token LoginUsuario(@RequestBody Usuario usuario) throws ServletException {
		
		if (usuarioService.validateUsuario(usuario)) {
			return new Token(generateToken(usuario.getEmail()));
			
		}
		throw new ServletException("Nombre de usuario o contraseña incorrecto");
		
	}
	
	
	private String generateToken(String username) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 10);//depende del tipo de aplciacion y el tiempo que queramos 
		return Jwts.builder().setSubject(username).claim("role", "user")
		.setIssuedAt(new Date()).setExpiration(calendar.getTime())
		.signWith(SignatureAlgorithm.HS256, JwtFilter.secret)
		.compact();
	}

}
