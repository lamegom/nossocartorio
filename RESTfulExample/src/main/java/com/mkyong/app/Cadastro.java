package com.mkyong.app;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.WordUtils;
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

public class Cadastro {
	
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
	


	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		
//		cliente.setNomeCompleto("Tulio Henrique de Albuquerque Lamego");
		
//		cliente.setCpf("262.370.558-51");
		
//		cliente.setDataNascimento("28/05/1977");
		
//		cliente.setEstado("SÃO PAULO");
//		cliente.setMunicipio("SAO PAULO");
		
		
//		cliente.setTipoPropriedade("Familiar");
		
//		cliente.setCep("05713460");
//		cliente.setBairro("Morumbi");
		
//		cliente.setResideDesde("03 01 1996");
		
//		Cadastro example = new Cadastro(cliente);
//		try {
//			example.loadLoginPage();
//			example.loginAs(USERNAME, PASSWORD);
//			
//			Thread.sleep(1000);
//			example.testHtmlUnitDriver();
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
	
	public Cadastro(Cliente cliente, ServletContext context){
		
		
		this.cliente = cliente;
		this.context = context;
		
//		useHtmlUnit();

//		useRemoteChrome();
	
//			useFirefox();
		
//		useFirefoxRemote();
	    
//		useHtmlUnitRemote();
		
		
		//TODO: web.xml driver path and type
		if(context.getInitParameter("type3").equals("firefox")){
			useFirefoxRemote(context.getInitParameter("url3"));
		}else{
			useRemoteChrome(context.getInitParameter("url3"));
		}
		
	    wait = new WebDriverWait(driver, 30);
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
//			driver = new RemoteWebDriver(new URL("http://54.200.174.75:4444/wd/hub"),//windows  
//			driver = new RemoteWebDriver(new URL("http://34.212.255.98:4444/wd/hub"),//01
//			driver = new RemoteWebDriver(new URL("http://54.69.24.196:4444/wd/hub"),//04
//			driver = new RemoteWebDriver(new URL("http://52.26.164.96:4444/wd/hub"),//03
			driver = new RemoteWebDriver(new URL(url),//balancer
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
			
			driver.navigate().to("https://portaldecredito.bb.com.br/pages/cadastro/consulta.bb");

			getCompradorForm();
			
			insertCadastro();

			
			return null;
			
			

			


		}catch(Exception ex)
		{
			try{
			String response = createElement("//*[@id=\"messages\"]").getText();
			System.out.println(response);
			 cliente.setResultado(response);
			}catch(Exception ex1)
			{}finally{
			 
				if(driver!=null)
				{
					driver.close();
					driver=null;
				}
			}
			
			throw new Exception(ex);
		}finally
		{
			if(driver!=null)
			{
				driver.close();
				driver=null;
			}
		}
	}
	
