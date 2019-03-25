package niipa.reloader;

@FunctionalInterface
public interface ReloadCondition {
  boolean shouldReload();
}
