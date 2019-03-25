package niipa.reloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

public class LinkerProvider {

  private Constructor<? extends Linker> linkerConstructor;
  private Object[] linkerConstructorArgs;

  public LinkerProvider() {
    this(defaultConstructor(), constructorDefaultArg());
  }

  public LinkerProvider(Constructor<? extends Linker> linkerConstructor, Object[] linkerConstructorArgs) {
    this.linkerConstructor = linkerConstructor;
    this.linkerConstructorArgs = linkerConstructorArgs;
  }

  public Linker newLinker() {
    try {
      return linkerConstructor.newInstance(linkerConstructorArgs);
    } catch(InstantiationException | IllegalAccessException | InvocationTargetException ex) {
      throw new LinkerCreationException(ex);
    }
  }

  private static Constructor<? extends Linker> defaultConstructor() {
    try{
      return LinkerImpl.class.getConstructor(URL[].class);
    } catch (NoSuchMethodException ex){
      throw new ExceptionInInitializerError(ex);
    }
  }

  private static URL[] constructorDefaultArg() {
    URL[] urls = new URL[1];
    urls[0] = ClassLoader.getSystemResource(System.getProperty("reloadLocation"));
    return urls;
  }
}
