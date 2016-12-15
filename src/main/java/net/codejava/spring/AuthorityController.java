package net.codejava.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.codejava.spring.model.AppUser;
import net.codejava.spring.model.Authority;
import net.codejava.spring.model.BlockChain;
import net.codejava.spring.model.BlockMetaData;
import net.codejava.util.EncryptionUtil;

@Controller
@RequestMapping(value = "/authority-service")
public class AuthorityController {
	BlockChain blockChain=new BlockChain();

	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Authority> registerAuthority(@RequestBody Authority authority, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) {

		String message = "New Authority " + authority.getAuthName() + " was successfully created.";
		System.out.println(message);
		
        return new ResponseEntity<Authority>(authority, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/checkifminer", method = RequestMethod.GET)
	public ResponseEntity<Void> isAuthorityMiner(@RequestBody Authority authority, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) {

		String message = "Authority " + authority.getAuthName() + "is miner";
		System.out.println(message);
		
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/verifyuser", method = RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> verifyDocuments(@RequestBody AppUser appUser, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) {

		String message = "appUser " + appUser.getUsername() + "is verifying documents."+appUser.getDocument().getDocumentName();
		System.out.println(message);
		
        Map<String,Object> outputMap=new HashMap<String,Object>();
        outputMap.put("status", "clear");// objection  / clear
        return new ResponseEntity<Map<String,Object>>(outputMap, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "/verifyblock", method = RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> verifyBlock(@RequestBody Map<String,byte[]> blockMap, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) throws JSONException, JsonParseException, JsonMappingException, IOException {

		String message = "Miner is verifying Block " + blockMap.get("blockMetaData");
		System.out.println(message);
        Map<String,Object> outputMap=new HashMap<String,Object>();
        
    	String decryptedData=EncryptionUtil.textToBeDecrypted(blockMap.get("blockMetaData"));
		System.out.println("decryptedData==>"+decryptedData);
        outputMap.put("status", "clear");// objection  / clear
        JSONObject jsonObject=new JSONObject(decryptedData);
        System.out.println("\n\t jsonObject-->"+jsonObject);
        
    	ObjectMapper mapper = new ObjectMapper();
      	BlockMetaData blockMetaData=mapper.readValue(jsonObject.toString(), BlockMetaData.class);
      	
          	Map<String,Object> output=new HashMap<String, Object>();
          	output.put("blockMetaData", blockMetaData);
          	output.put("data",  blockMap.get("data"));
          	
        return new ResponseEntity<Map<String,Object>>(output, HttpStatus.CREATED);
	}
	

}
