package zielu.gittoolbox.metrics;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jmx.JmxReporter;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

class Jmx {
  private static final String DOMAIN = "zielu.gittoolbox";

  private Jmx() {
    throw new IllegalStateException();
  }

  public static JmxReporter report(MetricRegistry registry) {
    return JmxReporter.forRegistry(registry).inDomain(DOMAIN).build();
  }

  public static JmxReporter report(@NotNull Project project, MetricRegistry registry) {
    String projectName = project.getName().replaceAll("\\W", "");
    return JmxReporter.forRegistry(registry).inDomain(DOMAIN + "." + projectName).build();
  }
}
