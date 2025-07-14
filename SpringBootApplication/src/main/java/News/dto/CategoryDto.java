package News.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class CategoryDto {

    private long id;
    private String title;
    private List<NewsDto> news;

    public CategoryDto() {
        news = new ArrayList<>();
    }
}
