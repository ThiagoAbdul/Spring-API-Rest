package com.abdul.hellospring.controller;

import java.util.Collection;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.abdul.hellospring.model.exception.ResourceNotFoundException;
import com.abdul.hellospring.service.ProductService;
import com.abdul.hellospring.shared.ProductDTO;
import com.abdul.hellospring.view.model.ProductRequest;
import com.abdul.hellospring.view.model.ProductResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
     
    private final ProductService productService;

    @GetMapping({"", "/"})
    public ResponseEntity<? extends Collection<ProductResponse>> findAll(){
        var products = productService.findAll()
                                .stream()
                                .map(dto -> new ModelMapper().map(dto, ProductResponse.class))
                                .toList();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> find(@PathVariable Integer id){ // Tem que casar o nome do parametro
        return ResponseEntity.ok(
            productService.find(id)
            .map(dto -> new ModelMapper().map(dto, ProductResponse.class))
            .orElseThrow(ResourceNotFoundException::new)
        );
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ProductResponse> save(@RequestBody ProductRequest product){
        var mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
        try{
            var productResponse = mapper.map(
                productService.save(productDTO), 
                ProductResponse.class);
            System.out.println("Produto salvo");
            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Integer id, @RequestBody ProductRequest product){
        ModelMapper mapper = new ModelMapper();
        ProductDTO productDTO = mapper.map(product, ProductDTO.class);
        var productResponse = mapper.map(
            productService.update(id, productDTO),
            ProductResponse.class
        );
        return ResponseEntity.ok(productResponse) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Integer id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
