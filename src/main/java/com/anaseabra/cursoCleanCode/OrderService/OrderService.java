package com.anaseabra.cursoCleanCode.OrderService;

public class OrderService {

    public Order EmitOrder (Order order, double discout) {

        return null;
    }

    public double calculateTotal (Order order) {
        double total = order.getItens().stream().map(c -> c.getPrice()).reduce(0., Double::sum);
        return total;
    }
}
