package me.roitgrund.hideapic;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import io.dropwizard.lifecycle.Managed;

import java.util.concurrent.CompletionStage;

public class Redis implements Managed {
    private final String redisHostUri;
    private RedisClient redisClient;
    private StatefulRedisConnection<String, String> connection;
    private RedisAsyncCommands<String, String> async;

    public Redis(String redisHostUri) {
        this.redisHostUri = redisHostUri;
    }

    public CompletionStage<String> set(String key, String value) {
        return async.set(key, value);
    }

    public CompletionStage<String> get(String key) {
        return async.get(key);
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
