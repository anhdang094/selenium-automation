package vn.betting.automation.infrastructure.response;

/**
 * @author anhdx
 */
public class DefaultResponse {

    protected int returncode;
    protected String returnmessage;

    public int getReturncode() {
        return returncode;
    }

    public void setReturncode(int returncode) {
        this.returncode = returncode;
    }

    public String getReturnmessage() {
        return returnmessage;
    }

    public void setReturnmessage(String returnmessage) {
        this.returnmessage = returnmessage;
    }
}
