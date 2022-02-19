package pl.javastart.equipy.user;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import lombok.*;
import pl.javastart.equipy.assigment.Assignment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String pesel;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Assignment> assignments = new ArrayList<>();

    public void addAssignment(Assignment assignment) {

        assignments.add(assignment);

    }

    public void removeAssignment(Assignment assignment) {

        assignments.remove(assignment);

    }

}
