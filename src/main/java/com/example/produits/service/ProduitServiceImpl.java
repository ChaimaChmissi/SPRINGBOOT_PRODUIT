package com.example.produits.service;

import com.example.produits.dao.CategorieRepository;
import com.example.produits.dao.ProduitRepository;
import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProduitServiceImpl implements ProduitService{
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Override
    public Produit saveProduit(Produit p) {
        return produitRepository.save(p);
    }

    @Override
    public Categorie saveCategorie(Categorie c) {
        return categorieRepository.save(c);
    }
    @Override
    public Produit updateProduit(Produit p) {
        return produitRepository.save(p);
    }
    @Override
    public void deleteProduit(Produit p) {
        produitRepository.delete(p);

    }
    @Override
    public void deleteProduitById(Long id) {
        produitRepository.deleteById(id);
    }
    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id).get();
    }
    @Override
    public List<Produit> getAllProduits() {

        return produitRepository.findAll();
    }

    @Override
    public Page<Produit> getAllProduitsParPage(int page, int size) {
        // TODO Auto-generated method stub
        return produitRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Produit> findByNomProduit(String nom) {
        return null;
    }

    @Override
    public List<Produit> findByNomProduitContains(String nom) {
        return produitRepository.findByNomProduitContains(nom);
    }

    @Override
    public List<Produit> findByNomPrix(String nom, Double prix) {
        return null;
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return null;
    }

    @Override
    public List<Produit> findByCategorieIdCat(Long id) {
        return null;
    }

    @Override
    public List<Produit> findByOrderByNomProduitAsc() {
        return null;
    }

    @Override
    public List<Produit> trierProduitsNomsPrix() {
        return null;
    }


}
