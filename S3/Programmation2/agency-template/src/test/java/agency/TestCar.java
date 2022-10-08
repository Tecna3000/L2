package agency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.TimeProvider;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class TestCar {
  // TODO : décommenter le code ci-dessous pour tester la tâche 2.


  Car catalinaCar = new Car(apple, catalina, catalinaYear, catalinaNumberOfSeats);
  Car windows95Car = new Car(microsoft, windows95, windows95Year, windows95NumberOfSeats);
  static final String apple = "Apple";
  static final String catalina = "Catalina";
  static final String microsoft = "Microsoft";
  static final String windows95 = "Windows95";
  static final int catalinaYear = 2019;
  static final int windows95Year = 1995;
  static final int catalinaNumberOfSeats = 3;
  private static final int windows95NumberOfSeats = 1;
  private static final LocalDateTime YEAR2000 = LocalDateTime.of(2000, 1, 1, 0, 0);
  private static final LocalDateTime YEAR2019 = LocalDateTime.of(2019, 1, 1, 0, 0);

  @BeforeEach
  void fixClock(){
    TimeProvider.useFixedClockAt(YEAR2019);
  }

  @Test
  void testConstructionOfTooOldVehicle(){
    assertThatIllegalArgumentException()
            .isThrownBy(()->new Car("Linux", "ubuntu",1899, 3))
            .withMessageContaining("1899");
  }

  @Test
  void testConstructionOfFutureVehicle() {
    assertThatIllegalArgumentException()
            .isThrownBy(() -> new Car("Linux", "ubuntu", 2020, 3))
            .withMessageContaining("2020");
  }

  @Test
  void testConstructionOfVehicleWithNoSeats() {
    assertThatIllegalArgumentException()
            .isThrownBy(() -> new Car("Linux", "ubuntu", 2000, 0))
            .withMessageContaining("0");
  }

  @Test
  void testGetters() {
    assertThat(catalinaCar).hasFieldOrPropertyWithValue("brand", apple)
            .hasFieldOrPropertyWithValue("model", catalina)
            .hasFieldOrPropertyWithValue("productionYear", catalinaYear)
            .hasFieldOrPropertyWithValue("numberOfSeats", catalinaNumberOfSeats);
  }

  @Test
  void testConstructionOfFutureCar(){
    assertThatIllegalArgumentException().
            isThrownBy(() -> new Car("Linux", "ubuntu",
            2020, 2));
  }

  @Test
  void testConstructionOfTooOldCar(){
    assertThatIllegalArgumentException().
            isThrownBy(() -> new Car("Linux", "ubuntu",
            1899, 1));
  }

  @Test
  void testThatCarImplementsVehicle(){
    assertThat(catalinaCar).withFailMessage("%s must implement %s",
            Car.class, Vehicle.class)
            .isInstanceOf(Vehicle.class);
  }


  @Test
  void testIsNew(){
    assertThat(windows95Car).withFailMessage("A car with production year %s should have been old",
            windows95Year)
            .returns(false, Car::isNew);
    assertThat(catalinaCar).withFailMessage("A car with production year %s should have been new",
            catalinaYear)
            .returns(true, Car::isNew);
    TimeProvider.useFixedClockAt(YEAR2000);
    assertThat(windows95Car).withFailMessage("A car with production year %s should have been new in %s",
            windows95Year, YEAR2000)
            .returns(true, Car::isNew);
    TimeProvider.useFixedClockAt(YEAR2019);
  }

  @Test
  void testDailyRentalPrice(){
    double expectedPriceCatalinaCar = 40.*catalinaNumberOfSeats;
    double expectedPriceWindowsCar = 20.*windows95NumberOfSeats;
    assertThat(catalinaCar).as("Car DailyRentalPrice")
            .withFailMessage("A new car with %s seats must have price %s",
                    catalinaNumberOfSeats, expectedPriceCatalinaCar )
            .returns(expectedPriceCatalinaCar, Car::dailyRentalPrice);
    assertThat(windows95Car).as("Car DailyRentalPrice")
            .withFailMessage("An old car with %s seat must have price %s",
            windows95NumberOfSeats, expectedPriceCatalinaCar )
            .returns(expectedPriceWindowsCar, Car::dailyRentalPrice);
  }

  @Test
  void testToString(){
    String expectedStringMacOs = "Car Apple Catalina 2019 (3 seats) : 120.0€";
    assertThat(catalinaCar).returns(expectedStringMacOs, Objects::toString);
    String expectedStringWin = "Car Microsoft Windows95 1995 (1 seat) : 20.0€";
    assertThat(windows95Car).returns(expectedStringWin, Objects::toString);
  }

}
