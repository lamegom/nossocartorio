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
"AnoObito",
"msgObito"
})
public class DadosObito {

@JsonProperty("AnoObito")
private String anoObito;
@JsonProperty("msgObito")
private String msgObito;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("AnoObito")
public String getAnoObito() {
return anoObito;
}

@JsonProperty("AnoObito")
public void setAnoObito(String anoObito) {
this.anoObito = anoObito;
}

public DadosObito withAnoObito(String anoObito) {
this.anoObito = anoObito;
return this;
}

@JsonProperty("msgObito")
public String getMsgObito() {
return msgObito;
}

@JsonProperty("msgObito")
public void setMsgObito(String msgObito) {
this.msgObito = msgObito;
}

public DadosObito withMsgObito(String msgObito) {
this.msgObito = msgObito;
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

public DadosObito withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}
}
