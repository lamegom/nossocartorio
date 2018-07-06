
package org.apache.pdfbox.examples.interactive.form;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import com.mkyong.app.CustomMethodExample;
import com.mkyong.app.CustomMethodExample.SubscribeResponse;

/**
 * This example will take a PDF document and print all the fields from the file.
 * 
 * @author Ben Litchfield
 * 
 */
public class PrintFields
{
private CustomMethodExample.SubscribeResponse cliente;


private static org.apache.log4j.Logger log = Logger.getLogger(org.apache.pdfbox.examples.interactive.form.PrintFields.class);
    public PrintFields(SubscribeResponse cliente2) {
    	
	super();
	this.cliente = cliente2;
}

	public SubscribeResponse getCliente() {
	return cliente;
}

public void setCliente(SubscribeResponse cliente) {
	this.cliente = cliente;
}

	/**
     * This will print all the fields from the document.
     * 
     * @param pdfDocument The PDF to get the fields from.
	 * @return 
     * 
     * @throws IOException If there is an error getting the fields.
     */
    public InputStream printFields() throws Exception
    {
    	java.util.logging.Logger.getLogger("org.apache.pdfbox.pdmodel.font.PDType1Font").setLevel(java.util.logging.Level.OFF);
    	log.setLevel(Level.INFO);
		PDDocument pdf = null;
		InputStream is = null;
		ByteArrayOutputStream out = null;
        String pdfFile = "/home/ubuntu/pdf/Proposta.pdf";
        try
        {

         pdf = PDDocument.load(new File(pdfFile));

    	
    	PDDocumentCatalog docCatalog = pdf.getDocumentCatalog();
        PDAcroForm acroForm = docCatalog.getAcroForm();
        

     // Retrieve an individual field and set its value.
        PDTextField field1 = (PDTextField) acroForm.getField( "Campo formatado 1" );
        
        
        String nomeCompleto = cliente.cliente.getNomeCompleto().get(0)!=null?cliente.cliente.getNomeCompleto().get(0): ((String) cliente.merge_fields.mapping.get("FNAME")) + " " + ((String) cliente.merge_fields.mapping.get("LNAME"));
        field1.setValue(nomeCompleto);
        
        PDTextField field1_2 = (PDTextField) acroForm.getField( "Campo formatado 1_2" );
        field1_2.setValue((String) cliente.merge_fields.mapping.get("MMERGE6"));
        
        PDTextField field1_14 = (PDTextField) acroForm.getField( "Campo formatado 1_14" );
        field1_14.setValue((String) cliente.merge_fields.mapping.get("PHONE"));
        
        PDCheckBox sel2 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 2" );
        sel2.check();
        
        PDTextField field1_13 = (PDTextField) acroForm.getField( "Campo formatado 1_13" );
        field1_13.setValue(cliente.email_address);
        
        
        PDCheckBox sel4 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 4" );
        sel4.check();
        
        PDTextField field1_15 = (PDTextField) acroForm.getField( "Campo formatado 1_15" );
        String despesa1 = (String) cliente.cliente.getDespesas().get(0); 
        field1_15.setValue(despesa1!=null?despesa1:"1.000,00");
        
        PDTextField field1_29 = (PDTextField) acroForm.getField( "Campo formatado 1_29" );
        String despesa2 = (String) cliente.cliente.getDespesas().get(0);
        field1_29.setValue(despesa2!=null?despesa2:"1.000,00");
        
        PDCheckBox sel1_61 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_61" );
        sel1_61.check();
        
        PDCheckBox sel1_64 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_64" );
        sel1_64.check();
        
        PDCheckBox sel1_66 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_66" );
        sel1_66.check();
        
        PDCheckBox sel1_68 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_68" );
        sel1_68.check();
        
        PDCheckBox sel1_70 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_70" );
        sel1_70.check();

        PDTextField field1_20 = (PDTextField) acroForm.getField( "Campo formatado 1_20" );
        double imovel = 0D;
        try{
        	imovel = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).parse((String) cliente.merge_fields.mapping.get("MMERGE15")).doubleValue();
        }catch(java.text.ParseException p){
        	imovel = getNumber(cliente.cliente.getValorImovel().get(0));
        }
        
        double registroCartorario = imovel * 0.01D;
        double registro = 0D;
        
        if(cliente.cliente.getProdutoBB().equals("SFH")){
        	registro = 20000.0;
        	if(registroCartorario < 20000.0D){
        		registro = registroCartorario;
        	}
        }else{
        	registro = 1900.0;
        	if(registroCartorario < 1900.0D){
        		registro = registroCartorario;
        	}
        }

        field1_20.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(registro));
        
