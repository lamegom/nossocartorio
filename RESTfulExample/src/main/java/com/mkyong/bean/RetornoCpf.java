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
"msg",
"DadosTitular",
"EnderecoTitular",
"DadosObito"
})
public class RetornoCpf {

@JsonProperty("msg")

private Msg msg;
@JsonProperty("DadosTitular")

private DadosTitular dadosTitular;
@JsonProperty("EnderecoTitular")
private EnderecoTitular enderecoTitular;
@JsonProperty("DadosObito")

private DadosObito dadosObito;
@JsonIgnore

private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("msg")
public Msg getMsg() {
return msg;
}

@JsonProperty("msg")
public void setMsg(Msg msg) {
this.msg = msg;
}

public RetornoCpf withMsg(Msg msg) {
this.msg = msg;
return this;
}

@JsonProperty("DadosTitular")
public DadosTitular getDadosTitular() {
return dadosTitular;
}

@JsonProperty("DadosTitular")
public void setDadosTitular(DadosTitular dadosTitular) {
this.dadosTitular = dadosTitular;
}


public RetornoCpf withDadosTitular(DadosTitular dadosTitular) {
this.dadosTitular = dadosTitular;
return this;
}

@JsonProperty("EnderecoTitular")
public EnderecoTitular getEnderecoTitular() {
return enderecoTitular;
}

@JsonProperty("EnderecoTitular")
public void setEnderecoTitular(EnderecoTitular enderecoTitular) {
this.enderecoTitular = enderecoTitular;
}

public RetornoCpf withEnderecoTitular(EnderecoTitular enderecoTitular) {
this.enderecoTitular = enderecoTitular;
return this;
}

@JsonProperty("DadosObito")
public DadosObito getDadosObito() {
return dadosObito;
}

@JsonProperty("DadosObito")
public void setDadosObito(DadosObito dadosObito) {
this.dadosObito = dadosObito;
}

public RetornoCpf withDadosObito(DadosObito dadosObito) {
this.dadosObito = dadosObito;
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

public RetornoCpf withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

@Override
public String toString() {
	return "RetornoCpf [msg=" + msg + ", dadosTitular=" + dadosTitular + ", enderecoTitular=" + enderecoTitular
			+ ", dadosObito=" + dadosObito + ", additionalProperties=" + additionalProperties + "]";
}



}

