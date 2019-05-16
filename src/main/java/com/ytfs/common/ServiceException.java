package com.ytfs.common;

import com.ytfs.service.packet.ErrorMessage;

public class ServiceException extends Exception {

    /**
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;

    public ServiceException() {
    }

    public ServiceException(int code) {
        super(Integer.toString(code));
        this.errorCode = code;
    }

    public ServiceException(int code, String msg) {
        super(msg);
        this.errorCode = code;
    }

    public ServiceException(ErrorMessage err) {
        super(err.getMsg());
        this.errorCode = err.getCode();
    }

    public ErrorMessage toErrMessage() {
        ErrorMessage err = new ErrorMessage();
        err.setCode(errorCode);
        err.setMsg(this.getMessage());
        return err;
    }

}
