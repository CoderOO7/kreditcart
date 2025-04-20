package com.kreditcart.productCatalogue.Services;

import com.kreditcart.productCatalogue.Dtos.UserDto;
import com.kreditcart.productCatalogue.Exceptions.CategoryNotFoundException;
import com.kreditcart.productCatalogue.Exceptions.ProductNotFoundException;
import com.kreditcart.productCatalogue.Models.Category;
import com.kreditcart.productCatalogue.Models.Product;
import com.kreditcart.productCatalogue.Repositories.CategoryRepo;
import com.kreditcart.productCatalogue.Repositories.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Comment below annotation to use StubProductService for test
@Service
public class StorageProductService implements IProductService {
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;
    private RestTemplate restTemplate;

    public StorageProductService(RestTemplate restTemplate, ProductRepo productRepo, CategoryRepo categoryRepo){
        this.restTemplate = restTemplate;
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Product> getAllProducts() {
        return this.productRepo.findAll();
    }

    // created to test service to service api call using service discovery
    @Override
    public Product getProductDetails(UUID userId, UUID productId) {
        Product product = productRepo.findProductById(productId);
//        RestTemplate restTemplate =  new RestTemplate();x
        UserDto userDto = restTemplate.getForEntity("http://userservice/kreditcart-user-svc/users/{id}", UserDto.class, userId).getBody();
        System.out.println("userEmail:" + userDto.getEmail());
        return product;
    }

    @Override
    public Product getProduct(UUID productId) {
        return  this.productRepo
                        .findById(productId)
                        .orElseThrow(()-> new ProductNotFoundException(String.format("Product not found with given id %s", productId)));
    }

    @Override
    public Product createProduct(Product product) {
        return this.productRepo.save(product);
    }

    @Override
    public Product updateProduct(UUID id, Map<String, Object> updates) {

        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Product.class, key);

            if (field != null) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();

                Object finalValue = value;

                if (fieldType.equals(Double.class) && value instanceof Integer) {
                    finalValue = ((Integer) value).doubleValue(); // converting to double
                }

                else if (fieldType.equals(Category.class)) {
                    UUID categoryId = UUID.fromString(value.toString());
                    finalValue = categoryRepo.findById(categoryId)
                            .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
                }

                ReflectionUtils.setField(field, product, finalValue);
            }
        });
        return productRepo.save(product);
    }
}
