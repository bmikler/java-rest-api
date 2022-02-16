package pl.javastart.equipy.asset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Asset with this serial number already exist.")
public class AssetAlreadyExistException extends RuntimeException {
}
