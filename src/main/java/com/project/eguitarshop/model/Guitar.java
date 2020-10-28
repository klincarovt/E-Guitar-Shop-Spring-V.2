package com.project.eguitarshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import javassist.expr.Cast;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.lang.NonNullApi;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Base64;
import java.util.List;

@Entity
@Table(name = "guitars")
public class Guitar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer samples;

    @Nullable
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image")
    @Lob
    private String base64;

    private int Price;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "created_by_craftsman",
            joinColumns = @JoinColumn(name="guitar_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "craftsman_id",referencedColumnName = "id")
    )
    private List<Craftsman> craftsmen;

    @Nullable
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name="guitar_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shoppingCart_id",referencedColumnName = "id")
    )
    private List<ShoppingCart> shoppingCarts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSamples() {
        return samples;
    }


    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public void setSamples(Integer samples) {
        this.samples = samples;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public List<Craftsman> getCraftsmen() {
        return craftsmen;
    }

    public void setCraftsmen(List<Craftsman> craftsmen) {
        this.craftsmen = craftsmen;
    }

    public String getCraftsmenString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Craftsman  c: getCraftsmen()) {
            stringBuilder.append(c.getName()+" ");
        }
        return stringBuilder.toString();
    }
}
