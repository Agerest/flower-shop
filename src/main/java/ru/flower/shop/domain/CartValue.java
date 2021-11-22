package ru.flower.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "cart_value")
public class CartValue {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    private Flower flower;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public CartValue(Flower flower, Cart cart) {
        this.flower = flower;
        this.cart = cart;
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }
}
