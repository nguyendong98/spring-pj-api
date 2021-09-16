package com.springboot.training.controllers.api;

import com.springboot.training.controllers.request.ProductCreateRequest;
import com.springboot.training.dto.response.Response;
import com.springboot.training.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public Response getAllProduct() {
        return Response
                .ok()
                .setPayload(productService.getAllProduct());
    }

    @PostMapping("/create")
    public Response createProduct(@RequestBody ProductCreateRequest productCreateRequest) {
        return Response.ok().setPayload(productService.createProduct(productCreateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public Response deleteById(@PathVariable Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "OPTIONS,HEAD,GET,POST,PUT");
        return Response.ok().setPayload(productService.deleteById(id));
    }


}
