package core.test.model.payment_method;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BasePaymentMethod{
	private String name;
	private String status;
	private Boolean init;
	private Boolean removalbe;
	private String type;
}