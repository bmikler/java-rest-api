package pl.javastart.equipy.category.dto;

import org.springframework.stereotype.Component;
import pl.javastart.equipy.category.Category;
import pl.javastart.equipy.category.CategoryRepository;

@Component
public class CategoryNameMapper {

    private final CategoryRepository categoryRepository;

    public CategoryNameMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public String map (Category category) {

        return category.getName();
    }

    public Category map (String category) {

        return categoryRepository.findByName(category)
                .orElseThrow(CategoryNotFoundException::new);


    }

}
