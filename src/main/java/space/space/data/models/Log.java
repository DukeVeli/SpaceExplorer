package space.space.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.base.BaseEntity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "logs")
public class Log extends BaseEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "time", nullable = false)
    private LocalDateTime time;


}
