package com.codegym.test.controller;

import com.codegym.test.model.entity.Branch;
import com.codegym.test.model.entity.Category;
import com.codegym.test.service.branch.IBranchService;
import com.codegym.test.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/categories")
public class CategoryRestController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<Category>> getAll(@PageableDefault(value = 10) Pageable pageable) {
        Page<Category> categories = this.categoryService.findAll(pageable);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        Optional<Category> category = this.categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return new ResponseEntity<>(this.categoryService.save(category), HttpStatus.CREATED) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateById(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = this.categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Category newCategory = categoryOptional.get();
        newCategory.setName(category.getName());
        this.categoryService.save(newCategory);
        return new ResponseEntity<>(newCategory, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        Optional<Category> categoryOptional = this.categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.categoryService.remove(id);
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }
}
