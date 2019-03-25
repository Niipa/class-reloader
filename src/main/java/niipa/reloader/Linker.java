package niipa.reloader;

/**
 * Linkers make the definition of a class definition available to the JVM.
 */
public interface Linker {

  /**
   * Links a loaded class definition to the JVM. {@link ClassLoader} provides
   * a native way to actually perform linking via a protected method.
   * <p>
   * Definitions per classloader can only be linked once, therefore a new
   * classloader must be instantiated for every new definition of the same
   * class.
   *
   * @param fullyQualifiedName name of the class to be retrieved
   * @return class definition for the given name
   * @throws ClassNotFoundException the linker could not successfully load the
   * class
   */
  Class<?> link(String fullyQualifiedName) throws ClassNotFoundException;
}
