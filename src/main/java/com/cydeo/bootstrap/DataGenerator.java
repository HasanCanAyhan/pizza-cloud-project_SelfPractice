package com.cydeo.bootstrap;

import com.cydeo.enums.Cheese;
import com.cydeo.enums.Sauce;
import com.cydeo.enums.Topping;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataGenerator {

    public static List<Cheese> cheeseTypeList;
    public static List<Sauce> sauceTypeList;
    public static List<Topping> toppingTypeList;

    static {

        cheeseTypeList = Arrays.asList(Cheese.values());
        sauceTypeList = Arrays.asList(Sauce.values());
        toppingTypeList = Arrays.asList(Topping.values());

    }

}