        PDTextField field1_21 = (PDTextField) acroForm.getField( "Campo formatado 1_21" );
        double itbiSFH = imovel * 0.04D;
        double itbiPMCMV = imovel * 0.03D;
        double itbi = 0D;
        
        if(cliente.cliente.getProdutoBB().equals("SFH")){
        	itbi = 400000.0D;
        	if(itbiSFH < 400000.0D){
        		itbi = itbiSFH;
        	}
        }else{
        	itbi = 5700.0D;
        	if(itbiPMCMV < 5700.0D){
        		itbi = itbiPMCMV;
        	}
        }

        field1_21.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(itbi));
        
 
        
        PDTextField field1_22 = (PDTextField) acroForm.getField( "Campo formatado 1_22" );
       // double despesasFianciar = getNumber(registro) + getNumber(itbi);
        
        double despesasFianciar = registro + itbi;
        field1_22.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(despesasFianciar));
        
        
        PDTextField field1_53 = (PDTextField) acroForm.getField( "Campo formatado 1_53" );
        field1_53.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(imovel));
        
        
        PDTextField field1_50 = (PDTextField) acroForm.getField( "Campo formatado 1_50" );
//        double fgts = getNumber(cliente.cliente.getFgts().get(0));
        double fgts = 0D;
        try{
        	fgts = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).parse((String) cliente.merge_fields.mapping.get("MMERGE16")).doubleValue();
        }catch(NullPointerException n){
        	
        }catch(java.text.ParseException p){
        	fgts = getNumber(cliente.cliente.getFgts().get(0));
        }
        field1_50.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(fgts));
        
        PDTextField field1_51 = (PDTextField) acroForm.getField( "Campo formatado 1_51" );
        //cliente.cliente.getSubsidio()
        double subsidio = 0D;
        try{
        	subsidio = getNumber((String) cliente.merge_fields.mapping.get("MMERGE18"));
        }catch(NullPointerException | java.text.ParseException p){
        	subsidio = getNumber(cliente.cliente.getSubsidio().toString());
        }
        field1_51.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(subsidio));
        
        PDTextField field1_52 = (PDTextField) acroForm.getField( "Campo formatado 1_52" );
        //double recursos = getNumber(cliente.cliente.getRecursosProprios().get(0));

        double recursos = 0D;
        try{
        	recursos = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).parse((String) cliente.merge_fields.mapping.get("MMERGE17")).doubleValue();
        }catch(NullPointerException | java.text.ParseException p){
        	recursos = getNumber(cliente.cliente.getRecursosProprios().get(0));
        }
        
        PDTextField field1_58 = (PDTextField) acroForm.getField( "Campo formatado 1_58" );
        double financiar = imovel - (recursos + fgts + subsidio);
        
        double valorProposto = 0D;
        try{
        	valorProposto = NumberFormat.getCurrencyInstance(new Locale("pt","BR")).parse((String) cliente.merge_fields.mapping.get("MMERGE8")).doubleValue();
        }catch(NullPointerException | java.text.ParseException p){

        }
        
        double diferenca = valorProposto - financiar;
        
        if(diferenca > 0){
        	recursos = recursos + diferenca;
        	financiar = imovel - (recursos + fgts + subsidio);
        }  
        
        field1_52.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(recursos));
        field1_58.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(financiar));
        
        PDTextField field1_55 = (PDTextField) acroForm.getField( "Campo formatado 1_55" );
        double total = financiar + despesasFianciar;
        field1_55.setValue(NumberFormat.getCurrencyInstance(new Locale("pt","BR")).format(total));
        
        PDTextField field1_56 = (PDTextField) acroForm.getField( "Campo formatado 1_56" );
