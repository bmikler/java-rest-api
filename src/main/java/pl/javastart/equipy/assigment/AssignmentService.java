package pl.javastart.equipy.assigment;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.asset.exception.AssetNotFoundException;
import pl.javastart.equipy.asset.AssetRepository;
import pl.javastart.equipy.asset.exception.AssetUnavailableException;
import pl.javastart.equipy.assigment.dto.AssignmentDto;
import pl.javastart.equipy.assigment.dto.AssignmentDtoMapper;
import pl.javastart.equipy.assigment.exception.AssignmentAlreadyReturnedException;
import pl.javastart.equipy.assigment.exception.AssignmentNotFoundException;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserNotFoundException;
import pl.javastart.equipy.user.UserRepository;

import java.time.LocalDateTime;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentDtoMapper assignmentDtoMapper;
    private final UserRepository userRepository;
    private final AssetRepository assetRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, AssignmentDtoMapper assignmentDtoMapper,
                             UserRepository userRepository, AssetRepository assetRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentDtoMapper = assignmentDtoMapper;
        this.userRepository = userRepository;
        this.assetRepository = assetRepository;
    }

    public AssignmentDto save(AssignmentDto assignmentDto) {

        User user = userRepository.findById(assignmentDto.getUserId())
                .orElseThrow(UserNotFoundException::new);

        Asset asset = assetRepository.findById(assignmentDto.getAssetId())
                .orElseThrow(AssetNotFoundException::new);

        asset.getAssignments().forEach(assignment -> {
                    if (assignment.getStart() != null && assignment.getEnd() == null) {
                        throw new AssetUnavailableException();
                    }
                });

        Assignment assignment = new Assignment(user, asset);
        Assignment assignmentSaved = assignmentRepository.save(assignment);

        return assignmentDtoMapper.map(assignmentSaved);

    }

    public LocalDateTime edit(Long id) {

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(AssignmentNotFoundException::new);

        if (assignment.getEnd() != null) {
            throw new AssignmentAlreadyReturnedException();
        }

        assignment.setEnd(LocalDateTime.now());
        return assignment.getEnd();

    }


}
