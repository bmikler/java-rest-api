package pl.javastart.equipy.asset;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.asset.dto.AssetDtoMapper;
import pl.javastart.equipy.asset.exception.AssetAlreadyExistException;
import pl.javastart.equipy.asset.exception.AssetNotFoundException;
import pl.javastart.equipy.assigment.dto.AssignmentDto;
import pl.javastart.equipy.assigment.dto.AssignmentDtoMapper;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetDtoMapper assetDtoMapper;
    private final AssignmentDtoMapper assignmentDtoMapper;

    public AssetService(AssetRepository assetRepository, AssetDtoMapper assetDtoMapper, AssignmentDtoMapper assignmentDtoMapper) {
        this.assetRepository = assetRepository;
        this.assetDtoMapper = assetDtoMapper;
        this.assignmentDtoMapper = assignmentDtoMapper;
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

    public AssetDto editAsset(Long id, AssetDto asset) {

        assetRepository.findBySerialNumber(asset.getSerialNumber())
                .ifPresent( a -> {
                    if (a.getId() != id) {
                        throw new AssetAlreadyExistException();
                    }
                });

        Asset assetToSave = assetDtoMapper.map(asset);
        Asset assetSaved = assetRepository.save(assetToSave);

        return assetDtoMapper.map(assetSaved);

    }

    public List<AssignmentDto> getAssignmentByAsset(Long id) {

        return assetRepository.findById(id)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(assignmentDtoMapper::map)
                .toList();

    }
}
