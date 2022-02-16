package pl.javastart.equipy.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetDto {

    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String category;

}
