package Util;

import java.util.List;
import java.util.Random;

public class RandomUtil {

    private RandomUtil(){}

    public static <T> T randomElement(List<T> elements, Random random){
       int position = random.nextInt(elements.size()) ;
       return elements.get(position);
    }
    public static <T> T randomElement(T[] elements, Random random){
        int position = random.nextInt(elements.length) ;
        return elements[position];


    }
}
