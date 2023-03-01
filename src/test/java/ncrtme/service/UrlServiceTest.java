package ncrtme.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ncrtme.dto.UrlDto;
import ncrtme.model.Url;
import ncrtme.repository.UrlRepository;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
	
	@Mock
	UrlRepository urlRepository;
	
	@Mock
	ConversaoService conversaoService;
	
	@InjectMocks
	UrlService urlService;
	
	@Test
	void converterUrl() {
		var url = new Url();
		url.setUrlLonga("https://github.com/caioorleans/EncurtadorUrl-API");
		url.setDataCriacao(new Date());
		url.setId(1);
		
		when(urlRepository.save(any(Url.class))).thenReturn(url);
		when(conversaoService.codificar(url.getId())).thenReturn("b");
		
		var urlDto = new UrlDto();
		urlDto.setUrlLonga("https://github.com/caioorleans/EncurtadorUrl-API");
		
		assertEquals("b", urlService.codificarUrl(urlDto));
	}
	
	@Test
	void getUrlOriginal() {
		when(conversaoService.decodificar("h")).thenReturn((long) 7);

        var url = new Url();
        url.setUrlLonga("https://github.com/AnteMarin/UrlShortener-API");
        url.setDataCriacao(new Date());
        url.setId(7);

        when(urlRepository.findById((long) 7)).thenReturn(java.util.Optional.of(url));
        assertEquals("https://github.com/AnteMarin/UrlShortener-API", urlService.obterUrlOriginal("h"));
	}
}
