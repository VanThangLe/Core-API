package core.test.cucumber.stepdefs;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import core.test.model.page.PageRequest;
import core.test.model.page.PageResponse;
import core.test.model.page.PagesResponse;
import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.val;

public class PageStepDefs extends StepDefs {
	public static final String TITLE = "Tiêu đề";
	public static final String ALIAS = "tieu-de";
	public static final String PUBLISH = "2014-07-16T20:00:00Z";
	public static final String CONTENT = "<h1>Warranty</h1><p><strong>Forget it</strong>, we aint giving you nothing</p>";
	public static final String META_DESCRIPTION = "Mô tả Seo X";
	public static final String META_TITLE = "Tiêu đề Seo";
	public static final String TEMPLATE_LAYOUT = "page";
	public static final String AUTHOR = "Bích Hà";
	public static final String APP_NAME = "Ứng dụng test Cấm xóa";

	private PageRequest request = createPageEntity();
	public static int idPage;
	public static List<Integer> idPages = new ArrayList<>();

	private PageRequest createPageEntity() {
		PageRequest request = new PageRequest();
		request.setTitle(TITLE);
		request.setAlias(ALIAS);
		request.setContent(CONTENT);
		request.setTemplateLayout(TEMPLATE_LAYOUT);
		request.setPublishedOn(PUBLISH);
		request.setMetaTitle(META_TITLE);
		request.setMetaDescription(META_DESCRIPTION);
		return request;
	}

	@Before
	public void setup() {
		request = createPageEntity();
	}

	@Given("^I create Page request$")
	public void i_create_Page_request() throws Exception {
		HttpEntity<PageRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("pages.json"), requestEntity, PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			idPage = entity.getBody().getPage().getId();
			assertThat(entity.getBody().getPage().getTitle()).isEqualTo(TITLE);
			assertThat(entity.getBody().getPage().getContent()).isEqualTo(CONTENT);
			assertThat(entity.getBody().getPage().getAlias()).isEqualTo(ALIAS);
			assertThat(entity.getBody().getPage().getTemplateLayout()).isEqualTo(TEMPLATE_LAYOUT);
			assertThat(entity.getBody().getPage().getContent()).isEqualTo(CONTENT);
			assertThat(entity.getBody().getPage().getMetaTitle()).isEqualTo(META_TITLE);
			assertThat(entity.getBody().getPage().getMetaDescription()).isEqualTo(META_DESCRIPTION);
		}
	}

	@Given("^I change title to '(.*)'$")
	public void i_change_title_to(String title) throws Exception {
		request.setTitle(title);
		HttpEntity<PageRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("pages/" + idPage + ".json"), HttpMethod.PUT, requestEntity,
				PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if (entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getPage().getTitle()).isEqualTo(title);
		}
	}

	@When("^I change content to '(.*)'$")
	public void i_change_content_to_update_content(String content) throws Exception {
		request.setContent(content);
		HttpEntity<PageRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.exchange(getUrl("pages/" + idPage + ".json"), HttpMethod.PUT, requestEntity,
				PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		if (entity.getStatusCodeValue() == 200) {
			assertThat(entity.getBody().getPage().getContent()).isEqualTo(content);
		}
	}

	@When("^I get a single page$")
	public void i_get_a_single_page() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("pages/" + idPage + ".json"), PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().getPage().getId()).isEqualTo(idPage);
	}

	@When("^I get list pages$")
	public void i_get_list_page() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("pages.json"), PagesResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		// assertThat(entity.getBody().getPages().get(0).getId()).isEqualTo(idPage);
		// assertThat(entity.getBody().getPages().size()).isEqualTo(6);
	}

	@When("^I delete page$")
	public void i_delete_page() throws Throwable {
		val entity = restTemplate.exchange(getUrl("pages/" + idPage + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@When("^I create some Page request$")
	public void i_create_some_Page_request(DataTable table) throws Throwable {
		List<List<String>> data = table.raw();
		for (int i = 1; i < data.size(); i++) {
			PageRequest request = new PageRequest();
			request.setTitle(data.get(i).get(0));
			HttpEntity<PageRequest> requestEntity = new HttpEntity<>(request, headers);
			val entity = restTemplate.postForEntity(getUrl("pages.json"), requestEntity, PageResponse.class);
			assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
			if (entity.getStatusCodeValue() == 201) {
				idPages.add(entity.getBody().getPage().getId());
				assertThat(entity.getBody().getPage().getTitle()).isEqualTo(data.get(i).get(0));
				assertThat(entity.getBody().getPage().getAlias()).isEqualTo(data.get(i).get(1));
				assertThat(entity.getBody().getPage().getContent()).isEqualTo(data.get(i).get(2));
			}
		}
	}
	
	@When("^I create page request with ([^\"]*) and ([^\"]*)$")
	public void i_create_page_request(String title, String content) throws Throwable {
		PageRequest request = new PageRequest();
		request.setTitle(title);
		request.setContent(content);
		HttpEntity<PageRequest> requestEntity = new HttpEntity<>(request, headers);
		val entity = restTemplate.postForEntity(getUrl("pages.json"), requestEntity, PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		if (entity.getStatusCodeValue() == 201) {
			idPage = entity.getBody().getPage().getId();
			assertThat(entity.getBody().getPage().getTitle()).isEqualTo(title);
			assertThat(entity.getBody().getPage().getContent()).isEqualTo(content);
		}
	}

	@Then("^I get details$")
	public void i_get_details() throws Throwable {
		val entity = restTemplate.getForEntity(getUrl("pages/" + idPage + ".json"), PageResponse.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	@Then("^I delete it$")
	public void i_delete_it() throws Throwable {
		val entity = restTemplate.exchange(getUrl("pages/" + idPage + ".json"), HttpMethod.DELETE, null, Void.class);
		assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}

