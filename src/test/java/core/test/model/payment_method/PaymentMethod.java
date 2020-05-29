package core.test.model.payment_method;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethod extends BasePaymentMethod{
	private int id;
	private int tenantId;
    private String createdOn;
    private String modifiedOn;
}
