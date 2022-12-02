package SpringBoot.App.manufacture.dto;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class ManufactureDto {
    private UUID id;
    private  String name;

    public ManufactureDto() {};

    public ManufactureDto(String name) {
        this.name = name;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public void setID(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