	private void insertCadastro() throws Exception {
		
		System.out.println("Proposta.insertCadastro()");
		try{
//		Thread.sleep(2000);
		createElement("//*[@id=\"formCadastro:cpfDecorate:cpf\"]").sendKeys(cliente.getCpf().get(0));
		createElement("//*[@id=\"formCadastro:nomeDecorate:nome\"]").sendKeys(removerAcentos(cliente.getNomeCompleto().get(0)));
		//System.out.println(cliente.getDataNascimento());
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
		Date dtNasc = format.parse(cliente.getDataNascimento().get(0));
		
		createElement("//*[@id=\"formCadastro:dataNascDecorate:dataNasc\"]").sendKeys(format2.format(dtNasc));

		String tipo = WordUtils.capitalize(cliente.getTipoPropriedade().get(0).replaceAll("-", " ")).replaceAll(" E ", " e ");
//		System.out.println(tipo);
		new Select(createElement("//*[@id=\"formCadastro:tipoPropriedadeDecorate:tipoPropriedade\"]")).selectByVisibleText(tipo);
		createElement("//*[@id=\"formCadastro:cepDecorate:cep\"]").sendKeys(cliente.getCep().get(0));
//		createElement("//*[@id=\"formCadastro:enderecoDecorate:endereco\"]").sendKeys(cliente.getEndereco());
//		new Select(createElement("//*[@id=\"formCadastro:ufDecorateEndereco:ufEndereco\"]")).selectByVisibleText(cliente.getEstado());
//		new Select(createElement("//*[@id=\"formCadastro:municipioDecorateEndereco:municipioEndereco\"]")).selectByVisibleText(removerAcentos(cliente.getMunicipio().toUpperCase()));
//		Thread.sleep(2000);
		WebElement bairro = createElement("//*[@id=\"formCadastro:bairroDecorate:bairro\"]");
//		bairro.clear();
		if(bairro.getAttribute("value").equals("")){
			bairro.sendKeys(removerAcentos(cliente.getBairro().get(0)));
		}
		
		
		Date dates = format.parse(cliente.getResideDesde().get(0));
		DateFormat format3 = new SimpleDateFormat("MM/yyyy");
		String dataResideDesde = format3.format(dates);
//		System.out.println(dataResideDesde);
		createElement("//*[@id=\"formCadastro:desdeDecorate:desde\"]").sendKeys(dataResideDesde);
		
		createElement("//*[@id=\"formCadastro:autorizaDecorate:autoriza:0\"]").click();
		
		
		
		
		
		
		Select dropdown3 = new Select(createElement("//*[@id=\"formCadastro:ufDecorate:uf\"]"));
		
		/*try{
		JDBCPreparedStatement j = new JDBCPreparedStatement();
		String ag = j.selectAgenciafromMunicipio(removerAcentos(cliente.getMunicipio().get(0).toUpperCase()));
		
		dropdown3.selectByVisibleText(new EstadosCidades().estados.get(uf).toUpperCase());
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(4000);

		createElement("//*[@id=\"formCadastro:buscaPorDecorate:selecao:1\"]").click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
		Thread.sleep(3000);

		
		createElement("//*[@id=\"formCadastro:prefixoDecorate:prefixo\"]").sendKeys(ag);
		
		

		createElement("//*[@id=\"formCadastro:prefixoDecorate:btnBuscar\"]").click();
		Thread.sleep(2000);

		selectLast("//*[@id=\"cForPsd_4VQ:dependenciaDecorate:propostaDependenciaAcolhedora\"]");
		
		
		}catch(Exception e){*/
			dropdown3.selectByVisibleText("SÃO PAULO");
	//		dropdown3.selectByVisibleText("PARANÁ");
			
			createElement("//*[@id=\"formCadastro:buscaPorDecorate:selecao:1\"]").click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
			Thread.sleep(3000);
			createElement("//*[@id=\"formCadastro:prefixoDecorate:prefixo\"]").sendKeys("5809");
			createElement("//*[@id=\"formCadastro:prefixoDecorate:btnBuscar\"]").click();
			Thread.sleep(2000);
			new Select(createElement("//*[@id=\"formCadastro:dependenciaDecorate:dependencia\"]")).selectByValue("5809");

		//}
		WebElement but2 = createElement("//*[@id=\"formCadastro:btnImprimir\"]");
		if(but2!=null)
		{
			but2.click();
			
			
		}
		
		WebElement but3 = createElement("//*[@id=\"formCadastro:btnSalvar\"]");
		if(but3!=null)
		{
			but3.click();
			
			
		}
		
		

			Thread.sleep(2000);
			
			createElement("//*[@id=\"formComplemento:autorizaSCRDecorate:autorizaSCR:0\"]").click();
			createElement("//*[@id=\"formComplemento:abrirCCDecorate:abrirCC:1\"]").click();
			
			createElement("//*[@id=\"formComplemento:cComCom_IPA\"]").click();
			Thread.sleep(2000);
			String res = createElement("//*[@id=\"messages\"]").getText();
			
			cliente.setResultado(res);
			
		ExistingMethodExample  mailchimp = new ExistingMethodExample("0c827d8a5da6e5bc6d2cac0116c5e96d-us17", "f0704e46ec");
		mailchimp.setEmail(cliente.getEmail().get(0));
	
			mailchimp.setAnalise("Cadastrais");

		mailchimp.setResultado("Cadastro inserido com sucesso.");
		
		String sid2 = null;	
try{	
		TwilioTest2 t2 = new TwilioTest2(); 
		 sid2 = t2.dial(cliente.getPhone().get(0));
}catch(Exception e){
//	System.out.println(e.getCause().getLocalizedMessage());
}
		
		mailchimp.setMotivo(sid2);
		
		mailchimp.start();
		}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException e){
			StringBuffer sb = new StringBuffer();
			try{
			List<WebElement> ness = driver.findElements(By.cssSelector("span[class='errors']"));
			
			for(WebElement elem: ness){
				System.out.println(elem.getText());
				sb.append(elem.getText());
			}

			cliente.setResultado(sb.toString());
			
			throw new Exception(sb.toString());
			
			}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException ex){
				
				try{
					String res = createElement("//*[@id=\"messages\"]").getText();
					sb.append(res);
					
					cliente.setResultado(sb.toString());
					
					throw new Exception(sb.toString());
					
					
				}catch(org.openqa.selenium.NoSuchElementException |org.openqa.selenium.TimeoutException ex2){
					
				}
				
				throw new Exception(e);
			}
		}

			Thread.sleep(2000);
		
	}
	
	public String removerAcentos(String str) {
	    return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
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

    	System.out.println("Cadastro.loadLoginPage()");
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

public void getCompradorForm() throws Exception{
	
//	System.out.println("Proposta.getCliente()");
	
	
	
	
	//
	

	
//	Thread.sleep(3000);
	
	
	WebElement rad = createElement("//*[@id=\"formConsulta:tipoOperacaoDecorete:tipoOperacao:1\"]");
	if(rad!=null)
	{
		rad.click();
		
		
	}
	
	WebElement serv = createElement("//*[@id=\"formConsulta:tipoCadastro:filtroCadastro\"]");
	if(serv!=null)
	{
		new Select(serv).selectByVisibleText("CRÉDITO IMOBILIÁRIO - COMPRADOR");
		
		
	}
	
	Thread.sleep(3000);

	

	
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



public void selectLast(String xpath) throws Exception{
	Select dropdown = new Select(createElement(xpath));

	int i = 0;
	if(dropdown.getOptions().size() > 0){
		i = dropdown.getOptions().size()-1;
	}
	
	dropdown.selectByIndex(i);;
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





public void print() throws IOException{
	String javascript = "return arguments[0].innerHTML";
	String pageSource=(String)((JavascriptExecutor) driver).executeScript(javascript, driver.findElement(By.tagName("html")));
//	pageSource = "<html>"+pageSource +"</html>";
//	System.out.println(pageSource);
	FileUtils.write(new File("C:\\Users\\marcio\\Desktop\\proposta.html"), pageSource, false);

	
}
}
