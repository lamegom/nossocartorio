package com.mkyong.bean;


import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
"CodigoControle",
"DataConsulta",
"HoraConsulta",
"DataInscricao",
"Cpf",
"DataNascimento",
"Titular",
"Situacao",
"Genero",
"NomeMae"
})
public class DadosTitular {

@JsonProperty("CodigoControle")
private String codigoControle;
@JsonProperty("DataConsulta")
private String dataConsulta;
@JsonProperty("HoraConsulta")
private String horaConsulta;
@JsonProperty("DataInscricao")
private String dataInscricao;
@JsonProperty("Cpf")
private String cpf;
@JsonProperty("DataNascimento")
private String dataNascimento;
@JsonProperty("Titular")
private String titular;
@JsonProperty("Situacao")
private String situacao;
@JsonProperty("Genero")
private String genero;
@JsonProperty("NomeMae")
private String nomeMae;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("CodigoControle")
public String getCodigoControle() {
return codigoControle;
}

@JsonProperty("CodigoControle")
public void setCodigoControle(String codigoControle) {
this.codigoControle = codigoControle;
}

public DadosTitular withCodigoControle(String codigoControle) {
this.codigoControle = codigoControle;
return this;
}

@JsonProperty("DataConsulta")
public String getDataConsulta() {
return dataConsulta;
}

@JsonProperty("DataConsulta")
public void setDataConsulta(String dataConsulta) {
this.dataConsulta = dataConsulta;
}

public DadosTitular withDataConsulta(String dataConsulta) {
this.dataConsulta = dataConsulta;
return this;
}

@JsonProperty("HoraConsulta")
public String getHoraConsulta() {
return horaConsulta;
}

@JsonProperty("HoraConsulta")
public void setHoraConsulta(String horaConsulta) {
this.horaConsulta = horaConsulta;
}

public DadosTitular withHoraConsulta(String horaConsulta) {
this.horaConsulta = horaConsulta;
return this;
}

@JsonProperty("DataInscricao")
public String getDataInscricao() {
return dataInscricao;
}

@JsonProperty("DataInscricao")
public void setDataInscricao(String dataInscricao) {
this.dataInscricao = dataInscricao;
}

public DadosTitular withDataInscricao(String dataInscricao) {
this.dataInscricao = dataInscricao;
return this;
}

@JsonProperty("Cpf")
public String getCpf() {
return cpf;
}

@JsonProperty("Cpf")
public void setCpf(String cpf) {
this.cpf = cpf;
}

public DadosTitular withCpf(String cpf) {
this.cpf = cpf;
return this;
}

@JsonProperty("DataNascimento")
public String getDataNascimento() {
return dataNascimento;
}

@JsonProperty("DataNascimento")
public void setDataNascimento(String dataNascimento) {
this.dataNascimento = dataNascimento;
}

public DadosTitular withDataNascimento(String dataNascimento) {
this.dataNascimento = dataNascimento;
return this;
}

@JsonProperty("Titular")
public String getTitular() {
return titular;
}

@JsonProperty("Titular")
public void setTitular(String titular) {
this.titular = titular;
}

public DadosTitular withTitular(String titular) {
this.titular = titular;
return this;
}

@JsonProperty("Situacao")
public String getSituacao() {
return situacao;
}

@JsonProperty("Situacao")
public void setSituacao(String situacao) {
this.situacao = situacao;
}

public DadosTitular withSituacao(String situacao) {
this.situacao = situacao;
return this;
}

@JsonProperty("Genero")
public String getGenero() {
return genero;
}

@JsonProperty("Genero")
public void setGenero(String genero) {
this.genero = genero;
}

public DadosTitular withGenero(String genero) {
this.genero = genero;
return this;
}

@JsonProperty("NomeMae")
public String getNomeMae() {
return nomeMae;
}

@JsonProperty("NomeMae")
public void setNomeMae(String nomeMae) {
this.nomeMae = nomeMae;
}

public DadosTitular withNomeMae(String nomeMae) {
this.nomeMae = nomeMae;
return this;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

public DadosTitular withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public String toString() {
	return "DadosTitular [codigoControle=" + codigoControle + ", dataConsulta=" + dataConsulta + ", horaConsulta="
			+ horaConsulta + ", dataInscricao=" + dataInscricao + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento
			+ ", titular=" + titular + ", situacao=" + situacao + ", genero=" + genero + ", nomeMae=" + nomeMae
			+ ", additionalProperties=" + additionalProperties + "]";
}



}
