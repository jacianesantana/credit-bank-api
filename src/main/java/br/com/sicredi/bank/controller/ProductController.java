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

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/consigned")
    @ProductStandard
    public ResponseEntity<ProductResponse> consigned(@RequestParam BigDecimal salary) {
        return ResponseEntity.ok().body(productService.consigned(salary));
    }

    @GetMapping("/financing")
    @ProductStandard
    public ResponseEntity<ProductResponse> financing(@RequestParam BigDecimal salary) {
        return ResponseEntity.ok().body(productService.financing(salary));
    }

    @GetMapping("/personal")
    @ProductStandard
    public ResponseEntity<ProductResponse> personal(@RequestParam BigDecimal salary) {
        return ResponseEntity.ok().body(productService.personal(salary));
    }

}
