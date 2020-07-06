package mntm.top.offer.test;


/**
 * @program: sdk-platform
 * @description:
 * @author: yingjun
 * @create: 2020-01-02 10:41
 **/
public class ExcelException extends Exception{

    private final ErrorCode errorCode;

    public ExcelException(int errorCode, Object errorMessage) {

        this.errorCode = new ErrorCode(errorCode, errorMessage == null ? null : errorMessage.toString());
    }

    public ExcelException(Object errorMessage) {

        this.errorCode = new ErrorCode(BaseErrorCode.SYSTEM_EXCEPTION.getCode(), errorMessage == null ? null : errorMessage.toString());
    }

}
