package ncrtme.service;

import org.springframework.stereotype.Service;

@Service
public class ConversaoService {

	//Caracteres usados para a conversão da url
	private String stringCaracteresPermitidos = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private char[] caracteresPermitidos = stringCaracteresPermitidos.toCharArray();
	
	//Base usada para conversão (base 62)
	private int base = caracteresPermitidos.length;
	
	public String codificar(Long input) {
		var stringCodificada = new StringBuilder();
		if(input == 0) {
			return String.valueOf(caracteresPermitidos[0]);
		}
		while(input > 0){
			stringCodificada.append(caracteresPermitidos[(int) (input % base)]);
			input = input/base;
		}
		return stringCodificada.reverse().toString();
	}
	
	public long decodificar(String input) {
		var stringCaracteres = input.toCharArray();
		var tamanho = stringCaracteres.length;
		var decodificado = 0;
		var contador = 1;
		for(int i = 0; i < tamanho; i++) {
			decodificado += this.stringCaracteresPermitidos.indexOf(stringCaracteres[i]) * Math.pow(base, tamanho-contador);
			contador++;
		}
		return decodificado;
	}
}
