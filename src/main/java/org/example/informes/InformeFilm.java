package org.example.informes;

import jakarta.persistence.*;
import org.example.entities.*;

import java.sql.*;
import java.util.*;

public class InformeFilm {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");
    private static final String COUNT_INVENTORY = "SELECT COUNT(*) AS inventory_count FROM inventory WHERE film_id = ? and store_id=?";
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
                showFilmActor(idFilm);
                showFilmInventory(idFilm);
                //idioma original = idioma pelicula?
            }
        } while (idFilm != 0);
    }

    //Muestra los detalles de la película
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

    //Muestra la categoría de la película
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

    //Muestra el idioma de la película
    public static void showLanguageOriginal(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);
            int idLanguage = film.getLanguageId();

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

    //Muestra los actores que participan en la película
    public static void showFilmActor(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);

            if (film != null) {
                Set<Actor> actors = film.getActors();
                if (actors != null && !actors.isEmpty()) {
                    System.out.println("-----ACTORES EN LA PELÍCULA-----");
                    for (Actor actor : actors) {
                        System.out.println("Actor: " + actor.getFirstName() + " " + actor.getLastName());
                    }
                } else {
                    System.out.println("La película no tiene actores asociados.");
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

    //Muestra la cantidad de películas que hay en cada una de las tiendas
    public static void showFilmInventory(int idFilm) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Film film = entityManager.find(Film.class, (short) idFilm);

            if (film != null) {
                Set<Inventory> inventories = film.getInventories();
                if (inventories != null && !inventories.isEmpty()) {
                    System.out.println("-----INVENTARIO DE LA PELÍCULA-----");

                    Map<Short, Integer> totalAvailableDVDsByStore = new HashMap<>();

                    for (Inventory inventory : inventories) {
                        int idTienda = inventory.getStoreId();

                        totalAvailableDVDsByStore.put((short) idTienda,
                                totalAvailableDVDsByStore.getOrDefault((short) idTienda, getInventoryCountForFilm(entityManager, idFilm, idTienda)));
                    }

                    for (Map.Entry<Short, Integer> entry : totalAvailableDVDsByStore.entrySet()) {
                        Short idTienda = entry.getKey();
                        Integer totalAvailableDVDs = entry.getValue();

                        Store store = entityManager.find(Store.class, idTienda);
                        System.out.println("Tienda: " + idTienda);
                        System.out.println("Dirección: " + store.getAddressByAddressId());
                        System.out.println("Cantidad de inventario: " + totalAvailableDVDs);
                        System.out.println("--------");
                    }
                } else {
                    System.out.println("La película no tiene inventario asociado.");
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

    //Obtiene la cantidad de películas que hay en una tienda específica con un Query
    public static int getInventoryCountForFilm (EntityManager entityManager,int filmId, int storeId){
        int inventoryCount = 0;

        try {
            Query query = entityManager.createNativeQuery(COUNT_INVENTORY);
            query.setParameter(1, filmId);
            query.setParameter(2, storeId);

            Number result = (Number) query.getSingleResult();
            inventoryCount = result.intValue();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return inventoryCount;
    }
}

