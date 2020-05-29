package bizweb.test.cucumber.stepdefs;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.When;

public class ProductStepDefs extends StepDefs{
	@When("^I post some product$")
	public void i_post_some_product(DataTable table) throws Throwable {
		List<List<String>> data = table.raw();
//		System.out.println(data.get(1).get(0));
		for(int i = 1; i<data.size(); i++){
			System.out.println("Danh sách sản phẩm");
			System.out.println(data.get(i).get(0));
			System.out.println(data.get(i).get(1));
			System.out.println(data.get(i).get(2));
			System.out.println(data.get(i).get(3));
			System.out.println(data.get(i).get(4));
		}
	}
	
	@When("^I post product with ([^\"]*) and ([^\"]*) and ([^\"]*)$")
	public void i_post_product(String name, String vendor, String product_type) throws Throwable {
		System.out.println(name);
	}
}
