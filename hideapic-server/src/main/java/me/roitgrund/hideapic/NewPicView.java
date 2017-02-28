package me.roitgrund.hideapic;

import io.dropwizard.views.View;

public class NewPicView extends View {
  private final String postUri;

  public NewPicView(String postUri) {
    super("new-pic.mustache");
    this.postUri = postUri;
  }
}
