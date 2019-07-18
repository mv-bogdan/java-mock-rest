package com.example.java_mock_rest.models;

public class Credentials {
    private String email;
    private String password;


    public Credentials(String password, String email) {
        this.password = password;
        this.email = email;
    }

}
