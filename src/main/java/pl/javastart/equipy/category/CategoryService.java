package pl.javastart.equipy.category;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.category.dto.CategoryNameMapper;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryNameMapper categoryNameMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryNameMapper categoryNameMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryNameMapper = categoryNameMapper;
    }

    public List<String> getCategoriesNames() {

        return categoryRepository.findAll()
                .stream()
                .map(categoryNameMapper::map)
                .toList();

    }

}
