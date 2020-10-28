package com.project.eguitarshop.model;

import com.project.eguitarshop.model.enumeration.CartStatus;
import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="shopping_carts")
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate=LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private CartStatus status =CartStatus.CREATED;

    @Nullable
    private LocalDateTime closeDate;


    @Nullable
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name="shoppingCart_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "guitar_id",referencedColumnName = "id")
    )
    private List<Guitar> guitars;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public List<Guitar> getGuitars() {
        return guitars;
    }

    public void setGuitars(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
