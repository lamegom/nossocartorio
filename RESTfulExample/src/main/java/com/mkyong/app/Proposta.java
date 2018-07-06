package com.mkyong.app;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mkyong.app.dao.EstadosCidades;
import com.mkyong.app.dao.JDBCPreparedStatement;
import com.mkyong.bean.Cliente;

public class Proposta {
	
	private static final Logger[] PINNED_LOGGERS;
	static {
	    System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "fatal");
	    PINNED_LOGGERS = new Logger[]{
	        Logger.getLogger("com.gargoylesoftware.htmlunit"),
	        Logger.getLogger("org.apache.http")
	    };

	    for (Logger l : PINNED_LOGGERS) {
	        l.setLevel(Level.OFF);
	    }
	}
	
	public double formatCurrency(String vlr){
		System.out.println(vlr);
		
		
		boolean decimals = true;
		
		if (vlr.matches(".*[^0-9].*")) {
			decimals = false;
		}
		
		double vlrImovel = Double.valueOf(vlr.replaceAll("[.,]", ""))/100;
		
		if(decimals){
			vlrImovel = Double.valueOf(vlr)*100;
		}
		
		
		return new BigDecimal( vlrImovel ).setScale( 2 , RoundingMode.UP ).doubleValue();
	}

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		
//		cliente.setValorImovel(150000.0);
//		
//		if(cliente.getValorImovel() > 150000.0){
//			cliente.setProdutoBB("SFH");
//		
//		}else{
//			cliente.setProdutoBB("PMCMV");
//		}
		
//		cliente.setCpf("18977841801");
		
//		cliente.setEstado("SP");
//		cliente.setCidade("SAO PAULO");
		
		
//		cliente.setDespesas((double) 500.0);
		
