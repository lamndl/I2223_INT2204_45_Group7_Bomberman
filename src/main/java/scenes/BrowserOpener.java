package scenes;
import mainClass.App;
import java.net.URI;
/**
 *
 * @author Narayan
 */
public class BrowserOpener {


  public void open(String url) throws Exception{
    URI u = new URI(url);
    java.awt.Desktop.getDesktop().browse(u);
  }
}