package ncrtme.controller;

import java.net.URI;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import ncrtme.dto.UrlDto;
import ncrtme.service.UrlService;

@RestController
@RequestMapping("api/")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(summary = "Converte uma URL longa em uma URL curta")
    @PostMapping(value = "criar-curto")
    public String convertToShortUrl(@RequestBody UrlDto url) {
        return urlService.codificarUrl(url);
    }

    @Operation(summary = "Redireciona através de uma URL curta")
    @GetMapping(value = "{urlCurta}")
    @Cacheable(value = "urls", key = "#urlCurta", sync = true)
    public ResponseEntity<Void> getAndRedirect(@PathVariable String urlCurta) {
        var url = urlService.obterUrlOriginal(urlCurta);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
