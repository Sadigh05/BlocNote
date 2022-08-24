package com.example.myblocnote;

class Users {

    private long IDuser;
    private String user;
    private String pass;
    private String cmfpass;

    Users(){}
    Users(String user,String pass, String cmfpass){
        this.user  = user;
        this.pass  = pass;
        this.cmfpass  =  cmfpass;

    }

    Users(long IDuser,String user,String pass, String cmfpass){
        this.user  = user;
        this.pass  = pass;
        this.cmfpass  =  cmfpass;
        this.IDuser   = IDuser;

    }
    public long getIDuser() {
        return IDuser;
    }

    public void setIDuser(long IDuser) {
        this.IDuser = IDuser;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCmfpass() {
        return cmfpass;
    }

    public void setCmfpass(String cmfpass) {
        this.cmfpass = cmfpass;
    }
}
