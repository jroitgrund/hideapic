package me.roitgrund.hideapic;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class HideAPicApplication extends Application<HideAPicConfiguration> {

  public static void main(String[] args) throws Exception {
    new HideAPicApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<HideAPicConfiguration> bootstrap) {
    bootstrap.addBundle(new ViewBundle<>());
  }

  @Override
  public void run(HideAPicConfiguration configuration, Environment environment) throws Exception {
    Redis redis = new Redis(configuration.getRedisHostUri());
    environment.lifecycle().manage(redis);

    environment.jersey().register(new PicResource(new PicService(redis, new UuidService())));
  }
}
