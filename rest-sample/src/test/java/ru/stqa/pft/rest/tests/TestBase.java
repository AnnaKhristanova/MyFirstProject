package ru.stqa.pft.rest.tests;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Test;
import ru.stqa.pft.rest.appmanager.ApplicationManager;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;


public class TestBase {

    protected static final ApplicationManager app

            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    boolean isIssueOpen(int issueId) throws IOException {
        String json= getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/"+issueId+".json")).returnContent().asString();
        JsonElement parsed=new JsonParser().parse(json);
        JsonElement  issuesFromJson =parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson( issuesFromJson, new TypeToken<Set<Issue>>(){}.getType());
        Issue issue = issues.iterator().next();
        if (issue.getState_name().equals("Open")) {
            return true;
        } else {return false;}
    }

    private Executor getExecutor() {
        return Executor.newInstance()
                .auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
