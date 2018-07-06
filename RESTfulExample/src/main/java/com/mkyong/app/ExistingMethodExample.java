package com.mkyong.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;

import com.ecwid.maleorang.MailchimpClient;
import com.ecwid.maleorang.MailchimpException;
import com.ecwid.maleorang.MailchimpObject;
import com.ecwid.maleorang.method.v3_0.lists.members.EditMemberMethod;
import com.ecwid.maleorang.method.v3_0.lists.members.GetMembersMethod;
import com.ecwid.maleorang.method.v3_0.lists.members.MemberInfo;
import com.mkyong.app.CustomMethodExample.SubscribeRequest;
import com.mkyong.app.CustomMethodExample.SubscribeResponse;

/**
 * Demonstrates how to use an existing method implementation (namely {@link com.ecwid.maleorang.method.v3_0.lists.members.EditMemberMethod.CreateOrUpdate}).
 */
public class ExistingMethodExample extends Thread{

    private final String apiKey, listId;
    
    private String email;
    private String analise;
    
    private String resultado;
    private String motivo;
    
    private String valorProposto;
    private String prazoProposto;
    private String prestacaoProposto;
    
    private String valorAprovado;
    private String prazoAprovado;
    private String prestacaoAprovado;
    
    private String valorImovel;
    private String valorFgts;
    private String valorRecusros;
    
    private String valorSubsidio;
    
    private String entrada;

	private String nome;

	private String sobrenome;


	private String phone;

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

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getValorSubsidio() {
		return valorSubsidio;
	}

	public void setValorSubsidio(String valorSubsidio) {
		this.valorSubsidio = valorSubsidio;
	}

	public String getValorImovel() {
		return valorImovel;
	}

	public void setValorImovel(String valorImovel) {
		this.valorImovel = valorImovel;
	}

	public String getValorFgts() {
		return valorFgts;
	}

	public void setValorFgts(String valorFgts) {
		this.valorFgts = valorFgts;
	}

	public String getValorRecusros() {
		return valorRecusros;
	}

