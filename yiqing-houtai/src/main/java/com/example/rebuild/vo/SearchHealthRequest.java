package com.example.rebuild.vo;

import lombok.Data;

@Data
public class SearchHealthRequest extends SearchRequest{

    private Integer fever;

    private Integer cough;

    private Double temperature;

}