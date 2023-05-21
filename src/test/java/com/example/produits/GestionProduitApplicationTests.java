package com.example.produits;

import com.example.produits.dao.ProduitRepository;
import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.service.ProduitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;

@SpringBootTest
class GestionProduitApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    ProduitRepository service;


    @Test
    public void testCreateProduit() {
        Produit prod = new Produit("PC Asus",1500.500,new Date(), null);
        produitRepository.save(prod);
    }

    @Test
    public void testFindProduit()
    {
        Optional<Produit> optionalProduit = produitRepository.findById(1L);
        if (optionalProduit.isPresent()) {
            Produit p = optionalProduit.get();
            System.out.println(p);
        } else {
            System.out.println("Produit not found");
        }
    }

    @Test
    public void testUpdateProduit()
    {
        Optional<Produit> optionalProduit = produitRepository.findById(1L);
        if (optionalProduit.isPresent()) {
            Produit p = optionalProduit.get();
            p.setPrixProduit(2000.0);
            produitRepository.save(p);

            System.out.println(p);
        } else {
            System.out.println("Produit not found");
        }
    }



    @Test
    public void testDeleteProduit() {
        Optional<Produit> produitOptional = produitRepository.findById(1L);
        if (produitOptional.isPresent()) {
            produitRepository.delete(produitOptional.get());
        } else {
            fail("Produit with id 1 not found");
        }
    }


    @Test
    public void testFindAllProduits()
    {
        List<Produit> prods = produitRepository.findAll();

        for (Produit p:prods)
            System.out.println(p);

    }

/*
    @Test
    public void testFindByNomProduitContains()
    {
        ProduitService service = null;
        Page<Produit> prods = service.getAllProduitsParPage(00,2);
        System.out.println(prods.getSize());
        System.out.println(prods.getTotalElements());

        System.out.println(prods.getTotalPages());
        prods.getContent().forEach(p -> {System.out.println(p.toString());});
    }
*/
    public void testFindByNomProduit() {
        List<Produit> prods = produitRepository.findByNomProduit("iphone X");
        for (Produit p : prods) {
            System.out.println("testFindByNomProduit " +
                    p.getNomProduit());
        }
    }
    @Test
    public void findByNomProduitContains() {
        List<Produit> prods = produitRepository.findByNomProduitContains("Asus");
        for (Produit p : prods) {System.out.println(p.getNomProduit());
        }
    }

    @Test
    public void testfindByNomPrix() {
        List<Produit> prods = produitRepository.findByNomPrix("iphone X", 1000.0);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testfindByCategorie() {
        Categorie cat = new Categorie();
        cat.setIdCat(1L);
        List<Produit> prods = produitRepository.findByCategorie(cat);
        for (Produit p : prods) {
            System.out.println(p);
        }
    }
        @Test
        public void findByCategorieIdCat() {
            List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
            for (Produit p : prods) {
                System.out.println(p);
            }
        }

    @Test
    public void testfindByOrderByNomProduitAsc() {
        List<Produit> prods =
                produitRepository.findByOrderByNomProduitAsc();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }

    @Test
    public void testTrierProduitsNomsPrix() {
        List<Produit> prods = produitRepository.trierProduitsNomsPrix();
        for (Produit p : prods) {
            System.out.println(p);
        }
    }



}



