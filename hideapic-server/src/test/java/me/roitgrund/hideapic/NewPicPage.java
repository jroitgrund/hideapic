package me.roitgrund.hideapic;

import java.net.URISyntaxException;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NewPicPage {

  private final RemoteWebDriver webDriver;

  private NewPicPage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public static NewPicPage navigateTo(RemoteWebDriver webDriver) throws URISyntaxException {
    UriUtil.INSTANCE.navigateTo(webDriver, "/");
    return new NewPicPage(webDriver);
  }

  public PicPage submitPic(String dataUri) {
    webDriver.findElementByCssSelector("input[type=text]").sendKeys(dataUri);
    webDriver.findElementByCssSelector("input[type=submit]").click();
    return new PicPage(webDriver);
  }
}
