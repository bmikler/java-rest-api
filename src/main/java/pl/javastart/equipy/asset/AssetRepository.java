package pl.javastart.equipy.asset;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

interface AssetRepository extends JpaRepository<Asset, Long> {

    List<Asset> findByNameContainsIgnoreCaseOrSerialNumberContainsIgnoreCase(String name, String serialNumber);

    Optional<Asset> findBySerialNumber(String serialNumber);

}
