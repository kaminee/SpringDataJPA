package net.codejava.spring.model;

public class BlockMetaData {
	    private String blockId;
	    private String blockType;
	    private String status;// clear/ objection
	    private String originater;
	    private String originaterAuth;
	    private String prevBlockId;

		public String getBlockId() {
			return blockId;
		}

		public void setBlockId(String blockId) {
			this.blockId = blockId;
		}

		public String getBlockType() {
			return blockType;
		}

		public void setBlockType(String blockType) {
			this.blockType = blockType;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getOriginater() {
			return originater;
		}

		public void setOriginater(String originater) {
			this.originater = originater;
		}

		public String getOriginaterAuth() {
			return originaterAuth;
		}

		public void setOriginaterAuth(String originaterAuth) {
			this.originaterAuth = originaterAuth;
		}

		public String getPrevBlockId() {
			return prevBlockId;
		}

		public void setPrevBlockId(String prevBlockId) {
			this.prevBlockId = prevBlockId;
		}

		@Override
		public String toString() {
			return "{'blockId':'" + blockId + "', 'blockType':'" + blockType + "', 'status':'" + status + "', 'originater':'"
					+ originater + "', 'originaterAuth':'" + originaterAuth + "', 'prevBlockId':'"
					+ prevBlockId + "'}";
		}
	    
	    
}
