package chksum.handlebars

import java.io.IOException
import java.util.Map
import java.net.URLEncoder

import com.github.jknack.handlebars.Options
import ratpack.handlebars.NamedHelper

import chksum.AssetService

import com.google.common.base.Strings
import com.google.common.net.UrlEscapers
import com.google.common.escape.Escaper

class AssetHelper implements NamedHelper<AssetService> {
  static final String NAME = "asset"

  String getName() {
    return NAME
  }

  CharSequence apply(final AssetService assetService, final Options options) throws IOException {
    String link = options.param(0)
    if (!link) {
      return null
    }
    // get link with checksum paramter
    try {
      link = encodeURIComponent(assetService.getAt(link))
    }
    catch (Exception ex) {
      ex.printStackTrace()
      throw new IOException(ex.getMessage())
    }
    return link
  }

  private String encodeURIComponent(String s) {
    Escaper escaper = UrlEscapers.urlFragmentEscaper()
    return escaper.escape(s)
  }
}
