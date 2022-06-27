package com.codegym.test.service.branch;

import com.codegym.test.model.entity.Branch;
import com.codegym.test.repository.IBrachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BranchService implements IBranchService{
    @Autowired
    private IBrachRepository brachRepository;

    @Override
    public Page<Branch> findAll(Pageable pageable) {
        return brachRepository.findAll(pageable);
    }

    @Override
    public Iterable<Branch> findAllNoPage() {
        return brachRepository.findAll();
    }

    @Override
    public Optional<Branch> findById(Long id) {
        return brachRepository.findById(id);
    }

    @Override
    public Branch save(Branch branch) {
        return brachRepository.save(branch);
    }

    @Override
    public void remove(Long id) {
        brachRepository.deleteById(id);
    }
}
