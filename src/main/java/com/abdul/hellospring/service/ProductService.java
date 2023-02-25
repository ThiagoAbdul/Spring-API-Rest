package com.abdul.hellospring.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.abdul.hellospring.model.Product;
import com.abdul.hellospring.model.exception.ResourceNotFoundException;
import com.abdul.hellospring.repository.ProductRepository;
import com.abdul.hellospring.service.exception.InvalidProductException;
import com.abdul.hellospring.shared.ProductDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService{

    private final ProductRepository productRepository;

    public Collection<ProductDTO> findAll() {
        var products = new LinkedList<ProductDTO>(
                productRepository.findAll()
                                .stream()
                                .map(this::parseProductToProductDTO)
                                .toList()
        );
        products.sort((a, b) -> a.getId().compareTo(b.getId()));
        return products;
    }

    public Optional<ProductDTO> find(Integer primaryKey) {
        if(isValidId(primaryKey)){
            Optional<Product> product = productRepository.findById(primaryKey);
            return product.map(this::parseProductToProductDTO);
            
        }
        throw new ResourceNotFoundException();
    }

    public ProductDTO save(ProductDTO productDTO) {
        if(isValidPrice(productDTO.getPrice())){
            Product product = parseProductDTOToProduct(productDTO);
            productRepository.save(product);
            productDTO.setId(product.getId());
            return productDTO;
        }
        else{
            throw new InvalidProductException();
        }
    }

    public ProductDTO update(Integer id, ProductDTO productDTO) {
        productDTO.setId(id);
        Product product = parseProductDTOToProduct(productDTO);
        if(isValidProduct(product)){
            productRepository.save(product);
            return productDTO;
        }
        else{
            throw new InvalidProductException();
        }
    }

    public void delete(Integer primaryKey) {
        if(isValidId(primaryKey)){
            try{
                productRepository.deleteById(primaryKey);
            }
            catch(Exception e){
                throw new ResourceNotFoundException();
            }
        }
        else{
            throw new InvalidProductException();
        }
    }

    private boolean isValidId(Integer id){
        return id != null && id > 0;
    }

    private boolean isValidPrice(Float price){
        return price != null && price > 0;
    }

    private boolean isValidProduct(Product product){
        return isValidId(product.getId()) && isValidPrice(product.getPrice());
    }

    private ProductDTO parseProductToProductDTO(Product product){
        return new ModelMapper().map(product, ProductDTO.class);
    }


    private Product parseProductDTOToProduct(ProductDTO product){
        return new ModelMapper().map(product, Product.class);
    }
    
}
