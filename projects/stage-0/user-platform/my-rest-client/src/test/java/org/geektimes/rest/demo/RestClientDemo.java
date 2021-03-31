package org.geektimes.rest.demo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class RestClientDemo {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        // Get请求
        Response responseGet = client
                .target("https://www.baidu.com/")      // WebTarget
                .request() // Invocation.Builder
                .get();                                     //  Response

        String contentGet = responseGet.readEntity(String.class);
        System.out.println(contentGet);

        // Post请求
        String jsonBody = "{\"password\": \"****\",\"username\": \"aweikeji\"}";
        Entity<String> entity = Entity.json(jsonBody);
        Response responsePost = client.target("https://test.gateway.api.lerke.cn/service/user/token/web/v1/create")
            .request().post(entity);
        String contentPost = responsePost.readEntity(String.class);
        System.out.println(contentPost);

    }
}
