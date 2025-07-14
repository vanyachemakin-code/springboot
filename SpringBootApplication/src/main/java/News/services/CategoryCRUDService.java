package News.services;

import News.database.Category;
import News.dto.CategoryDto;
import News.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryCRUDService implements CRUDService<CategoryDto> {

    private final CategoryRepository repository;

    @Override
    public CategoryDto getById(int id) {

        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Категория с ID " + id + " не найдена.\"");
        }
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return repository.findAll()
                .stream()
                .map(CategoryCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CategoryDto dto) {
        repository.save(mapToDatabase(dto));
    }

    @Override
    public void update(int id, CategoryDto dto) {
        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Категория с ID " + id + " не найдена.\"");
        }
        dto.setId(id);
        repository.save(mapToDatabase(dto));
    }

    @Override
    public void deleteById(int id) {
        if (repository.findById(id).isEmpty()) {
            System.out.println("\"message\": \"Категория с ID " + id + " не найдена.\"");
        }
        repository.deleteById(id);
    }

    public static Category mapToDatabase(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setTitle(dto.getTitle());
        category.setNews(dto.getNews()
                .stream()
                .map(NewsCRUDService::mapToDatabase)
                .toList());
        return category;
    };

    public static CategoryDto mapToDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setTitle(category.getTitle());
        dto.setNews(category.getNews()
                .stream()
                .map(NewsCRUDService::mapToDto)
                .toList());
        return dto;
    }
}
