package net.codejava.spring;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import net.codejava.spring.model.Block;
import net.codejava.spring.model.BlockChain;
import net.codejava.spring.model.BlockMetaData;
import net.codejava.util.EncryptionUtil;

@Controller
@RequestMapping(value = "/block-service")
public class BlockController {
	BlockChain blockChain=new BlockChain();
	 
	 @ResponseBody
	 @RequestMapping(value = "/block", method = RequestMethod.POST)
		public ResponseEntity<Map<String,byte[]>> createBlock(@RequestBody Block block, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {
		
		 BlockMetaData blockMetaData=new BlockMetaData();
		 blockMetaData.setBlockId(block.getBlockMetaData().get("blockId").toString());
		 blockMetaData.setBlockType(block.getBlockMetaData().get("blockType").toString());
		 blockMetaData.setStatus(block.getBlockMetaData().get("status").toString());
		 blockMetaData.setOriginater(block.getBlockMetaData().get("originater").toString());
		 blockMetaData.setOriginaterAuth(block.getBlockMetaData().get("originaterAuth").toString());

		 	System.out.println(blockMetaData);
			String message = "New Block " + blockMetaData.toString()+ " was successfully created."+block.getData();
			System.out.println(message);
			EncryptionUtil.generateKey();
			

			byte[] encryptedData=EncryptionUtil.textToBeEncrypted(block.getData().toString().getBytes("UTF-8"));
			System.out.println("\n\t encrypteddata-"+encryptedData);
			block.getData().put("data", encryptedData.toString());
			
			byte[] encryptedBlock=EncryptionUtil.textToBeEncrypted(blockMetaData.toString().getBytes());
			
			System.out.println("\n\t encrypted blcok --"+encryptedBlock);

			Map<String,byte[]> output=new HashMap<String, byte[]>();
			output.put("blockMetaData", encryptedBlock);
			output.put("data", encryptedData);
//			HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<Map<String,byte[]>>(output, HttpStatus.CREATED);
		}
	 
	 
	 @RequestMapping(value = "/blockchain", method = RequestMethod.POST)
		public ResponseEntity<BlockChain> addBlockToChain(@RequestBody Block block, UriComponentsBuilder ucBuilder, final RedirectAttributes redirectAttributes) {
		 LinkedList<Block> blckList=new LinkedList<Block>();
			String message = "New Block addec tp blockchain " + block+ " was successfully created.";
			System.out.println(message);
			  final byte[] authBytes = block.getData().toString().getBytes();
				System.out.println("authBytes==>"+authBytes);

			String encryptedData=EncryptionUtil.textToBeDecrypted(authBytes);
			System.out.println("encryptedData==>"+encryptedData);

			blckList.add(block);
			blockChain.setBlockChainList(blckList);

//	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<BlockChain>(blockChain, HttpStatus.CREATED);
		}
	 

}
