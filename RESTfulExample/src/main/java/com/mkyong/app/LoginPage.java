package com.mkyong.app;


import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LoginPage {
    private final WebDriver driver;

    JavascriptExecutor js;

    @FindBy(id="buscaAnaliseIntervPreSAC:cDecPidpp_808:comboLinhaCreditoSemConvenio") WebElement linhaCredito;
    
    @FindBy(name="buscaAnaliseIntervPreSAC:ufMaisSac:comboUfMaisSac") WebElement uf;
//    @FindBy(linkText="Aquisição PF PMCMV Convênios") WebElement choice;
    final static String USERNAME = "JC049957";  
    final static String PASSWORD = "spb91744"; 

    public LoginPage(WebDriver driver) {
        this.driver = driver; 
        js=(JavascriptExecutor)driver;
        PageFactory.initElements(driver, this);
    }
    
    
    
    public JavascriptExecutor getJs() {
		return js;
	}



	public void setJs(JavascriptExecutor js) {
		this.js = js;
	}



	public WebDriver getDriver() {
		return driver;
	}



	public void newLoginAs(String username, String password) throws Exception {
//      driver.get("https://portaldecredito.bb.com.br/login.bb");
//  	driver.navigate().to("https://portaldecredito.bb.com.br/login.bb");
  	
//  	System.out.println(driver.getCurrentUrl());
    	
      WebDriverWait wait = new WebDriverWait(driver, 10);
 	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idChaveJ")));
      driver.findElement(By.id("idChaveJ")).sendKeys(username);
      driver.findElement(By.name("password")).sendKeys(password);
//      driver.findElement(By.linkText("ENTRAR")).click();    
      driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
      
      
      percentage = 3;
      
      
      
      Thread.sleep(1000);
  	
//  	check();
  	
//  	System.out.println("Logged in.");
  	
//  	System.out.println(driver.getPageSource());
//  	System.out.println(driver.getCurrentUrl());
  	
//  	driver.navigate().to("https://portalcredito.bb.com.br/imob");
//  	try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
  	

  	
//  	getPage();
  	
  	
//  	WebDriverWait wait2 = new WebDriverWait(driver, 60);
//	 	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//portal-credito-app/home-componente/perfil-componente/iframe-redimensionavel-componente/span/iframe/home/body/app-imob/home-componente/menu-componente/div/div/div/div[@id='idItemMenu0']")));
//  	driver.findElement(By.xpath("//portal-credito-app/home-componente/perfil-componente/iframe-redimensionavel-componente/span/iframe/home/body/app-imob/home-componente/menu-componente/div/div/div/div[@id='idItemMenu0']")).click();
//  	 System.out.println(driver.getCurrentUrl());
  }
    
    
    public void getPage() throws Exception{
//    	System.out.println("Waiting frames to be loaded.");
    	
    	
    	Thread.sleep(2000);
    	
//    	driver.findElement(By.xpath("/html/body/portal-credito-app/selecao-perfil-componente/div/card-componente/div/div[2]/div/button")).click();
//    	Thread.sleep(3000);
//  	driver.switchTo().frame(0);
  	WebDriverWait waitFrame = new WebDriverWait(driver,30);
//  	waitFrame.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("idFramePerfilSelecionado"));
  	waitFrame.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("idIframePortalCredito"));
  	
//  	System.out.println("Frame selected.");
  	percentage = 4;
  	
      	WebDriverWait wait2 = new WebDriverWait(driver, 30);
     	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"idItemMenu0\"]")));
     	
     	driver.findElement(By.xpath("//*[@id=\"idItemMenu0\"]")).click();
     	percentage = 5; 	
//     	Actions action=new Actions(driver);
//	    
//        
//        action.keyDown(Keys.LEFT_CONTROL)
//        .click(driver.findElement(By.xpath("//*[@id=\"idItemMenu0\"]")))
//        .keyUp(Keys.LEFT_CONTROL)
//        .build()
//        .perform();
        
