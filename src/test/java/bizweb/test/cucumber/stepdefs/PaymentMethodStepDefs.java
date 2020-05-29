package bizweb.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import bizweb.test.model.payment_method.PaymentMethodRequest;
import bizweb.test.model.payment_method.PaymentMethodResponse;
import bizweb.test.model.payment_method.PaymentMethodsResponse;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import lombok.val;

public class PaymentMethodStepDefs extends StepDefs{
	public static final String NAME = "Thich thanh toan";
	public static final String TYPE = "point";
	
	private PaymentMethodRequest request = createPaymentMethodEntity();
    public static int idPaymentMethod;
    private PaymentMethodRequest createPaymentMethodEntity() {
        PaymentMethodRequest request = new PaymentMethodRequest();
        request.setName(NAME);
        request.setType(TYPE);
		return request;
    }

    @Before
    public void setup() {
        request = createPaymentMethodEntity();
    }

	@Given("^I post a payment method$")
	public void i_post_a_payment_method() throws Throwable {
		HttpEntity<PaymentMethodRequest> requestEntity = new HttpEntity<>(request, headers);
        val entity = restTemplate.postForEntity(getUrl("payment_methods.json"), requestEntity, PaymentMethodResponse.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        if(entity.getStatusCodeValue() == 201){
            idPaymentMethod = entity.getBody().getPaymentMethod().getId();
            assertThat(entity.getBody().getPaymentMethod().getName()).isEqualTo(NAME);
            assertThat(entity.getBody().getPaymentMethod().getType()).isEqualTo(TYPE);
            assertThat(entity.getBody().getPaymentMethod().getTenantId()).isEqualTo(0);
            assertThat(entity.getBody().getPaymentMethod().getStatus()).isEqualTo("active");
            assertThat(entity.getBody().getPaymentMethod().getInit()).isFalse();
            Thread.sleep(2000);
//            assertThat(entity.getBody().getPaymentMethod().getRemovalbe()).isTrue();
        }
	}

	@Given("^I put a payment method with name: '(.*)'$")
	public void i_put_a_payment_method_with_name(String name) throws Throwable {
		request.setName(name);
		HttpEntity<PaymentMethodRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("payment_methods/"+ idPaymentMethod + ".json"), HttpMethod.PUT, requestEntity, PaymentMethodResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200){
			assertThat(entity.getBody().getPaymentMethod().getId()).isEqualTo(idPaymentMethod);
			assertThat(entity.getBody().getPaymentMethod().getName()).isEqualTo(name);
            assertThat(entity.getBody().getPaymentMethod().getType()).isEqualTo(TYPE);
            assertThat(entity.getBody().getPaymentMethod().getTenantId()).isEqualTo(0);
            assertThat(entity.getBody().getPaymentMethod().getStatus()).isEqualTo("active");
            assertThat(entity.getBody().getPaymentMethod().getInit()).isFalse();
		}
	}

	@Given("^I get a single payment method$")
	public void i_get_a_single_payment_method() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("payment_methods/" + idPaymentMethod + ".json"), PaymentMethodResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200){
			assertThat(entity.getBody().getPaymentMethod().getId()).isEqualTo(idPaymentMethod);
			assertThat(entity.getBody().getPaymentMethod().getName()).isEqualTo("update name");
            assertThat(entity.getBody().getPaymentMethod().getType()).isEqualTo(TYPE);
            assertThat(entity.getBody().getPaymentMethod().getTenantId()).isEqualTo(0);
            assertThat(entity.getBody().getPaymentMethod().getStatus()).isEqualTo("active");
            assertThat(entity.getBody().getPaymentMethod().getInit()).isFalse();
		}
	}

	@Given("^I get list payment method$")
	public void i_get_list_payment_method() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("payment_methods.json"), PaymentMethodsResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if(entity.getStatusCodeValue() == 200){
			assertThat(entity.getBody().getMetadata().getTotal()).isEqualTo(1);
			assertThat(entity.getBody().getPaymentMethods().size()).isEqualTo(1);
		}
	}

	@Then("^I delete a payment method$")
	public void i_delete_a_payment_method() throws Throwable {
		val entity = restTemplate.exchange(getUrl("payment_methods/" + idPaymentMethod + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

}
