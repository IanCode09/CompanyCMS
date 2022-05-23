package com.woyo.cms.service.impl;

import com.woyo.cms.DTO.ProductDTO;
import com.woyo.cms.model.ProductModel;
import com.woyo.cms.model.request.CreateProductRequestModel;
import com.woyo.cms.repository.CompanyRepository;
import com.woyo.cms.repository.ProductRepository;
import com.woyo.cms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    protected ProductRepository productRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public ProductDTO createProduct(CreateProductRequestModel productRequestModel) {
        ProductModel product = new ProductModel();
        product.setProductName(productRequestModel.getProductName());
        product.setPrice(productRequestModel.getPrice());
        product.setStock(productRequestModel.getStock());
        product.setCreatedAt(LocalDateTime.now());

        return convertProductDTO(productRepository.save(product));
    }

    @Override
    public ProductDTO getProductById(int productId) {
        Optional<ProductModel> product = productRepository.findById(productId);

        if (product.isPresent()) {
            return convertProductDTO(product.get());
        } else {
            return null;
        }
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        return productRepository.findAll().stream().map(this::convertProductDTO).collect(Collectors.toList());
    }

    public ProductDTO convertProductDTO(ProductModel item) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(item.getProductId());
        productDTO.setProductName(item.getProductName());
        productDTO.setPrice(item.getPrice());
        productDTO.setStock(item.getStock());
        productDTO.setCreatedAt(item.getCreatedAt());

        return productDTO;
    }
}
