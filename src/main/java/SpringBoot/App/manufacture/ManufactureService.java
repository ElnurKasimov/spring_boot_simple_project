package SpringBoot.App.manufacture;

import SpringBoot.App.manufacture.dto.ManufactureDto;

import java.util.Set;
import java.util.UUID;

public interface ManufactureService {
    public Set<ManufactureDto> listAll ();

    public ManufactureDto getById(UUID id);

    public ManufactureDto getByName(String name);

    public Manufacture save(ManufactureDto manufactureDto);

    public ManufactureDto deleteById(UUID id);
}
