package com.example.writenow.Login;

public class UserAccount {

    private String name;
    private String ID;
    private String PW;
    private String birth1;
    private String birth2;
    private String birth3;

    private String idToken;

    public UserAccount(){}

    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    public String getIdToken(){return idToken;}

    public void setIdToken(String idToken){this.idToken = idToken;}

    public String getID(){return ID;}

    public void setID(String ID){this.ID = ID;}

    public String getPW(){return PW;}

    public void setPW(String PW){this.PW = PW;}

    public String getBirth1(){return birth1;}

    public void setBirth1(String birth1){this.birth1 = birth1;}

    public String getBirth2(){return birth2;}

    public void setBirth2(String birth2){this.birth2 = birth2;}

    public String getBirth3(){return birth3;}

    public void setBirth3(String birth3){this.birth3 = birth3;}




}