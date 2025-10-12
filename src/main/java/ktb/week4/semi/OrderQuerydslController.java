package ktb.week4.semi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/qdsl/orders")
@RequiredArgsConstructor
public class OrderQuerydslController {

    private final OrderQuerydslService orderQuerydslService;

    @GetMapping("/count")
    public Long countAllOrders() {
        return orderQuerydslService.countAllOrders();
    }
}
