package com.example.produits.service;

import com.example.produits.entities.Categorie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategorieService {
    Categorie saveCategorie(Categorie c);
    Categorie updateCategorie(Categorie c);
    void deleteCategorie(Categorie c);
    void deleteCategorieById(Long id);
    Categorie getCategorie(Long id);
    List<Categorie> getAllCategories();

    Page<Categorie> getAllCategoriesParPage(int page, int size);

    List<Categorie> findByNomCatContains(String nom);
}
