package com.product.manage.service.impl;

import com.product.manage.dto.ProductDTO;
import com.product.manage.repository.ProductRepository;
import com.product.manage.repository.model.Product;
import com.product.manage.service.ProductService;
import com.product.manage.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDto) {
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
