package zielu.gittoolbox.cache;

import com.google.common.base.Preconditions;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;
import git4idea.repo.GitRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface VirtualFileRepoCache extends DirMappingAware {
  Topic<VirtualFileCacheListener> CACHE_CHANGE = Topic.create("File cache change",
      VirtualFileCacheListener.class);

  @NotNull
  static VirtualFileRepoCache getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, VirtualFileRepoCache.class);
  }

  @Nullable
  GitRepository getRepoForRoot(@NotNull VirtualFile root);

  @Nullable
  GitRepository getRepoForDir(@NotNull VirtualFile dir);

  @Nullable
  default GitRepository getRepoForFile(@NotNull VirtualFile file) {
    Preconditions.checkArgument(!file.isDirectory(), "%s is not file", file);
    VirtualFile parent = file.getParent();
    if (parent != null && parent.isDirectory()) {
      return getRepoForDir(parent);
    }
    return null;
  }

  default boolean isUnderGitRoot(@NotNull VirtualFile file) {
    return (file.isDirectory() ? getRepoForDir(file) : getRepoForFile(file)) != null;
  }
}
