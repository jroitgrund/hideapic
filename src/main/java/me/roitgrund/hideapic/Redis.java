package me.roitgrund.hideapic;

import static com.google.common.base.Preconditions.checkState;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import io.dropwizard.lifecycle.Managed;
import java.util.concurrent.CompletionStage;
import org.assertj.core.util.Preconditions;

public class Redis implements Managed {
  private final String redisHostUri;
  private RedisClient redisClient;
  private StatefulRedisConnection<String, String> connection;
  private RedisAsyncCommands<String, String> async;

  public Redis(String redisHostUri) {
    this.redisHostUri = redisHostUri;
  }

  public CompletionStage<Void> set(String key, String value) {
    return async
        .set(key, value)
        .thenAccept(
            simpleStringReply ->
                checkState(simpleStringReply.equals("OK"), "Unable to set key %s", key));
  }

  public CompletionStage<String> get(String key) {
    return async.get(key).thenApply(Preconditions::checkNotNull);
  }

  @Override
  public void start() throws Exception {
    redisClient = RedisClient.create(String.format("redis://%s", redisHostUri));
    connection = redisClient.connect();
    async = connection.async();
  }

  @Override
  public void stop() throws Exception {
    connection.close();
    redisClient.shutdown();
  }
}
