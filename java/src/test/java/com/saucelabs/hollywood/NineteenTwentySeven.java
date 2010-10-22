package com.saucelabs.hollywood;

import java.io.InputStream;
import java.util.Properties;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;

import com.thoughtworks.selenium.Selenium;
import java.lang.reflect.Method;

import com.saucelabs.ondemand.*;
import com.saucelabs.selenium.*;

@RunWith(Parameterized.class)
public class NineteenTwentySeven {
    
    private String json;
    private String browser;
    private String browserVersion;
    private String titleCard;
    private String os;

    public static Properties browserProps = new Properties();
    
    Selenium se;
    Gson gson;
    
    public NineteenTwentySeven(String os, String browser, String version) throws Exception
    {
        this.browser = browser;
        this.browserVersion = version;
        this.os = os;
        this.titleCard="http://adam.goucher.ca/user-extensions/titlecard.js";

        gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();
        ConnectionParameters od = new ConnectionParameters();
        od.setBrowser(this.browser);
        od.browserVersion = this.browserVersion;
        od.userExtensionsUrl = this.titleCard;
        od.os = this.os;
        this.json = gson.toJson(od);
    }
        
    @Parameters
    public static LinkedList browsersStrings() throws Exception
    { 
      LinkedList browsers = new LinkedList();

      InputStream is = NineteenTwentySeven.class.getResourceAsStream("/browser.properties");
      browserProps.load(is);
      
      String[] rawBrowserStrings = browserProps.getProperty("browsers").split(",");
      for (String rawBrowserString : rawBrowserStrings) {
          String[] browserParts = rawBrowserString.split(";");
          browsers.add(new String[] { browserParts[0], browserParts[1], browserParts[2] });
      }
      return browsers;
    }
    
    @Before
    public void setUp() throws Exception {
        se = new SauceSelenium("ondemand.saucelabs.com", 4444,  this.json , "http://saucelabs.com");

        se.start();
        se.setTimeout("30000");
        se.windowMaximize();
    }

    @After
    public void tearDown() throws Exception {
        se.stop();
    }
    
    @Test
    public void titlecard() {
        se.open("/");
        
        ((SauceSelenium)se).showTitleCard("Welcome to 1927!");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {}
        ((SauceSelenium)se).hideTitleCard();
    }
}
