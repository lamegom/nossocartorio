package com.mkyong.bean;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DynaBean
{


    // and then "other" stuff:
	@JsonProperty(value = "results")
    protected Map<String,Object> other = new HashMap<String,Object>();



    public Object get(String name) {
        return other.get(name);
    }

    // "any getter" needed for serialization    
    @JsonAnyGetter
    public Map<String,Object> any() {
        return other;
    }

    @JsonAnySetter
    public void set(String name, Object value) {
        other.put(name, value);
    }
}
