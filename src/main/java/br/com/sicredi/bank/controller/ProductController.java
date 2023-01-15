package br.com.sicredi.bank.controller;

import br.com.sicredi.bank.controller.request.product.ProductRequest;
import br.com.sicredi.bank.controller.response.product.ProductResponse;
import br.com.sicredi.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/consigned")
    public ResponseEntity<ProductResponse> consigned(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.consigned(request));
    }

    @PostMapping("/financing")
    public ResponseEntity<ProductResponse> financing(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.financing(request));
    }

    @PostMapping("/personal")
    public ResponseEntity<ProductResponse> personal(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok().body(productService.personal(request));
    }

}
