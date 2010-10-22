package com.saucelabs.ondemand;

import java.io.InputStream;
import java.util.Properties;

public class ConnectionParameters {
  // hyphenated strings like access-key and browser-version need to be camel-case
  public String username;
  public String accessKey;
  public String os;
  private String browser;
  public String browserVersion;
  public String jobName;
  public String userExtensionsUrl;
  public String idleTimeout;
  
  private transient String propertiesFile = "/ondemand.properties";
  private transient Properties ondemandProperties = new Properties();

  public ConnectionParameters() throws Exception {
    InputStream is = this.getClass().getResourceAsStream(propertiesFile);
    ondemandProperties.load(is);
    this.username = ondemandProperties.getProperty("username");
    this.accessKey = ondemandProperties.getProperty("access-key");
    
  }
  
  // ondemand browser strings cant start with the *
  public void setBrowser(String browser) {
    if (browser.startsWith("*")) {
      this.browser = browser.substring(1);
    }
  }
  
  public String getBrowser() {
    return this.browser;
  }
}