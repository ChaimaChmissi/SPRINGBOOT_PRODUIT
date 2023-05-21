package com.example.produits.controllers;

import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.service.CategorieService;
import com.example.produits.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
    public class CatController {

        @Autowired
        private ProduitService produitService;

        @Autowired
        private CategorieService categorieService;


        @RequestMapping("/showCreate")
        public String showCreate(ModelMap modelMap) {
            List<Categorie> categories = categorieService.getAllCategories();

            modelMap.addAttribute("categories", categories);
            modelMap.addAttribute("produit", new Produit());
            return "createProduit";
        }

        @RequestMapping("/saveProduit")
        public String saveProduit(@Valid Produit produit,  @RequestParam("categorieProduit") Long categorieProduit,
                                  BindingResult bindingResult,
                                  ModelMap modelMap) {
            if (bindingResult.hasErrors()) {
                return "createProduit";
            }

            List<Categorie> categories = categorieService.getAllCategories();
            Categorie categorie = categorieService.getCategorie(categorieProduit);
            produit.setCategorie(categorie);

            Produit saveProduit = produitService.saveProduit(produit);
            String msg = "produit enregistré avec Id " +
                    saveProduit.getIdProduit();
            modelMap.addAttribute("categories", categories);

            modelMap.addAttribute("msg", msg);

            return "createProduit";
        }

        @RequestMapping("/ListeProduits")
        public String listeProduits(
                ModelMap modelMap,
                @RequestParam(name = "page", defaultValue = "0") int page,
                @RequestParam(name = "size", defaultValue = "10") int size) {
            Page<Produit> prods = produitService.getAllProduitsParPage(page, size);

            modelMap.addAttribute("produits", prods);
            modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            return "listeProduits";
        }

        @RequestMapping("/supprimerProduit")
        public String supprimerProduit(@RequestParam("id") Long id, ModelMap
                modelMap,
                                       @RequestParam(name = "page", defaultValue = "0") int page,
                                       @RequestParam(name = "size", defaultValue = "2") int size) {
            produitService.deleteProduitById(id);
            Page<Produit> prods = produitService.getAllProduitsParPage(page,
                    size);
            modelMap.addAttribute("produits", prods);
            modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "listeProduits";
        }

        @RequestMapping("/modifierProduit")
        public String editerProduit(@RequestParam("id") Long id, ModelMap modelMap,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size) {
            List<Categorie> categories = categorieService.getAllCategories();
            modelMap.addAttribute("categories", categories);
            Produit p = produitService.getProduit(id);
            modelMap.addAttribute("produit", p);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "editerProduit";
        }

    @RequestMapping("/updateProduit")
    public String updateProduit(@ModelAttribute("produit") Produit new_produit, @RequestParam("date") String date,
                                @RequestParam("categorieProduit") Long categorieProduit,
                                @RequestParam(name = "page", defaultValue = "0") int page,
                                @RequestParam(name = "size", defaultValue = "7") int size,
                                ModelMap modelMap) throws ParseException {
        Produit old_produit = produitService.getProduit(new_produit.getIdProduit());
        if (date != "" && old_produit.getDateCreation() != new_produit.getDateCreation()) {
            // conversion de la date
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreation = dateformat.parse(String.valueOf(date));
            old_produit.setDateCreation(dateCreation);
        }

        if (old_produit.getNomProduit() != new_produit.getNomProduit() && new_produit.getNomProduit().length() > 0) {
            old_produit.setNomProduit(new_produit.getNomProduit());
        }
        if (old_produit.getPrixProduit() != new_produit.getPrixProduit() && new_produit.getPrixProduit() > 0D) {
            old_produit.setPrixProduit(new_produit.getPrixProduit());
        }
        if (old_produit.getCategorie().getIdCat() != categorieProduit && categorieProduit > 0D) {
            System.out.println("hello world !");
            Categorie categorie = categorieService.getCategorie(categorieProduit);
            System.out.println("qsdfqsdfqsdfq " + categorie.getNomCat());
            old_produit.setCategorie(categorie);
        }
        produitService.updateProduit(old_produit);

        Page<Produit> produits = produitService.getAllProduitsParPage(page, size);
        modelMap.addAttribute("produits", produits);
        modelMap.addAttribute("pages", new int[produits.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("size", size);

        String msg = "Produit modifié";
        modelMap.addAttribute("msg", msg);
        return "listeProduits";
    }
/*
        @RequestMapping("/updateProduit")
        public String updateProduit(@ModelAttribute("produit") Produit produit,
                                    @RequestParam("date") String date,
                                    ModelMap modelMap,
                                    @RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {
            //conversion de la date
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateCreation = dateformat.parse(String.valueOf(date));
            produit.setDateCreation(dateCreation);



            produitService.updateProduit(produit);
            Page<Produit> prods = produitService.getAllProduitsParPage(page, size);
            modelMap.addAttribute("produits", prods);
            modelMap.addAttribute("pages", new int[prods.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);

            return "listeProduits";
        }*/

        @RequestMapping("/createCat")
        public String showCreateCat(ModelMap modelMap) {

            modelMap.addAttribute("categorie", new Categorie());
            return "createCat";
        }


        @RequestMapping("/saveCategorie")
        public String saveCategorie(@Valid Categorie categorie,
                                    BindingResult bindingResult,
                                    ModelMap modelMap) {
            if (bindingResult.hasErrors()) {
                return "createCat";
            }
            Categorie saveCategorie = categorieService.saveCategorie(categorie);
            String msg = "categorie enregistré avec Id " +
                    saveCategorie.getIdCat();
            modelMap.addAttribute("msg", msg);
            return "createCat";
        }


        @RequestMapping("/listeCategories")
        public String listeCategories(ModelMap modelMap,@RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "2") int size)
        {
            Page<Categorie> cat = categorieService.getAllCategoriesParPage(page, size);
            modelMap.addAttribute("categories", cat);
            modelMap.addAttribute("pages", new int[cat.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            return "listeCategories";
        }



        @RequestMapping("/supprimerCategorie")
        public String supprimeCategorie(@RequestParam("id") Long id, ModelMap
                modelMap,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "2") int size) {

            categorieService.deleteCategorieById(id);
            Page<Categorie> catgs = categorieService.getAllCategoriesParPage(page,
                    size);
            modelMap.addAttribute("categories", catgs);
            modelMap.addAttribute("pages", new int[catgs.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "listeCategories";
        }


        @RequestMapping("/modifierCategorie")
        public String editerCategorie(@RequestParam("id") Long id, ModelMap modelMap,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "2") int size) {
            Categorie c = categorieService.getCategorie(id);
            modelMap.addAttribute("categorie", c);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);
            return "editerCategories";
        }

        @RequestMapping("/updateCategorie")
        public String updateCategorie(@ModelAttribute("categorie") Categorie categorie,
                                      ModelMap modelMap,
                                      @RequestParam(name = "page", defaultValue = "0") int page,
                                      @RequestParam(name = "size", defaultValue = "2") int size) throws ParseException {

            Categorie old_categorie = categorieService.getCategorie(categorie.getIdCat());

            if (old_categorie.getNomCat() != categorie.getNomCat() &&
                    categorie.getNomCat().length() > 0) {
                old_categorie.setNomCat(categorie.getNomCat());
            }
            if (old_categorie.getDescriptionCat() != categorie.getDescriptionCat()
                    && categorie.getDescriptionCat().length() > 0D) {
                old_categorie.setDescriptionCat(categorie.getDescriptionCat());
            }
            categorieService.updateCategorie(old_categorie);

            Page<Categorie> categories = categorieService.getAllCategoriesParPage(page,
                    size);
            modelMap.addAttribute("categories", categories);
            modelMap.addAttribute("pages", new int[categories.getTotalPages()]);
            modelMap.addAttribute("currentPage", page);
            modelMap.addAttribute("size", size);

            String msg = "Categorie modifié";
            modelMap.addAttribute("msg", msg);
            return "listeCategories";
        }


    @GetMapping("/ListeProduitsByName")
    public String listeProduitsByName(ModelMap modelMap, @RequestParam("nom") String name) {
        List<Produit> produits = produitService.findByNomProduitContains(name);
        List<Produit> all_produits = produitService.getAllProduits();

        if (produits.isEmpty()) {
            modelMap.addAttribute("msg", "Pas de produits avec se clé !");
            modelMap.addAttribute("produits", all_produits);
        } else {
            modelMap.addAttribute("produits", produits);
            modelMap.addAttribute("searchTerm", name);
        }

        return "listeProduits";
    }


    @GetMapping("/ListeCategoriesByName")
    public String listeCategoriesByName(ModelMap modelMap, @RequestParam("nom") String name) {
        List<Categorie> categories = categorieService.findByNomCatContains(name);
        List<Categorie> all_categories = categorieService.getAllCategories();

        if (categories.isEmpty()) {
            modelMap.addAttribute("msg", "Pas de categories avec se clé !");
            modelMap.addAttribute("categories", all_categories);
        } else {
            modelMap.addAttribute("categories", categories);
            modelMap.addAttribute("searchTerm", name);
        }

        return "listeCategories";
    }

}


