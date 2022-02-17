package pl.javastart.equipy.assigment.dto;

import org.springframework.stereotype.Component;
import pl.javastart.equipy.assigment.Assignment;

@Component
public class AssignmentDtoMapper {

    public AssignmentDto map (Assignment assignment) {

        return new AssignmentDto(
                assignment.getId(),
                assignment.getStart(),
                assignment.getEnd(),
                assignment.getUser().getId(),
                assignment.getAsset().getId(),
                assignment.getAsset().getName(),
                assignment.getAsset().getSerialNumber()
        );

    }


}
