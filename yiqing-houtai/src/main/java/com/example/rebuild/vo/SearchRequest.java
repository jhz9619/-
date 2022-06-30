package com.example.rebuild.vo;

import lombok.Data;

@Data
public class SearchRequest {

    private  Integer group;

    private String keyword;

    private String role;

    private Integer userId;

    private String major;

}
