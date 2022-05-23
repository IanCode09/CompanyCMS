package com.woyo.cms.controller;

import com.woyo.cms.DTO.ProductDTO;
import com.woyo.cms.model.request.CreateProductRequestModel;
import com.woyo.cms.response.DataResponse;
import com.woyo.cms.response.HandlerResponse;
import com.woyo.cms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/product", produces = {"application/json"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public void createProduct(HttpServletResponse response, @RequestBody CreateProductRequestModel productRequestModel) {
        ProductDTO productDTO = productService.createProduct(productRequestModel);

        DataResponse<ProductDTO> data = new DataResponse<>();
        data.setData(productDTO);
        HandlerResponse.responseSuccessCreatedWithData(response, data);
    }

    @GetMapping("/{productId}")
    public void getProductById(HttpServletResponse response, @PathVariable int productId) {
        ProductDTO productDTO = productService.getProductById(productId);

        if (productDTO != null) {
            DataResponse<ProductDTO> data = new DataResponse<>();
            data.setData(productDTO);
            HandlerResponse.responseSuccessCreatedWithData(response, data);
        } else {
            HandlerResponse.responseNotFoundRequest(response, "404", "Product not found");
        }
    }

    @GetMapping("/all")
    public void getAllProduct(HttpServletResponse response) {
        List<ProductDTO> productDTOS = productService.getAllProduct();

        DataResponse<List<ProductDTO>> data = new DataResponse<>();
        data.setData(productDTOS);
        HandlerResponse.responseSuccessWithData(response, data);
    }
}