//    	URL jqueryUrl = Resources.getResource("./jquery.js");
//    	System.out.println(jqueryUrl.getPath());
//    	String jqueryText = Resources.toString(jqueryUrl,Charsets.UTF_8);
//    	js.executeScript(jqueryText);
//    	
//    	isjQueryLoaded(driver);
//
//        String script = "e = jQuery.Event('click');e.ctrlKey = true;    $x('//*[@id=\"idItemMenu0\"]').trigger(e);";
//        js.executeScript(script);
     	
      	
      	
//      	System.out.println("Page link clicked.");
      	
      	
     	Thread.sleep(1000);
      	
//      	check();
      	
    }

    public void check() throws Exception{
      	try {
      		
        	
      		WebDriverWait wait3 = new WebDriverWait(driver, 2);
         	wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/portal-credito-app/revalidar-autenticacao-componente/span/modal-componente/span/div/div/div[2]/div/div[3]/input")));
         	
//      		verifyElementVisible(driver.findElement(By.xpath("/html/body/portal-credito-app/revalidar-autenticacao-componente/span/modal-componente/span/div/div/div[2]/div/div[3]/input")));
      		
         	driver.findElement(By.xpath("/html/body/portal-credito-app/revalidar-autenticacao-componente/span/modal-componente/span/div/div/div[2]/div/div[3]/input")).sendKeys(PASSWORD);;
         	driver.findElement(By.xpath("/html/body/portal-credito-app/revalidar-autenticacao-componente/span/modal-componente/span/div/div/div[2]/div/div[3]/div[2]/div[1]/button")).click();
         	
		} catch (Exception e) {
//			throw new Exception(e);
//			e.printStackTrace();
		}finally{
//			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t"); 
			percentage = 3;
		}
    }
    
    
    public void loginAs(String username, String password) throws Exception {
//        driver.get("https://portaldecredito.bb.com.br/login.bb");
//    	driver.navigate().to("https://portaldecredito.bb.com.br/login.bb");
    	
//    	System.out.println(driver.getCurrentUrl());
    	
        WebDriverWait wait = new WebDriverWait(driver, 30);
   	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginForm:botaoEntrar")));
        driver.findElement(By.id("loginForm:cDecChaveJUsuario:identificacaoUsuario")).sendKeys(username);
        driver.findElement(By.id("loginForm:cDecSenhaChaveJUsuario:senhaUsuario")).sendKeys(password);
        driver.findElement(By.id("loginForm:botaoEntrar")).click(); 
        
        percentage = 1;
        
        Thread.sleep(1000);
//    	System.out.println(driver.getCurrentUrl());
    	WebDriverWait wait2 = new WebDriverWait(driver, 30);
    	 wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("cabecalhoDireita")));
//    	 System.out.println(driver.getCurrentUrl());
    	 
    	 
    }
    
public void loadLoginPage() throws Exception{

    	
    	driver.get("https://portaldecredito.bb.com.br/index.bb");
//    	driver.get("https://portalcredito.bb.com.br/portal");
//    	System.out.println("Login Page Loaded.");
    	
    	WebDriverWait wait = new WebDriverWait(driver, 3);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='cForInd_478:cComInd_8KQ']")));
    	
    	driver.findElement(By.xpath("//a[@id='cForInd_478:cComInd_8KQ']")).click();
    	
