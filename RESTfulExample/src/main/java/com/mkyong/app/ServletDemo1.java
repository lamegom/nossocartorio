package com.mkyong.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.examples.interactive.form.PrintFields;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import com.mkyong.app.CustomMethodExample.SubscribeResponse;
import com.mkyong.app.dao.JDBCPreparedStatement;
import com.mkyong.bean.Cliente;
import com.mkyong.bean.DynaBean;
import com.mkyong.bean.RetornoCpf;

public class ServletDemo1 extends HttpServlet{
	private static final long serialVersionUID = 1L;
   
	private SubscribeResponse subsresponse = null;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException{
		doGet(request, response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		try{
			
		String email = request.getParameter("email");
		
		System.out.println("email: " + email);
		
        if (email != null) {
        	
        	Cookie fileDwnld = new Cookie("fileDownload", "true");
            fileDwnld.setPath("/");
            
        	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");

        	
        	try {
        		subsresponse = mailchimp.getMemberInfo(email);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        	
        	subsresponse.cliente = getCliente(email);
        	
        	if(subsresponse.cliente.getNomeCompleto()==null){
        		
        		String cpf = (String) subsresponse.merge_fields.mapping.get("MMERGE6");
        	
        		JDBCPreparedStatement dao = new JDBCPreparedStatement();

        		JSONObject result = dao.selectLastRecordsFromTable(cpf);
        		String strNomeCompleto = (String) result.get("nomeCompleto");

        		
        		if(strNomeCompleto.equals("")){
	        		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		    		
		    		RetornoCpf retorno = null;
		    		
		    		if(subsresponse.cliente.getDataNascimento()!=null){
		    			retorno = getCpf(cpf, format.format(df.parse(subsresponse.cliente.getDataNascimento().get(0))) );
		    			strNomeCompleto = retorno.getDadosTitular().getTitular();
		    		}else{
		    			strNomeCompleto = null;
		    		}
        		}
	        	
	    		try{
	    		List<String> nomeCompleto = new ArrayList<String>();
	    		
	    		nomeCompleto.add(strNomeCompleto);
	    		subsresponse.cliente.setNomeCompleto(nomeCompleto);
	    		
	    		}catch(java.lang.NullPointerException n){}
        	}

                PrintFields exporter = new PrintFields(subsresponse);
                InputStream is = exporter.printFields();
            
            
            
//            String fileName = "target/FillFormField.pdf";
            String fileType = "application/pdf";
            // Find this file id in database to get file name, and file type

            // You must tell the browser the file type you are going to send
            // for example application/pdf, text/plain, text/html, image/jpg
            response.setContentType(fileType);

            // Make sure to show the download dialog
            response.setHeader("Content-disposition","attachment; filename=Proposta-" + subsresponse.merge_fields.FNAME + "-" + subsresponse.merge_fields.LNAME + ".pdf");

            // Assume file name is retrieved from database
            // For example D:\\file\\test.pdf

//            File my_file = new File(fileName);
            response.addCookie(fileDwnld);
            // This should send the file to browser
            OutputStream out = response.getOutputStream();
            
//            FileInputStream in = new FileInputStream(my_file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = is.read(buffer)) > 0){
               out.write(buffer, 0, length);
            }
            
            is.close();
            
            out.flush();
            
            
            

        }else{
        	throw new Exception("No E-mail");
        }



} catch (Exception e) {
    e.printStackTrace();

}finally{
	persist(subsresponse.cliente);
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
	
	public RetornoCpf getCpf(String cpf, String dtNasc) throws Exception{
		Cliente cliente = null;
		HttpURLConnection conn = null;
	 
		String chave = "AAAAA-BBBBB-CCCCC-DDDDD";
		  
		String webservice = "http://ws.iwebservice.info/CPF/?chave=" + chave + "&cpf=" +cpf  + "&dataNascimento=" +dtNasc  + "&formato=JSON";
		System.out.println(webservice);
		
		URL url = new URL(webservice);
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
		
		RetornoCpf ret = null;
		
		if(!sb.toString().equals("null")){
		

				ObjectMapper mapper = new ObjectMapper();
				ret = mapper.readValue(sb.toString(), RetornoCpf.class);
				
				System.out.println(ret);
		}
		return ret;
	}
	
	public void persist(Cliente cliente){
        Date now = new Date();


        JDBCPreparedStatement dao = new JDBCPreparedStatement();
		dao.insertRecordIntoTabledownload(cliente.getCpf().get(0), cliente.getEmail().get(0));
	}
}
