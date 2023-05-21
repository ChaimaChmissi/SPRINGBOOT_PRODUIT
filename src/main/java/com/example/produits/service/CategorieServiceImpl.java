package com.example.produits.service;

import com.example.produits.dao.CategorieRepository;
import com.example.produits.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategorieServiceImpl implements CategorieService{
    @Autowired
    CategorieRepository categorieRepository;

    @Override
    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }
    @Override
    public Categorie updateCategorie(Categorie c) {
        return categorieRepository.save(c);
    }
    @Override
    public void deleteCategorie(Categorie c) {
        categorieRepository.delete(c);

    }
    @Override
    public void deleteCategorieById(Long id) {
        categorieRepository.deleteById(id);
    }
    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id).get();
    }
    @Override
    public List<Categorie> getAllCategories() {

        return categorieRepository.findAll();
    }

    @Override
    public Page<Categorie> getAllCategoriesParPage(int page, int size) {
        // TODO Auto-generated method stub
        return categorieRepository.findAll(PageRequest.of(page, size));
    }


    @Override
    public List<Categorie> findByNomCatContains(String nom) {
        return categorieRepository.findByNomCatContains(nom);
    }



}
