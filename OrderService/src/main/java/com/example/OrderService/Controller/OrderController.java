package com.example.OrderService.Controller;

import com.example.OrderService.Model.OrderRequest;
import com.example.OrderService.Model.OrderResponse;
import com.example.OrderService.Service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Log4j2
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place-order")
    public ResponseEntity<Long> placeOrder(@RequestBody OrderRequest orderRequest)
    {
        long orderId = orderService.placeOrder(orderRequest);

        log.info("Order created with id"+orderId);

        return new ResponseEntity<>(orderId, HttpStatus.CREATED);
    }

    @GetMapping("/get-order/{orderid}")
    public ResponseEntity<OrderResponse> getOrderDetails(@PathVariable Long orderid){
        OrderResponse orderResponse = orderService.getOrderDetails(orderid);

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }
}
