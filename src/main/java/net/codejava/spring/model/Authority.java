package net.codejava.spring.model;

public class Authority {

	    private Integer authId;
	    private String authName;
	    private String authType;
	    private boolean miner;
	
		public Integer getAuthId() {
			return authId;
		}


		public void setAuthId(Integer authId) {
			this.authId = authId;
		}


		public String getAuthName() {
			return authName;
		}


		public void setAuthName(String authName) {
			this.authName = authName;
		}


		public String getAuthType() {
			return authType;
		}


		public void setAuthType(String authType) {
			this.authType = authType;
		}


		public boolean isMiner() {
			return miner;
		}


		public void setMiner(boolean miner) {
			this.miner = miner;
		}

	    
}
