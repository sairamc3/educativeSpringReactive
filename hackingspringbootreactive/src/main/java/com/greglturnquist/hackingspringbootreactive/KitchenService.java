package com.greglturnquist.hackingspringbootreactive;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KitchenService {

    private Random picker = new Random();

    private List<Dish> menu = Arrays.asList(
            new Dish("Chicken"),
            new Dish("Noodles"),
            new Dish("Beef")
    );

    public Flux<Dish> getDishes() {
        return Flux
                .<Dish>generate(sink -> sink.next(randomDish()))
                .delayElements(Duration.ofMillis(250));
    }

    private Dish randomDish(){
        return menu.get(picker.nextInt(menu.size()));
    }
}
