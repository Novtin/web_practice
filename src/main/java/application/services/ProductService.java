package application.services;

import application.entity.Category;
import application.entity.Product;
import application.entity.PushNotifyConf;
import application.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {
    private final FcmService fcmService;
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository, FcmService fcmService) {
        this.productRepository = productRepository;
        this.fcmService = fcmService;
    }

    public Optional<Product> findById(int id){
        return productRepository.findById(id);
    }

    public Map<Integer, List<Product>> findAll(List<Category> categories){
        Map<Integer, List<Product>> productMap = new HashMap<>();
        for (Category category: categories) {
            productMap.put(category.getCategoryId(), productRepository.findAllByCategoryId(category.getCategoryId()));
        }
        return productMap;
    }

    public Product save(Product product){
        productRepository.save(product);
        PushNotifyConf pushMessage = new PushNotifyConf("Добавление продукта",
                "Продукт " + product.getName() + " был добавлен.",
                "http://localhost:8080/menu", "1000");
        try {
            fcmService.sendByTopic(pushMessage, "product_updates");
        } catch (InterruptedException | ExecutionException e)  {
            System.out.println("Ошибка отправки push уведомления о сохранении");
        }
        return productRepository.findFirstByOrderByProductIdDesc();
    }

    public Product update(Product product){
        productRepository.save(product);
        PushNotifyConf pushMessage = new PushNotifyConf("Изменение продукта",
                "Продукт " + product.getName() + " был изменён.",
                "http://localhost:8080/menu", "1000");
        try {
            fcmService.sendByTopic(pushMessage, "product_updates");
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ошибка отправки push уведомления об изменениях");
        }
        return productRepository.findFirstByOrderByProductIdDesc();
    }

    public void delete(int productId){
        if (productRepository.existsById(productId)) {
            Optional<Product> product = productRepository.findById(productId);
            productRepository.deleteById(productId);
            PushNotifyConf pushMessage = new PushNotifyConf("Удаление продукта",
                    "Продукт " + product.get().getName() + " был удалён.",
                    "http://localhost:8080/menu", "1000");
            try {
                fcmService.sendByTopic(pushMessage, "product_updates");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Ошибка отправки push уведомления об удалении");
            }
        }
    }

    public boolean existsById(int productId){
        return productRepository.existsById(productId);
    }

    public Product updatePrice(int productId, int price){
        Optional<Product> productFromDB = productRepository.findById(productId);
        if (productFromDB.isPresent()){
            Product product = productFromDB.get();
            product.setPrice(price);
            productRepository.save(product);
            PushNotifyConf pushMessage = new PushNotifyConf("Изменение цены продукта",
                    "Цена продукта " + product.getName() + " была изменёна.",
                    "http://localhost:8080/menu", "1000");
            try {
                fcmService.sendByTopic(pushMessage, "product_updates");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Ошибка отправки push уведомления об изменении цены");
            }
            return product;
        }
        return null;
    }

}
