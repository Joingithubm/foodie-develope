package com.athome.restful;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/6/28 17:02
 * @Version 1.0
 */
public class CommonResult<T> {
    private Integer status;
    private String message;
    private T data;

    public CommonResult(ResultCode code) {
        this.status = code.code();
        this.message = code.message();
    }

    public CommonResult(T data){
        this.status= ResultCode.SUCCESS.code();
        this.message = ResultCode.SUCCESS.message();
        this.data = data;
    }

    public CommonResult(ResultCode code, T data){
        this.status = code.code();
        this.message = code.message();
        this.data = data;
    }

    public static <T>CommonResult success(){
        return new CommonResult(ResultCode.SUCCESS);
    }

    public static <T>CommonResult success(T data){
        return new CommonResult(data);
    }

    public static CommonResult error(ResultCode resultCode){
        return new CommonResult(resultCode);
    }

    public static <T>CommonResult<T> error(ResultCode code, T data){
        return   new CommonResult<T>(code,data);
    }





    public Integer getResultCode() {
        return status;
    }

    public void setResultCode(Integer resultCode) {
        this.status = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
