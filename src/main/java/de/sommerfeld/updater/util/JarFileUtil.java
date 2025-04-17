package de.sommerfeld.updater.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * Utility class to retrieve the path to the JAR file of the current class.
 */
public class JarFileUtil {

  private JarFileUtil() {}

  /**
   * Returns the path to the JAR file of the current class.
   */
  public static File getJarFile() throws IOException {
    ProtectionDomain protectionDomain = JarFileUtil.class.getProtectionDomain();
    CodeSource codeSource = protectionDomain.getCodeSource();
    if (codeSource == null) {
      throw new IOException("CodeSource is null. Is the application running from a Jar file?");
    }
    URL location = codeSource.getLocation();

    String decodedPath;
    try {
      decodedPath = URLDecoder.decode(location.getPath(), StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      throw new IOException("UTF-8 is not supported.", e);
    }

    return new File(decodedPath);
  }
}
