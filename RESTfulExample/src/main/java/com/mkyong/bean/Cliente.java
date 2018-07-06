package com.mkyong.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

public class Cliente {

	
	@JsonProperty(value = "wpleads_billing_first_name")
	private List<String> cpf;
	
	private String agencia;
	
	private String produtoBB;

	@JsonProperty(value = "wpleads_billing_region_name")
	private List<String> estado;
	@JsonProperty(value = "wpleads_shipping_region_name")
	private List<String> estadoImovel;
	@JsonProperty(value = "wpleads_shipping_city")
	private List<String> cidade;
	@JsonProperty(value = "wpleads_shipping_zip")
	private List<String> valorImovel;
	
	private Double subsidio = 0D;
	@JsonProperty(value = "wpleads_shipping_last_name")
	private List<String> fgts ;
	@JsonProperty(value = "wpleads_shipping_company_name")
	private List<String> recursosProprios ;
	@JsonProperty(value = "wpleads_shipping_country_code")
	private List<String> despesas ;
	@JsonProperty(value = "wpleads_shipping_first_name")
	private List<String> rendaBruta;
	@JsonProperty(value = "wpleads_shipping_address_line_1")
	private List<String> prazo ;
	
	@JsonProperty(value = "wpleads_shipping_address_line_2")
	private List<String> amortizacao;
	@JsonProperty(value = "wplead_emails_address")
	private List<String> email;
	
	private String resultado;
	
	private Double entrada;
	@JsonProperty(value = "comment_status")
	private List<String> commentStatus;
	@JsonProperty(value = "wpleads_work_phone")
	private List<String> phone;
	@JsonProperty(value = "wpleads_job_title")
	private List<String> dataNascimento;
	@JsonProperty(value = "wpleads_billing_last_name")
	private List<String> nomeCompleto;
	@JsonProperty(value = "wpleads_company_name")
	private List<String> tipoPropriedade;
	@JsonProperty(value = "wpleads_billing_zip")
	private List<String> cep;
	@JsonProperty(value = "wpleads_billing_address_line_1")
	private List<String> endereco;
	@JsonProperty(value = "wpleads_billing_address_line_2")
	private List<String> bairro;
	@JsonProperty(value = "wpleads_billing_country_code")
	private List<String> resideDesde;

	@JsonProperty(value = "wpleads_billing_city")
	private List<String> municipio;
	
	@JsonProperty(value = "wpleads_first_name")
	private List<String> nome;
	@JsonProperty(value = "wpleads_last_name")
	private List<String> sobrenome;
	
	
	
	
	
	public List<String> getNome() {
		return nome;
	}

	public void setNome(List<String> nome) {
		this.nome = nome;
	}

