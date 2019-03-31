package niipa.reloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class LinkerProvider {

  private Constructor<? extends Linker> linkerConstructor;
  private Object[] linkerConstructorArgs;

  public LinkerProvider(Constructor<? extends Linker> linkerConstructor,
      Object[] linkerConstructorArgs) {
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
}
