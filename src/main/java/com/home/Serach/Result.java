package com.home.Serach;

/**
 * @param <T>
 * state 状态码
 * message:消息
 * object:返回对象
 */
public class Result<T>{
    private int state;
    private String message;
    private T object;
    public Result() {
    }

    public Result(ResultState successResponse, String insert_success) {
        this.state=successResponse.state;
        this.message=insert_success;
    }


    public Result(ResultState successResponse, String insert_success, T city) {
      this.state=successResponse.state;
      this.message=insert_success;
      object=city;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }


    /**
     * 状态码枚举
     */
    public enum ResultState{
        SUCCESS_RESPONSE(200),ERROR_RESPONSE(500),MISS(400),SOMETHING_WRONG(403);
        public int state;

        ResultState(int state) {
            this.state = state;
        }
    }


}
