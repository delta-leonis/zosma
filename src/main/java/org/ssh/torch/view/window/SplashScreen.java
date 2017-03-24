package org.ssh.torch.view.window;


import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.ProgressBar;
import java.util.Collections;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.ssh.torch.LifeCycle;
import org.ssh.torch.lifecycle.PreRequisite;
import org.ssh.torch.view.BasicWindow;

/**
 * The Class SplashScreen.
 *
 * @author Jeroen de Jong
 */
public class SplashScreen extends BasicWindow implements Subscriber<PreRequisite> {

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
   * @param object the object
   */
  public SplashScreen(LifeCycle object) {
    this.setHints(Collections.singletonList(Hint.CENTERED));

    this.progressBar = new ProgressBar(0, object.getPreRequisites().size());
    this.loadingLabel = new Label("");
    this.setComponent(
        new Panel()
            .addComponent(new Label(ZOSMA_ASCII_LOGO))
            .addComponent(this.progressBar)
            .addComponent(this.loadingLabel)
    );

    object.subscribe(this);
  }

  @Override
  public void onSubscribe(Subscription s) {
    s.request(Long.MAX_VALUE);
  }

  @Override
  public void onNext(PreRequisite loadingMessage) {
    this.progressBar.setPreferredWidth(this.getPreferredSize().getColumns());
    this.loadingLabel.setText(loadingMessage.toString());
    this.progressBar.setValue(this.progressBar.getValue() + 1);
  }

  @Override
  public void onError(Throwable t) {
    this.loadingLabel.setText("An error occurred!");
  }

  @Override
  public void onComplete() {
    this.close();
  }

  @Override
  public boolean isInvalid() {
    return super.isInvalid();
  }
}
