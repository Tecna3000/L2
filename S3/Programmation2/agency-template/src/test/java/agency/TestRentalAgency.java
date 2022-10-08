package agency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class TestRentalAgency extends agency.TestCar {

  // TODO : décommenter le code ci-dessous pour tester la tâche 2.

  private RentalAgency rentalAgency;

  @BeforeEach
  void initializeRentalAgency(){
    rentalAgency = new RentalAgency();
  }

  @Test
  void testAddingCars(){
    rentalAgency.add(catalinaCar);
    assertThat(rentalAgency.getVehicles())
            .containsExactly(catalinaCar);
    rentalAgency.add(windows95Car);
    assertThat(rentalAgency.getVehicles())
            .containsExactly(catalinaCar, windows95Car);
  }

  @Test
  void testAddingSameCar() {
    assertThat(rentalAgency.add(catalinaCar)).isTrue();
    assertThat(rentalAgency.add(catalinaCar)).isFalse();
  }

  @Test
  void testRemovingExistingVehicle(){
    rentalAgency.add(catalinaCar);
    System.out.println(rentalAgency.getVehicles());
    rentalAgency.remove(catalinaCar);
    assertThat(rentalAgency.getVehicles()).doesNotContain(catalinaCar);
  }



  // TODO : décommenter le code ci-dessous pour tester la méthode select.

  /*
  @Test
  void testRemovingNonExistingVehicle(){
    assertThatThrownBy(() -> rentalAgency.remove(catalinaCar))
            .isInstanceOf(UnknownVehicleException.class)
            .hasMessageContaining(catalinaCar.toString());
  }
  */

  // TODO : décommenter le code ci-dessous pour tester la tâche 6.
  /*
  @Test
  void testSelect(){
    rentalAgency.add(catalinaCar);
    rentalAgency.add(windows95Car);
    assertThat(rentalAgency.select(vehicle -> vehicle.getBrand().equals(apple)))
            .containsExactly(catalinaCar);
    assertThat(rentalAgency.select(vehicle -> vehicle.getModel().equals(windows95)))
            .containsExactly(windows95Car);
  }



  private Client arnaud = new Client("Arnaud", "Labourel", 1981);
  private Client paul = new Client("Paul", "Calcul", 2018);

  @Test
  void testRentingVehicles(){
    rentalAgency.add(catalinaCar);
    rentalAgency.add(windows95Car);
    rentalAgency.rentVehicle(arnaud, catalinaCar);
    rentalAgency.rentVehicle(paul, windows95Car);
    assertThat(rentalAgency.allRentedVehicles()).containsExactly(catalinaCar, windows95Car);
  }

  @Test
  void testSameClientRentingSeveralVehicles(){
    rentalAgency.add(catalinaCar);
    rentalAgency.add(windows95Car);
    assertThat(rentalAgency.rentVehicle(arnaud, catalinaCar)).isEqualTo(120.);
    assertThatIllegalStateException().isThrownBy(() -> rentalAgency.rentVehicle(arnaud, windows95Car));
  }


  @Test
  void testSameVehicleRentedTwice(){
    rentalAgency.add(catalinaCar);
    rentalAgency.rentVehicle(arnaud, catalinaCar);
    assertThatIllegalStateException().isThrownBy(() -> rentalAgency.rentVehicle(paul, catalinaCar));
  }


  @Test
  void testRentingAVehicleNotInTheAgency(){
    assertThatThrownBy(() -> rentalAgency.rentVehicle(paul, catalinaCar))
            .isInstanceOf(UnknownVehicleException.class)
            .hasMessageContaining(catalinaCar.toString());
  }
  */
}
