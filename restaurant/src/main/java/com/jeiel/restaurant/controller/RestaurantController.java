package com.jeiel.restaurant.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    // Criar produto
    @PostMapping("/create")
    public void create() {}

    // Deletar produto
    @DeleteMapping("/delete")
    public void delete() {}

    // Busca produto por id
    @GetMapping("/get/{id}")
    public void get(@PathVariable String id) {}

    // Busca todos os produtos
    @GetMapping("/get")
    public void get() {}

    // Alteração nos produtos
    @PatchMapping
    public void patch() {}

    // Teste do endpoint
    @GetMapping("/ping")
    public String pong(){
        return "pong";
    }
}
