package pl.javastart.equipy.asset.dto;

import org.springframework.stereotype.Component;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.category.Category;
import pl.javastart.equipy.category.dto.CategoryNameMapper;

@Component
public class AssetDtoMapper {

    private final CategoryNameMapper categoryNameMapper;

    public AssetDtoMapper(CategoryNameMapper categoryNameMapper) {
        this.categoryNameMapper = categoryNameMapper;
    }

    public AssetDto map(Asset asset) {

        return new AssetDto(
                asset.getId(),
                asset.getName(),
                asset.getDescription(),
                asset.getSerialNumber(),
                categoryNameMapper.map(asset.getCategory())
        );

    }

    public Asset map(AssetDto asset) {

        return new Asset(
                asset.getId(),
                asset.getName(),
                asset.getDescription(),
                asset.getSerialNumber(),
                categoryNameMapper.map(asset.getCategory()),
                null
        );

    }



}
