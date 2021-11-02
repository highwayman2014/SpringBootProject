package com.shepa.repositories;

import com.shepa.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findAll();

    Product findTopByOrderByCostDesc();
    Product findTopByOrderByCostAsc();
}
