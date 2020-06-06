package com.example.myapplication.MKCorePack;


public class Response {
    private boolean status;
    private boolean error;
    private String text;

    public Response() {
        status = false;
        error = false;
        text = "";
    }
    public Response(boolean status) {
        this.status = status;
    }
    public Response(boolean status, String text) {
        Init(status, text, false);
    }
    public Response(boolean status, String text, boolean error){
        Init(status, text, error);
    }
    public Response(boolean status, StringBuffer text, boolean error){
        Init(status, text.toString(), error);
    }
    private void Init(boolean status, String text, boolean error){
        this.status = status;
        this.text = text;
        this.error = error;
    }

    public boolean IsSuccess(){
        return status;
    }
    public boolean IsError(){
        return error;
    }
    public String Text(){
        return text;
    }
}