//		cliente.setRendaBruta(2000.0D);
//		
//		Proposta example = new Proposta(cliente);
//		try {
//			example.loadLoginPage();
//			example.loginAs(USERNAME, PASSWORD);
//			
//			Thread.sleep(1000);
//			example.salvarPropostaPMCMV();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String res = example.testHtmlUnitDriver();
//		
//		System.out.println(res);
	}
	
	Cliente cliente;
	ServletContext context;
	
	public Proposta(Cliente cliente, ServletContext context){
		
		this.cliente = cliente;
		this.context = context;
		
//		useHtmlUnit();

//		useRemoteChrome();
	
//			useFirefox();
		
//		useFirefoxRemote();
	    
//		useHtmlUnitRemote();
		
		//TODO: web.xml driver path and type
		if(context.getInitParameter("type2").equals("firefox")){
			useFirefoxRemote(context.getInitParameter("url2"));
		}else{
			useRemoteChrome(context.getInitParameter("url2"));
		}
		
	    wait = new WebDriverWait(driver, 20);
	}
	
	public void useRemoteChrome(String url){
		
		
		java.util.logging.Logger.getLogger("org.openqa.selenium.remote.ProtocolHandshake").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */
		
		final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("window-size=1200x600");

        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

		    System.out.println("-------------------------------------------------");
			
			 String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
				System.out.println("Starting Proposta at " + date  );
				
				
//				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
				try {
					driver = new RemoteWebDriver(new URL(url), 
							 
					        capability);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void useHtmlUnit(){
		driver = new HtmlUnitDriver(true);
		
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	}
	
	public void useHtmlUnitRemote(){
		DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();


	    
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
		try {
//			driver = new RemoteWebDriver(new URL("http://35.161.203.86:4444/wd/hub"),//windows  
//			driver = new RemoteWebDriver(new URL("http://34.212.255.98:4444/wd/hub"),//01
//			driver = new RemoteWebDriver(new URL("http://54.69.24.196:4444/wd/hub"),//04
			driver = new RemoteWebDriver(new URL("http://52.26.164.96:4444/wd/hub"),//03
					capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((HtmlUnitDriver) driver).setJavascriptEnabled(true);
	}
	
	public void useFirefoxRemote(String url){
//		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
//    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

//    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");

    	DesiredCapabilities caps = DesiredCapabilities.firefox();

//	    FirefoxBinary firefoxBinary = caps.
//	    firefoxBinary.addCommandLineOptions("--headless");

	    
//		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),  
		try {
//			driver = new RemoteWebDriver(new URL("http://35.161.203.86:4444/wd/hub"),//windows
					driver = new RemoteWebDriver(new URL(url),//windows  
//			driver = new RemoteWebDriver(new URL("http://34.212.255.98:4444/wd/hub"),//01
//			driver = new RemoteWebDriver(new URL("http://54.69.24.196:4444/wd/hub"),//04
//			driver = new RemoteWebDriver(new URL("http://52.26.164.96:4444/wd/hub"),//03
//			driver = new RemoteWebDriver(new URL("http://www.nossocartorio.com.br/blog/selenium"),//balancer
					caps);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void useFirefox(){
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");

    	DesiredCapabilities caps = DesiredCapabilities.firefox();

	    FirefoxBinary firefoxBinary = new FirefoxBinary();
//	    firefoxBinary.addCommandLineOptions("--headless");


	    FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setBinary(firefoxBinary);
	    
	    firefoxOptions.addTo(caps);
	    
	    driver = new FirefoxDriver(caps);
	}
	
    final static String USERNAME = "JC049957";  
    final static String PASSWORD = "spb91744"; 
	
    WebDriver driver = null;
    
    WebDriverWait wait = null;
    
	public String testHtmlUnitDriver() throws Exception
	{
		
		
		try
		{
			
			
			
//			loadLoginPage();
//			loginAs(USERNAME, PASSWORD);
//			
//			Thread.sleep(1000);
			
//			System.out.println(driver.getPageSource());
			
			driver.navigate().to("https://portaldecredito.bb.com.br/pages/imobiliario/pmcmv/principal.bb");

			getCliente();
			
			insertProposta();
			
			
			if(cliente.getProdutoBB().equals("PMCMV")){
				return minhaCasaMinhaVida();
			}else if(cliente.getProdutoBB().equals("SFH")){
				return sfh();
			}
			
			return null;
			
			

			


		}catch(Exception ex)
		{
			try{
			String response = createElement("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span").getText();
			 cliente.setResultado(response);
			}catch(Exception ex1)
			{
				if(driver!=null)
				{
					driver.close();
					driver=null;
				}
				
			}finally{
			 

			}
			
			throw new Exception(ex);
		}finally
		{
//			if(driver!=null)
//			{
//				driver.close();
//				driver=null;
//			}
		}
	}
	
	private void insertProposta() throws Exception {
		
		System.out.println("Proposta.insertProposta()");
		
//		Thread.sleep(2000);
		
		
		WebElement but2 = createElement("//*[@id=\"busca:cComLis_74S\"]");
		if(but2!=null)
		{
			but2.click();
			
			
		}
		
		WebElement but3 = createElement("//*[@id=\"formBotaoProponente:b1\"]");
		if(but3!=null)
		{
			but3.click();
			
			
		}
		
		String agenciaBB = "";
		WebElement age = createElement("//*[@id=\"formaPesquisaCliente:selecaoInclusaoProponentePropostaTabela:0:j_id616\"]");
		if(age!=null)
		{
			
			agenciaBB = age.getText();
			cliente.setAgencia(agenciaBB);
			
			
		}

		
		WebElement but4 = createElement("//*[@id=\"formaPesquisaCliente:selecaoInclusaoProponentePropostaTabela:0:j_id629\"]");
		if(but4!=null)
		{
			but4.click();
			
			
		}
		
		
		String parent = driver.getWindowHandle();

		Thread.sleep(2000);
		
		WebElement but5 = createElement("//*[@id=\"cForPse_1LX:empreendimentoDadosPropostaDecorate:cComPse_ODE\"]");
		if(but5!=null)
		{
			but5.click();
			
			
		}
		
//		Thread.sleep(5000);
		
//		switchToModalDialog(driver, parent);
		
		
//		System.out.println(driver.getPageSource());

		
		

			Thread.sleep(2000);

		
		WebElement elementLink2 = driver.findElement(By.xpath("//*[@id=\"formModalEmpreendimento:idEmpreendimento:0:cComMod_MTW\"]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elementLink2);
		
		

			Thread.sleep(3000);

		
		WebElement dropdown = createElement("//*[@id=\"cForPidp_ZBX:ufDadosPropostaDecorate:uf\"]");
		try{
			new Select(dropdown).selectByVisibleText(new EstadosCidades().estados.get(cliente.getEstadoImovel().get(0)).toUpperCase());
		}catch(Exception e){
			new Select(dropdown).selectByVisibleText(cliente.getEstadoImovel().get(0).toUpperCase().replaceAll("-", " "));
		}
		

//			Thread.sleep(5000);

		
		
		createElement("/html/body/div[2]/div[3]/div/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr/td/div/div/form/div[3]/div/fieldset/div/div[2]/div/span[1]/div/a").click();
		try{
			createElement("//*[@id=\"cForPidp_ZBX:municipioDadosPropostaDecorate\"]/span[1]/div/div/div/input").sendKeys(removerAcentos(new EstadosCidades().cidades.get(Integer.valueOf(cliente.getCidade().get(0)))));
		}catch(Exception e){
//			e.printStackTrace();
			createElement("//*[@id=\"cForPidp_ZBX:municipioDadosPropostaDecorate\"]/span[1]/div/div/div/input").sendKeys(removerAcentos(cliente.getCidade().get(0).trim()));
		}
		
		createElement("//*[@id=\"cForPidp_ZBX:municipioDadosPropostaDecorate\"]/span[1]/div/div/ul/li").click();
//		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/div[2]/div[1]/div[1]/div[2]/table/tbody/tr/td/div/div/form/div[3]/div/fieldset/div/div[2]/div/span[1]/div/div/ul/li[567]")).click();
		
		

			Thread.sleep(4000);
			new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			try{
		createElement("//*[@id=\"cForPidp_ZBX:cDecPidp_000:idBotaoAlterarItemEmpreendimento\"]").click();
		

//			Thread.sleep(2000);

		
//		driver.findElement(By.xpath("//*[@id=\"cForMiia_6F3:tabelaItensPesquisados:0:cOutMiia_MMZ\"]")).click();
		
			createElement("//*[@id=\"cForMiia_6F3:tabelaItensPesquisados:1:cOutMiia_FJX\"]").click();
		}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e){
//			String erro = createElement("//*[@id=\"messages\"]/li/span").getText();
//			
//			cliente.setResultado(erro);
			
			throw new Exception(e);
			
		}

			Thread.sleep(2000);
		
	}
	
	public String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private String setAgencia(String agenciaBB) {
		String agenciaBBCode = cliente.getAgencia().split(" - ")[0];
		
//		System.out.println("Proposta.setAgencia() " + agenciaBBCode);
		
		if(agenciaBBCode.equals("4777")){
			return "5809";
		}else{
			return Integer.valueOf(agenciaBBCode).toString() ;
		}
		
	}

	public String formatDecimal(float number) {
		  float epsilon = 0.004f; // 4 tenths of a cent
		  if (Math.abs(Math.round(number) - number) < epsilon) {
		     return String.format("%10.0f", number); // sdb
		  } else {
		     return String.format("%10.2f", number); // dj_segfault
		  }
		}
	
public void loadLoginPage() throws Exception{

    	System.out.println("Proposta.loadLoginPage()");
    	driver.get("https://portaldecredito.bb.com.br/index.bb");
//    	driver.get("https://portalcredito.bb.com.br/portal");
//    	System.out.println("Login Page Loaded.");
    	
    	WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cForInd_478:cComInd_8KQ']")));
    	
    	driver.findElement(By.xpath("//a[@id='cForInd_478:cComInd_8KQ']")).click();
    	
//    	System.out.println(driver.getPageSource());
//    	System.out.println(driver.getCurrentUrl());
    	
    	Thread.sleep(1000);
    	
    	
//    	percentage = 0;

    	
    }

public void loginAs(String username, String password) throws Exception {
//  driver.get("https://portaldecredito.bb.com.br/login.bb");
//	driver.navigate().to("https://portaldecredito.bb.com.br/login.bb");
	
//	System.out.println(driver.getCurrentUrl());
	
  WebDriverWait wait = new WebDriverWait(driver, 30);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginForm:botaoEntrar")));
  driver.findElement(By.id("loginForm:cDecChaveJUsuario:identificacaoUsuario")).sendKeys(username);
  driver.findElement(By.id("loginForm:cDecSenhaChaveJUsuario:senhaUsuario")).sendKeys(password);
  driver.findElement(By.id("loginForm:botaoEntrar")).click(); 
  
//  percentage = 1;
  
  Thread.sleep(1000);
//	System.out.println(driver.getCurrentUrl());
	WebDriverWait wait2 = new WebDriverWait(driver, 30);
	 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("cabecalhoDireita")));
//	 System.out.println(driver.getCurrentUrl());
	 
	 
}

public static void switchToModalDialog(WebDriver driver, String parent) { 
    //Switch to Modal dialog
//	System.out.println(driver.getWindowHandles().size() );
	
if (driver.getWindowHandles().size() > 1) {
    for (String window : driver.getWindowHandles()) {
        if (!window.equals(parent)) {
            driver.switchTo().window(window);
            System.out.println("Modal dialog found");
            break;
        }
    }
}
}

public void getCliente() throws Exception{
	
//	System.out.println("Proposta.getCliente()");
	
	WebElement serv = createElement("//*[@id=\"busca:dcombo:idComboFiltroPropostas\"]");
	if(serv!=null)
	{
		new Select(serv).selectByValue("2");
		
		
	}
	
//	Thread.sleep(3000);
	
	
	WebElement rad = createElement("//*[@id=\"busca:j_id148:criterioPesquisaRadio:1\"]");
	if(rad!=null)
	{
		rad.click();
		
		
	}
	
//	Thread.sleep(3000);
	
	
	WebElement inp = createElement("//*[@id=\"busca:listaPropostasDoClientecpfBuscaClienteDecorate:listaPropostasDoClientecpfBuscaCliente\"]");
	if(inp!=null)
	{
		inp.sendKeys((String) cliente.getCpf().get(0));
		
		
	}
	
	
	WebElement but = createElement("//*[@id=\"busca:listaPropostasDoClientebtnPesquisa\"]");
	if(but!=null)
	{
		but.click();
		
		
	}
	

	
}

public String minhaCasaMinhaVida() throws Exception{
	
//	System.out.println("Proposta.minhaCasaMinhaVida()");
	


	
	Select dropdown2 = new Select(driver.findElement(By.id("formInclusaoDadosProposta:cDecPidpp_808:comboLinhaCreditoSemConvenio")));
	
	dropdown2.selectByValue("524");
	

//		Thread.sleep(3000);

	
	createElement("//*[@id=\"formEnquadramento:idDec1:j_id715:1\"]").click();
	

//		Thread.sleep(2000);

	try{
	Select dropdown3 = new Select(createElement("//*[@id=\"cForPsd_4VQ:ufDecorate:uf\"]"));
	
	String ag = setAgencia(cliente.getAgencia());
//	System.out.println(ag);
	
	try{
		JDBCPreparedStatement j = new JDBCPreparedStatement();
		String uf = j.selectUFfromAgencia(ag);
		
		dropdown3.selectByVisibleText(new EstadosCidades().estados.get(uf).toUpperCase());
		
	}catch(Exception ex){
	
		try{
			dropdown3.selectByVisibleText(new EstadosCidades().estados.get(cliente.getEstadoImovel().get(0)).toUpperCase());
		}catch(Exception e){
			dropdown3.selectByVisibleText(cliente.getEstadoImovel().get(0).toUpperCase().replaceAll("-", " "));
		}
	}
	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(3000);
	
	createElement("//*[@id=\"cForPsd_4VQ:buscaPorDecorate:selecao:1\"]").click();
	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(4000);
	
	
	
	createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:prefixo\"]").sendKeys(ag);
	createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:btnBuscar\"]").click();
	Thread.sleep(4000);
	//new Select(createElement("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]")).selectByValue(setAgencia(cliente.getAgencia()));
	selectLast("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]");

	}catch(Exception e){
		e.printStackTrace();
		Select dropdown3 = new Select(createElement("//*[@id=\"cForPsd_4VQ:ufDecorate:uf\"]"));
		
		dropdown3.selectByVisibleText("SÃO PAULO");
//		dropdown3.selectByVisibleText("PARANÁ");
		Thread.sleep(3000);
		createElement("//*[@id=\"cForPsd_4VQ:buscaPorDecorate:selecao:1\"]").click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(4000);
		createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:prefixo\"]").sendKeys("5809");
		createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:btnBuscar\"]").click();
		Thread.sleep(2000);
		new Select(createElement("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]")).selectByValue("5809");

	}
//		Thread.sleep(2000);

	
//	Select dropdown4 = new Select(driver.findElement(By.xpath("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]")));
//	boolean agencia = false;
//	for(WebElement element : dropdown4.getOptions()){
//		
//		if(element.getText().contains((agenciaBB.split("-")[0]))){
//			dropdown4.selectByValue(element.getAttribute("value"));
//			agencia = true;
//			break;
//		}
//	}
	
	
//	if(!agencia) dropdown4.selectByValue("5809");
	

//		Thread.sleep(2000);

	
	createElement("//*[@id=\"formGerarProposta:cpBotaoSalvar\"]").click();
	

//		Thread.sleep(5000);

	
	
//	verifyElementVisible( driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")));
	
//	WebDriverWait wait2 = new WebDriverWait(driver, 60);
//	 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")));
//	
	
//	String response = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")).getAttribute("innerHTML");
	
	String response = createElement("//*[@id=\"messages\"]").getText();//getAttribute("innerHTML");
	
	try{
		createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
	{}
		
//		System.out.println(response);
		
		return response;
}

public String sfh() throws Exception{
	
//	System.out.println("Proposta.sfh()");
	
	Thread.sleep(2000);

	
	Select dropdown2 = new Select(driver.findElement(By.id("formInclusaoDadosProposta:cDecPidpp_808:comboLinhaCreditoSemConvenio")));
	
	dropdown2.selectByValue("475");
	

//		Thread.sleep(3000);

	
//	createElement("//*[@id=\"formEnquadramento:idDec1:j_id715:1\"]").click();
	

//		Thread.sleep(2000);

	try{
	Select dropdown3 = new Select(createElement("//*[@id=\"cForPsd_4VQ:ufDecorate:uf\"]"));

	String ag = setAgencia(cliente.getAgencia());
//	System.out.println(ag);
	
	try{
		JDBCPreparedStatement j = new JDBCPreparedStatement();
		String uf = j.selectUFfromAgencia(ag);
		System.out.println(ag + " : " + uf);
		dropdown3.selectByVisibleText(new EstadosCidades().estados.get(uf).toUpperCase());
		
	}catch(Exception ex){
	
		try{
			dropdown3.selectByVisibleText(new EstadosCidades().estados.get(cliente.getEstadoImovel().get(0)).toUpperCase());
		}catch(Exception e){
			dropdown3.selectByVisibleText(cliente.getEstadoImovel().get(0).toUpperCase().replaceAll("-", " "));
		}
	}
	

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(4000);

	
	
	
	createElement("//*[@id=\"cForPsd_4VQ:buscaPorDecorate:selecao:1\"]").click();
	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(3000);

	
	createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:prefixo\"]").sendKeys(ag);
	
	

	createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:btnBuscar\"]").click();
	Thread.sleep(3000);
	/*Select agCliente = new Select(createElement("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]"));
	System.out.println(agCliente.getOptions().size());
	
	agCliente.selectByValue(setAgencia(cliente.getAgencia()));*/
	selectLast("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]");
//	createElement("/html/body/div[2]/div[3]/div/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/div[2]/div[1]/div[2]/div/div[2]/form/div/div/fieldset/span/div[3]/span[1]/div/a").click();
//	createElement("/html/body/div[2]/div[3]/div/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/div[2]/div[1]/div[2]/div/div[2]/form/div/div/fieldset/span/div[3]/span[1]/div/div/ul/li[567]").click();

	}catch(Exception e){
		e.printStackTrace();
		Select dropdown3 = new Select(createElement("//*[@id=\"cForPsd_4VQ:ufDecorate:uf\"]"));
		dropdown3.selectByVisibleText("SÃO PAULO");
//		dropdown3.selectByVisibleText("PARANÁ");
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(3000);

		createElement("//*[@id=\"cForPsd_4VQ:buscaPorDecorate:selecao:1\"]").click();
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(4000);
		
		createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:prefixo\"]").sendKeys("5809");
		createElement("//*[@id=\"cForPsd_4VQ:prefixoDecorate:btnBuscar\"]").click();
		Thread.sleep(2000);
		new Select(createElement("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]")).selectByValue("5809");

	}
