package com.codegym.test.controller;

import com.codegym.test.model.entity.Product;
import com.codegym.test.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(@PageableDefault(value = 12) Pageable pageable) {
        Page<Product> products = this.productService.findAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return new ResponseEntity<>(this.productService.save(product), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Product newProduct = productOptional.get();
        newProduct.setBranch(product.getBranch());
        newProduct.setCategory(product.getCategory());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setImage(product.getImage());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setDescription(product.getDescription());
        this.productService.save(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable Long id) {
        Optional<Product> productOptional = this.productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.productService.remove(id);
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }
}
