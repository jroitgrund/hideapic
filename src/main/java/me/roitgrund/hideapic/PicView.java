package me.roitgrund.hideapic;

import io.dropwizard.views.View;

public class PicView extends View {
    private String dataUri;

    public PicView(String dataUri) {
        super("pic.mustache");
        this.dataUri = dataUri;
    }

    public String getDataUri() {
        return dataUri;
    }
}
