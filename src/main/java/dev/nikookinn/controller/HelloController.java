package dev.nikookinn.controller;

import dev.nikookinn.annotations.GetMapping;
import dev.nikookinn.annotations.MyController;

@MyController
public class HelloController {
    @GetMapping("/hello")
    public String helloMethod() {
        return "{message: Hello from mini Dispatcher!}";
    }
}
