package ktb.week4.semi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static ktb.week4.semi.OrderDto.*;

@Entity
@Table(name = "product_orders")
@Getter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String productName;

    private int price;

    private int quantity;

    public Order(OrderCreateRequest request) {
        this.userId = request.userId();
        this.productName = request.productName();
        this.price = request.price();
        this.quantity = request.quantity();
    }

    public void updateOrderInfo(OrderUpdateRequest request) {
        this.productName = request.productName();
        this.price = request.price();
        this.quantity = request.quantity();
    }

}
