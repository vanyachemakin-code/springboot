package News.services;

import News.database.Category;
import News.database.News;
import News.dto.NewsDto;
import News.repository.CategoryRepository;
import News.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class NewsCRUDService implements CRUDService<NewsDto> {

    private final NewsRepository repository;
    private final CategoryRepository categoryRepository;

    @Override
    public NewsDto getById(int id) {
        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Новость с ID " + id + " не найдена.\"");
        }
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<NewsDto> getAll() {
        return repository.findAll()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(NewsDto dto) {
        News news = mapToDatabase(dto);
        setCategory(dto, news);
        repository.save(news);
    }

    @Override
    public void update(int id, NewsDto dto) {
        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Новость с ID " + id + " не найдена.\"");
            return;
        }
        dto.setId(id);
        News news = mapToDatabase(dto);
        setCategory(dto, news);
        repository.save(news);
    }

    @Override
    public void deleteById(int id) {
        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Новость с ID " + id + " не найдена.\"");
        }
        repository.deleteById(id);
    }

    public static News mapToDatabase(NewsDto dto) {
        News news = new News();
        news.setId(dto.getId());
        news.setTitle(dto.getTitle());
        news.setText(dto.getText());
        news.setDate(dto.getDate());
        return news;
    }

    public static NewsDto mapToDto(News news) {
        NewsDto dto = new NewsDto();
        dto.setId(news.getId());
        dto.setCategoryId(news.getCategory().getId());
        dto.setTitle(news.getTitle());
        dto.setText(news.getText());
        dto.setDate(news.getDate());
        return dto;
    }
    private void setCategory(NewsDto dto, News news) {
        Long categoryId = dto.getCategoryId();
        Category category = categoryRepository.findById(categoryId.intValue()).orElseThrow();
        news.setCategory(category);
    }
}
