package niipa.reloader;

/**
 * Constructs new instances of a class with updatable class definitions.
 */
public interface ClassReloader {

  /**
   * Constructs a new instance of type T, refreshing the class definition first
   * if and only if a registered {@link ReloadCondition} returns true.
   *
   * @param fullyQualifiedName name of the implementation to instantiate
   * @param type return type of the implementation
   * @param paramList params of constructor to use
   * @param args arguments to the constructor chosen
   * @return an instance whose definition may be new
   * @throws ReflectiveOperationException if an issue occurs trying to load or
   * link the given class name.
   * @throws ClassCastException if the definition of the loaded class could not
   * be cast into the given type.
   */
  <T> T newInstance(String fullyQualifiedName, Class<T> type, Class<?>[] paramList, Object[] args)
      throws ReflectiveOperationException, ClassCastException;

  /**
   * Reload class definitions.
   */
  void rebuildLinker();
}
