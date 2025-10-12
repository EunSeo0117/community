package ktb.week4.semi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static ktb.week4.semi.OrderDto.*;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderRepository orderRepository;

    public Long createOrder(OrderCreateRequest request) {

        Order order = new Order(request);
        orderRepository.save(order);
        return order.getId();
    }

    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return OrderResponse.of(order);
    }

    public Long updateOrder(Long orderId, OrderUpdateRequest request) {
        Order order = orderRepository.findById(orderId).get();
        order.updateOrderInfo(request);
        orderRepository.save(order);
        return order.getId();
    }

    public String deleteOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        orderRepository.delete(order);
        return "delete completed";
    }
}