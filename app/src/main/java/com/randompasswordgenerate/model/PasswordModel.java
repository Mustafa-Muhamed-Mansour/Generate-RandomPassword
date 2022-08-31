package com.randompasswordgenerate.model;

public class PasswordModel
{

//    private String id;
    private String randomKey;
    private String password;
    private String passwordTitle;

    public PasswordModel()
    {
    }

//    public PasswordModel(String id, String randomKey, String password, String passwordTitle)
//    {
//        this.id = id;
//        this.randomKey = randomKey;
//        this.password = password;
//        this.passwordTitle = passwordTitle;
//    }
//
//    public String getId() {
//        return id;
//    }


    public PasswordModel(String randomKey, String password, String passwordTitle)
    {
        this.randomKey = randomKey;
        this.password = password;
        this.passwordTitle = passwordTitle;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordTitle() {
        return passwordTitle;
    }
}
