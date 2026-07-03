package com.emapaez.storepay.features.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.emapaez.storepay.features.product.ProductRepository;
import com.emapaez.storepay.features.product.domain.ProductMapper;
import com.emapaez.storepay.features.product.domain.ProductEntity;
import com.emapaez.storepay.features.productCategory.ProductCategoryRepository;
import com.emapaez.storepay.features.product.domain.dto.ProductRequest;
import com.emapaez.storepay.features.product.domain.dto.ProductResponse;
import com.emapaez.storepay.features.product.exception.ProductExistsWithNameException;
import com.emapaez.storepay.features.productCategory.exception.ProductCategoryNotFoundException;

import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ProductService extends IProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final ProductCategoryRepository productCategoryRepository;


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

        ProductEntity saved = repository.save(saved);

        return mapper.toDto(saved);
    }
  

}