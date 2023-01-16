package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.annotation.product.ProductStandard;
import br.com.sicredi.bank.controller.response.product.ProductResponse;
import br.com.sicredi.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    @ProductStandard
    public ResponseEntity<List<ProductResponse>> listProducts(@RequestParam BigDecimal salary) {
        return ResponseEntity.ok().body(productService.listProducts(salary));
    }

}
