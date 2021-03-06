package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.asset.dto.AssetDto;
import pl.javastart.equipy.assigment.dto.AssignmentDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<AssetDto> getAssetById(@PathVariable Long id) {

        return assetService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/{id}/assignments")
    public List<AssignmentDto> getAssignmentByAssetId(@PathVariable Long id) {

        return assetService.getAssignmentByAsset(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDto> editAsset(@PathVariable Long id, @RequestBody AssetDto asset) {

        if (id != asset.getId()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ID must be equal to edited asset");
        }

        AssetDto assetEdited = assetService.editAsset(id, asset);

        return ResponseEntity.ok(assetEdited);

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
