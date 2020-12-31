package org.example.core.protocol;

import org.example.core.constant.ResultEnum;

/**
 * @author tjstj
 * @description TODO
 * @date 2020/11/21 23:04
 */
public class CommonResult {

    private int result = ResultEnum.Success.getResult();
    private long timestamp;
    private String  command;
    private String message = ResultEnum.Success.getMessage();
    private CommonBean param;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CommonBean getParam() {
        return param;
    }

    public void setParam(CommonBean param) {
        this.param = param;
    }
}