//        double prazo = getNumber(cliente.cliente.getPrazo());
        int prazo = 360;
        try{
        	prazo = Integer.valueOf((String) cliente.merge_fields.mapping.get("MMERGE9"));
        }catch(NullPointerException | java.lang.NumberFormatException n){
        	prazo = (int) getNumber(cliente.cliente.getPrazo().get(0));
        }
        field1_56.setValue(NumberFormat.getInstance().format(prazo));
        
        if(cliente.cliente.getPrazo().get(0).equals("sac")){
	        
	        PDCheckBox sel1_72 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_72" );
            sel1_72.check();
        }else{
        	PDCheckBox sel1_71 =  (PDCheckBox) acroForm.getField( "Caixa de sele#C3#A7#C3#A3o 1_71" );
	        sel1_71.check();
        }
        
        
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        PDTextField field1_66 = (PDTextField) acroForm.getField( "Campo formatado 1_66" );
        field1_66.setValue(day+"");
        
        PDTextField field1_68 = (PDTextField) acroForm.getField( "Campo formatado 1_68" );
        field1_68.setValue(month+"");
        
        PDTextField field1_67 = (PDTextField) acroForm.getField( "Campo formatado 1_67" );
        field1_67.setValue(year+"");
        
        
        PDTextField field1_69 = (PDTextField) acroForm.getField( "Campo formatado 1_69" );
        try{
        	field1_69.setValue(cliente.cliente.getMunicipio().get(0));
        }catch(NullPointerException n){
        	field1_69.setValue(cliente.cliente.getCidade().get(0));
        }
        /* List<PDField> fields = acroForm.getFields();

        System.out.println(fields.size() + " top-level fields were found on the form");

        for (PDField field : fields)
        {
            processField(field, "|--", field.getPartialName());
        }*/
        
     // Save and close the filled out form.
//        pdf.save("target/FillFormField.pdf");
        out = new ByteArrayOutputStream();

        pdf.save(out);

        byte[] data = out.toByteArray();
        is = new ByteArrayInputStream(data);
        
        }
        finally
        {
            if (pdf != null)
            {
                pdf.close();
            }
        }
        
        return is;
    }

    private void processField(PDField field, String sLevel, String sParent) throws IOException
    {
        String partialName = field.getPartialName();
        
        if (field instanceof PDNonTerminalField)
        {
            if (!sParent.equals(field.getPartialName()))
            {
                if (partialName != null)
                {
                    sParent = sParent + "." + partialName;
                }
            }
            System.out.println(sLevel + sParent);

            for (PDField child : ((PDNonTerminalField)field).getChildren())
            {
                processField(child, "|  " + sLevel, sParent);
            }
        }
        else
        {
            
        	String fieldValue = field.getValueAsString();
            StringBuilder outputString = new StringBuilder(sLevel);
            outputString.append(sParent);
            if (partialName != null)
            {
                outputString.append(".").append(partialName);
            }
            outputString.append(" = ").append(fieldValue);
            outputString.append(",  type=").append(field.getClass().getName());
            System.out.println(outputString);
        }
    }

    /**
     * This will read a PDF file and print out the form elements. <br>
     * see usage() for commandline
     * 
     * @param args command line arguments
     * 
     * @throws IOException If there is an error importing the FDF document.
     */
    public static void main(String[] args) throws IOException
    {
 
//    		Cliente cliente = new Cliente();
//    		
//    		List<String> nome = new ArrayList<String>();
//    		nome.add("Tulio Henrique de Albuquerque Lamego");
//    		cliente.setNomeCompleto(nome);
//    		
//    		List<String> cpf = new ArrayList<String>();
//    		cpf.add("262.370.558-51");
//    		cliente.setCpf(cpf);
//    		
//    		List<String> phone = new ArrayList<String>();
//    		phone.add("(11) 98108-4320");
//    		cliente.setPhone(phone);
//    		
//    		List<String> email = new ArrayList<String>();
//    		email.add("lamegom@me.com");
//    		cliente.setEmail(email);
//    		
//    		List<String> despesas = new ArrayList<String>();
//    		despesas.add("1.000,00");
//    		cliente.setDespesas(despesas);
//    		
//    		
//    		List<String> imovel = new ArrayList<String>();
//    		imovel.add("160.000,00");
//    		cliente.setValorImovel(imovel);
    		
//    		cliente.setDataNascimento("28/05/1977");
    		
//    		cliente.setEstado("SÃO PAULO");
//    		cliente.setMunicipio("SAO PAULO");
    		
    		
//    		cliente.setTipoPropriedade("Familiar");
    		
//    		cliente.setCep("05713460");
//    		cliente.setBairro("Morumbi");
    		
//    		cliente.setResideDesde("03 01 1996");
//                PrintFields exporter = new PrintFields(cliente);
//                exporter.printFields();
//            }

    }

    /**
     * This will print out a message telling how to use this example.
     */
    private static void usage()
    {
        System.err.println("usage: org.apache.pdfbox.examples.interactive.form.PrintFields <pdf-file>");
    }
    
    public double getNumber(String valor) throws ParseException{
    	/*DecimalFormat df = new DecimalFormat(); 
    	DecimalFormatSymbols sfs = new DecimalFormatSymbols();
    	sfs.setDecimalSeparator(','); 
    	df.setDecimalFormatSymbols(sfs);

		return df.parse(valor).doubleValue();*/
    	
    	return NumberFormat.getInstance(new Locale("pt", "BR")).parse(valor).doubleValue();

    }
}
