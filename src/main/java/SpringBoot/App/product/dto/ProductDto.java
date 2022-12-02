package SpringBoot.App.product.dto;

import SpringBoot.App.manufacture.dto.ManufactureDto;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ProductDto {
    private UUID id;
    private  String name;
    long price;
    ManufactureDto manufacture;

    public ProductDto() {};

    public ProductDto(String name, long price, ManufactureDto manufacture) {
        this.name = name;
        this.price = price;
        this.manufacture = manufacture;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setManufacture(ManufactureDto manufacture) {
        this.manufacture = manufacture;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public ManufactureDto getManufacture() {
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
