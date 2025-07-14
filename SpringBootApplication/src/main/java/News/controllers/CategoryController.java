package News.controllers;

import News.dto.CategoryDto;
import News.services.CategoryCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryCRUDService service;

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping
    public Collection<CategoryDto> getAllCategory() {
        return service.getAll();
    }

    @PostMapping
    public void createCategory(@RequestBody CategoryDto category) {
        service.create(category);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable int id, @RequestBody CategoryDto category) {
        service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        service.deleteById(id);
    }

}
