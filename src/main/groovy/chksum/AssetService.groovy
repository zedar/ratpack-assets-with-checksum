package chksum

import java.util.HashMap
import java.util.Map
import com.google.common.collect.ImmutableMap

import ratpack.file.FileSystemChecksumService

class AssetService extends HashMap<String, String> {
  private final FileSystemChecksumService checksumService

  AssetService(FileSystemChecksumService checksumService) {
    super()
    this.checksumService = checksumService
  }

  Map<String, Object> convertTo(final Map<String, Object> model) {
    if (model == null) {
      return new ImmutableMap.Builder<String, Object>()
                    .put("assets", this)
                    .build()
    }
    else {
      return new ImmutableMap.Builder<String, Object>()
                    .put("assets", this)
                    .putAll(model)
                    .build()
    }
  }

  String getAt(final String path) {
    String[] parts = path.split("\\?", 2)
    String p = "/${parts[0]}?chksum=" + checksumService.checksum(parts[0]) ?: ""
    if (parts.length > 1) {
      p += "&${parts[1]}"
    }
    return p
  }

  @Override
  String get(final Object key) {
    try {
      return getAt(key)
    }
    catch(Exception ex) {
      return null
    }
  }
}
