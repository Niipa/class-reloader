package niipa.reloader;

import java.net.URL;
import java.net.URLClassLoader;

public class LinkerImpl extends URLClassLoader implements Linker {

  public LinkerImpl(URL[] urls) {
    super(urls);
  }

  @Override
  public Class<?> link(String fullyQualifiedName) throws ClassNotFoundException {
    return super.loadClass(fullyQualifiedName, true);
  }
}
