package niipa.reloader;

import java.lang.reflect.Constructor;

/**
 * {@inheritDoc}
 */
public class ClassReloaderImpl implements ClassReloader {

  private LinkerProvider linkerProvider;
  private Linker disposableLinker;
  private final ReloadCondition reloadCondition;

  public ClassReloaderImpl(
      LinkerProvider linkerProvider,
      ReloadCondition reloadCondition
  ) {
    this.linkerProvider = linkerProvider;
    this.reloadCondition = reloadCondition;
  }

  public ReloadCondition getReloadCondition() {
    return reloadCondition;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized <T> T newInstance(
      String fullyQualifiedName,
      Class<T> returnType,
      Class<?>[] constructorParameters,
      Object[] arguments
  ) throws ReflectiveOperationException, ClassCastException {

    if (disposableLinker == null || reloadCondition.shouldReload()) {
      rebuildLinker();
    }
    Class<?> clazz = disposableLinker.link(fullyQualifiedName);
    Constructor<?> constructor = clazz.getConstructor(constructorParameters);

    return returnType.cast(constructor.newInstance(arguments));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized void rebuildLinker() {
    disposableLinker = linkerProvider.newLinker();
  }
}
