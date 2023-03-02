package ncrtme.dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de requisição POST")
public class UrlDto {
	
	@Schema(description = "Url a ser convertida")
	private String urlLonga;
	
	@Schema( description = "Data de expiração da url")
	private Date dataExpiracao;

	public String getUrlLonga() {
		return urlLonga;
	}

	public void setUrlLonga(String urlLonga) {
		this.urlLonga = urlLonga;
	}

	public Date getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Date dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	
	
}
