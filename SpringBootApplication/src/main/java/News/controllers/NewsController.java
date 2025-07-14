package News.controllers;

import News.dto.NewsDto;
import News.services.NewsCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsCRUDService service;

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public Collection<NewsDto> getAllNews() {
        return service.getAll();
    }

    @PostMapping
    public void createNews(@RequestBody NewsDto news) {

        service.create(news);
    }

    @PutMapping("/{id}")
    public void updateNews (@PathVariable int id, @RequestBody NewsDto news) {
        service.update(id, news);
    }

    @DeleteMapping("/{id}")
    public void deleteNewsById(@PathVariable int id) {
        service.deleteById(id);
    }
}
