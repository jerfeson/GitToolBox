package zielu.gittoolbox.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.Transient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zielu.gittoolbox.ui.StatusPresenter;
import zielu.gittoolbox.ui.StatusPresenters;
import zielu.gittoolbox.ui.update.DefaultUpdateProjectAction;

/**
 * Superseded by {@link GitToolBoxConfig2}.
 * @deprecated will be removed in release for 2018.3
 */
@State(
    name = "GitToolBoxAppSettings",
    storages = @Storage("git_toolbox.xml")
)
@Deprecated
public class GitToolBoxConfig implements PersistentStateComponent<GitToolBoxConfig> {
  public String presentationMode = StatusPresenters.arrows.key();
  public boolean behindTracker = true;
  public boolean showStatusWidget = true;
  public boolean showProjectViewStatus = true;
  public boolean showProjectViewLocationPath = true;
  public boolean showProjectViewStatusBeforeLocation = false;
  public boolean showProjectViewHeadTags = true;
  public String projectViewStatusColorHex = ColorUtil.toHex(UIUtil.getInactiveTextColor());
  public boolean projectViewStatusCustomColor = false;
  public boolean projectViewStatusBold = false;
  public boolean projectViewStatusItalic = false;
  public String updateProjectActionId = DefaultUpdateProjectAction.ID;

  public static GitToolBoxConfig getInstance() {
    return ServiceManager.getService(GitToolBoxConfig.class);
  }

  @Transient
  public StatusPresenter getPresenter() {
    return StatusPresenters.forKey(presentationMode);
  }

  public void setPresenter(StatusPresenter presenter) {
    presentationMode = presenter.key();
  }

  public boolean isPresenterChanged(StatusPresenter presenter) {
    return !presentationMode.equals(presenter.key());
  }

  public boolean isShowStatusWidgetChanged(boolean showStatusWidget) {
    return this.showStatusWidget != showStatusWidget;
  }

  public boolean isShowProjectViewStatusChanged(boolean showProjectViewStatus) {
    return this.showProjectViewStatus != showProjectViewStatus;
  }

  public boolean isShowProjectViewHeadTagsChanged(boolean showProjectViewHeadTags) {
    return this.showProjectViewHeadTags != showProjectViewHeadTags;
  }

  public boolean isShowProjectViewLocationPathChanged(boolean showProjectViewLocationPath) {
    return this.showProjectViewLocationPath != showProjectViewLocationPath;
  }

  public boolean isShowProjectViewStatusBeforeLocationChanged(boolean showProjectViewStatusBeforeLocation) {
    return this.showProjectViewStatusBeforeLocation != showProjectViewStatusBeforeLocation;
  }

  public boolean isBehindTrackerChanged(boolean behindTracker) {
    return this.behindTracker != behindTracker;
  }

  public String getUpdateProjectActionId() {
    return updateProjectActionId;
  }

  public void setUpdateProjectActionId(String id) {
    updateProjectActionId = id;
  }

  public boolean isUpdateProjectActionId(@NotNull String id) {
    return !updateProjectActionId.equals(id);
  }

  @Nullable
  @Override
  public GitToolBoxConfig getState() {
    return this;
  }

  @Override
  public void loadState(GitToolBoxConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }
}
