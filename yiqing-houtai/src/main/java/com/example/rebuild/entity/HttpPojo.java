package com.example.rebuild.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpPojo implements Serializable {

    private static final long serialVersionUID = -2019661705306735496L;

    private String httpIp;
    private String httpHost;
    private String httpAccept;
    private String httpConnection;
    private String httpUserAgent;
    private String httpReferer;
    private String httpOrigin;
    private String httpCookie;
    private String httpContentType;

}
