package pl.javastart.equipy.asset.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Asset is already assigned.")
public class AssetUnavailableException extends RuntimeException {
}
