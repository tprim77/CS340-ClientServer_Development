package com.snhu.sslserver;

import java.security.MessageDigest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.Server.config.web",  // MVC @Configuration
        "com.Server.config.security", // Security @Configuration
        "com.Server.config.jpa", // Database @Configuration -> does Entity Scan and Repository scan
        "com.Server.service", // Service scan @Service
        "com.Server.controller", // Controller scan @Controller
        "com.snhu.sslserver" //Directs to package that is currently being used, will not work without

}) 
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";
@RestController
class ServerController{
    @RequestMapping("/hash")
    public String myHash() throws Exception{
    	String data = "Hello Tyler Primas!";
    	
    	MessageDigest md = MessageDigest.getInstance("SHA-256");
    	
    	md.update(data.getBytes());
    	
    	byte[] digest = md.digest();
    	System.out.println(digest);
    	
    	
    	StringBuffer hexString = new StringBuffer();
    	
    	//Generates hexString from bytes
    	for (int i = 0; i < digest.length; i++) {
    		hexString.append(Integer.toHexString(0xFF & digest[i]));
    	}
    	System.out.println("Hex format : " + hexString.toString());
       
    	
    	//returns data and hexString info to web browser
        return "<p>data: "+ data + "<p>SHA-256: CheckSum Value: " + hexString;
       
    }
}