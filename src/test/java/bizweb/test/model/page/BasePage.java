package bizweb.test.model.page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePage {
    private String title;
    private String metaDescription;
    private String metaTitle;
    private String alias;
    private String templateLayout;
    private String content;
    private String publishedOn;
    private String author;
}
