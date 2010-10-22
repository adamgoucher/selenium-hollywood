package com.saucelabs.selenium;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
 
public class SauceSelenium extends DefaultSelenium {
    public SauceSelenium(String rcHost, int rcPort, String browserString, String baseURL) {
        super(rcHost, rcPort, browserString, baseURL);
    }
 
    public void showTitleCard(String message) {
        commandProcessor.doCommand("showTitleCard", new String[] {message,});
    }
    
    public void hideTitleCard() {
        commandProcessor.doCommand("removeTitleCard", new String[] {});
    }
}