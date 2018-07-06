package com.mkyong.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;

public class JDBCPreparedStatement {

	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
//	private static final String DB_CONNECTION = "jdbc:mysql://54.245.155.42:3306/coban_leads?verifyServerCertificate=false&useSSL=true";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/coban_leads?verifyServerCertificate=false&useSSL=true";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "3778brooklinsp07";

	public static void main(String[] argv) {

//		try {
//
//			insertRecordIntoTable();
//
//		} catch (SQLException e) {
//
//			System.out.println(e.getMessage());
//
//		}
		
		/*Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.MILLISECOND,0);

		java.util.Date d = cal.getTime();
		System.out.println(new java.sql.Timestamp(new Date().getTime()));/*/
		
String result = "<div _ngcontent-c5=\"\" class=\"titulo\" style=\"font-size: 18px;\"><em _ngcontent-c5=\"\" class=\"icone icone-seta-direita\"></em> Resultado da Análise Prévia<div _ngcontent-c5=\"\" class=\"subtitulo\" hidden=\"\"></div><!----><!----></div><div _ngcontent-c5=\"\" class=\"row\"><!----><div _ngcontent-c14=\"\" class=\"col-lg-6 col-md-6 col-sm-6\"><texto-output-componente _ngcontent-c14=\"\" _nghost-c16=\"\"><span _ngcontent-c16=\"\" class=\"texto-output\"><!----><em _ngcontent-c16=\"\" class=\"icone icone-usuario\"></em> <span _ngcontent-c16=\"\" class=\"titulo\">Nome <small _ngcontent-c16=\"\"><em _ngcontent-c16=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c16=\"\" class=\"valor\" style=\"background: rgb(242, 242, 242) none repeat scroll 0% 0%; color: rgb(68, 105, 148); font-size: 14px;\" align=\"left\">ELVIS JOSE RIBEIRO</div></span></texto-output-componente></div><!----><div _ngcontent-c14=\"\" class=\"col-lg-6 col-md-6 col-sm-6\"><texto-output-componente _ngcontent-c14=\"\" _nghost-c16=\"\"><span _ngcontent-c16=\"\" class=\"texto-output\"><!----><em _ngcontent-c16=\"\" class=\"icone icone-usuario\"></em> <span _ngcontent-c16=\"\" class=\"titulo\">CPF/CNPJ <small _ngcontent-c16=\"\"><em _ngcontent-c16=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c16=\"\" class=\"valor\" style=\"background: rgb(242, 242, 242) none repeat scroll 0% 0%; color: rgb(68, 105, 148); font-size: 14px;\" align=\"left\">200.586.218-57</div></span></texto-output-componente></div><!----><div _ngcontent-c14=\"\" class=\"col-lg-12 col-md-12 col-sm-12\" style=\"padding-bottom: 10px\"><texto-output-componente _ngcontent-c14=\"\" _nghost-c16=\"\"><span _ngcontent-c16=\"\" class=\"texto-output\"><!----><em _ngcontent-c16=\"\" class=\"icone-seta-direita\"></em> <span _ngcontent-c16=\"\" class=\"titulo\">Data da Análise <small _ngcontent-c16=\"\"><em _ngcontent-c16=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c16=\"\" class=\"valor\" style=\"background: rgb(242, 242, 242) none repeat scroll 0% 0%; color: rgb(68, 105, 148); font-size: 14px;\" align=\"left\">24 de Fevereiro de 2018 às 05h35</div></span></texto-output-componente></div><!----><span _ngcontent-c14=\"\"><!----><!----><div _ngcontent-c14=\"\" style=\"padding-top: 30px\" align=\"center\"><em _ngcontent-c14=\"\" class=\"icone icone-checado icon-resultado\"></em></div></span><!----><div _ngcontent-c14=\"\" class=\"mensagem-sucesso\" align=\"center\"><em _ngcontent-c14=\"\" style=\"font-size: 16px\">Parabéns! Sem pendências!</em></div><!----></div><!----> || <div _ngcontent-c7=\"\" class=\"titulo\" style=\"font-size: 18px;\"><em _ngcontent-c7=\"\" class=\"icone icone-seta-direita\"></em> Resultado da Análise Prévia<div _ngcontent-c7=\"\" class=\"subtitulo\" hidden=\"\"></div><!----><!----></div><div _ngcontent-c7=\"\" class=\"row\"><!----><div _ngcontent-c16=\"\" class=\"col-lg-6 col-md-6 col-sm-6\"><texto-output-componente _ngcontent-c16=\"\" _nghost-c18=\"\"><span _ngcontent-c18=\"\" class=\"texto-output\"><!----><em _ngcontent-c18=\"\" class=\"icone icone-usuario\"></em> <span _ngcontent-c18=\"\" class=\"titulo\">Nome <small _ngcontent-c18=\"\"><em _ngcontent-c18=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c18=\"\" class=\"valor\" align=\"left\" style=\"background: rgb(242, 242, 242); color: rgb(68, 105, 148); font-size: 14px;\">ELVIS JOSE RIBEIRO</div></span></texto-output-componente></div><!----><div _ngcontent-c16=\"\" class=\"col-lg-6 col-md-6 col-sm-6\"><texto-output-componente _ngcontent-c16=\"\" _nghost-c18=\"\"><span _ngcontent-c18=\"\" class=\"texto-output\"><!----><em _ngcontent-c18=\"\" class=\"icone icone-usuario\"></em> <span _ngcontent-c18=\"\" class=\"titulo\">CPF/CNPJ <small _ngcontent-c18=\"\"><em _ngcontent-c18=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c18=\"\" class=\"valor\" align=\"left\" style=\"background: rgb(242, 242, 242); color: rgb(68, 105, 148); font-size: 14px;\">200.586.218-57</div></span></texto-output-componente></div><!----><div _ngcontent-c16=\"\" class=\"col-lg-12 col-md-12 col-sm-12\" style=\"padding-bottom: 10px;\"><texto-output-componente _ngcontent-c16=\"\" _nghost-c18=\"\"><span _ngcontent-c18=\"\" class=\"texto-output\"><!----><em _ngcontent-c18=\"\" class=\"icone-seta-direita\"></em> <span _ngcontent-c18=\"\" class=\"titulo\">Data da Análise <small _ngcontent-c18=\"\"><em _ngcontent-c18=\"\" style=\"color: #616060\"></em></small></span><div _ngcontent-c18=\"\" class=\"valor\" align=\"left\" style=\"background: rgb(242, 242, 242); color: rgb(68, 105, 148); font-size: 14px;\">04 de Abril de 2018 às 13h23</div></span></texto-output-componente></div><!----><span _ngcontent-c16=\"\"><!----><!----><div _ngcontent-c16=\"\" align=\"center\" style=\"padding-top: 30px;\"><em _ngcontent-c16=\"\" class=\"icone icone-checado icon-resultado\"></em></div></span><!----><div _ngcontent-c16=\"\" align=\"center\" class=\"mensagem-sucesso\"><em _ngcontent-c16=\"\" style=\"font-size: 24px;\">Parabéns! Sem pendências!</em><br _ngcontent-c16=\"\"><br _ngcontent-c16=\"\"><!----></div><!----></div><!----> ";
JDBCPreparedStatement j = new JDBCPreparedStatement();
String res = j.getNomeCompleto(result);

System.out.println(res);

	}

