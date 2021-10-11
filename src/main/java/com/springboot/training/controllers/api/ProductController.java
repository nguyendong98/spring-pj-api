package com.springboot.training.controllers.api;

import com.springboot.training.dto.model.ProductDto;
import com.springboot.training.dto.response.Response;
import com.springboot.training.models.Product;
import com.springboot.training.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@Api
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public Response getAllProduct() {
        return Response
                .ok()
                .setPayload(productService.getAllProduct());
    }

    @PostMapping("/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response createProduct(@RequestBody Product product) {
        return Response.ok().setPayload(productService.createProduct(product));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public Response deleteById(@PathVariable Long id) {
        return Response.ok().setPayload(productService.deleteById(id));
    }
}
