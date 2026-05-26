package com.daw.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/holamundo")
public class HolaMundoController {

	@GetMapping("/hola")
	public String saludar() {
		
		return "Hola cabesaaaa!!";
		
	}
	
	@GetMapping("/saludo")
	public String saludar(@RequestParam(defaultValue = "Señor Palmilla") String nombre) {
		
		return String.format("Hola %s mi cabesitaaaa!!!", nombre);
		
	}
	
	@GetMapping("/numeros")
	public String numeros(@RequestParam int min, @RequestParam int max) {
		
		String result = String.format("Los números entre el %d y el %d son: ", min, max);
		
		for(int i = min+1; i<max; i++) {
			
			result = result + i + " ";
			
		}
		
		return result;
	}
	
}
