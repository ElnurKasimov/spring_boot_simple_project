package SpringBoot.App.product;

import SpringBoot.App.manufacture.Manufacture;
import SpringBoot.App.manufacture.dto.ManufactureConverter;
import SpringBoot.App.product.dto.ProductConverter;
import SpringBoot.App.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@RequiredArgsConstructor
@Service
public class DbProductService implements ProductService{
    private final ProductRepository repository;

    @Override
    public Set<ProductDto> listAll() {
        return repository.findAll()
                .stream()
                .map(ProductConverter::from)
                .collect(Collectors.toSet());
    }

    @Override
    public ProductDto getById(UUID id) {
        return null;
    }

    @Override
    public ProductDto getByName(String name) {
        return null;
    }

    @Override
    public Product save(ProductDto productDto) {
        if (productDto.getId() == null) {
            productDto.setId(UUID.randomUUID());
        }
        return repository.save(ProductConverter.to(productDto));
    }

    @Override
    public ProductDto deleteById(UUID id) {
        return null;
    }
}
