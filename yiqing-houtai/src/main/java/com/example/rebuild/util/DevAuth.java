package com.example.rebuild.util;

import com.example.rebuild.util.aip.*;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class DevAuth {

    public static JSONObject oauth(String apiKey, String secretKey, AipClientConfiguration config) {
        try {
            AipRequest request = new AipRequest();
            request.setUri(new URI(AipClientConst.OAUTH_URL));
            request.addBody("grant_type", "client_credentials");
            request.addBody("client_id", apiKey);
            request.addBody("client_secret", secretKey);
            request.setConfig(config);
            int statusCode = 500;
            AipResponse response = null;
            // add retry
            int cnt = 0;
            while (statusCode == 500 && cnt < 3) {
                response = AipHttpClient.post(request);
                statusCode = response.getStatus();
                cnt++;
            }
            String res = response.getBodyStr();
            if (res != null && !res.equals("")) {
                return new JSONObject(res);
            } else {
                return Util.getGeneralError(statusCode, "Server response code: " + statusCode);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return Util.getGeneralError(-1, "unknown error");
    }

}
