package ru.stqa.pft.mantis.appmanager;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession(ApplicationManager app) {
        this.app = app;
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("username", username));
        parameters.add(new BasicNameValuePair("password", password));
        parameters.add(new BasicNameValuePair("secure_session", "on"));
        parameters.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(parameters));
        CloseableHttpResponse response = httpclient.execute(post);
        String body = getTextFrom(response);
        System.out.println("<a href=\"/mantisbt-2.25.2/account_page.php\">%s</a>");
        return body.contains(String.format("<a href=\"/mantisbt-2.25.2/account_page.php\">%s</a>", username));
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean isLoggedInAs (String username) throws IOException {
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        CloseableHttpResponse response = httpclient.execute(get);
        String body = getTextFrom(response);
        return body.contains(String.format("<a href=\"/mantisbt-2.25.2/account_page.php\">%s</a>", username));
    }
}