	public void insertRecordIntoTable(String cpf, String result, String userAgent){

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO verify"
				+ "( cpf, verify_date, verify_result, userAgent) VALUES"
				+ "(?,?,?,?)";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, cpf);
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			preparedStatement.setString(3, result);
			preparedStatement.setString(4, userAgent);

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

//			System.out.println("Record is inserted into verify table!");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}

		}

	}
	
	public void insertRecordIntoTabledownload(String cpf, String email){

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String insertTableSQL = "INSERT INTO download"
				+ "( cpf, down_date, email) VALUES"
				+ "(?,?,?)";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertTableSQL);

			preparedStatement.setString(1, cpf);
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(new java.util.Date().getTime()));
			preparedStatement.setString(3, email);

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into verify table download!");

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}

			if (dbConnection != null) {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}

		}

	}
	
	public String selectRecordsFromTable(String cpf) throws SQLException {
		
		
		
		JSONArray array = new JSONArray();


		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT verify_date, verify_result, userAgent FROM verify WHERE cpf = ?";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cpf);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			
			while (rs.next()) {

				@SuppressWarnings("deprecation")
				String data = df.format(rs.getTimestamp("verify_date"));
				String result = rs.getString("verify_result");
				String userAgent = rs.getString("userAgent");
				
				JSONObject item = new JSONObject();
				item.put("verify_date", data);
				item.put("verify_result", result);
				item.put("userAgent", userAgent);
				array.put(item);

//				System.out.println("data : " + data);
//				System.out.println("result : " + result);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		String message;
		JSONObject json = new JSONObject();
		
		json.put(cpf, array);

		message = json.toString();
		
		return message;

	}
	
public String selectAllRecordsFromTable() throws SQLException {
		
		
		
		JSONArray array = new JSONArray();


		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM verify";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			
			while (rs.next()) {

				@SuppressWarnings("deprecation")
				String data = df.format(rs.getTimestamp("verify_date"));
				String result = rs.getString("verify_result");
				String cpf = rs.getString("cpf");
				String userAgent = rs.getString("userAgent");
				
				
				JSONObject item = new JSONObject();
				
				item.put("cpf", cpf);
				item.put("verify_date", data);
				item.put("verify_result", result);
				item.put("userAgent", userAgent);
				array.put(item);

//				System.out.println("data : " + data);
//				System.out.println("result : " + result);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
		String message;
		JSONObject json = new JSONObject();
		
		json.put("results", array);

		message = json.toString();
		
		return message;

	}

public String selectUFfromAgencia(String agencia) throws SQLException {
	String estado = null;

	Connection dbConnection = null;
	PreparedStatement preparedStatement = null;

	String selectSQL = "SELECT UF FROM agencias where COD_COMPE_AG=?";

	try {
		dbConnection = getDBConnection();
		preparedStatement = dbConnection.prepareStatement(selectSQL);
		preparedStatement.setString(1, agencia);

		// execute select SQL stetement
		ResultSet rs = preparedStatement.executeQuery();

		
		while (rs.next()) {

			estado = rs.getString("UF");
			

		}

	} catch (SQLException e) {

		System.out.println(e.getMessage());

	} finally {

		if (preparedStatement != null) {
			preparedStatement.close();
		}

		if (dbConnection != null) {
			dbConnection.close();
		}

	}

	
	return estado;

}


public String selectAgenciafromMunicipio(String municipio) throws SQLException {
	String agencia = null;

	Connection dbConnection = null;
	PreparedStatement preparedStatement = null;

	String selectSQL = "SELECT COD_COMPE_AG FROM agencias where MUNICIPIO=? ORDER BY UF DESC LIMIT 1";

	try {
		dbConnection = getDBConnection();
		preparedStatement = dbConnection.prepareStatement(selectSQL);
		preparedStatement.setString(1, municipio);

		// execute select SQL stetement
		ResultSet rs = preparedStatement.executeQuery();

		
		while (rs.next()) {

			agencia = rs.getString("COD_COMPE_AG");
			

		}

	} catch (SQLException e) {

		System.out.println(e.getMessage());

	} finally {

		if (preparedStatement != null) {
			preparedStatement.close();
		}

		if (dbConnection != null) {
			dbConnection.close();
		}

	}

	
	return agencia;

}


	private Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                            DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}

	private java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

	public JSONObject selectLastRecordsFromTable(String cpf) throws SQLException {
		JSONObject item = new JSONObject();


		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT verify_result FROM verify WHERE cpf = ? ORDER BY verify_date DESC LIMIT 1";

		try {
			dbConnection = getDBConnection();
			preparedStatement = dbConnection.prepareStatement(selectSQL);
			preparedStatement.setString(1, cpf);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
			
			while (rs.next()) {

				String result = rs.getString("verify_result");

				
				
				item.put("cpf", cpf);
				item.put("analise",getAnalisys(result));
				try{
					item.put("nomeCompleto",getNomeCompleto(result));
				}catch(Exception e){
					item.put("nomeCompleto","");
				}

//				System.out.println("data : " + data);
//				System.out.println("result : " + result);

			}

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}

		}
//		String message;
//
//		message = item.toString();
		
		return item;
	}
	
	public String getNomeCompleto(String result){
		Document doc = Jsoup.parse(result);
		
		//System.out.println(doc.html());

		try{
			Elements nomeCompletoElem = doc.select("div>div>texto-output-componente>span>div");
			return nomeCompletoElem.first().text();
		
		}catch(SelectorParseException e){
			
			Elements nomeCompletoElem = doc.select("#resultadoAnaliseInterv\3a j_id155 > span");
			return nomeCompletoElem.first().text();
		}
		
		
	}
	
	public String getAnalisys(String result){
		
		String analise = "N/A";
		
		
		if(result.toUpperCase().indexOf("Error".toUpperCase()) != -1){
            
			analise = "Erro";
            
            
           
            
            }else if(result.equals("<html>\n <head></head>\n <body>\n  \n </body>\n</html>")){
            
            	analise = "OK";
            
            
            


            
            }else{
            
            if(result.toUpperCase().indexOf("xvfb-run".toUpperCase()) != -1){
            
            	analise = "Erro";
            
            }else if(result.toUpperCase().indexOf("Timeout".toUpperCase()) != -1){
            
            	analise = "Erro";
            
            }else if(result.toUpperCase().indexOf("POSSUI LIMITE DE ".toUpperCase()) != -1){
                
                
            	analise = "Reprovado - Limite de credito";
            
            

            
            
            }else if(result.toUpperCase().indexOf("ORIENTE O CLIENTE A PROCURAR A SUA".toUpperCase()) != -1){
            
            
            	analise = "Reprovado";
            

            }else if(result.toUpperCase().indexOf("CADASTRA".toUpperCase()) != -1){
            
            
            	analise = "Cadastrais";
            
            }else if(result.toUpperCase().indexOf("firefox".toUpperCase()) != -1){
            
            
            	analise = "Erro";
            
            }
            else{
            
            
            	analise = "Aprovado";
            }
            }
		
		return analise;

	}

}