	public List<String> getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(List<String> sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<String> getEstadoImovel() {
		return estadoImovel;
	}

	public void setEstadoImovel(List<String> estadoImovel) {
		this.estadoImovel = estadoImovel;
	}

	public List<String> getCpf() {
		return cpf;
	}

	public List<String> getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(List<String> dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<String> getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(List<String> nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public List<String> getTipoPropriedade() {
		return tipoPropriedade;
	}

	public void setTipoPropriedade(List<String> tipoPropriedade) {
		this.tipoPropriedade = tipoPropriedade;
	}

	public List<String> getCep() {
		return cep;
	}

	public void setCep(List<String> cep) {
		this.cep = cep;
	}

	public List<String> getEndereco() {
		return endereco;
	}

	public void setEndereco(List<String> endereco) {
		this.endereco = endereco;
	}

	public List<String> getBairro() {
		return bairro;
	}

	public void setBairro(List<String> bairro) {
		this.bairro = bairro;
	}

	public List<String> getResideDesde() {
		return resideDesde;
	}

	public void setResideDesde(List<String> resideDesde) {
		this.resideDesde = resideDesde;
	}

	public List<String> getMunicipio() {
		return municipio;
	}

	public void setMunicipio(List<String> municipio) {
		this.municipio = municipio;
	}

	public List<String> getPhone() {
		return phone;
	}

	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

	public List<String> getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(List<String> commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Double getEntrada() {
		return entrada;
	}

	public void setEntrada(Double entrada) {
		this.entrada = entrada;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public List<String> getPrazo() {
		return prazo;
	}

	public void setPrazo(List<String> prazo) {
		this.prazo = prazo;
	}

	public String getAmortizacao() {
		
		int i = 1;
		
//		if(!amortizacao.toUpperCase().equals("SAC".toUpperCase())){
//			i = 5;
//		}
		
		return i + "";
	}

	public void setAmortizacao(List<String> amortizacao) {
		this.amortizacao = amortizacao;
	}

	public List<String> getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(List<String> rendaBruta) {
		this.rendaBruta = rendaBruta;
	}

	public List<String> getDespesas() {
		return despesas;
	}

	public void setDespesas(List<String> despesas) {
		this.despesas = despesas;
	}

	public Double getSubsidio() {
		return subsidio;
	}

	public void setSubsidio(Double subsidio) {
		this.subsidio = subsidio;
	}

	public List<String> getFgts() {
		return fgts;
	}

	public void setFgts(List<String> fgts) {
		this.fgts = fgts;
	}

	public List<String> getRecursosProprios() {
		return recursosProprios;
	}

	public void setRecursosProprios(List<String> recursosProprios) {
		this.recursosProprios = recursosProprios;
	}

	public List<String> getValorImovel() {
		return valorImovel;
	}

	public void setValorImovel(List<String> valorImovel) {
		this.valorImovel = valorImovel;
	}


	public String getAgencia() {
		return agencia;
	}
	
	

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public void setCpf(List<String> cpf) {
		this.cpf = cpf;
	}

	public String getProdutoBB() {
		return produtoBB;
	}

	public void setProdutoBB(String produtoBB) {
		this.produtoBB = produtoBB;
	}

	public List<String> getEstado() {
		return estado;
	}

	public void setEstado(List<String> estado) {
		this.estado = estado;
	}

	public List<String> getCidade() {
		return cidade;
	}

	public void setCidade(List<String> cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Cliente [cpf=" + cpf + ", agencia=" + agencia + ", produtoBB=" + produtoBB + ", estado=" + estado
				+ ", estadoImovel=" + estadoImovel + ", cidade=" + cidade + ", valorImovel=" + valorImovel
				+ ", subsidio=" + subsidio + ", fgts=" + fgts + ", recursosProprios=" + recursosProprios + ", despesas="
				+ despesas + ", rendaBruta=" + rendaBruta + ", prazo=" + prazo + ", amortizacao=" + amortizacao
				+ ", email=" + email + ", resultado=" + resultado + ", entrada=" + entrada + ", commentStatus="
				+ commentStatus + ", phone=" + phone + ", dataNascimento=" + dataNascimento + ", nomeCompleto="
				+ nomeCompleto + ", tipoPropriedade=" + tipoPropriedade + ", cep=" + cep + ", endereco=" + endereco
				+ ", bairro=" + bairro + ", resideDesde=" + resideDesde + ", municipio=" + municipio
				+ ", getEstadoImovel()=" + getEstadoImovel() + ", getCpf()=" + getCpf() + ", getDataNascimento()="
				+ getDataNascimento() + ", getNomeCompleto()=" + getNomeCompleto() + ", getTipoPropriedade()="
				+ getTipoPropriedade() + ", getCep()=" + getCep() + ", getEndereco()=" + getEndereco()
				+ ", getBairro()=" + getBairro() + ", getResideDesde()=" + getResideDesde() + ", getMunicipio()="
				+ getMunicipio() + ", getPhone()=" + getPhone() + ", getCommentStatus()=" + getCommentStatus()
				+ ", getEntrada()=" + getEntrada() + ", getResultado()=" + getResultado() + ", getEmail()=" + getEmail()
				+ ", getPrazo()=" + getPrazo() + ", getAmortizacao()=" + getAmortizacao() + ", getRendaBruta()="
				+ getRendaBruta() + ", getDespesas()=" + getDespesas() + ", getSubsidio()=" + getSubsidio()
				+ ", getFgts()=" + getFgts() + ", getRecursosProprios()=" + getRecursosProprios()
				+ ", getValorImovel()=" + getValorImovel() + ", getAgencia()=" + getAgencia() + ", getProdutoBB()="
				+ getProdutoBB() + ", getEstado()=" + getEstado() + ", getCidade()=" + getCidade() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}







	
	
	
	

}
