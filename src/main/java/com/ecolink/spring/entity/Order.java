package com.ecolink.spring.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserBase user;

    private OrderStatus status;

    private LocalDate purchaseDate;

    private String shippingAddress = "";
    private String shippingCity = "";
    private String shippingZipCode = "";
    private String shippingCountry = "";
    private String shippingPhone = "";


    @OneToMany(mappedBy = "order")
    List<OrderLine> orderLines;

    public Order(UserBase user, OrderStatus status, LocalDate purchaseDate) {
        this.user = user;
        this.status = status;
        this.purchaseDate = purchaseDate;
        this.orderLines = new ArrayList<>();
    }

    public void addOrderLine(OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public void removeOrderLine(OrderLine orderLine) {
        orderLine.setOrder(null);
        orderLine.setProduct(null);
        this.orderLines.remove(orderLine);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderLine orderLine : orderLines) {
            total = total.add(orderLine.getTotal());
        }
        return total;
    }
}
