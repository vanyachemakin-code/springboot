package News.dto;

import lombok.Data;

import java.time.Instant;


@Data
public class NewsDto {

    private long id;
    private long categoryId;
    private String title;
    private String text;
    private Instant date;

    public NewsDto() {
        if (date == null) {
            date = Instant.now();
        }
    }
}
