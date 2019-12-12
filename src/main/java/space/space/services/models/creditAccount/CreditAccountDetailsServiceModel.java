package space.space.services.models.creditAccount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import space.space.data.models.CreditType;

@Getter
@Setter
@NoArgsConstructor
public class CreditAccountDetailsServiceModel {
    private long creditAmount;
    private CreditType creditType;

}
