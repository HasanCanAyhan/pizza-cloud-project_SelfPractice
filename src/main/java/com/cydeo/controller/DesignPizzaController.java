package com.cydeo.controller;

import com.cydeo.bootstrap.DataGenerator;
import com.cydeo.model.Pizza;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class DesignPizzaController {

    //ctrl-shift f : to search in the application ex:UUID

    private final PizzaRepository pizzaRepository;

    public DesignPizzaController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/design")
    public String showDesignForm(Model model) {

        model.addAttribute("pizza" , new Pizza());
        model.addAttribute("cheeses", DataGenerator.cheeseTypeList);
        model.addAttribute("sauces", DataGenerator.sauceTypeList);
        model.addAttribute("toppings", DataGenerator.toppingTypeList);

        return "/design";

    }

    @PostMapping("/design") // submit your pizza button inside the form
    public String processPizza(@ModelAttribute("pizza") Pizza pizza) {

        pizza.setId(UUID.randomUUID());
        // because pizzaId which is unique does not exist in submit pizza form, you should first set the id
        pizzaRepository.createPizza(pizza);

        System.out.println("pizza = " + pizza);


        return "redirect:/orders/current?pizzaId=" +  pizza.getId() ;


    }

}