	public void setValorRecusros(String valorRecusros) {
		this.valorRecusros = valorRecusros;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getValorProposto() {
		return valorProposto;
	}

	public void setValorProposto(String valorProposto) {
		this.valorProposto = valorProposto;
	}

	public String getPrazoProposto() {
		return prazoProposto;
	}

	public void setPrazoProposto(String prazoProposto) {
		this.prazoProposto = prazoProposto;
	}

	public String getPrestacaoProposto() {
		return prestacaoProposto;
	}

	public void setPrestacaoProposto(String prestacaoProposto) {
		this.prestacaoProposto = prestacaoProposto;
	}

	public String getValorAprovado() {
		return valorAprovado;
	}

	public void setValorAprovado(String valorAprovado) {
		this.valorAprovado = valorAprovado;
	}

	public String getPrazoAprovado() {
		return prazoAprovado;
	}

	public void setPrazoAprovado(String prazoAprovado) {
		this.prazoAprovado = prazoAprovado;
	}

	public String getPrestacaoAprovado() {
		return prestacaoAprovado;
	}

	public void setPrestacaoAprovado(String prestacaoAprovado) {
		this.prestacaoAprovado = prestacaoAprovado;
	}

	public String getAnalise() {
		return analise;
	}

	public void setAnalise(String analise) {
		this.analise = analise;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Parameters({"mailchimp.test.apikey", "mailchimp.test.listid"})
	public ExistingMethodExample(String apiKey, String listId) {
        this.apiKey = apiKey;
        this.listId = listId;
    }
	
	private static org.apache.log4j.Logger log = Logger.getLogger(com.ecwid.maleorang.MailchimpClient.class);

    /**
     * Subscribes a user to list.
     */
//    @Test
    public void run() {
    	log.setLevel(Level.WARN);
    	
    	
    	java.util.logging.Logger.getLogger("com.ecwid.maleorang.MailchimpClient").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
    	
        MailchimpClient client = new MailchimpClient(apiKey);
        try {
            EditMemberMethod.CreateOrUpdate method = new EditMemberMethod.CreateOrUpdate(listId, getEmail());
            method.status = "subscribed";
            method.merge_fields = new MailchimpObject();
            method.merge_fields.mapping.put("FNAME", getNome());
            method.merge_fields.mapping.put("LNAME", getSobrenome());
            method.merge_fields.mapping.put("PHONE", getPhone());
            method.merge_fields.mapping.put("MMERGE6", getCpf());
            
            
            method.merge_fields.mapping.put("MMERGE5", getAnalise() );
            
            method.merge_fields.mapping.put("MMERGE7", getResultado() );
            method.merge_fields.mapping.put("MMERGE14", getMotivo() );
            
            method.merge_fields.mapping.put("MMERGE8", getValorProposto() );
            method.merge_fields.mapping.put("MMERGE9", getPrazoProposto() );
            method.merge_fields.mapping.put("MMERGE10", getPrestacaoProposto() );
            
            method.merge_fields.mapping.put("MMERGE11", getValorAprovado() );
            method.merge_fields.mapping.put("MMERGE12", getPrazoAprovado() );
            method.merge_fields.mapping.put("MMERGE13", getPrestacaoAprovado() );
            
            method.merge_fields.mapping.put("MMERGE15", getValorImovel() );
            method.merge_fields.mapping.put("MMERGE16", getValorFgts() );
            method.merge_fields.mapping.put("MMERGE17", getValorRecusros() );
            method.merge_fields.mapping.put("MMERGE18", getValorSubsidio() );
            
            method.merge_fields.mapping.put("MMERGE19", getEntrada() );

            MemberInfo member = client.execute(method);
  
            System.err.println("The user has been successfully subscribed: " + getEmail() + " : " + getAnalise());
            System.err.println("The user has been successfully subscribed: " + getResultado() + " : " + getMotivo());
            
            
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public static void main(String[] args){
    	
    	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
//    	mailchimp.setEmail("lamegom@me.com");
//    	mailchimp.setAnalise("N/A");
//    	mailchimp.setAnalise("OK");
//    	System.out.println(mailchimp.getList().size());
    	try {
			mailchimp.getMemberInfo("lamegom@me.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public List<MemberInfo> getList(){
    	
    	java.util.logging.Logger.getLogger("com.ecwid.maleorang.MailchimpClient").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
    	
    	
    	MailchimpClient client = new MailchimpClient(apiKey);
        try {
        	
        	GetMembersMethod method = new GetMembersMethod("f0704e46ec/segments/6289");
    	List< MemberInfo> infos = new ArrayList<MemberInfo>();
    	method.count = 150;
    	MailchimpObject members = client.execute(method);
    	
//    	System.out.println(method.toJson());
    	
//    	for(MemberInfo i : ((List<MemberInfo>) members.mapping.get("members"))){
//    		System.out.println(i.merge_fields.mapping.get("MMERGE5"));
//    		if("Aprovado".equals(i.merge_fields.mapping.get("MMERGE5"))){
//    			infos.add(i);
//    		}
//    		if("OK".equals(i.merge_fields.mapping.get("MMERGE5"))){
//    			infos.add(i);
//    		}
//    		if("Cadastrais".equals(i.merge_fields.mapping.get("MMERGE5"))){
//    			infos.add(i);
//    		}

//    	}
    	
//    	System.out.println(infos.size());
    	
    	return  (List<MemberInfo>) members.mapping.get("members");
    		
    	
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return null;
    }
    
    
    public SubscribeResponse  getMemberInfo(String email) throws Exception{

    	java.util.logging.Logger.getLogger("com.ecwid.maleorang.MailchimpClient").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
    	MailchimpClient client = new MailchimpClient(apiKey);
        try {
        	CustomMethodExample.SubscribeRequest request = new SubscribeRequest(listId, email);


        	CustomMethodExample.SubscribeResponse response = client.execute(request);
            //System.err.println("The user has been successfully subscribed: " + response);
            
            return response;
        } finally {
            client.close();
        }
    }
}
