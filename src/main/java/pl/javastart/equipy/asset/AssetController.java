package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.category.CategoryRepository;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService, CategoryRepository categoryRepository) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<AssetDto> getAsset(@RequestParam(required = false) String text) {

        if (text != null) {
            return assetService.findByNameOrSerialNumber(text);
        }
        return assetService.getAllAssets();

    }

    @PostMapping
    public ResponseEntity<AssetDto> saveAsset(AssetDto asset) {

        if (asset.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID of new created entity must be null!");
        }

        AssetDto assetSaved = assetService.addAsset(asset);
        URI savedEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(assetSaved.getId())
                .toUri();

        return ResponseEntity.created(savedEntityLocation).body(assetSaved);


    }

}
