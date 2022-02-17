package pl.javastart.equipy.assigment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private Long userId;
    private String assetName;
    private String assetSerialNumber;

}
