package com.megamart.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderLine {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer productId;
    private double quantity;

}
