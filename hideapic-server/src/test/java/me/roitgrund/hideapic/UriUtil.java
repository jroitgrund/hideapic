package me.roitgrund.hideapic;

import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.WebDriver;

public enum UriUtil {
  INSTANCE;

  public void navigateTo(WebDriver webDriver, String relativeUrl) throws URISyntaxException {
    webDriver.get(new URI(webDriver.getCurrentUrl()).resolve(relativeUrl).toString());
  }
}
