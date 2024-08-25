package com.example.learningcoffee;

import com.example.learningcoffee.bean.Coffee;
import com.example.learningcoffee.bean.CoffeeOrder;
import com.example.learningcoffee.bean.OrderState;
import com.example.learningcoffee.repository.CoffeeOrderRepository;
import com.example.learningcoffee.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class LearningCoffeeApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(LearningCoffeeApplication.class, args);
    }


    @Override
    @Transactional  // 方法执行 开启事务
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
        findOrders();
    }

    private void initOrders() {
        Coffee espresso = Coffee.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);
        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .build();
        coffeeRepository.save(latte);
        log.info("Coffee: {}", latte);

        CoffeeOrder order = CoffeeOrder.builder().customer("Li Lei")
                .items(Collections.singletonList(espresso))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("CoffeeOrder: {}", order);

        order = CoffeeOrder.builder().customer("Li Lei")
                .items(Arrays.asList(espresso, latte))
                .state(OrderState.INIT)
                .build();
        coffeeOrderRepository.save(order);
        log.info("CoffeeOrder: {}", order);
    }

    private void findOrders () {
        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));

        List<CoffeeOrder> coffeeList = coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(coffeeList));

        coffeeList = coffeeOrderRepository.findByCustomerOrderById("Li Lei");
        log.info("findByCustomerOrderById: {}", getJoinedOrderId(coffeeList));

        coffeeList.forEach(o -> {
            log.info("Order: {}", o.getId());
            o.getItems().forEach(i -> log.info("  Item: {}", i));
        });

        coffeeList = coffeeOrderRepository.findByItems_Name("latte");
        log.info("findByItems_Name: {}", getJoinedOrderId(coffeeList));
    }

    private String getJoinedOrderId(List<CoffeeOrder> coffeeOrders) {
        return coffeeOrders.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }
}
