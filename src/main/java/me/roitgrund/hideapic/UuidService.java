package me.roitgrund.hideapic;

import com.google.common.io.BaseEncoding;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class UuidService {
    public String getUuid() {
        return BaseEncoding.base64().encode(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
    }
}
