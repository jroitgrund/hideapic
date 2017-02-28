package me.roitgrund.hideapic;

import java.util.concurrent.CompletionStage;

public class PicService {
  private final Redis redis;
  private final UuidService uuidService;

  public PicService(Redis redis, UuidService uuidService) {
    this.redis = redis;
    this.uuidService = uuidService;
  }

  public CompletionStage<Pic> getPic(String key) {
    return redis.get(key).thenApply(ImmutablePic::of);
  }

  public CompletionStage<String> savePic(Pic pic) {
    String uuid = uuidService.getUuid();
    return redis.set(uuid, pic.dataUri()).thenApply(ignored -> uuid);
  }
}