//		Thread.sleep(2000);

	
//	Select dropdown4 = new Select(driver.findElement(By.xpath("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]")));
//	boolean agencia = false;
//	for(WebElement element : dropdown4.getOptions()){
//		
//		if(element.getText().contains((agenciaBB.split("-")[0]))){
//			dropdown4.selectByValue(element.getAttribute("value"));
//			agencia = true;
//			break;
//		}
//	}
	
	
//	if(!agencia) dropdown4.selectByValue("5809");
	

//		Thread.sleep(2000);

	
	createElement("//*[@id=\"formGerarProposta:cpBotaoSalvar\"]").click();
	

//		Thread.sleep(5000);

	
	
//	verifyElementVisible( driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")));
	
//	WebDriverWait wait2 = new WebDriverWait(driver, 60);
//	 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")));
//	
	
//	String response = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span")).getAttribute("innerHTML");
	
	String response = createElement("//*[@id=\"messages\"]").getText();//getAttribute("innerHTML");
	
	try{
		createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
	{}
		
//		System.out.println(response);
		
		return response;
}



public WebElement createElement(String xpath) throws InterruptedException{
//	while(driver.findElement(By.id("loading")).isDisplayed()){
//		Thread.sleep(100);
//	};

	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	
	By by = By.xpath(xpath);
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	
	
	return driver.findElement(by);
}

