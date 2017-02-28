package me.roitgrund.hideapic;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.immutables.value.Value;

@Value.Immutable
public interface Pic {
  @JsonCreator
  @Value.Parameter
  String dataUri();
}
