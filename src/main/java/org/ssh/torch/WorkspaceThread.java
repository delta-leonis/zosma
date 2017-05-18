package org.ssh.torch;

import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import java.io.*;
import java.util.concurrent.CountDownLatch;
import lombok.Getter;
import org.ssh.math.function.LambdaExceptions;
import org.ssh.torch.view.Workspace;

/**
 * The Class WorkspaceThread.
 *
 * @author Jeroen de Jong
 */
public class WorkspaceThread extends AbstractTextGUIThread implements AsynchronousTextGUIThread {

  @Getter
  private final Thread thread;
  private final CountDownLatch waitLatch;
  private final Object pauseLock = new Object();
  @Getter
  private volatile State state;
  private volatile boolean paused = false;

  private WorkspaceThread(TextGUI textGUI) {
    super(textGUI);
    this.waitLatch = new CountDownLatch(1);
    this.thread = new Thread(this::mainGUILoop);
    state = State.CREATED;
  }

  @Override
  public void start() {
    thread.start();
    state = State.STARTED;
  }

  @Override
  public void stop() {
    if (state != State.STARTED) {
      return;
    }
    state = State.STOPPING;
  }

  @Override
  public void waitForStop() throws InterruptedException {
    waitLatch.await();
  }

  /**
   * Pause.
   */
  public void pause() {
    paused = true;
  }

  /**
   * Resume.
   */
  public void resume() {
    synchronized (pauseLock) {
      paused = false;
      pauseLock.notifyAll(); // Unblocks thread
      invokeLater(LambdaExceptions.rethrowRunnable(() ->
          getWorkspace().getScreen().refresh(Screen.RefreshType.COMPLETE)
      ));
    }
  }

  /**
   * Gets workspace.
   *
   * @return the workspace
   */
  public Workspace getWorkspace() {
    return (Workspace) textGUI;
  }

  private void mainGUILoop() {
    try {
      //Draw initial screen, after this only draw when the GUI is marked as invalid
      try {
        textGUI.updateScreen();
      } catch (IOException e) {
        exceptionHandler.onIOException(e);
      } catch (RuntimeException e) {
        exceptionHandler.onRuntimeException(e);
      }
      while (state == State.STARTED) {
        synchronized (pauseLock) {
          // is possible when obtaining synchronized on lock
          if (state != State.STARTED) {
            break;
          }
          if (paused) {
            try {
              pauseLock.wait();
            } catch (InterruptedException ex) {
              break;
            }
          }
          // running might have changed since we paused
          if (state != State.STARTED) {
            break;
          }
        }
        try {
          if (!processEventsAndUpdate()) {
            try {
              Thread.sleep(25);
            } catch (InterruptedException ignored) {
            }
          }
        } catch (EOFException e) {
          stop();
          break; //Break out quickly from the main loop
        } catch (IOException e) {
          if (exceptionHandler.onIOException(e)) {
            stop();
            break;
          }
        } catch (RuntimeException e) {
          if (exceptionHandler.onRuntimeException(e)) {
            stop();
            break;
          }
        }
      }
    } finally {
      state = State.STOPPED;
      waitLatch.countDown();
    }
  }


  @Override
  public synchronized boolean processEventsAndUpdate() throws IOException {
    if (getThread() != Thread.currentThread()) {
      throw new IllegalStateException("Calling processEventAndUpdate outside of GUI thread");
    }

    textGUI.processInput();
    while (!customTasks.isEmpty()) {
      Runnable r = customTasks.poll();
      if (r != null) {
        r.run();
      }
    }
    if (textGUI.isPendingUpdate()) {
      textGUI.updateScreen();
      return true;
    }
    return false;
  }

  /**
   * The type Factory.
   */
  public static class Factory implements TextGUIThreadFactory {

    @Override
    public TextGUIThread createTextGUIThread(final TextGUI textGUI) {
      return new WorkspaceThread(textGUI);
    }
  }
}
