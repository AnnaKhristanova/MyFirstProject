package ru.stqa.pft.mantis.appmanager;


import org.hibernate.SessionBuilder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PanelHelper extends HelperBase {


    private SessionBuilder<SessionBuilder> sessionFactory;

    public PanelHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.xpath("//input[@name='username']"), username);
        click(By.xpath("//input[@class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
        type(By.xpath("//input[@name='password']"), password);
        click(By.xpath("//input[@class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));
    }

    public void getUser() {
        click(By.xpath("//i[@class='fa fa-gears menu-icon']"));
        wd.findElement(By.cssSelector(String.format("a[href='/mantisbt-2.25.2/manage_user_page.php']"))).click();
        List<WebElement> users = wd.findElements(By.cssSelector(".widget-main.no-padding .table-bordered td:first-child"));
        List<WebElement> cells = wd.findElements(By.cssSelector("tr > td:nth-child(4)"));
        for (WebElement cell : cells){
            String status = cell.getText();
            if (status.contains("reporter")) {
                wd.findElement(By.cssSelector(String.format("tr > td > a"))).click();
                return;
            }
        }
    }

    public String getEmail() {

        String email = wd.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        return email;
    }

    public String getRealname() {
        String realname = wd.findElement(By.xpath("//input[@name='realname']")).getAttribute("value");
         return realname;
    }

    public void resetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
    }


    public void finish(String confirmationLink) {
        wd.get(confirmationLink);
    }

    public void changePassword(String realname, String password, String password_confirm) {
        type(By.xpath("//input[@name='realname']"), realname);
        type(By.xpath("//input[@name='password']"), password);
        type(By.xpath("//input[@name='password_confirm']"), password_confirm);
        click(By.xpath("//button[@class='width-100 width-40 pull-right btn btn-success btn-inverse bigger-110']"));
    }


    public String getUsername() {
        String username = wd.findElement(By.xpath("//input[@name='username']")).getAttribute("value");
        return username;
    }
}