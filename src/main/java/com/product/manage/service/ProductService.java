package com.product.manage.service;

import com.product.manage.repository.model.Product;
import com.product.manage.repository.ProductRepository;
import com.product.manage.dto.ProductDto;
import com.product.manage.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Mettre à jour l'entité existante avec les données du DTO
                    productMapper.updateEntityFromDTO(productDto, existingProduct);
                    // Mettre à jour la date de modification
                    existingProduct.setUpdatedAt(LocalDateTime.now());
                    // Sauvegarder l'entité mise à jour
                    Product updatedProduct = productRepository.save(existingProduct);
                    // Retourner le DTO correspondant
                    return productMapper.toDTO(updatedProduct);
                })
                .orElse(null); // Retourner null si le produit n'existe pas
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