//    	System.out.println(driver.getPageSource());
//    	System.out.println(driver.getCurrentUrl());
    	
    	Thread.sleep(1000);
    	
    	
    	percentage = 0;

    	
    }
    
    public void loadLoginPage(String cpf) throws Exception{

    	
//    	driver.get("https://portaldecredito.bb.com.br/index.bb");
    	driver.get("https://portalcredito.bb.com.br/portal?rand="+Math.floor((Math.random() * 10000) + 1));
//    	System.out.println("Login Page Loaded.");
    	
//    	driver.findElement(By.xpath("//a[@id='cForInd_478:cComInd_8KQ']")).click();
    	
//    	System.out.println(driver.getPageSource());
//    	System.out.println(driver.getCurrentUrl());
    	
    	Thread.sleep(1000);
    	
    	
    	percentage = 2;

    	
    }
    
    public String getUrl(){
    	return driver.getCurrentUrl();
    }
    
    public void quit(){
    	driver.quit();
    }
    
    public String analyse(String cpf) throws Exception{
    	
//    	URL jqueryUrl = Resources.getResource("jquery-3.2.1.min.js");
//    	System.out.println(jqueryUrl.getPath());
//    	String jqueryText = null;
//		try {
//			jqueryText = Resources.toString(jqueryUrl,Charsets.UTF_8);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//    	js.executeScript(jqueryText);
////    	
//    	isjQueryLoaded(driver);
    	
    	percentage = 0;
    	
    	driver.navigate().to("https://portaldecredito.bb.com.br/pages/verificacoesPrevias/analiseIntervenientes.bb");
    	
    	
//    	verifyElementVisible(driver.findElement(By.id("buscaAnaliseInterv:j_id36:criterioPesquisaRadio:1")));
//    	
//    	WebDriverWait wait = new WebDriverWait(driver, 5);
//	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buscaAnaliseInterv:j_id36:criterioPesquisaRadio:1")));
//    	
//    	js.executeScript("arguments[0].click();", driver.findElement(By.id("buscaAnaliseInterv:j_id36:criterioPesquisaRadio:1")));
//    	
//    	driver.findElement(By.id("buscaAnaliseInterv:j_id36:criterioPesquisaRadio:1")).click();
    	
    	
    	WebDriverWait wait = new WebDriverWait(driver, 10);
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"buscaAnaliseInterv:j_id33:criterioPesquisaRadio:1\"]")));
    	
//    	js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:j_id33:criterioPesquisaRadio:1\"]")));
    	
    	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:j_id33:criterioPesquisaRadio:1\"]")).click();
    	
    	percentage = 2;

    	Thread.sleep(2000);
    	
    	
    	
//    	verifyElementVisible(driver.findElement(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAIcpfBuscaClienteDecorate:selecaoInclusaoProponenteAIcpfBuscaCliente")));
    	
    	WebDriverWait wait2 = new WebDriverWait(driver, 5);
	 	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAIcpfBuscaClienteDecorate:selecaoInclusaoProponenteAIcpfBuscaCliente")));
    	
    	
//    	driver.findElement(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAIcpfBuscaClienteDecorate:selecaoInclusaoProponenteAIcpfBuscaCliente")).sendKeys("189.778.418-01");
    	driver.findElement(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAIcpfBuscaClienteDecorate:selecaoInclusaoProponenteAIcpfBuscaCliente")).sendKeys(cpf);	
    	
    	driver.findElement(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAIbtnPesquisa")).click();
    	
    	percentage = 4;
    	
    	Thread.sleep(2000);
    	
//    	Actions hover = new Actions(driver);
    	
    	WebDriverWait wait3 = new WebDriverWait(driver, 5);
    	
    	
    	
    	
	 	wait3.until(ExpectedConditions.visibilityOfElementLocated(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:tb")));

//    	verifyElementVisible(driver.findElement(By.id("buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:tb")));
//    	hover.moveToElement(driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:j_id101\"]"))).perform();
    	wait3.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:j_id101\"]"))));  
//    	hover.moveToElement(driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:j_id101\"]"))).click();
	 	  

    	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:j_id104\"]")).getText();
    	
    	
    	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:j_id101\"]")).click();
//    	js.executeScript("A4J.AJAX.Submit('buscaAnaliseInterv',event,{'oncomplete':function(request,event,data){},'similarityGroupingId':'buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:orc','parameters':{'buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:orc':'buscaAnaliseInterv:selecaoInclusaoProponenteAITabela:0:orc'} ,'containerId':'buscaAnaliseInterv:selecaoInclusaoProponenteAIregiaoResultadoPesquisa'} )");
    	
    	
    	percentage = 6;
    	
    	Thread.sleep(2000);
    	
    	
//    	verifyElementVisible(driver.findElement(By.id("buscaAnaliseInterv:comboLinhaDeCreditoProduto")));
    	
    	WebDriverWait wait4 = new WebDriverWait(driver, 5);
//	 	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.id("buscaAnaliseInterv:comboLinhaDeCreditoProduto")));
	 	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"buscaAnaliseInterv:cDecPidpp_808\"]/span[1]/div/a/span")));
    	
	 	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:cDecPidpp_808\"]/span[1]/div/a/span")).click();
	 	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:cDecPidpp_808\"]/span[1]/div/div/ul/li[2]")).click();
    	
