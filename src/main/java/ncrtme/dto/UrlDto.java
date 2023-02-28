package ncrtme.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto de requisição POST")
public class UrlDto {
	
	@ApiModelProperty(required=true, notes = "Url a ser convertida")
	private String urlLonga;
	
	@ApiModelProperty( notes = "Data de expiração da url")
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
