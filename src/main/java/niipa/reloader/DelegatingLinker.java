package niipa.reloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * Delegator that provides a default constructor to {@link URLClassLoader}
 */
public class DelegatingLinker implements Linker {

  ClassloaderHack classloaderHack;

  public DelegatingLinker() {
    URL[] urls = new URL[1];
    urls[0] = ClassLoader.getSystemResource(System.getProperty("reloadLocation"));

    classloaderHack = new ClassloaderHack(urls);
  }

  @Override
  public Class<?> link(String fullyQualifiedName) throws ClassNotFoundException {
    return classloaderHack.loadClass(fullyQualifiedName, true);
  }

  /**
   * Makes {@link URLClassLoader#loadClass(String, boolean)} usable by
   * enclosing class.
   */
  class ClassloaderHack extends URLClassLoader {

    public ClassloaderHack(URL[] urls) {
      super(urls);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
      return super.loadClass(name, resolve);
    }
  }
}