//    	selectOne("buscaAnaliseInterv:comboLinhaDeCreditoProduto", "Aquisição PF - SFH");
//    	
//    	driver.findElement(By.id("buscaAnaliseInterv:botaoAnalisarInterveniente")).click();
	 	
	 	driver.findElement(By.xpath("//*[@id=\"buscaAnaliseInterv:botaoAnalisarInterveniente\"]")).click();
    	
    	percentage = 8;
    	
    	Thread.sleep(2000);
    	
//    	verifyElementVisible(driver.findElement(By.id("divfieldsetResultadoAnaliseInterv")));
    	
    	WebDriverWait wait5 = new WebDriverWait(driver, 5);
//	 	wait5.until(ExpectedConditions.visibilityOfElementLocated(By.id("divfieldsetResultadoAnaliseInterv")));
    	wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"divfieldsetResultadoAnaliseInterv\"]")));

			String response = driver.findElement(By.xpath("//*[@id=\"divfieldsetResultadoAnaliseInterv\"]")).getAttribute("innerHTML");

//if(response.indexOf("CLIENTE NÃO POSSUI LIMITE DE CRÉDITO VIGENTE")>1){
//	System.out.println("==============================");
//	System.out.println("tchau");
//}else{
//	System.out.println("==============================");
//	System.out.println("proximo");
//}
			
