import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ByteGrayColorTest {
  @Test
  public void testGetLuminosity_whenColorCreatedWithGrayLevel(){
    ByteGrayColor black = new ByteGrayColor(0);
    ByteGrayColor white = new ByteGrayColor(255);
    assertThat(black.getLuminosity()).isCloseTo(0., within(.01));
    assertThat(white.getLuminosity()).isCloseTo(1., within(.01));
  }

  @Test
  public void testGetLuminosity_whenColorCreatedWithLuminosity(){
    ByteGrayColor color1 = new ByteGrayColor(.25);
    ByteGrayColor color2 = new ByteGrayColor(.75);
    assertThat(color1.getLuminosity()).isCloseTo(.25, within(.01));
    assertThat(color2.getLuminosity()).isCloseTo(.75, within(.01));
  }

  @Test
  public void testCompareTo_whenColorsCreatedWithGrayLevel(){
    ByteGrayColor color1 = new ByteGrayColor(100);
    ByteGrayColor color2 = new ByteGrayColor(150);
    ByteGrayColor color3 = new ByteGrayColor(200);
    ByteGrayColor sameGrayLevelAsColor1 = new ByteGrayColor(100);
    assertThat(color1.compareTo(color2)).isNegative();
    assertThat(color2.compareTo(color3)).isNegative();
    assertThat(color1.compareTo(color3)).isNegative();
    assertThat(color2.compareTo(color1)).isPositive();
    assertThat(color1.compareTo(sameGrayLevelAsColor1)).isZero();
    assertThat(color1.compareTo(color2)).isEqualTo(sameGrayLevelAsColor1.compareTo(color2));
  }

  @Test
  public void testCompareTo_whenColorsCreatedWithLuminosity(){
    ByteGrayColor color1 = new ByteGrayColor(0.20);
    ByteGrayColor color2 = new ByteGrayColor(0.60);
    ByteGrayColor color3 = new ByteGrayColor(0.80);
    ByteGrayColor sameLuminosityAsColor1 = new ByteGrayColor(0.20);
    assertThat(color1.compareTo(color2)).isNegative();
    assertThat(color2.compareTo(color3)).isNegative();
    assertThat(color1.compareTo(color3)).isNegative();
    assertThat(color2.compareTo(color1)).isPositive();
    assertThat(color1.compareTo(sameLuminosityAsColor1)).isZero();
    assertThat(color1.compareTo(color2)).isEqualTo(sameLuminosityAsColor1.compareTo(color2));
  }

  @Test
  public void testCompareTo_whenColorsCreatedWithLuminosityAndGrayLevel(){
    ByteGrayColor color1 = new ByteGrayColor(0.);
    ByteGrayColor color2 = new ByteGrayColor(150);
    ByteGrayColor color3 = new ByteGrayColor(1.);
    assertThat(color1.compareTo(color2)).isNegative();
    assertThat(color2.compareTo(color3)).isNegative();
    assertThat(color1.compareTo(color3)).isNegative();
    assertThat(color2.compareTo(color1)).isPositive();
  }
}