public void resultadoProposta(){
try{	
	
	System.out.println("Proposta.resultadoProposta()");
	
	loadLoginPage();
	loginAs(USERNAME, PASSWORD);
	
	Thread.sleep(1000);
	
	accessCliente();

	createElement("//*[@id=\"tabSacImobiliario_lbl\"]").click();
	String resultado = null;
	Thread.sleep(2000);
	try{
		
		resultado = createElement("//*[@id=\"sacFormularioIMOBDaLista:tabelaSacVinculadaIMOB:0:cOutPls_BGKIMOB\"]").getText();
		
	}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e){
		
		try{
			createElement("//*[@id=\"sacFormularioIMOBDaLista:tabelaListaSacsIMOB:0:regravarSacDaListaIMOB\"]").click();
			Thread.sleep(2000);
			resultado = createElement("//*[@id=\"sacFormularioIMOBDaLista:tabelaListaSacsIMOB:0:cOutPls_R15IMOB\"]").getText();
		}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException ex){
			resultado = "SAC nao cadastrada";
		}
		
		
	}
	System.out.println(resultado);
	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
	mailchimp.setEmail(cliente.getEmail().get(0));
	
//	String fgts = NumberFormat.getInstance(new Locale ("pt", "BR")).format(cliente.getFgts());
//	mailchimp.setValorFgts(cliente.getFgts()>0?NumberFormat.getCurrencyInstance().parse(fgts).toString():null);
	
	NumberFormat currencyFormatter = 
	        NumberFormat.getCurrencyInstance(new Locale ("pt", "BR"));
	
	String strFgts = cliente.getFgts()!=null?cliente.getFgts().get(0):"0.0";
	String strRecursosProprios = cliente.getRecursosProprios()!=null?cliente.getRecursosProprios().get(0):"0.0";

	
	Double valorImovel = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(cliente.getValorImovel().get(0)).doubleValue();
	Double recursosProprios = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strRecursosProprios).doubleValue();
	Double fgts = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strFgts).doubleValue();
	
	mailchimp.setValorFgts(currencyFormatter.format(fgts));
	
//	String imovel = NumberFormat.getInstance(new Locale ("pt", "BR")).format(cliente.getValorImovel());
//	mailchimp.setValorImovel(cliente.getFgts()>0?NumberFormat.getCurrencyInstance().parse(imovel).toString():null);
	mailchimp.setValorImovel(currencyFormatter.format(valorImovel));
	
//	String recursos = NumberFormat.getInstance(new Locale ("pt", "BR")).format(cliente.getRecursosProprios());
//	mailchimp.setValorRecusros(cliente.getFgts()>0?NumberFormat.getCurrencyInstance().parse(recursos).toString():null);
	mailchimp.setValorRecusros(currencyFormatter.format(recursosProprios));
	
//	String entrada = NumberFormat.getInstance(new Locale ("pt", "BR")).format(cliente.getEntrada());
//	mailchimp.setEntrada(cliente.getFgts()>0?NumberFormat.getCurrencyInstance().parse(entrada).toString():null);
	try{
		mailchimp.setEntrada(currencyFormatter.format(cliente.getEntrada()));
	}catch(NullPointerException  | IllegalArgumentException n){
		cliente.setEntrada(cliente.getSubsidio() + fgts + recursosProprios);
		mailchimp.setEntrada(currencyFormatter.format(cliente.getEntrada()));
	}
	
	if(resultado.equals("Pre-Aprovada Parcial") || resultado.equals("Pre-Aprovada Total") || resultado.equals("Pre-Aprovada pela DICRE")){
		
		String valorProposto = createElement("//*[@id=\"sacFormularioIMOB:valorSolicitadoIMOBDecorate:valorSolicitadoIMOB\"]").getAttribute("value");
		String prazoProposto = createElement("//*[@id=\"sacFormularioIMOB:prazoSolicitadoIMOBDecorate:prazoSolicitadoIMOB\"]").getAttribute("value");
		String prestacaoProposto = createElement("//*[@id=\"sacFormularioIMOB:valorPrestacaoSolicitadaIMOBDecorate:valorPrestacaoSolicitadaIMOB\"]").getAttribute("value");
		

		String valorAprovado = createElement("//*[@id=\"sacFormularioIMOB:valorAprovadoIMOBDecorate:valorAprovadoIMOB\"]").getAttribute("value");
		String prazoAprovado = createElement("//*[@id=\"sacFormularioIMOB:prazoAprovadoIMOBDecorate:prazoAprovadoIMOB\"]").getAttribute("value");
		String prestacaoAprovado = createElement("//*[@id=\"sacFormularioIMOB:valorPrestacaoAprovadaIMOBDecorate:valorPrestacaoAprovadaIMOB\"]").getAttribute("value");
		
		System.out.println("valorProposto: " + valorProposto);
		System.out.println("prazoProposto: " + prazoProposto);
		System.out.println("prestacaoProposto: " + prestacaoProposto);
		System.out.println("valorAprovado: " + valorAprovado);
		System.out.println("prazoAprovado: " + prazoAprovado);
		System.out.println("prestacaoAprovado: " + prestacaoAprovado);
		String sid = null;	
try{
		 TwilioTest t = new TwilioTest(); 
		 sid = t.dial(cliente.getPhone().get(0));
}catch(Exception e){
//	System.out.println(e.getCause().getLocalizedMessage());
}
		

		mailchimp.setAnalise("OK");
		
		mailchimp.setResultado(resultado);
		mailchimp.setMotivo(sid);
//		mailchimp.setValorProposto(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).parse(valorProposto).toString());
//		mailchimp.setPrazoProposto(prazoProposto);
//		mailchimp.setPrestacaoProposto(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).format(prestacaoProposto));
		
		mailchimp.setValorProposto(valorProposto);
		mailchimp.setPrazoProposto(prazoProposto);
		mailchimp.setPrestacaoProposto(prestacaoProposto);
		
//		mailchimp.setValorAprovado(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).format(valorAprovado));
//		mailchimp.setPrazoAprovado(prazoAprovado);
//		mailchimp.setPrestacaoAprovado(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).format(prestacaoAprovado));
		
		mailchimp.setValorAprovado(valorAprovado);
		mailchimp.setPrazoAprovado(prazoAprovado);
		mailchimp.setPrestacaoAprovado(prestacaoAprovado);
		
		mailchimp.start();
		
		//TODO: preencher pdf
//		PrintFields exporter = new PrintFields(cliente);
//        exporter.printFields();
		
			
	}else if(resultado.equals("Reprovada nas condicoes minimas")){
		createElement("//*[@id=\"sacFormularioIMOBDaLista:tabelaSacVinculadaIMOB:0:cComPls_PLMIMOB\"]").click();
		
		String motivo = createElement("//*[@id=\"sacFormularioIMOBDaLista:gridDetalhamentoSacRepCondMinIMOB:0:cOutMdsrcm_4NUIMOB\"]").getText();
		
		System.out.println("motivo: " + motivo);
		
		String valorProposto = createElement("//*[@id=\"sacFormularioIMOB:valorSolicitadoIMOBDecorate:valorSolicitadoIMOB\"]").getAttribute("value");
		String prazoProposto = createElement("//*[@id=\"sacFormularioIMOB:prazoSolicitadoIMOBDecorate:prazoSolicitadoIMOB\"]").getAttribute("value");
		String prestacaoProposto = createElement("//*[@id=\"sacFormularioIMOB:valorPrestacaoSolicitadaIMOBDecorate:valorPrestacaoSolicitadaIMOB\"]").getAttribute("value");
//
		System.out.println("valorProposto: " + valorProposto);
		System.out.println("prazoProposto: " + prazoProposto);
		System.out.println("prestacaoProposto: " + prestacaoProposto);
		


		mailchimp.setAnalise("Cadastrais");
		
		mailchimp.setResultado("Cliente nao preencheu formulario Cadastro");
		StringBuffer sb = new StringBuffer();
		sb.append(resultado);	
		sb.append(motivo);
		mailchimp.setMotivo(sb.toString());
		
//		mailchimp.setValorProposto(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).format(valorProposto));
//		mailchimp.setPrazoProposto(prazoProposto);
//		mailchimp.setPrestacaoProposto(NumberFormat.getCurrencyInstance(new Locale ("pt", "BR")).format(prestacaoProposto));
		
		mailchimp.setValorProposto(valorProposto);
		mailchimp.setPrazoProposto(prazoProposto);
		mailchimp.setPrestacaoProposto(prestacaoProposto);
		
		mailchimp.start();
		

		
	}else  if(resultado.equals("SAC em analise pela DICRE")){
		
		ScheduledExecutorService scheduler
	    = Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {
				public void run() {
					
					Proposta example = new Proposta(cliente, context);
					
					example.resultadoProposta();
		
		}
	};

	int delay = 2;
	scheduler.schedule(task, delay, TimeUnit.DAYS);
	scheduler.shutdown();
		

		mailchimp.setAnalise("OK");
		
		mailchimp.setResultado(resultado);
		mailchimp.start();
		
		
		
	}else  if(resultado.equals("Em processamento (Normal)")){
		
		ScheduledExecutorService scheduler
	    = Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {
				public void run() {
					
					Proposta example = new Proposta(cliente, context);
					
					example.resultadoProposta();
		
		}
	};

	int delay = 5;
	scheduler.schedule(task, delay, TimeUnit.MINUTES);
	scheduler.shutdown();
		

		mailchimp.setAnalise("OK");
		
		mailchimp.setResultado(resultado);
		mailchimp.start();
		
		
	}else{
		String motivo = null;
		String sid2 = null;	
try{
		TwilioTest2 t2 = new TwilioTest2(); 
		 sid2 = t2.dial(cliente.getPhone().get(0));
}catch(Exception e){
//	System.out.println(e.getCause().getLocalizedMessage());
}
		 
		try{
		createElement("//*[@id=\"sacFormularioIMOBDaLista:tabelaSacVinculadaIMOB:0:cComPls_PLMIMOB\"]").click();
		
		motivo = createElement("//*[@id=\"sacFormularioIMOBDaLista:gridDetalhamentoSacRepCondMinIMOB:0:cOutMdsrcm_4NUIMOB\"]").getText();
		
		System.out.println("motivo: " + motivo + "\n" + sid2);
		}catch(org.openqa.selenium.TimeoutException t){
			motivo = sid2;
		}
		

		mailchimp.setAnalise("Cadastrais");
		
		mailchimp.setResultado(resultado);
		mailchimp.setMotivo(motivo);
		
		
		try{
		String valorProposto = createElement("//*[@id=\"sacFormularioIMOB:valorSolicitadoIMOBDecorate:valorSolicitadoIMOB\"]").getAttribute("value");
		String prazoProposto = createElement("//*[@id=\"sacFormularioIMOB:prazoSolicitadoIMOBDecorate:prazoSolicitadoIMOB\"]").getAttribute("value");
		String prestacaoProposto = createElement("//*[@id=\"sacFormularioIMOB:valorPrestacaoSolicitadaIMOBDecorate:valorPrestacaoSolicitadaIMOB\"]").getAttribute("value");

//		System.out.println("valorProposto: " + valorProposto);
//		System.out.println("prazoProposto: " + prazoProposto);
//		System.out.println("prestacaoProposto: " + prestacaoProposto);
		
//		String vlrProp = (String) NumberFormat.getInstance(new Locale ("pt", "BR")).parse(valorProposto).toString();
		
//		mailchimp.setValorProposto(NumberFormat.getCurrencyInstance().format(vlrProp));
//		mailchimp.setPrazoProposto(prazoProposto);
//		mailchimp.setPrestacaoProposto(NumberFormat.getCurrencyInstance().format((String) NumberFormat.getInstance(new Locale ("pt", "BR")).format(prestacaoProposto)));
		
		mailchimp.setValorProposto(valorProposto);
		mailchimp.setPrazoProposto(prazoProposto);
		mailchimp.setPrestacaoProposto(prestacaoProposto);
		

		}catch(org.openqa.selenium.TimeoutException t){
		}

		

		
		mailchimp.start();
	
	}

	
}catch(Exception ex)
{
	ex.printStackTrace();
}finally
{
	if(driver!=null)
	{
		driver.close();
		driver=null;
	}
}
//return null;
	
}

