package application.repositories;

import application.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findFirstByOrderByProductIdDesc();
    List<Product> findAllByCategoryId(Integer categoryId);
}
