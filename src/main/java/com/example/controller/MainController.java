package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.OrderService;

@RestController
public class MainController {
    @Autowired
    OrderService orderService;

    @PostMapping("/startWorkflow")
    public String createOrder(@RequestParam("id") String id) {
        orderService.placeOrder(id);
        return "Order Placed";
    }

    @PostMapping("/orderAccepted")
    public String orderAccepted(@RequestParam("id") String id) {
        orderService.makeOrderAccepted(id);
        return "order Accepted";
    }

    @PostMapping("/orderPickedUp")
    public String orderPickedUp(@RequestParam("id") String id) {
        orderService.makeOrderPickedUp(id);
        return "Order Picked Up";
    }
 }
