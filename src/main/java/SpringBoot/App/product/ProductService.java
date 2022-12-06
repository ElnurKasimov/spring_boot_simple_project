package SpringBoot.App.product;

import SpringBoot.App.product.dto.ProductDto;

import java.util.Set;
import java.util.UUID;

public interface ProductService {
    public Set<ProductDto> listAll ();

    public ProductDto getById(UUID id);
    public ProductDto getByName(String name);

    public Product save(ProductDto productDto);

    public ProductDto deleteById(UUID id);

    public Set<ProductDto> getManufactureProductsById(UUID id);

    public Set<ProductDto> getManufactureProductsByName(String name);
}
