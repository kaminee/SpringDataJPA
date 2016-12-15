package net.codejava.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.codejava.spring.model.Block;
import net.codejava.spring.model.BlockMetaData;

public class EncryptionUtil {

  public static final String ALGORITHM = "RSA";
  public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
  public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
  
  public static void main(String[] args) throws JSONException {
	  
//	  textToBeEncrypted("My Test text");
	  
/*	  Map<String,String> input=new HashMap<String, String>();
	  input.put("transId", "234234");
	  input.put("transVa", "4534");
	  
	  String encodedEmail = new String(Base64.getEncoder().encode(
              "ABC".getBytes(StandardCharsets.UTF_8)));
	  System.out.println("--encode-"+encodedEmail);
//	  textToBeEncrypted(input);
	  
//	  byte[] btxt=input.toString().getBytes();
	  
	  byte[] btxt=textToBeEncrypted(encodedEmail.getBytes());
	  
//	  byte[] decoded = Base64.Decode()
	  
	  byte[] decoded =   Base64.getDecoder().decode(encodedEmail);
	  System.out.println("decode=="+decoded);
	  textToBeDecrypted(decoded);*/
	  
	  String str="{'blockMetaData':{'blockId':'blockId', 'blockType':'blockType'}}";
	  
      JSONObject jsonObject=new JSONObject(str);
      String str1=jsonObject.getString("blockMetaData");
	  ObjectMapper mapper = new ObjectMapper();
	  System.out.println(str1);
	  try {
		BlockMetaData b=mapper.readValue(str1, BlockMetaData.class);
		
		System.out.println(b);
		
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }
  
  public static String textToBeDecrypted(final byte[]  cipherText) {

	  try {
	      
          ObjectInputStream inputStream = null;
          

          inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
          System.out.println("inputStream-->"+inputStream);
          final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
          
          System.out.println("\t\t private key-->"+privateKey+"\t privateKey=="+cipherText);
          final String plainText = decrypt(cipherText, privateKey);
          inputStream.close();

          System.out.println("cipherText: " + cipherText);
//          System.out.println("Encrypted: " +new String(Base64.encodeBase64(cipherText)));
          System.out.println("Decrypted: " + plainText);
	         return plainText;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
	return null;
  }
  
  
  public static byte[] textToBeEncrypted(final byte[] originalText) {

	  try {
	      if (!areKeysPresent()) {
	        generateKey();
	      }
	      
          ObjectInputStream inputStream = null;

          inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
          final PublicKey publicKey = (PublicKey) inputStream.readObject();
//          final byte[] cipherText = encrypt(originalText, publicKey);
          final byte[] cipherText = encrypt(originalText, publicKey);
          inputStream.close();
          System.out.println("encrypt: " + cipherText);

          inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
          final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
          final String plainText = decrypt(cipherText, privateKey);
          inputStream.close();

          System.out.println("Original: " + originalText);
//          System.out.println("Encrypted: " +new String(Base64.encodeBase64(cipherText)));
          System.out.println("Decrypted: " + plainText);
	         return cipherText;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
	return null;
  }

  public static void generateKey() {
	  System.out.println("\t generating keys .......");
    try {
      final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
      keyGen.initialize(2024);
      final KeyPair key = keyGen.generateKeyPair();
      
      File privateKeyFile = new File(PRIVATE_KEY_FILE);
      File publicKeyFile = new File(PUBLIC_KEY_FILE);

      if (privateKeyFile.getParentFile() != null) {
        privateKeyFile.getParentFile().mkdirs();
      }
      privateKeyFile.createNewFile();

      if (publicKeyFile.getParentFile() != null) {
        publicKeyFile.getParentFile().mkdirs();
      }
      publicKeyFile.createNewFile();

      ObjectOutputStream publicKeyOS = new ObjectOutputStream(
          new FileOutputStream(publicKeyFile));
      publicKeyOS.writeObject(key.getPublic());
      publicKeyOS.close();

      ObjectOutputStream privateKeyOS = new ObjectOutputStream(
          new FileOutputStream(privateKeyFile));
      privateKeyOS.writeObject(key.getPrivate());
      privateKeyOS.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static boolean areKeysPresent() {

    File privateKey = new File(PRIVATE_KEY_FILE);
    File publicKey = new File(PUBLIC_KEY_FILE);

    if (privateKey.exists() && publicKey.exists()) {
      return true;
    }
    return false;
  }

  public static byte[] encrypt(byte[] text, PublicKey key) {
    byte[] cipherText = null;
    try {
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      
      cipher.init(Cipher.ENCRYPT_MODE, key);

      cipherText = cipher.doFinal(text);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return cipherText;
  }

  public static String decrypt(byte[] text, PrivateKey key) throws UnsupportedEncodingException {
	  System.out.println("\n\t text==1=>"+text);
    byte[] dectyptedText = null;
    try {
      final Cipher cipher = Cipher.getInstance(ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      dectyptedText = cipher.doFinal(text);
	  System.out.println("\n\t dectyptedText=1==>"+new String(dectyptedText,"UTF-8"));

    } catch (Exception ex) {
      ex.printStackTrace();
    }

    return new String(dectyptedText,"UTF-8");
  }
  
  public static byte[] encryptJSON(Map<String,Object> inputData, PublicKey key) {
	    byte[] cipherText = null;
	    try {
	      final Cipher cipher = Cipher.getInstance(ALGORITHM);
	      cipher.init(Cipher.ENCRYPT_MODE, key);
	      cipherText = cipher.doFinal(inputData.toString().getBytes());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return cipherText;
	  }
  

}