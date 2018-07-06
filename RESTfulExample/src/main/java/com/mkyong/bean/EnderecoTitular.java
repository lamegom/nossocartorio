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
"Logradouro",
"Numero",
"Complemento",
"Bairro",
"Cidade",
"UF",
"Cep"
})
public class EnderecoTitular {

@JsonProperty("Logradouro")
private String logradouro;
@JsonProperty("Numero")
private String numero;
@JsonProperty("Complemento")
private String complemento;
@JsonProperty("Bairro")
private String bairro;
@JsonProperty("Cidade")
private String cidade;
@JsonProperty("UF")
private String uF;
@JsonProperty("Cep")
private String cep;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("Logradouro")
public String getLogradouro() {
return logradouro;
}

@JsonProperty("Logradouro")
public void setLogradouro(String logradouro) {
this.logradouro = logradouro;
}

public EnderecoTitular withLogradouro(String logradouro) {
this.logradouro = logradouro;
return this;
}

@JsonProperty("Numero")
public String getNumero() {
return numero;
}

@JsonProperty("Numero")
public void setNumero(String numero) {
this.numero = numero;
}

public EnderecoTitular withNumero(String numero) {
this.numero = numero;
return this;
}

@JsonProperty("Complemento")
public String getComplemento() {
return complemento;
}

@JsonProperty("Complemento")
public void setComplemento(String complemento) {
this.complemento = complemento;
}

public EnderecoTitular withComplemento(String complemento) {
this.complemento = complemento;
return this;
}

@JsonProperty("Bairro")
public String getBairro() {
return bairro;
}

@JsonProperty("Bairro")
public void setBairro(String bairro) {
this.bairro = bairro;
}

public EnderecoTitular withBairro(String bairro) {
this.bairro = bairro;
return this;
}

@JsonProperty("Cidade")
public String getCidade() {
return cidade;
}

@JsonProperty("Cidade")
public void setCidade(String cidade) {
this.cidade = cidade;
}

public EnderecoTitular withCidade(String cidade) {
this.cidade = cidade;
return this;
}

@JsonProperty("UF")
public String getUF() {
return uF;
}

@JsonProperty("UF")
public void setUF(String uF) {
this.uF = uF;
}

public EnderecoTitular withUF(String uF) {
this.uF = uF;
return this;
}

@JsonProperty("Cep")
public String getCep() {
return cep;
}

@JsonProperty("Cep")
public void setCep(String cep) {
this.cep = cep;
}

public EnderecoTitular withCep(String cep) {
this.cep = cep;
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

public EnderecoTitular withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}


}
