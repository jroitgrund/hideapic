package me.roitgrund.hideapic;

import io.dropwizard.views.View;

public class PicView extends View {
  private String dataUri;

  public PicView(Pic pic) {
    super("pic.mustache");
    this.dataUri = pic.dataUri();
  }

  public String getDataUri() {
    return dataUri;
  }
}