public void accessCliente() throws Exception{
	driver.navigate().to("https://portaldecredito.bb.com.br/pages/imobiliario/pmcmv/principal.bb");

	getCliente();
	
//	WebElement row = createElement("/html/body/div[2]/div[3]/div/div/div[2]/table/tbody/tr[2]/td/table/tbody/tr/td/div[2]/form/div[2]/span/div/div/div/div[2]/table/tbody/tr");
	WebElement row = createElement("//*[@id=\"busca:listaPropostasDoClienteTabela:0:j_id216\"]");
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", row);
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(3000);
	
	createElement("//*[@id=\"tableListaPropostas:tabelaListaPropostas:nroPropostaTooltipheader\"]").click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(2000);
	
	createElement("//*[@id=\"tableListaPropostas:tabelaListaPropostas:nroPropostaTooltipheader\"]").click();
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
	Thread.sleep(3000);
	
	List<WebElement> alterarButtons =
			driver.findElements(By.cssSelector("input[type='image'][title='Alterar a proposta']"));
	
	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

	checktStale(alterarButtons.get(0)).click();
	
	
	Thread.sleep(2000);
}


public void salvarPropostaPMCMV(){
try{	
	
//	System.out.println("Proposta.salvarPropostaPMCMV()");
	
//	accessCliente();
	Thread.sleep(2000);
	
	salvarContaCorrenteRendaBruta();
	
//	makeSACPMCMV();
	
	
	
}catch(Exception ex)
{
	ex.printStackTrace();
}finally
{
//	if(driver!=null)
//	{
//		driver.close();
//		driver=null;
//	}
}
//return null;
	
}

public void salvarContaCorrenteRendaBruta() throws Exception{
	System.out.println("Proposta.salvarContaCorrenteRendaBruta()");
//	createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	
	
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:decorateContaCorrente:comboContaCorrenteProposta\"]");
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:decorateEndereco:comboEnderecoProposta\"]");
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:idDivDecorateOcupacao:comboAtividadePF\"]");
	

	createElement("//*[@id=\"caracterizacaoPropFormBtnSalvar:cpBotaoSalvar\"]").click();
	
	
	createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	
	
	
	rendaBruta();
	
	Thread.sleep(2000);
}

