package com.example.learningcoffee.bean;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeOrder extends BaseEntity {

    private String customer;

    @ManyToMany
    @JoinTable(name = "T_ORDER_COFFEE")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderState state;
}
