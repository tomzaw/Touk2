package ticketbooking.service;

import java.time.LocalDateTime;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Order;
import ticketbooking.model.Screening;
import ticketbooking.repository.OrderRepository;

@Transactional
@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public LocalDateTime getExpirationTime(Long id) {

        Order order = orderRepository.findById(id).get();
        Screening screening = order.getScreening();

        return screening.getDate().minusMinutes(15);
    }

    public List<Order> findAll() {

        List<Order> orders = orderRepository.findAll();
        return orders;
    }

    public List<Order> findAllByAppUserId(Long id) {

        List<Order> orders = orderRepository.findAllByAppUserId(id);
        return orders;
    }

    public List<Order> findAllClientId(Long id) {

        List<Order> orders = orderRepository.findAllByClientId(id);
        return orders;
    }

    public Order find(Long id) {

        Order order = orderRepository.findById(id).get();
        return order;
    }

    public Order save(Order order) {

        return orderRepository.save(order);
    }

    public void delete(Long id) {

        orderRepository.deleteById(id);
    }
}