public void makeSACPMCMV() throws Exception{
try{
	sacPMCMV();
	
	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
	mailchimp.setEmail(cliente.getEmail().get(0));
	
	
	String strFgts = cliente.getFgts()!=null?cliente.getFgts().get(0):"0.0";
	String strRecursosProprios = cliente.getRecursosProprios()!=null?cliente.getRecursosProprios().get(0):"0.0";

	
	double valorImovel = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(cliente.getValorImovel().get(0)).doubleValue();
	double recursosProprios = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strRecursosProprios).doubleValue();
	double fgts = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strFgts).doubleValue();
	
	mailchimp.setValorImovel(NumberFormat.getCurrencyInstance().format(valorImovel));
	
	mailchimp.setValorRecusros(NumberFormat.getCurrencyInstance().format(recursosProprios));
	mailchimp.setValorFgts(NumberFormat.getCurrencyInstance().format(fgts));
	mailchimp.setValorSubsidio(NumberFormat.getCurrencyInstance().format(cliente.getSubsidio()));
	
	mailchimp.setEntrada(NumberFormat.getCurrencyInstance().format(cliente.getEntrada()));
	
	if(cliente.getResultado().equals("Sac gravada com sucesso")){
		mailchimp.setAnalise("OK");
		mailchimp.setResultado(cliente.getResultado());
		
		mailchimp.start();
		
		System.out.println("-------------------------------------------------");
		
		 String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
			System.out.println("Scheduling SAC result at " + date  );
			
			
			
	    ScheduledExecutorService scheduler
	    = Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {
				public void run() {
					
					Proposta example = new Proposta(cliente, context);
					
					example.resultadoProposta();
		
		}
	};

	int delay = 5;
	scheduler.schedule(task, delay, TimeUnit.MINUTES);
	scheduler.shutdown();

		
	}else{
		if(cliente.getResultado().endsWith("impossibilita o prosseguim")){
			mailchimp.setAnalise("Cadastrais");
		}else if(cliente.getResultado().endsWith("LIMITE CREDITO NAO PERMITIDO")){
			mailchimp.setAnalise("Cadastrais");
		}else{
			mailchimp.setAnalise("Aprovado");
		}
		mailchimp.setResultado("SAC não cadastrada");
		mailchimp.setMotivo(cliente.getResultado());
		
		mailchimp.start();
	}
	
}catch(Exception ex)
{
	ex.printStackTrace();
}finally
{
	if(driver!=null)
	{
		driver.close();
		driver=null;
	}
}
//return null;
}

public void makeSACSFH() throws Exception{
	try{
sacSFH();
	
	ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
	mailchimp.setEmail(cliente.getEmail().get(0));
	
	
	String strFgts = cliente.getFgts()!=null?cliente.getFgts().get(0):"0.0";
	String strRecursosProprios = cliente.getRecursosProprios()!=null?cliente.getRecursosProprios().get(0):"0.0";

	
	double valorImovel = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(cliente.getValorImovel().get(0)).doubleValue();
	double recursosProprios = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strRecursosProprios).doubleValue();
	double fgts = NumberFormat.getInstance(new Locale("pt", "BR" )).parse(strFgts).doubleValue();
	
	
	mailchimp.setValorImovel(NumberFormat.getCurrencyInstance().format(valorImovel));
	
	mailchimp.setValorRecusros(NumberFormat.getCurrencyInstance().format(recursosProprios));
	mailchimp.setValorFgts(NumberFormat.getCurrencyInstance().format(fgts));
	mailchimp.setValorSubsidio(NumberFormat.getCurrencyInstance().format(cliente.getSubsidio()));
	
	mailchimp.setEntrada(NumberFormat.getCurrencyInstance().format(cliente.getEntrada()));
	
	if(cliente.getResultado().equals("Sac gravada com sucesso")){
		mailchimp.setAnalise("OK");
		mailchimp.setResultado(cliente.getResultado());
		
		mailchimp.start();
		
		System.out.println("-------------------------------------------------");
		
		 String date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date());
			System.out.println("Scheduling SAC result at " + date  );
			
			
			
	    ScheduledExecutorService scheduler
	    = Executors.newSingleThreadScheduledExecutor();

			Runnable task = new Runnable() {
				public void run() {
					
					Proposta example = new Proposta(cliente, context);
					
					example.resultadoProposta();
		
		}
	};

	int delay = 5;
	scheduler.schedule(task, delay, TimeUnit.MINUTES);
	scheduler.shutdown();
		
	}else{

		if(cliente.getResultado().endsWith("impossibilita o prosseguim")){
			mailchimp.setAnalise("Cadastrais");
		}else{
			mailchimp.setAnalise("Aprovado");
		}
		
		mailchimp.setResultado("SAC não cadastrada");
		mailchimp.setMotivo(cliente.getResultado());
		
		mailchimp.start();
	}
	
	}catch(Exception ex)
	{
		ex.printStackTrace();
	}finally
	{
		if(driver!=null)
		{
			driver.close();
			driver=null;
		}
	}
	//return null;	
	
}

public void salvarPropostaSFH(){
try{	
	
//	System.out.println("Proposta.salvarPropostaSFH()");
	
//	accessCliente();
	Thread.sleep(2000);
	
	salvarContaCorrenteSFH();

//	makeSACSFH();
	

	
}catch(Exception ex)
{
	ex.printStackTrace();
}finally
{
//	if(driver!=null)
//	{
//		driver.close();
//		driver=null;
//	}
}
//return null;
	
}

public void salvarContaCorrenteSFH() throws Exception{
	System.out.println("Proposta.salvarContaCorrenteSFH()");
	
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:decorateContaCorrente:comboContaCorrenteProposta\"]");
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:decorateEndereco:comboEnderecoProposta\"]");
	selectLast("//*[@id=\"caracterizacaoPropFormBtnSalvar:idDivDecorateOcupacao:comboAtividadePF\"]");
	
	
	createElement("//*[@id=\"caracterizacaoPropFormBtnSalvar:cpBotaoSalvar\"]").click();
	
	
	createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	
	
	Thread.sleep(2000);
}

public void selectLast(String xpath) throws Exception{
	Select dropdown = new Select(createElement(xpath));

	int i = 0;
	if(dropdown.getOptions().size() > 0){
		i = dropdown.getOptions().size()-1;
	}
	
	dropdown.selectByIndex(i);;
}

public void rendaBruta() throws Exception{
	createElement("//*[@id=\"tabRendaBruta_lbl\"]").click();
	
	
	createElement("//*[@id=\"formRendaBrutaProp:tabelaListaProponentes:0:rndBrutTooltip\"]").click();
	
//	Thread.sleep(1000);
	
	createElement("//*[@id=\"formRendaBrutaProp:cComPrbp_ARE\"]").click();
	
//	Thread.sleep(2000);
//	
//	createElement("//*[@id=\"formRendaBrutaProp:tabelaListaOcu:0:cComMirb_FMC\"]").click();
	
	
	 String renda = new BigDecimal( NumberFormat.getInstance(new Locale("pt","BR")).parse(cliente.getRendaBruta().get(0) ).doubleValue() ).setScale( 2 , RoundingMode.UP ).toString();
	
	System.out.println("Proposta.rendaBruta() " + renda);

try{
	 
	WebElement salario = createElement("//*[@id=\"formRendaBrutaProp:tabelaListaOcu:0:cDecMirb_WIH:cInpMirb_AKH\"]");
	salario.sendKeys(renda);

	((JavascriptExecutor) driver).executeScript("arguments[0].blur();", salario);
//	Thread.sleep(1000);
//	createElement("//*[@id=\"formRendaBrutaProp:tabelaListaOcu:0:cComMirb_W83\"]").click();

	SimpleDateFormat sdf = new SimpleDateFormat("MM/YYYY");
	
	
	WebElement mes = createElement("//*[@id=\"formRendaBrutaProp:tabelaListaOcu:0:cDecMirb_ZWO:cInpMirb_AKe\"]");
	mes.sendKeys(sdf.format(new Date()));

	((JavascriptExecutor) driver).executeScript("arguments[0].blur();", mes);
}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e){

}
	createElement("//*[@id=\"formRendaBrutaProp:cComMirb_BHL\"]").click();

	Thread.sleep(1000);
	createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
	

}

public WebElement checktStale(WebElement anElement) throws Exception{
	boolean finished = false;
	for (int i = 0; i < 20; i++)
	{
	    try
	    {
//	        System.out.print(waitForAngular());
	    	waitForAngular();
	    	
	        // scan table here
//	    	System.out.println(" : " + anElement.getAttribute("id"));
	        anElement.getAttribute("id");
	        
	        
	        finished = true;
	        break;
	    }
	    catch (StaleElementReferenceException e)
	    {
	        continue;
	    }
	}
	if (!finished)
	{
	    // test flaked out
	}
	
	return anElement;
}

