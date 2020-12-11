package cn.edu.bupt.ch10_1.controller;

public class User {
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String qq;
    private String id;


    public User(String name, String phone, String mail, String address, String qq, String id) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.qq = qq;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
