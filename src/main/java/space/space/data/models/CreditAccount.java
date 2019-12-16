package space.space.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.base.BaseEntity;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_account")
public class CreditAccount extends BaseEntity {
    @Column()
    private long id;

    @Column()
    private double creditAmount;

    @Enumerated(EnumType.STRING)
    @Column
    private CreditType creditType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