public void sacPMCMV() throws Exception{
	
	System.out.println("Proposta.sacPMCMV()");
	Thread.sleep(3000);
	createElement("//*[@id=\"tabSacImobiliario_lbl\"]").click();
	
	
	String subsidio = createElement("//*[@id=\"sacFormularioIMOB:valorSubsidioIMOBDecorate:valorSubsidioIMOB\"]").getAttribute("value");

	DecimalFormat df = new DecimalFormat(); 
	DecimalFormatSymbols sfs = new DecimalFormatSymbols();
	sfs.setDecimalSeparator(','); 
	df.setDecimalFormatSymbols(sfs);
	cliente.setSubsidio(df.parse(subsidio).doubleValue());
	

	Double vlrImovel = NumberFormat.getInstance(new Locale("pt","BR")).parse(cliente.getValorImovel().get(0) ).doubleValue();
	Double entrada = vlrImovel * 0.1D;
	
	String fgts = cliente.getFgts()!=null?cliente.getFgts().get(0):"0.0";
	String recursosProprios = cliente.getRecursosProprios()!=null?cliente.getRecursosProprios().get(0):"0.0";
	
	Double fgtsNum = NumberFormat.getInstance(new Locale("pt","BR")).parse(fgts ).doubleValue();
	Double recursosNum = NumberFormat.getInstance(new Locale("pt","BR")).parse(recursosProprios ).doubleValue();

	Double recursos = entrada - (cliente.getSubsidio() + fgtsNum  + recursosNum);
	Double recursosNumFinal = recursosNum;
	
	if(recursos > 0.0D){
		
		recursosNumFinal =  recursosNum + recursos;

	}
	
	
	double newEntrada = cliente.getSubsidio() +  fgtsNum + recursosNumFinal;

	
	Thread.sleep(3000);
	createElement("//*[@id=\"sacFormularioIMOB:valorFgtsIMOBDecorate:valorFgtsIMOB\"]").sendKeys(fgtsNum.toString()+"0");
	createElement("//*[@id=\"sacFormularioIMOB:valorEntradaIMOBDecorate:valorEntradaIMOB\"]").sendKeys(recursosNumFinal.toString()+"0");
	createElement("//*[@id=\"sacFormularioIMOB:valorImovelIMOBTeste:valorImovelIMOB\"]").sendKeys(vlrImovel.toString()+"0");;
	
	cliente.setEntrada( newEntrada );
	
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPai_7VVIMOB:finalidadeImovelIMOB\"]")).selectByVisibleText("Moradia"); 
		
		WebElement res = createElement("//*[@id=\"sacFormularioIMOB:cDecPai_6JHIMOB:tipoImovelIMOB\"]");
		((JavascriptExecutor) driver).executeScript("arguments[0].focus();", res);
//		res.sendKeys("Residencial");
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		
		new Select(res).selectByVisibleText("Residencial");
		
		Thread.sleep(3000);
		createElement("//*[@id=\"sacFormularioIMOB:prazoSolicitadoIMOBDecorate:prazoSolicitadoIMOB\"]").sendKeys(cliente.getPrazo().toString());
		Thread.sleep(2000);
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPai_P63IMOB:sistemaReposicaoIMOB\"]")).selectByValue(cliente.getAmortizacao());//PRICE-POS
			
		Thread.sleep(2000);
		try{
		
			createElement("//*[@id=\"sacFormularioIMOB:cDecPai_BF6IMOB:j_id1316:1\"]").click();
		
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_CV3IMOB:j_id1465:1\"]").click();//*[@id="sacFormularioIMOB:cDecPpp_CV3IMOB:j_id514:1"]
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_VQEIMOB:j_id1482:1\"]").click();//*[@id="sacFormularioIMOB:cDecPpp_VQEIMOB:j_id521:1"]
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_FLDIMOB:regimeFgtsTresAnosIMOB:1\"]").click();//*[@id="sacFormularioIMOB:cDecPpp_FLDIMOB:regimeFgtsTresAnosIMOB:1"]
		}catch(Exception e){
			createElement("//*[@id=\"sacFormularioIMOB:cDecPai_BF6IMOB:j_id469:1\"]").click();
			
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_CV3IMOB:j_id514:1\"]").click();
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_VQEIMOB:j_id521:1\"]").click();
			Thread.sleep(2000);
			createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_FLDIMOB:regimeFgtsTresAnosIMOB:1\"]").click();
			
		}
		
		Thread.sleep(2000);
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_EO5IMOB:comboSeguradoraIMOB\"]")).selectByVisibleText("BB Seguro Imobiliário");
		
		Double despesas = cliente.getDespesas()!=null?NumberFormat.getInstance(new Locale("pt","BR")).parse(cliente.getDespesas().get(0) ).doubleValue() :500.0D;
		if(despesas<=0.0D){
			despesas = 500.0D;
		}
		
		
		createElement("//*[@id=\"sacFormularioIMOB:despesaAlimentacaoIMOBDecorate:despesaAlimentacaoIMOB\"]").sendKeys("" +despesas*10 );
		
		Thread.sleep(3000);
		
		
		WebElement salvar = createElement("//*[@id=\"sacFormularioIMOB:cpBotaoSalvarSacIMOB\"]");
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", salvar);
		
		
		Thread.sleep(3000);
		
	
	try{	
//String response = createElement("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span").getText();//getAttribute("innerHTML");
	
		
		
//		String button = "/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span";
				String button = "//*[@id=\"messages\"]/li/span";


new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

String response = checktStale(createElement(button)).getText();
//		String response = "Aguardando SAC";
		
		System.out.println(response);
		
		cliente.setResultado(response);
		
		
		
	}catch(org.openqa.selenium.StaleElementReferenceException ex)
		{
//			System.out.println(driver.getPageSource());
		
			String response = "Sac gravada com sucesso?";
			
//			System.out.println(response);
			
			cliente.setResultado(response);
//		org.openqa.selenium.TimeoutException
		}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
		{
//			System.out.println(driver.getPageSource());
			
			
//			String response = createElement("//*[@id=\"messages\"]/li/span").getText();//getAttribute("innerHTML");
				
			String	response = "Sem resultado";
			
			List<WebElement> errors = driver.findElements(By.cssSelector("span[class=\"errors\"]"));

			for (WebElement anElement : errors) {
				System.out.println(anElement.getAttribute("id"));

			}
					
			System.out.println(response);
			
			cliente.setResultado(response);
		}
		
		
		try{
			createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
		}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
		{}
	
}


public boolean waitForAngular(){
	
	JavascriptExecutor js = (JavascriptExecutor)driver;

	return (boolean) js.executeScript("try {"+
			"if (document.readyState !== 'complete') {"+
			"  return false; "+// Page not loaded yet
			"}"+
			"if (window.jQuery) {"+
			"  if (window.jQuery.active) {"+
			"    return false;"+
			"  } else if (window.jQuery.ajax && window.jQuery.ajax.active) {"+
			"    return false;"+
			"  }"+
			"}"+
			"if (window.angular) {"+
			"  if (!window.qa) {"+
// Used to track the render cycle finish after loading is complete"+
			"    window.qa = {"+
			"      doneRendering: false"+
			"    };"+
			"  }"+
// Get the angular injector for this app (change element if necessary)"+
			"  var injector = window.angular.element('body').injector();"+
// Store providers to use for these checks"+
			"  var $rootScope = injector.get('$rootScope');"+
			"  var $http = injector.get('$http');"+
			"  var $timeout = injector.get('$timeout');"+
 // Check if digest"+
			"  if ($rootScope.$$phase === '$apply' || $rootScope.$$phase === '$digest' || $http.pendingRequests.length !== 0) {"+
			"    window.qa.doneRendering = false;"+
			"    return false; "+// Angular digesting or loading data
			"  }"+
			"  if (!window.qa.doneRendering) {"+
// Set timeout to mark angular rendering as finished"+
			"    $timeout(function() {"+
			"      window.qa.doneRendering = true;"+
			"    }, 0);"+
			"    return false;"+
			"  }"+
			"}"+
			"return true;"+
			"} catch (ex) {"+
			"return false;}");
	
}

