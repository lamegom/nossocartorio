package com.mkyong.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class InbuondLead {
	
	@JsonProperty(value = "wpleads_first_name")
	private String nome;
	@JsonProperty(value = "wpleads_last_name")
	private String sobrenome;
	@JsonProperty(value = "wpleads_email_address")
	private String email;
	@JsonProperty(value = "wpleads_work_phone")
	private String phone;
	@JsonProperty(value = "wpleads_billing_first_name")
	private String cpf;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	@Override
	public String toString() {
		return "InbuondLead [nome=" + nome + ", sobrenome=" + sobrenome + ", email=" + email + ", phone=" + phone
				+ ", cpf=" + cpf + "]";
	}
	
	

}