//			System.out.println(response);
			
			percentage = 10;
			
			return response;
			
    }
    
    
    public int percentage = 0;;
    
 public String verify(String cpf) throws Exception{
    	
//    	System.out.println("Filling the form");
	 	
//    	verifyElementVisible(driver.findElement(By.id("idCpfCnpj")));
	 
	 	WebDriverWait wait2 = new WebDriverWait(driver, 5);
	 	wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("idCpfCnpj")));
    	
    	driver.findElement(By.id("idCpfCnpj")).clear();
    	
    	driver.findElement(By.id("idCpfCnpj")).sendKeys(cpf);
    	
    	percentage = 6;
    	
    	WebDriverWait wait3 = new WebDriverWait(driver, 3);
	 	wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[2]/span/selecao-linha-credito-convenio-componente/selecao-componente/select")));
    	
    	Select dropdown = new Select(driver.findElement(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[2]/span/selecao-linha-credito-convenio-componente/selecao-componente/select")));
    	Thread.sleep(2000);
    	dropdown.selectByValue("475");
    	
    	percentage = 7;
    	
    	Thread.sleep(1000);
    	
    	WebDriverWait wait4 = new WebDriverWait(driver, 3);
	 	wait4.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[3]/intervenientes-componente/span/div/selecao-componente/select")));

    	
    	Select dropdown2 = new Select(driver.findElement(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[3]/intervenientes-componente/span/div/selecao-componente/select")));
    	
    	dropdown2.selectByValue("1");
    	
    	percentage = 8;
    	
    	WebDriverWait wait5 = new WebDriverWait(driver, 3);
	 	wait5.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[5]/button")));

    	
    	driver.findElement(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span/bloco-conteudo-componente/div/div[2]/form/div[5]/button")).click();
    	
    	percentage = 9;
    	
    	
    	Thread.sleep(1000);
    	
    		
			
    		WebDriverWait wait = new WebDriverWait(driver, 3);
   	 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span[2]/span/resultado-analise-componente/bloco-conteudo-componente/div")));

			String response = driver.findElement(By.xpath("/html/body/app-imob/home-componente/div/ng-component/analise-previa-componente/span[2]/span/resultado-analise-componente/bloco-conteudo-componente/div")).getAttribute("innerHTML");
			
			percentage = 10;

//			System.out.println(response);
			
			return response;
			
    }
    
    	
    
    public void closeTab() throws Exception {
    	driver.close();
	
}

	public void print(){
    	String javascript = "return arguments[0].innerHTML";
    	String pageSource=(String)js.executeScript(javascript, driver.findElement(By.tagName("html")));
    	pageSource = "<html>"+pageSource +"</html>";
//    	System.out.println(pageSource);
    	//FileUtils.write(new File("e:\\test.html"), pageSource,);

    	
    }
    
    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver webdriver) {
            try {
              return ((Long)((JavascriptExecutor)webdriver).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
              // no jQuery present
              return true;
            }
          }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
          @Override
          public Boolean apply(WebDriver webdriver) {
            return ((JavascriptExecutor)webdriver).executeScript("return document.readyState")
            .toString().equals("complete");
          }
        };

      return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
    
    public void post(){
    	
    	driver.navigate().to("https://portaldecredito.bb.com.br/pages/verificacoesPrevias/analiseIntervenientesPreSAC.bb");
    	js.executeScript("window.focus();");
//      WebDriverWait wait = new WebDriverWait(driver, 5);
// 	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ENTRAR")));
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:idDecoretaCPFinformado1:cpfInformado1")).sendKeys("189.778.418-01");  
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:idDecoretaCPFinformado2:cpfInformado2")).sendKeys("");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:idDecoretaCPFinformado3:cpfInformado3")).sendKeys("");
      
      try {
		initSelect();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
//      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_808:comboLinhaCreditoSemConvenio")).sendKeys("584");
//      new Select(linhaCredito).selectByValue("584");
//      js.executeScript("$(arguments[0]).change();", linhaCredito);
      
      selectOne("buscaAnaliseIntervPreSAC:cDecPidpp_808", "584");
        
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_808998:rendaBrutaFamiliar")).sendKeys("8.000,00");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_8089988877:valorImovel")).sendKeys("200.000,00");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_808998_Financiamento:valorFinancimentoPreSac_Financiamento")).sendKeys("50.000,00");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_8089988877_Compromisso:valorCompromisso")).sendKeys("0,00");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_8089988877_MediaCredito:mediaCreditoRotativo")).sendKeys("0,00");
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPai_6JHIMOB_TipoImovel:tipoImovelIMOB_SacMais")).sendKeys("1");
      new Select(driver.findElement(By.name("buscaAnaliseIntervPreSAC:cDecPai_6JHIMOB_TipoImovel:tipoImovelIMOB_SacMais"))).selectByValue("1");
      js.executeScript("$(arguments[0]).change();", driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPai_6JHIMOB_TipoImovel:tipoImovelIMOB_SacMais")));

      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPai_P63IMOB_Sist_Amort:sistemaReposicaoSacMais")).sendKeys("1");
      new Select(driver.findElement(By.name("buscaAnaliseIntervPreSAC:cDecPai_P63IMOB_Sist_Amort:sistemaReposicaoSacMais"))).selectByValue("1");
      js.executeScript("$(arguments[0]).change();", driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPai_P63IMOB_Sist_Amort:sistemaReposicaoSacMais")));

      
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:cDecPidpp_8089988877_PrazoSacMais:prazoAnalisePreSac")).sendKeys("300");
      
//      driver.findElement(By.name("buscaAnaliseIntervPreSAC:ufMaisSac:comboUfMaisSac")).sendKeys("SP");
      new Select(uf).selectByValue("SP");
//      js.executeScript("$(arguments[0]).change();", uf);

      System.out.println(js.executeScript("$(\"#buscaAnaliseIntervPreSAC\\\\:municipioPreSacMais\\\\:municipioDadosPresac\").show();"));

      verifyElementVisible(driver.findElement(By.id("buscaAnaliseIntervPreSAC:municipioPreSacMais:municipioDadosPresac")));
        
//      driver.findElement(By.name("buscaAnaliseIntervPreSAC:municipioPreSacMais:municipioDadosPresac")).sendKeys("86900");
      new Select(driver.findElement(By.name("buscaAnaliseIntervPreSAC:municipioPreSacMais:municipioDadosPresac"))).selectByValue("86900");
//      js.executeScript("$(arguments[0]).change();", driver.findElement(By.name("buscaAnaliseIntervPreSAC:municipioPreSacMais:municipioDadosPresac")));
      
      driver.findElement(By.id("buscaAnaliseIntervPreSAC:botaoAnalisarInterveniente")).click(); // works fine
        

    }
    
//    public void waitForAjax() {
//        waitForRenderedElements(By.className("ajaxLoadingImg"));
//        waitForRenderedElementsToDisappear(By.className("ajaxLoadingImg"));
//    }

    public void clickLinkWithText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }


    public void selectOne(String idPrefix, String value) {
    	
    	WebElement elem=(new WebDriverWait(driver, 100)) //added this line
    		    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='" + idPrefix + "']")));
    	
