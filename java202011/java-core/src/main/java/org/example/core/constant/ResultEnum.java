package org.example.core.constant;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/12/12 20:50
 */
public enum ResultEnum {
    /**
     * 成功
     */
    Success(0, "success"),
    /**
     * 失败
     */
    Fail(1, "fail"),
    /**
     * 超时
     */
    Timeout(2,"timeout");

    ResultEnum(int result, String message) {
        this.result =result;
        this.message = message;
    }

    private int result;
    private String message;

    public int getResult() {
        return result;
    }
    public String getMessage() {
        return message;
    }

}
