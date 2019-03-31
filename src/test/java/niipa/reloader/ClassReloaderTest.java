package niipa.reloader;

import java.net.URI;
import java.net.URL;
import org.junit.Assert;
import org.junit.Test;

public class ClassReloaderTest {

  @Test
  public void shouldReloadClassDefinition() throws Exception {

    System.getProperties().put("reloadLocation", "version1/");
    ClassReloader reloader = new ClassReloaderImpl(
        new LinkerProvider(DelegatingLinker.class.getConstructor(),
            new URL[]{new URI("version1/").toURL()}), () -> true);
    SharedTestInterface testObject = reloader.newInstance("niipa.reloader.TestClass",
        SharedTestInterface.class, null, null);

    Assert.assertEquals(1, testObject.intMethodWhoseDefinitionChanges());
    Assert.assertEquals(1L, testObject.longMethodWhoseDefinitionChanges());
    testObject.voidMethodWhoseDefinitionChanges();

    System.getProperties().put("reloadLocation", "version2/");
    SharedTestInterface testObjectWithDifferentDefinition =
        reloader.newInstance("niipa.reloader.TestClass", SharedTestInterface.class,
            null, null);

    Assert.assertEquals(2, testObjectWithDifferentDefinition.intMethodWhoseDefinitionChanges());
    Assert.assertEquals(2L, testObjectWithDifferentDefinition.longMethodWhoseDefinitionChanges());
    testObjectWithDifferentDefinition.voidMethodWhoseDefinitionChanges();
  }

  @Test
  public void shouldBeAbleToMakeInstancesFrom() {

  }

}
