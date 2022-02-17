package pl.javastart.equipy.asset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.javastart.equipy.assigment.Assignment;
import pl.javastart.equipy.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    @ManyToOne
    private Category category;
    @OneToMany
    @JoinColumn(name = "asset_id")
    private List<Assignment> assignments = new ArrayList<>();

}
