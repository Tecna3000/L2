import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //read file
        File sac0 = new File("/amuhome/r20031646/Documents/Tp3-Algo/src/files/sac2.txt");
        BackPack sac = new BackPack(sac0);
        System.out.println("----------Backpack----------\n"+sac);
        //Dynamic
        Dynamic table = new Dynamic(sac);
        System.out.println("-----------Dynamic table----------\n" + table);
        System.out.println("The optimal charging is : " + table.OptimalCharging());
    }
}
