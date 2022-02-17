package pl.javastart.equipy.user;

import ch.qos.logback.core.joran.action.IADataForComplexProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.javastart.equipy.assigment.Assignment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String pesel;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Assignment> assignments = new ArrayList<>();

}
