package core.test.model.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Page extends BasePage {
	private int id;
    private String createdOn;
    private String modifiedOn;
}
