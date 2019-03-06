package com.yang.domain;

public class User {
    private int id;
    private String account;
    private String password;
    private int permission;
    private String nickname;
    public User(){}

    public User(String account, String password, String nickname, int permission) {
        this.account = account;
        this.password = password;
        this.permission = permission;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", permission=" + permission +
                ", nickname=" + nickname +
                '}';
    }
}
