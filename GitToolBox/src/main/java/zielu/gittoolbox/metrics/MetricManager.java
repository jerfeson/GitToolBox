package zielu.gittoolbox.metrics;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.jetbrains.annotations.NotNull;

class MetricManager implements Metrics {
  private final MetricRegistry registry = new MetricRegistry();
  private final ConcurrentMap<String, Gauge> gauges = new ConcurrentHashMap<>();

  MetricRegistry getRegistry() {
    return registry;
  }

  private String name(@NotNull String simpleName) {
    return MetricRegistry.name(simpleName);
  }

  @Override
  public Timer timer(@NotNull String simpleName) {
    return registry.timer(name(simpleName));
  }

  @Override
  public Counter counter(@NotNull String simpleName) {
    return registry.counter(name(simpleName));
  }

  @Override
  public <T> Gauge<T> gauge(@NotNull String simpleName, Gauge<T> gauge) {
    String name = name(simpleName);
    return gauges.computeIfAbsent(name, gaugeName -> registry.register(name, gauge));
  }
}
