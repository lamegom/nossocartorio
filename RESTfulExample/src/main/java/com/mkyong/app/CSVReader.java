package com.mkyong.app;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.mkyong.bean.Cliente;
import com.mkyong.bean.DynaBean;

public class CSVReader {

    public static void main(String[] args) {
    	String csvFile = "C:\\\\Users\\marcio\\Downloads\\download (49).csv";
    	String csvFile2 = "C:\\\\Users\\marcio\\Downloads\\download (50).csv";
    	
    	
    	CSVReader csv = new CSVReader();
    	List<String> inbound = csv.run(csvFile);
    	
    	List<String> mailchimpList = csv.run(csvFile2);
    	
    	List<String> fora = new ArrayList<String>();
    	
    	
    	for(String lead: inbound){
    		if(!mailchimpList.contains(lead)){
    			if(lead!=null) fora.add(lead);
    		}
    	}
    	
    	System.out.println(fora.size());
    	
    	for(String email: fora){
    		try {
				Cliente cliente = csv.getCliente(email);
				
				ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
		    	mailchimp.setEmail(cliente.getEmail().get(0));
		    	mailchimp.setNome(cliente.getNome().get(0));
		    	mailchimp.setSobrenome(cliente.getSobrenome().get(0));
		    	mailchimp.setPhone(cliente.getPhone().get(0));
		    	mailchimp.setCpf(cliente.getCpf().get(0));
		    	mailchimp.start();
		    	
//		    	System.out.println("mailchimp.getNome(): " + mailchimp.getNome());
		    	System.out.println("mailchimp.getSobrenome(): " + mailchimp.getSobrenome());
				
				
    		} catch (NullPointerException n) {
    		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    
    }
    
    public Cliente getCliente(String email) throws Exception{
		Cliente cliente = null;
		HttpURLConnection conn = null;
	 
		  

		URL url = new URL("http://www.nossocartorio.com.br/blog/inbound-api/v1/leads/?key=e650011b8bcc937edbc73d37fee667c5&token=e14e3d974bad2da3945e3947873c22ac&results_per_page=-1&page=1&include_lists[]=24&email="+email);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
//		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		StringBuffer sb = new StringBuffer();
//		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
//			System.out.println(output);
			sb.append(output);
		}
		
		if(!sb.toString().equals("null")){
		

				ObjectMapper mapper = new ObjectMapper();
				DynaBean db = mapper.readValue(sb.toString(), DynaBean.class);
				
				for(Object obj : db.any().keySet()){
					Object object =((LinkedHashMap) db.any().get(obj)).get("meta_data");
					
					cliente = mapper.convertValue(object, Cliente.class);
					
					//System.out.println(cliente);
				}
				

				String imovelCliente = cliente.getValorImovel().get(0);
				double imovel = NumberFormat.getInstance().parse(imovelCliente).doubleValue();
				
//				System.out.println("Valor Imovel Cliente: " + imovelCliente);
//				System.out.println("Valor Imovel: " + imovel);
				
				if(imovel > 150000.0D){

					cliente.setProdutoBB("SFH");
				
				}else{	

					cliente.setProdutoBB("PMCMV");
				
				}
		
		
	    		List<String> lstEmail = new ArrayList<String>();
	    		lstEmail.add(email);
	    		cliente.setEmail(lstEmail);
	    		

	    		
	    		
		}
		return cliente;
	}
    
    public List<String> run(String csvFile){
        
        
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        List<String> lst = new ArrayList<String>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

//                System.out.println("Lead [cpf= " + country[0] + " , name=" + country[1] + "]");
                
                lst.add(country[0]);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return lst;
    }

}