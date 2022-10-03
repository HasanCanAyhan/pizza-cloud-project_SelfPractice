package com.cydeo.controller;

import com.cydeo.exception.PizzaNotFoundException;
import com.cydeo.model.Pizza;
import com.cydeo.model.PizzaOrder;
import com.cydeo.repository.PizzaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final PizzaRepository pizzaRepository;

    public OrderController(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @GetMapping("/current")
    public String orderForm(@RequestParam UUID pizzaId, Model model) {

        PizzaOrder pizzaOrder = new PizzaOrder();

        //Fix the getPizza method below in line 49.

        try {
            pizzaOrder.setPizza(getPizza(pizzaId));
            System.out.println("pizzaId = " + pizzaId);
        }catch (RuntimeException e){
            System.out.println( e.getMessage() );
        }


        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }



    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId, @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {

        // Save the order
        pizzaRepository.createPizza(pizzaOrder.getPizza());


        pizzaOrder.setPizza(getPizza(pizzaId));

        System.out.println("Pizza Order : " );
        System.out.println(pizzaOrder.getPizza());
        System.out.println(pizzaOrder.getDeliveryName());
        System.out.println(pizzaOrder.getDeliveryCity());


        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) {
        // Get the pizza from repository based on it's id

        return pizzaRepository.readAll().stream()
                .filter(pizza -> pizza.getId().toString().equals(pizzaId.toString()))
                .findFirst()
                .orElseThrow( () -> new PizzaNotFoundException("PIZZA NOT FOUND !"));

    }




}
