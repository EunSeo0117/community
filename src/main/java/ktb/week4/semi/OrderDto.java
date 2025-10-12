package ktb.week4.semi;

public class OrderDto {
    public record OrderCreateRequest(
            String productName,
            Long userId,
            int price,
            int quantity
    ) {}

    public record OrderUpdateRequest(
            String productName,
            int price,
            int quantity
    ) {}

    public record OrderResponse(
            Long orderId,
            String productName,
            Long userId,
            int price,
            int quantity
    ) {
        public static OrderResponse of(Order order) {
            return new OrderResponse(
                    order.getId(),
                    order.getProductName(),
                    order.getUserId(),
                    order.getPrice(),
                    order.getQuantity()
            );
        }
    }
}
