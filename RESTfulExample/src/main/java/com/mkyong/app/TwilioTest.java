package com.mkyong.app;

import java.net.URI;
import java.net.URISyntaxException;
//You may want to be more specific in your imports 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.Twilio;
import com.twilio.http.HttpMethod;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;
import com.twilio.type.PhoneNumber;

//import com.twilio.sdk.TwilioRestClient;
//import com.twilio.sdk.TwilioRestException;
//import com.twilio.sdk.resource.factory.CallFactory;
//import com.twilio.sdk.resource.factory.MessageFactory;
//import com.twilio.sdk.resource.instance.Call;
//import com.twilio.sdk.resource.instance.Message;

public class TwilioTest extends Thread{ 
	 // Find your Account Sid and Token at twilio.com/user/account 
	 public static final String ACCOUNT_SID = "AC47ebdee5f6d2049fe18cf3ca151ff3a7"; 
	 public static final String AUTH_TOKEN = "fd796970ac14962988643a242d686edc"; 

//	 String[] nums = null;
//	 
//	 public TwilioTest(String[] nums2) {
//		this.nums = nums2;
//	}

	public static void main(String[]args) {
		 
////		 String[] nums = {"(11) 97684-3443","(69) 99368-2283","(11) 94007-7632","(34) 3223-7577","(85) 98840-9312","(45) 98826-9045","(51) 98350-2331","(63) 98425-4722","(21) 99305-0693","(16) 99189-7985","(14) 98817-6263","(61) 99938-4849","(11) 2486-9083","(11) 4811-1289"};
//		 String[] nums = {"(11) 98108-4320"};
////		String[] nums = {"(21) 96500-6092","(41) 99828-4537","(34) 99236-1385","(45) 99987-5008","(24) 99853-4003","(21) 97267-3195","(15) 3261-1305","(17) 99728-4948","(48) 99119-1999","(65) 99226-1598","(66) 3422-8282","(31) 99265-9281","(21) 99605-3330","(11) 99362-8464","(11) 98950-7140","(11) 97950-0267","(11) 98420-3062","(21) 96402-1182","(31) 2552-0837","(11) 97684-3443","(11) 94332-8968"};
//		 TwilioTest t = new TwilioTest(nums);
//		 
//		  t.nums = nums;
//		  
//		  
//		  t.start();
	 }
	 
//	 public void run(){
//		 
//		 
//		 for (int i = 0; i < nums.length; i++) {
//			String number = "+55" + nums[i];
//			
//
//				dial(number);
//
//			
//		}
//	 }
	 
	 public  String dial(String number)  { 
		 String phone = "+55" + number;
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 StringBuffer sb = new StringBuffer();


		 System.out.println("TwilioTest.dial() to " + phone);
		 try{
		 String body = "Sua Proposta de crédito imobiliário do Banco do Brasil está aprovada! Verifique seu email e envie a proposta assinada.";
	        MessageCreator messageCreator = new MessageCreator(new PhoneNumber(phone), new PhoneNumber("+16466063895"), body);
	        com.twilio.rest.api.v2010.account.Message message = messageCreator.create();
	        
	        
//	       System.out.println(message.getSid());
	        sb.append(message.getSid());
		 }catch(Exception e){
				 try {
//			 Called=%2B551135892112&ToState=SP&CallerCountry=US&Direction=outbound-api&Timestamp=Sun%2C%2025%20Mar%202018%2009%3A53%3A19%20%2B0000&CallbackSource=call-progress-events&CallerState=NY&ToZip=&SequenceNumber=3&To=%2B551135892112&CallSid=CAbe982f58915a2def4cf7aaa1f1766236&ToCountry=BR&CallerZip=10004&CalledZip=&ApiVersion=2010-04-01&CallStatus=completed&CalledCity=S%C3%A3o%20Paulo&Duration=1&From=%2B16466063895&CallDuration=49&AccountSid=AC47ebdee5f6d2049fe18cf3ca151ff3a7&CalledCountry=BR&CallerCity=NEW%20YORK&ToCity=S%C3%A3o%20Paulo&FromCountry=US&Caller=%2B16466063895&FromCity=NEW%20YORK&CalledState=SP&FromZip=10004&AnsweredBy=human&FromState=NY			 
					 List<String> callbackEvents = Arrays.asList( "ringing", "answered", "completed");

					 Call call = Call.creator(new PhoneNumber(number), new PhoneNumber("+551149502277"),//16466063895//15005550006
			            new URI("http://www.nossocartorio.com.br/blog/wp-content/uploads/twilio/voice.xml"))
			        .setMethod(HttpMethod.GET).setStatusCallback("http://www.nossocartorio.com.br/credito/rest/hook/call4")
			        .setStatusCallbackMethod(HttpMethod.POST).setStatusCallbackEvent(callbackEvents)
			        .setIfMachine("Hangup")
			        .create();

//			    System.out.println(call.getSid());
					 sb.append(call.getSid());

			
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			sb.append(ex.getCause().getLocalizedMessage());
			sb.append(e.getCause().getLocalizedMessage());
		}
		 }
	        
	        return sb.toString();
	 } 
	}
