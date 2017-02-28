package me.roitgrund.hideapic;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PicPage {
  private final RemoteWebDriver webDriver;
  private final WebElement image;

  public PicPage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
    image = webDriver.findElementByTagName("img");
  }

  public WebElement getImage() {
    return image;
  }
}
