package com.example.produits;

import com.example.produits.entities.Categorie;
import com.example.produits.entities.Produit;
import com.example.produits.service.ProduitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
@SpringBootApplication
@EnableWebSecurity
@ComponentScan("com.example")
public class GestionProduitApplication implements CommandLineRunner {
    @Autowired
    private ProduitServiceImpl service;
    public static void main(String[] args) {
        SpringApplication.run(GestionProduitApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
   /* Produit prod1 = new Produit("PC Asus",1500.500,new Date(), null);
        Produit prod2 = new Produit("PC Dell",2000.500,new Date(), null);
        Produit prod3 = new Produit("PC Toshiba",2500.500,new Date(), null);
        service.saveProduit(prod1);
        service.saveProduit(prod2);
        service.saveProduit(prod3);
*/

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}