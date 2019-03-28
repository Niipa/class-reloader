package niipa.reloader;

public interface SharedTestInterface {
  int intMethodWhoseDefinitionChanges();
  long longMethodWhoseDefinitionChanges();
  void voidMethodWhoseDefinitionChanges();
}
