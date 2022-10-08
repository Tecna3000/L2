public class Main {
  public static void main(String[] args){
    // TODO: add code.
    Student rameriem = new Student("Meriem", "Ramdani");

    rameriem.addResult("Programmation 2", new Grade(20));
    rameriem.addResult("Structures discrètes", new Grade(2));
    Cohort cohort = new Cohort("L2 informatique");
    cohort.addStudent(rameriem);
    cohort.printStudentsResults();

  }
}
