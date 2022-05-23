package com.woyo.cms.service;

import com.woyo.cms.DTO.ProductDTO;
import com.woyo.cms.model.request.CreateProductRequestModel;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(CreateProductRequestModel productRequestModel);
    ProductDTO getProductById(int productId);
    List<ProductDTO> getAllProduct();
}
