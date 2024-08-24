package com.greglturnquist.hackingspringbootreactive;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerController {

    private final KitchenService kitchenService;

    public ServerController(KitchenService kitchenService) {
        this.kitchenService = kitchenService;
    }

    @GetMapping(path = "/server", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish>  serveDishes() {
        return this.kitchenService.getDishes();
    }

    @GetMapping(path = "/served-dishes", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Dish>  servedDishes() {
        return this.kitchenService.getDishes().map(Dish::deliver);
    }
}
