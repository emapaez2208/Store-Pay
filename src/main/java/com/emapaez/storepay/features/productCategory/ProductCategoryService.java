package com.emapaez.storepay.features.productCategory;

import com.emapaez.storepay.features.product.ProductRepository;
import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;
import com.emapaez.storepay.features.productCategory.domain.ProductCategoryMapper;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryRequest;
import com.emapaez.storepay.features.productCategory.domain.dto.ProductCategoryResponse;
import com.emapaez.storepay.features.productCategory.exception.ProductCategoryExistsWithNameException;
import com.emapaez.storepay.features.productCategory.exception.ProductCategoryNotFoundException;
import com.emapaez.storepay.features.productCategory.exception.ProductExistsWithThisCategoryException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductCategoryService implements IProductCategoryService{

    private final ProductCategoryRepository repository;
    private final ProductCategoryMapper mapper;
    private final ProductRepository productRepository;

    @Override
    public Page<ProductCategoryResponse> getAll(int page, int size, String name, String description){

        PredicateSpecification<ProductCategoryEntity> spec = PredicateSpecification.allOf(
                ProductCategorySpecification.nameContains(name),
                ProductCategorySpecification.descriptionContains(description)
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        return repository.findAll(Specification.where(spec), pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ProductCategoryResponse create(ProductCategoryRequest request){

        if(repository.existsByNameIgnoreCase(request.name())){
            throw new ProductCategoryExistsWithNameException();
        }
        ProductCategoryEntity entity = repository.save(mapper.toEntity(request));

        return mapper.toDto(entity);
    }

    @Override
    @Transactional
    public ProductCategoryResponse update(String oldName, ProductCategoryRequest request){

        if(!oldName.equals(request.name())){
            if(repository.existsByNameIgnoreCase(request.name())){
                throw new ProductCategoryExistsWithNameException();
            }
        }

        ProductCategoryEntity entity = repository.findByNameIgnoreCase(oldName)
                .orElseThrow(ProductCategoryNotFoundException::new);

        entity.setName(request.name());
        entity.setDescription(request.description());

        ProductCategoryEntity saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public void delete(String name){

        ProductCategoryEntity toBeDeleted = repository.findByNameIgnoreCase(name)
                .orElseThrow(ProductCategoryNotFoundException::new);

        if(productRepository.existsByProductCategoryName(name)){
            throw new ProductExistsWithThisCategoryException(
                    "There is one or more products in this category. the category cannot be deleted.");
        }

        repository.delete(toBeDeleted);
    }
}
