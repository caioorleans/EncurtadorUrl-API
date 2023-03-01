package ncrtme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConversaoServiceTest {
	
	@InjectMocks
	private ConversaoService conversaoService;
	
	@Test
	void codificarMaior62(){
		String result =  conversaoService.codificar((long)100);
		assertEquals("bM", result);
	}
	
	@Test
	void codificarMenor62() {
		String result = conversaoService.codificar((long)18);
		assertEquals("s", result);
	}
	
	@Test
	void decodificarUmCaractere() {
		assertEquals(51, conversaoService.decodificar("Z"));
	}
	
	@Test
	void decodificarVariosCaracteres() {
		assertEquals(2488, conversaoService.decodificar("Oi"));
	}
}
