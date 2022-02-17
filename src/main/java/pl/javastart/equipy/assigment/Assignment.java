package pl.javastart.equipy.assigment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.javastart.equipy.asset.Asset;
import pl.javastart.equipy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    private User user;
    @ManyToOne
    private Asset asset;

    public Assignment(User user, Asset asset) {
        this.user = user;
        this.asset = asset;
        this.start = LocalDateTime.now();
    }
}
