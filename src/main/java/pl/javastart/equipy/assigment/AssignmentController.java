package pl.javastart.equipy.assigment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assigment.dto.AssignmentDto;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    ResponseEntity<AssignmentDto> assign(@RequestBody AssignmentDto assignmentDto) {

        if(assignmentDto.getUserId() == null || assignmentDto.getAssetId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID can not be null");
        }

        AssignmentDto assignmentSaved = assignmentService.save(assignmentDto);
        URI saveEntityLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(assignmentSaved.getId())
                .toUri();

        return ResponseEntity.created(saveEntityLocation).body(assignmentSaved);
    }

    @PostMapping("/{assignmentId}/end")
    ResponseEntity<LocalDateTime> giveBack(@PathVariable Long assignmentId) {

        if (assignmentId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID can not be null");
        }

        LocalDateTime returnTime = assignmentService.edit(assignmentId);

        return ResponseEntity.ok(returnTime);
    }

}
