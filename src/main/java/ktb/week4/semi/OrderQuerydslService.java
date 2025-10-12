package ktb.week4.semi;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderQuerydslService {

    private final JPAQueryFactory queryFactory;

    public Long countAllOrders() {
        QOrder qOrder = QOrder.order;
        return queryFactory
                .select(qOrder.count())
                .from(qOrder)
                .fetchOne();
    }
}
