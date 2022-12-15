package SpringBoot.App.product;

import SpringBoot.App.product.dto.ProductConverter;
import SpringBoot.App.product.dto.ProductDto;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InMemoryProductService implements ProductService {
    @Getter
    private Map<UUID, Product> products = new HashMap<>();

    @Override
    public Set<ProductDto> listAll() {
        return products.values().stream().map(ProductConverter::from).collect(Collectors.toSet());
    }

    @Override
    public ProductDto getById(UUID id) {
        return ProductConverter.from(products.get(id));
    }

    @Override
    public ProductDto getByName(String name) {
        return products.values()
                .stream()
                .map(ProductConverter::from)
                .filter(productDto -> productDto.getName().equals(name))
                .findFirst().orElse(null);
    }

    @Override
    public synchronized Product save(ProductDto productDto) {
        if (productDto.getId() == null) {
            productDto.setId(UUID.randomUUID());
        }
        products.put(productDto.getId(), ProductConverter.to(productDto));
        return ProductConverter.to(productDto);
    }

    @Override
    public Set<ProductDto> getManufactureProductsById(UUID id) {
        return products.values()
                .stream()
                .map(ProductConverter::from)
                .filter(pr -> (pr.getManufacture().getId().equals(id)))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ProductDto> getManufactureProductsByName(String name) {
        return products.values()
                .stream()
                .map(ProductConverter::from)
                .filter(pr -> pr.getManufacture().getName().equals(name))
                .collect(Collectors.toSet());
    }

    @Override
    public synchronized ProductDto deleteById(UUID id) {
        return ProductConverter.from(products.remove(id));
    }

    public synchronized void deleteManufactureProducts(UUID id) {
        products.entrySet().removeIf(entry -> entry.getValue().getManufacture().getId().equals(id));
    }

}
