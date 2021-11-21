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
@Table(name = "basket_value")
public class BasketValue {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    private Flower flower;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "basket_id")
    private Basket basket;

    public BasketValue(Flower flower, Basket basket) {
        this.flower = flower;
        this.basket = basket;
        this.count = 0;
    }

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }
}
