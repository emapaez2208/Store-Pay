package com.emapaez.storepay.features.product;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.emapaez.storepay.features.product.domain.ProductMapper;
import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;
import com.emapaez.storepay.features.product.exception.ProductExistsWithNameException;
import com.emapaez.storepay.features.product.exception.ProductNotFoundException;
import com.emapaez.storepay.features.product.exception.StoreProductExistsWithProductException;
import com.emapaez.storepay.features.productCategory.exception.ProductCategoryNotFoundException;
import com.emapaez.storepay.features.storeProduct.StoreProductRepository;
import com.emapaez.storepay.features.productCategory.ProductCategoryRepository;
import com.emapaez.storepay.features.productCategory.domain.ProductCategoryEntity;

import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductCategoryRepository productCategoryRepository;
    private final StoreProductRepository storeProductRepository;

    /// ------------------------ PRIVATE METHOD -------------------------- ///

    private ProductEntity getByExternalId(UUID externalId){
        return repository.findByExternalId(externalId)
                        .orElseThrow(ProductNotFoundException::new);
    }
    /// ------------------------ PRIVATE METHOD -------------------------- ///


    @Override
    @Transactional
    public ProductResponse create(ProductRequest request){
        
        if(repository.existsByNameIgnoreCase(request.name())){

            throw new ProductExistsWithNameException();
        }

        ProductCategoryEntity productCategory = productCategoryRepository.findByNameIgnoreCase(request.productCategory())
                                                    .orElseThrow(ProductCategoryNotFoundException::new);

        ProductEntity entity = mapper.toEntity(request);
        entity.setProductCategory(productCategory);

        ProductEntity saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ProductResponse update(UUID externalId, ProductRequest request){

        if(repository.existsByNameIgnoreCase(request.name())){

            throw new ProductExistsWithNameException();
        }

        ProductEntity entity = getByExternalId(externalId);
        ProductCategoryEntity productCategory = productCategoryRepository.findByNameIgnoreCase(request.productCategory())
                                                    .orElseThrow(ProductCategoryNotFoundException::new);

        entity.setName(request.name());
        entity.setDescription(request.description());
        entity.setProductCategory(productCategory);
        entity.setSuggestedPrice(request.suggestedPrice());

        ProductEntity saved = repository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public ProductResponse findByExternalId(UUID externalId){
        return mapper.toDto(getByExternalId(externalId));
    }

    @Override
    @Transactional
    public void delete(UUID externalId){

        ProductEntity entity = getByExternalId(externalId);

        if(storeProductRepository.existsByProduct(entity)){
            throw new StoreProductExistsWithProductException("There is one or more store products in this Product. the Product cannot be deleted.");
        }

        entity.setEnable(false);
        repository.save(entity);
    }
  

    @Override
    public Page<ProductResponse> getAll(int page,
                                        int size,
                                        String name,
                                        String description,
                                        BigDecimal suggestedPriceMin,
                                        BigDecimal suggestedPriceMax,
                                        String productCategory,
                                        Boolean enable){


        PredicateSpecification<ProductEntity> spec = PredicateSpecification.allOf(
            ProductSpecification.nameContains(name),
            ProductSpecification.descriptionContains(description),
            ProductSpecification.suggestedPriceBetween(suggestedPriceMin, suggestedPriceMax),
            ProductSpecification.productCategoryEqual(productCategory),
            ProductSpecification.enableEqual(enable)
        );

        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        return repository.findAll(Specification.where(spec), pageable)
                    .map(mapper::toDto);

    }

}