//        if (StringUtils.isNotBlank(value)) {
            driver.findElement(By.xpath("//div[@id='" + idPrefix + "']/div/span/div/a")).click();
            
            js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//div[@id='" + idPrefix + "']/div/span/div/a")));
            
            verifyElementVisible(driver.findElement(By.xpath("//div[@id='" + idPrefix + "']/div/span/div/div/ul/li[text()='" + value + "']")));
            
            driver.findElement(By.xpath("//div[@id='" + idPrefix + "']/div/span/div/div/ul/li[text()='" + value + "']")).click();
//        }
    }

   
    
    public void initSelect() throws Exception{
    	
//    	URL jqueryUrl = Resources.getResource("jquery-3.2.1.min.js");
//    	System.out.println(jqueryUrl.getPath());
//    	String jqueryText = Resources.toString(jqueryUrl,Charsets.UTF_8);
//    	js.executeScript(jqueryText);
//    	
    	isjQueryLoaded(driver);

//    	arrow.click();
//    	choice.click();
    	
//    	System.out.println(js.executeScript("jQuery(\"#buscaAnaliseIntervPreSAC\\\\:cDecPidpp_808\\\\:comboLinhaCreditoSemConvenio\").show();"));
    	
//    	System.out.println(js.executeScript("var selector = \"#buscaAnaliseIntervPreSAC\\\\:cDecPidpp_808\\\\:comboLinhaCreditoSemConvenio\";try {selector = eval(\"#buscaAnaliseIntervPreSAC\\\\:cDecPidpp_808\\\\:comboLinhaCreditoSemConvenio\");} catch (e) {}jQuery(selector).select2();"));
    	
//    	verifyElementVisible(linhaCredito);
        
        
    }


   

public void verifyElementVisible(WebElement element){
    //new WebDriverWait(driver,10000).until(ExpectedConditions.visibilityOf(element));
	 for (int second = 0;; second++) {
         if (second >= 60){
             System.out.println("timeout");
         }
         try {
             if (isElementPresent(element)){
                 break;
             }
             }
         catch (Exception e) {

         }
         try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

}
	private boolean isElementPresent(WebElement element) {
        try {
        	return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void isjQueryLoaded(WebDriver driver) {
        System.out.println("Waiting for ready state complete");
        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                System.out.println("Ready State: " + readyState);
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }
    
    public static void main(String[] args) throws Exception{

    	long startTime = System.currentTimeMillis();
    	System.setProperty("javax.net.ssl.trustStore","jssecacerts");

    	java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(java.util.logging.Level.OFF); /* comment out to turn off annoying htmlunit warnings */

    	System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,"true");
    	System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");

    	System.setProperty("webdriver.gecko.driver","geckodriver.exe");

    	DesiredCapabilities caps = new FirefoxOptions().setLogLevel(Level.OFF).addTo(DesiredCapabilities.firefox());

	    FirefoxBinary firefoxBinary = new FirefoxBinary();
	    firefoxBinary.addCommandLineOptions("--headless");


	    FirefoxOptions firefoxOptions = new FirefoxOptions();
	    firefoxOptions.setBinary(firefoxBinary);
	    
	    firefoxOptions.addTo(caps);
	    
	    WebDriver firefoxdriver = new FirefoxDriver(caps);
    	
    	LoginPage login = new LoginPage(firefoxdriver);
    	String cpf = "18977841801";
        login.loadLoginPage(cpf);
        login.newLoginAs(USERNAME, PASSWORD);
        
//        String cpf = args[0]!=null?cpf:"18977841801";
        
//        login.post();
        try {
			login.verify(cpf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        login.quit();
        
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        
        long second = TimeUnit.MILLISECONDS.toSeconds(elapsedTime);
        long minute = TimeUnit.MILLISECONDS.toMinutes(elapsedTime);
        long hour = TimeUnit.MILLISECONDS.toHours(elapsedTime);
        elapsedTime -= TimeUnit.SECONDS.toMillis(second);
        System.out.println(String.format("%02d:%02d:%02d:%d", hour, minute, second, elapsedTime));
        
    }
}