public void sacSFH() throws Exception{
	
	System.out.println("Proposta.sacSFH()");
	
	createElement("//*[@id=\"tabSacImobiliario_lbl\"]").click();

	Double vlrImovel = NumberFormat.getInstance(new Locale("pt","BR")).parse(cliente.getValorImovel().get(0)).doubleValue();
	Double entrada = vlrImovel * 0.2D;
	
	String fgts = cliente.getFgts()!=null?cliente.getFgts().get(0):"0.0";
	String recursosProprios = cliente.getRecursosProprios()!=null?cliente.getRecursosProprios().get(0):"0.0";
	
	Double vlrFgts = NumberFormat.getInstance(new Locale("pt","BR")).parse(fgts).doubleValue();
	Double vlrRecursos = NumberFormat.getInstance(new Locale("pt","BR")).parse(recursosProprios).doubleValue();

	Double recursos = entrada - ( vlrFgts+ vlrRecursos);
	

	Double vlrRecursosFinal = vlrRecursos;
	
	if(recursos > 0.0D){

		vlrRecursosFinal = vlrRecursos + recursos;

	}

	
	Double newEntrada = vlrFgts + vlrRecursosFinal;
//	
//	System.out.println("vlrRecursosFinal*10D: " + vlrRecursosFinal);
//	System.out.println("vlrFgts: " + vlrFgts );
//	System.out.println("vlrImovel*10D: " + vlrImovel);
	
	Thread.sleep(3000);
	createElement("//*[@id=\"sacFormularioIMOB:valorFgtsIMOBDecorate:valorFgtsIMOB\"]").sendKeys(vlrFgts.toString()+"0");
	createElement("//*[@id=\"sacFormularioIMOB:valorEntradaIMOBDecorate:valorEntradaIMOB\"]").sendKeys(vlrRecursosFinal.toString()+"0");
	createElement("//*[@id=\"sacFormularioIMOB:valorImovelIMOBTeste:valorImovelIMOB\"]").sendKeys(vlrImovel.toString()+"0");
	
	cliente.setEntrada( newEntrada );
	
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPai_7VVIMOB:finalidadeImovelIMOB\"]")).selectByVisibleText("Moradia"); 
		WebElement res = createElement("//*[@id=\"sacFormularioIMOB:cDecPai_6JHIMOB:tipoImovelIMOB\"]");
		((JavascriptExecutor) driver).executeScript("arguments[0].focus();", res);
//		res.sendKeys("Residencial");

		
		new Select(res).selectByVisibleText("Residencial");
		

			
		
		Thread.sleep(3000);
		
//		createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_CV3IMOB:j_id1465:1\"]").click();
//		
//		Thread.sleep(2000);
		
		List<WebElement> radios = driver.findElements(By.cssSelector("input[type='radio']")); //this will provide the number of elements with mentioned type
		for (WebElement anElement : radios) {
			new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
//			if(anElement.getAttribute("id").equals("sacFormularioIMOB:cDecPpp_CV3IMOB:j_id1465:1")){
//				continue;
//			}
			if(checktStale(anElement).getAttribute("id").endsWith(":1")){
				
				anElement.click();
				
				
			}
		}
		
		createElement("//*[@id=\"sacFormularioIMOB:prazoSolicitadoIMOBDecorate:prazoSolicitadoIMOB\"]").sendKeys(cliente.getPrazo().toString());
		
		
		
		
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPai_P63IMOB:sistemaReposicaoIMOB\"]")).selectByValue(cliente.getAmortizacao());//PRICE-POS
			
		Thread.sleep(2000);
		
		
		
		new Select(createElement("//*[@id=\"sacFormularioIMOB:idDecorateCategoriaImovelIMOB:categoriaImovelIMOB\"]")).selectByVisibleText("Novo");
		
		Thread.sleep(1000);
		
		
//		createElement("//*[@id=\"sacFormularioIMOB:cDecPai_BF6IMOB:j_id1316:1\"]").click();
		
		new Select(createElement("//*[@id=\"sacFormularioIMOB:cDecPpp_EO5IMOB:comboSeguradoraIMOB\"]")).selectByVisibleText("BB Seguro Imobiliário");
		
		Double despesas = cliente.getDespesas().get(0)!=null?NumberFormat.getInstance(new Locale("pt", "BR" )).parse(cliente.getDespesas().get(0)).doubleValue() :500.0D;

		if(despesas<=0.0D){
			despesas = 500.0D;
		}
		
		createElement("//*[@id=\"sacFormularioIMOB:despesaAlimentacaoIMOBDecorate:despesaAlimentacaoIMOB\"]").sendKeys(new BigDecimal( despesas ).setScale( 2 , RoundingMode.UP ).toString());
		new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(4000);
		
		
		WebElement salvar = createElement("//*[@id=\"sacFormularioIMOB:cpBotaoSalvarSacIMOB\"]");
		
		String resSalvar = (String) ((JavascriptExecutor) driver).executeScript("arguments[0].click();", salvar);
		
//		System.out.println("Proposta.sacSFH() salvando.." + resSalvar);
//		print();
//		salvar.click();
		
		Thread.sleep(2000);
		
//		String response = createElement("//*[@id=\"messages\"]").getText();//getAttribute("innerHTML");
		try{	
	//String response = createElement("/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span").getText();//getAttribute("innerHTML");
		
			
			
//	String button = "/html/body/div[4]/div[2]/div/div[2]/table/tbody/tr[2]/td/ul/li/span";
			String button = "//*[@id=\"messages\"]/li/span";

	new WebDriverWait(driver, 30).until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));

	Thread.sleep(2000);
	
	String response = checktStale(createElement(button)).getText();
//			String response = "Aguardando SAC";
			
			System.out.println(response);
			
			cliente.setResultado(response);
			
			
			
		}catch(org.openqa.selenium.StaleElementReferenceException ex)
			{
//				System.out.println(driver.getPageSource());
			
			ex.printStackTrace();
			
				String response = "Sac gravada com sucesso?";
				
//				System.out.println(response);
				
				cliente.setResultado(response);
//			org.openqa.selenium.TimeoutException
			}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
			{
				String response = "Sem Resultado";	
				//print();
//				String response = createElement("//*[@id=\"painelMensagens\"]").getAttribute("innerHTML");
				
				List<WebElement> errors = driver.findElements(By.cssSelector("span[class=\"errors\"]"));

				for (WebElement anElement : errors) {
					System.out.println(anElement.getAttribute("id"));

				}
//				System.out.println(response);
				
				cliente.setResultado(response);
			}
			
			
			try{
				createElement("//*[@id=\"cForMod_BJ9:cComMod_5QS\"]").click();
			}	catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e)
			{}
}

public void print() throws IOException{
	String javascript = "return arguments[0].innerHTML";
	String pageSource=(String)((JavascriptExecutor) driver).executeScript(javascript, driver.findElement(By.tagName("html")));
//	pageSource = "<html>"+pageSource +"</html>";
//	System.out.println(pageSource);
	FileUtils.write(new File("C:\\Users\\marcio\\Desktop\\proposta.html"), pageSource, false);

	
}
}
