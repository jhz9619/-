package com.example.rebuild.vo;

import lombok.Data;

@Data
public class PasswordVO {

    private Integer id;

    private String oldPass;

    private String newPass;

    private String rePass;

}