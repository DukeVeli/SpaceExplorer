package space.space.web.view.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.CreditType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditAddModel {
    private double creditAmount;
    private CreditType creditType;
    /*private String username;*/
}
