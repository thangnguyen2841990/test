package com.codegym.test.controller;

import com.codegym.test.model.entity.Branch;
import com.codegym.test.service.branch.IBranchService;
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
@RequestMapping("/branchs")
public class BranchRestController {
    @Autowired
    private IBranchService branchService;

    @GetMapping
    public ResponseEntity<Page<Branch>> getAll(@PageableDefault(value = 10) Pageable pageable) {
        Page<Branch> branchs = this.branchService.findAll(pageable);
        return new ResponseEntity<>(branchs, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Branch> getById(@PathVariable Long id) {
        Optional<Branch> branch = this.branchService.findById(id);
        if (!branch.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(branch.get(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Branch> save(@RequestBody Branch branch) {
        return new ResponseEntity<>(this.branchService.save(branch), HttpStatus.CREATED) ;
    }
    @PutMapping("/{id}")
    public ResponseEntity<Branch> updateById(@PathVariable Long id, @RequestBody Branch branch) {
        Optional<Branch> branchOptional = this.branchService.findById(id);
        if (!branchOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Branch newBranch = branchOptional.get();
        newBranch.setName(branch.getName());
        this.branchService.save(newBranch);
        return new ResponseEntity<>(newBranch, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Branch> deleteById(@PathVariable Long id) {
        Optional<Branch> branch = this.branchService.findById(id);
        if (!branch.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.branchService.remove(id);
        return new ResponseEntity<>(branch.get(), HttpStatus.OK);
    }

}
