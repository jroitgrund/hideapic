package me.roitgrund.hideapic;

import io.dropwizard.Configuration;

public class HideAPicConfiguration extends Configuration {
  private String redisHostUri;

  public String getRedisHostUri() {
    return redisHostUri;
  }

  public void setRedisHostUri(String redisHostUri) {
    this.redisHostUri = redisHostUri;
  }
}
