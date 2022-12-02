package SpringBoot.App.product;

import SpringBoot.App.manufacture.Manufacture;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "product")
@AllArgsConstructor
public class Product {
    private UUID id;
    private  String name;
    long price;
    Manufacture manufacture;

    public Product() {};

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setManufacture(Manufacture manufacture) {
        this.manufacture = manufacture;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    public UUID getId() {
        return id;
    }
    @Column (name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "price")
    public long getPrice() {
        return price;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacture_id")
    public Manufacture getManufacture() {
        return manufacture;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", manufacture=" + manufacture +
                '}';
    }
}
