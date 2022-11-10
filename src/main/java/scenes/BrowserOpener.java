package scenes;

import java.net.URI;

public class BrowserOpener {


  public void open(String url) throws Exception {
    URI u = new URI(url);
    java.awt.Desktop.getDesktop().browse(u);
  }
}
