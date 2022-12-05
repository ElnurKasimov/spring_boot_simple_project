package SpringBoot.App.manufacture;

import SpringBoot.App.manufacture.dto.ManufactureConverter;
import SpringBoot.App.manufacture.dto.ManufactureDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Service
@RequiredArgsConstructor
public class DbManufactureService implements ManufactureService{
    private final ManufactureRepository repository;

    @Override
    public Set<ManufactureDto> listAll() {
        return repository.findAll()
                .stream()
                .map(ManufactureConverter::from)
                .collect(Collectors.toSet());
    }

    @Override
    public ManufactureDto getById(UUID id) {
        return null;
    }

    @Override
    public ManufactureDto getByName(String name) {
        return null;
    }

    @Override
    public Manufacture save(ManufactureDto manufactureDto) {
        if (manufactureDto.getId() == null) {
            manufactureDto.setId(UUID.randomUUID());
        }
        Manufacture toReturn = repository.save(ManufactureConverter.to(manufactureDto));
        return toReturn;
    }

    @Override
    public ManufactureDto deleteById(UUID id) {
        return null;
    }
}
