package lv.div.exposedservice.ws;

import java.util.Date;

/**
 * Generic web service response object, if needed
 */
public class GenericResponse {

    private Integer result = 200; // HTTP OK
    private String value;
    private Date responseTime = new Date();

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }
}
