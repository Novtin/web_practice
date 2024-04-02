package application.controllers;

import application.entity.Category;
import application.entity.Product;
import application.services.CategoryService;
import application.services.ProductService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/")
    public ResponseEntity<Map<Integer, List<Product>>> getAllProducts(@RequestBody List<Category> categories) {
        Map<Integer, List<Product>> products = productService.findAll(categories);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
        Optional<Product> product = productService.findById(productId);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @PatchMapping("/{productId}/{price}")
    public ResponseEntity<Product> updatePrice(@PathVariable Integer productId, @PathVariable Integer price) {
        Product patchedProduct = productService.updatePrice(productId, price);
        if (patchedProduct != null) {
            return new ResponseEntity<>(patchedProduct, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {
        productService.delete(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.OPTIONS, value = "/{productId}")
    public ResponseEntity<Void> options(@PathVariable String productId) {
        return ResponseEntity.ok().allow(HttpMethod.GET, HttpMethod.PUT,
                HttpMethod.DELETE, HttpMethod.OPTIONS,
                HttpMethod.HEAD, HttpMethod.PATCH).build();
    }

    @RequestMapping(method = RequestMethod.HEAD, value = "/{productId}")
    public ResponseEntity<Void> head(@PathVariable Integer productId) {
        boolean product = productService.existsById(productId);
        if (product) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}