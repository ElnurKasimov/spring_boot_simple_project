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
        return ManufactureConverter.from(repository.findById(id).orElse(null));
    }

    @Override
    public ManufactureDto getByName(String name) {
        return ManufactureConverter.from(repository.getByName(name));
    }

    @Override
    public Manufacture save(ManufactureDto manufactureDto) {
        if (manufactureDto.getId() == null) {
            manufactureDto.setId(UUID.randomUUID());
        }
        return repository.save(ManufactureConverter.to(manufactureDto));
    }

    @Override
    public ManufactureDto deleteById(UUID id) {
        ManufactureDto toDelete = getById(id);
        if(toDelete != null) {repository.deleteById(id);}
        return toDelete;
    }
}
