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

            pizzaOrder.setPizza(getPizza(pizzaId));
            System.out.println("pizzaId = " + pizzaId);



        model.addAttribute("pizzaOrder", pizzaOrder);

        return "/orderForm";
    }



    @PostMapping("/{pizzaId}")
    public String processOrder(@PathVariable UUID pizzaId, @ModelAttribute("pizzaOrder") PizzaOrder pizzaOrder) {

        // Save the order
        //if you want to save this order, you should create another repository to save this order


        pizzaOrder.setPizza(getPizza(pizzaId)); // one more time : because inside submit order form we don't have pizza object
        //pizzaOrder.setName(asd)
        //pizzaOrder.setPizza(null) // Note: If there is no field in the form , this will run
        /* NOTE:
            private String deliveryName;
            private String deliveryStreet;
            private String deliveryCity;
            private String deliveryState;
            private String deliveryZip;
            private String ccNumber;
            private String ccExpiration;
            private String ccCVV;
            private Pizza pizza; ONLY , IN THE FORM WE DON'T HAVE IT !!!
         */




        System.out.println("Pizza Order : " );
        System.out.println(pizzaOrder.getPizza());
        System.out.println(pizzaOrder.getDeliveryName());
        System.out.println(pizzaOrder.getDeliveryCity());

        System.out.println("------------------------------------------");

        return "redirect:/home";
    }

    //TODO
    private Pizza getPizza(UUID pizzaId) throws PizzaNotFoundException {
        // Get the pizza from repository based on it's id

        return pizzaRepository.readAll().stream()
                .filter(pizza -> pizza.getId().toString().equals(pizzaId.toString()))
                .findFirst()
                .orElseThrow( () -> new PizzaNotFoundException("PIZZA NOT FOUND !"));

    }


    //pizzaOrder.setName(asd)
    //pizzaOrder.setPizza(null) // Note: If there is no field in the form , this will run


}
