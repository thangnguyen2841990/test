package com.codegym.test.model.dto;

import com.codegym.test.model.entity.Branch;
import com.codegym.test.model.entity.Category;
import com.codegym.test.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private String name;

    private double price;

    private String description;

    private int quantity;

    private Category category;

    private Branch branch;

    private List<Image> images;
}
