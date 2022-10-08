import formula.Constant;
import formula.Formula;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

public class ConstantTest {
  @Test
  public void testEval(){
    Constant constantTen = new Constant(10);
    assertThat(constantTen.eval(0.)).isCloseTo(10., within(.001));
    Constant constantOne = new Constant(1);
    assertThat(constantOne.eval(10.)).isCloseTo(1., within(.001));
  }

  @Test
  public void testToString(){
    Constant constantTen = new Constant(10);
    assertThat(constantTen.toString()).isEqualTo("10.0");
    Constant constantOne = new Constant(1);
    assertThat(constantOne.toString()).isEqualTo("1.0");
  }

  @Test
  public void testEquals() {
    Constant constantTen = new Constant(10);
    Constant constantTen2 = new Constant(10);
    Constant constantOne = new Constant(0);
    assertThat(constantTen).isEqualTo(constantTen);
    assertThat(constantTen).isEqualTo(constantTen2);
    assertThat(constantTen).isNotEqualTo(constantOne);
  }

  @Test
  public void testDerivative() {
    Constant constantTen = new Constant(10);
    Formula derivedConstant = constantTen.derivative();
    assertThat(derivedConstant.eval(10.)).isCloseTo(0., within(.001));
    Constant constantZero = new Constant(0);
    assertThat(derivedConstant).isEqualTo(constantZero);
  }
}
