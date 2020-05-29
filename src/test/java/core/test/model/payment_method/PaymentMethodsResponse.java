package core.test.model.payment_method;

import java.util.List;

import core.test.response.Metadata;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodsResponse {
	private List<PaymentMethod> paymentMethods;
	private Metadata metadata;
}
