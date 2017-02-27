package me.roitgrund.hideapic;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HideAPicApplication extends Application<HideAPicConfiguration> {

    public static void main(String[] args) throws Exception {
        new HideAPicApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<HideAPicConfiguration> bootstrap) {

    }

    @Override
    public void run(HideAPicConfiguration configuration, Environment environment) throws Exception {

    }
}
