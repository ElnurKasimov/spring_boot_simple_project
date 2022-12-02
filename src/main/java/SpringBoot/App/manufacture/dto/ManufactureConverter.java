package SpringBoot.App.manufacture.dto;

import SpringBoot.App.manufacture.Manufacture;

public class ManufactureConverter {

    public static ManufactureDto from(Manufacture manufacture) {
        ManufactureDto result = new ManufactureDto();
        result.setId(manufacture.getId());
        result.setName(manufacture.getName());
        return result;
    }

    public static Manufacture to(ManufactureDto manufactureDto) {
        Manufacture result = new Manufacture();
        result.setId(manufactureDto.getId());
        result.setName(manufactureDto.getName());
        return result;
    }

}
