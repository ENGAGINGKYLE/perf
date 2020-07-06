package mntm.top.offer.test;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangpeng
 */
@Data
public class ErrorCode implements Serializable {

    private int code;

    private String message;

    private boolean success = false;

    private boolean alert = true;

    public ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode(int code) {
        this.code = code;
    }

    public ErrorCode setMessage(String message) {
        this.message = message;
        return this;
    }


}
