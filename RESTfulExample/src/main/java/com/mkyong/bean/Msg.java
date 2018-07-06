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
"TempoConsulta",
"Resultado",
"ResultadoTXT",
"Creditos"
})
public class Msg {

@JsonProperty("TempoConsulta")
private String tempoConsulta;
@JsonProperty("Resultado")
private String resultado;
@JsonProperty("ResultadoTXT")
private String resultadoTXT;
@JsonProperty("Creditos")
private String creditos;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("TempoConsulta")
public String getTempoConsulta() {
return tempoConsulta;
}

@JsonProperty("TempoConsulta")
public void setTempoConsulta(String tempoConsulta) {
this.tempoConsulta = tempoConsulta;
}

public Msg withTempoConsulta(String tempoConsulta) {
this.tempoConsulta = tempoConsulta;
return this;
}

@JsonProperty("Resultado")
public String getResultado() {
return resultado;
}

@JsonProperty("Resultado")
public void setResultado(String resultado) {
this.resultado = resultado;
}

public Msg withResultado(String resultado) {
this.resultado = resultado;
return this;
}

@JsonProperty("ResultadoTXT")
public String getResultadoTXT() {
return resultadoTXT;
}

@JsonProperty("ResultadoTXT")
public void setResultadoTXT(String resultadoTXT) {
this.resultadoTXT = resultadoTXT;
}

public Msg withResultadoTXT(String resultadoTXT) {
this.resultadoTXT = resultadoTXT;
return this;
}

@JsonProperty("Creditos")
public String getCreditos() {
return creditos;
}

@JsonProperty("Creditos")
public void setCreditos(String creditos) {
this.creditos = creditos;
}

public Msg withCreditos(String creditos) {
this.creditos = creditos;
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

public Msg withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}



}
