package org.example.informes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InformeFilm {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int idFilm;

        do {
            System.out.print("Introduce el ID de la película (introduce 0 para salir): ");
            idFilm = scanner.nextInt();
            if (idFilm != 0) {
                System.out.println("-----DETALLES DE LA PELÍCULA-----");
                showFilmDetail(idFilm);
                showCategory(idFilm);
                showLanguageOriginal(idFilm);
                //idioma original = idioma pelicula?
                //falta actores,copias disponibles
            }
        } while (idFilm != 0);
    }

    public static void showFilmDetail(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);

            if (film != null) {
                System.out.println("Título: " + film.getTitle());
                System.out.println("Descripción: " + film.getDescription());
                System.out.println("Año de lanzamiento: " + film.getReleaseYear());
                System.out.println("Duración de alquiler: " + film.getRentalDuration() + " días");
                System.out.println("Tarifa de alquiler: $" + film.getRentalRate());
                System.out.println("Costo de reemplazo: $" + film.getReplacementCost());
                System.out.println("Clasificación: " + film.getRating());
                System.out.println("Características especiales: " + film.getSpecialFeatures());
                System.out.println("Última actualización: " + film.getLastUpdate());

            } else {
                System.out.println("No se encontró una película con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public static void showCategory(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);

            if (film != null) {
                System.out.println("-----CATEGORÍAS DE LA PELÍCULA-----");
                Set<Category> categories = film.getCategories();

                if (categories != null && !categories.isEmpty()) {
                    for (Category category : categories) {
                        System.out.println("Categoría: " + category.getName());
                    }
                } else {
                    System.out.println("La película no tiene categorías asociadas.");
                }
            } else {
                System.out.println("No se encontró una película con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public static void showLanguageOriginal(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);
            int idLanguage=film.getLanguageId();

            if (film != null) {
                Language originalLanguage = entityManager.find(Language.class, (short) idLanguage);

                if (originalLanguage != null) {
                    System.out.println("-----LENGUAJE ORIGINAL DE LA PELÍCULA-----");
                    System.out.println("Lenguaje: " + originalLanguage.getName());
                } else {
                    System.out.println("La película no tiene un lenguaje original asociado.");
                }
            } else {
                System.out.println("No se encontró una película con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }



}
