package net.codejava.spring.model;

import java.util.Map;

public class Block {
	public Map<String,Object>  blockMetaData;
	public Map<String,String> data; // encrypted format


		public Map<String, String> getData() {
			return data;
		}
		
		public void setData(Map<String, String> data) {
			this.data = data;
		}


		public Map<String, Object> getBlockMetaData() {
			return blockMetaData;
		}

		public void setBlockMetaData(Map<String, Object> blockMetaData) {
			this.blockMetaData = blockMetaData;
		}
	
		@Override
		public String toString() {
			return "{block={'blockMetaData':'" + blockMetaData.toString() + "', 'data':'" + data + "'}}";
		}
	    
	    
}
