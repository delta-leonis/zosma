package org.ssh.torch.view.window;


import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.ProgressBar;
import java.util.Collections;
import org.ssh.ipc.Zosma;
import org.ssh.ipc.event.LifeCycleEvent;
import org.ssh.ipc.event.LifeCycleEvent.State;
import org.ssh.torch.lifecycle.Prerequisite;
import org.ssh.torch.view.BasicWindow;
import org.ssh.torch.view.MainWorkspace;

/**
 * The Class SplashScreen.
 *
 * @author Jeroen de Jong
 */
public class SplashScreen extends BasicWindow {

  private static final String ZOSMA_ASCII_LOGO =
      "sdSSSSSSSbs    sSSs_sSSs      sSSs   .S_SsS_S.    .S_SSSs   \n" +
          "YSSSSSSSS%S   d%%SP~YS%%b    d%%SP  .SS~S*S~SS.  .SS~SSSSS  \n" +
          "       S%S   d%S'     `S%b  d%S'    S%S `Y' S%S  S%S   SSSS \n" +
          "      S&S    S%S       S%S  S%|     S%S     S%S  S%S    S%S \n" +
          "     S&S     S&S       S&S  S&S     S%S     S%S  S%S SSSS%S \n" +
          "     S&S     S&S       S&S  Y&Ss    S&S     S&S  S&S  SSS%S \n" +
          "    S&S      S&S       S&S  `S&&S   S&S     S&S  S&S    S&S \n" +
          "   S*S       S&S       S&S    `S*S  S&S     S&S  S&S    S&S \n" +
          "  S*S        S*b       d*S     l*S  S*S     S*S  S*S    S&S \n" +
          ".s*S         S*S.     .S*S    .S*P  S*S     S*S  S*S    S*S \n" +
          "sY*SSSSSSSP   SSSbs_sdSSS   sSS*S   S*S     S*S  S*S    S*S \n" +
          "sY*SSSSSSSP    YSSP~YSSY    YSS'    SSS     S*S  SSS    S*S \n" +
          "                                            SP          SP  \n" +
          "                                            Y           Y   \n" +
          "  V.0.0.1-beta                                              \n" +
          "                                                            \n";
  private final ProgressBar progressBar;
  private final Label loadingLabel;

  /**
   * Instantiates a new Splash screen.
   *
   * @param prerequisiteCount Number of prerequisite events to expect.
   */
  public SplashScreen(int prerequisiteCount) {
    this.setHints(Collections.singletonList(Hint.CENTERED));

    this.progressBar = new ProgressBar(0, prerequisiteCount);
    this.loadingLabel = new Label("");
    this.setComponent(
        new Panel()
            .addComponent(new Label(ZOSMA_ASCII_LOGO))
            .addComponent(this.progressBar)
            .addComponent(this.loadingLabel));

    Zosma.listen(LifeCycleEvent.class)
        .filter(thing -> thing.getState().equals(State.PREREQUISITE))
        .filter(thing -> thing.getContext().getClass().equals(MainWorkspace.class))
        .take(prerequisiteCount)
        .doOnComplete(this::close)
        .subscribe(this::updateLoadingStatus);
  }

  /**
   * Updates the loading status with the information contained in the supplied {@link LifeCycleEvent}
   * from a {@link MainWorkspace}.
   * @param mainWorkspaceEvent The {@link LifeCycleEvent}.
   */
  public void updateLoadingStatus(LifeCycleEvent<Prerequisite, MainWorkspace> mainWorkspaceEvent) {
    this.progressBar.setPreferredWidth(this.getPreferredSize().getColumns());
    this.loadingLabel.setText(mainWorkspaceEvent.getSource().toString());
    this.progressBar.setValue(this.progressBar.getValue() + 1);
  }

  @Override
  public boolean isInvalid() {
    return super.isInvalid();
  }
}
