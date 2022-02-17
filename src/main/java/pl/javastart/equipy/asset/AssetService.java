package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.asset.dto.AssetDtoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetDtoMapper assetDtoMapper;

    public AssetService(AssetRepository assetRepository, AssetDtoMapper assetDtoMapper) {
        this.assetRepository = assetRepository;
        this.assetDtoMapper = assetDtoMapper;
    }

    public List<AssetDto> getAllAssets() {
        return assetRepository.findAll()
                .stream()
                .map(assetDtoMapper::map)
                .toList();
    }

    public Optional<AssetDto> findById(Long id) {

        return assetRepository.findById(id)
                .map(assetDtoMapper::map);

    }

    public List<AssetDto> findByNameOrSerialNumber(String searchText) {

        return assetRepository.findByNameContainsIgnoreCaseOrSerialNumberContainsIgnoreCase(searchText, searchText)
                .stream()
                .map(assetDtoMapper::map)
                .toList();

    }

    public AssetDto addAsset(AssetDto asset) {

        if(assetRepository.findBySerialNumber(asset.getSerialNumber()).isPresent()) {
            throw new AssetAlreadyExistException();
        }

        System.out.println(asset);

        Asset assetToSave = assetDtoMapper.map(asset);
        Asset save = assetRepository.save(assetToSave);

        return assetDtoMapper.map(save);

    }

}
