package org.ssh.torch.view.component.graph;


import com.google.common.collect.ImmutableMap;
import java.util.*;
import lombok.Value;
import lombok.experimental.Delegate;
import org.ssh.torch.view.model.ViewModel;

/**
 * Takes an arbitrary number and presents it in a shortened form.
 * For example, 594203940 would be reduced to 594M, 5942039 would be reduced to 5.9M
 *
 * @author Thomas Hakkers
 */
@Value
public class PrefixPresenter implements ViewModel<Long> {

  @Delegate
  private Long object;

  private static final transient Map<Integer, String> suffixes =
      ImmutableMap.<Integer, String>builder()
        .put(0, "")
        .put(3, "k")
        .put(6, "M")
        .put(9, "G")
        .put(12, "T")
        .put(-3, "m")
        .put(-6, "u")
        .build();

  public final String format() {
    long tval = object;
    int order = 0;
    while(tval > 1000.0) {
      tval /= 1000.0;
      order += 3;
    }
    while(tval < 1.0) {
      tval *= 1000.0;
      order -= 3;
    }
    return tval + suffixes.get(order);
  }
}
