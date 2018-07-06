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
"RetornoCpf"
})
public class BuscaCPF {

@JsonProperty("RetornoCpf")
private RetornoCpf retornoCpf;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("RetornoCpf")
public RetornoCpf getRetornoCpf() {
return retornoCpf;
}

@JsonProperty("RetornoCpf")
public void setRetornoCpf(RetornoCpf retornoCpf) {
this.retornoCpf = retornoCpf;
}

public BuscaCPF withRetornoCpf(RetornoCpf retornoCpf) {
this.retornoCpf = retornoCpf;
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

public BuscaCPF withAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
return this;
}

}
