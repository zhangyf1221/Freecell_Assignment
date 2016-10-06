package cs3500.hw03;


import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import java.io.StringReader;
import cs3500.hw02.IFreeCellModel;
import cs3500.hw02.FreeCellModel;
import java.util.Scanner;

/**
 * Created by jowenfan on 10/6/16.
 */



public class FreeCellControllerTest {
  @Test
  public void main() throws Exception {
    Scanner s = new Scanner("4 4 false");
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(bytes);
    FreeCellController controller = new FreeCellController(new InputStreamReader(System.in), out);

  }

}