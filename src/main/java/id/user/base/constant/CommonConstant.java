package id.user.base.constant;

public interface CommonConstant {

    enum RESPONSE_MSG {
        FAILED("Failed"), OK("Success");
        private String value;

        private RESPONSE_MSG(String val) {
            value = val;
        }

        public String getValue() {
            return value;
        }
    }

    enum RESPONSE_STATUS {
        FAILED(0), OK(1);
        private Integer value;

        private RESPONSE_STATUS(Integer val) {
            value = val;
        }

        public Integer getValue() {
            return value;
        }
    }
    
    enum STATUS_DATA {
    	ACTIVE(1), NON_ACTIVE(0);
    	private final int value;
    	
    	STATUS_DATA(int levelCode) {
    		this.value = levelCode;
    	}
    	
    	public int getValue() {
    		return this.value;
    	}
    }
}
