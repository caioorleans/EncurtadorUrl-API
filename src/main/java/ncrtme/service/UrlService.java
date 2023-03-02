package ncrtme.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import ncrtme.dto.UrlDto;
import ncrtme.exceptionHandlers.exceptions.UrlExpiredException;
import ncrtme.exceptionHandlers.exceptions.UrlNotFoundException;
import ncrtme.model.Url;
import ncrtme.repository.UrlRepository;

@Service
public class UrlService {
	
	private final UrlRepository urlRepository;
	private final ConversaoService conversaoService;
	
	public UrlService(UrlRepository urlRepository, ConversaoService conversaoService) {
		this.urlRepository = urlRepository;
		this.conversaoService = conversaoService;
	}
	
	public String codificarUrl(UrlDto urlLonga) {
		var url = new Url();
		url.setUrlLonga(urlLonga.getUrlLonga());
		url.setDataCriacao(new Date());
		url.setDataExpiracao(urlLonga.getDataExpiracao());
		
		var entidade = urlRepository.save(url);
		
		return conversaoService.codificar(entidade.getId());
	}
	
	public String obterUrlOriginal(String urlCodificada) {
		var id = conversaoService.decodificar(urlCodificada);
		Url url = urlRepository.findById(id).orElseThrow(()-> new UrlNotFoundException());
		
		if(url.getDataExpiracao() != null && url.getDataExpiracao().before(new Date())) {
			urlRepository.delete(url);
			throw new UrlExpiredException();
		}
		
		return url.getUrlLonga();
	}
}
