package net.codejava.spring.model;

import java.util.LinkedList;

public class BlockChain {
	
		private String blockChainId;
	    LinkedList<Block> blockChainList;
		public String getBlockChainId() {
			return blockChainId;
		}
		public void setBlockChainId(String blockChainId) {
			this.blockChainId = blockChainId;
		}
		public LinkedList<Block> getBlockChainList() {
			return blockChainList;
		}
		public void setBlockChainList(LinkedList<Block> blockChainList) {
			this.blockChainList = blockChainList;
		}
	   
	    
	    
}
