package org.example.informes;

import jakarta.persistence.*;
import org.example.entities.*;

import java.util.Scanner;

public class RentalFilm {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");
    private static final String COUNT_INVENTORY = "SELECT COUNT(*) AS inventory_count FROM inventory WHERE film_id = ? and store_id=?";

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Ingrese el ID del empleado que realiza el alquiler: ");
            int staffId = scanner.nextInt();

            System.out.print("Ingrese el ID de la película a alquilar: ");
            int filmId = scanner.nextInt();

            System.out.print("Ingrese el ID del cliente que realiza el alquiler: ");
            int customerId = scanner.nextInt();

            Staff empleado = entityManager.find(Staff.class, staffId);
            if (empleado == null) {
                System.out.println("Error: El empleado con ID " + staffId + " no existe.");
                return;
            }

            int storeId = getTiendaDelEmpleado(staffId);

            if (!existInventoryForFilm(entityManager, filmId, storeId)) {
                System.out.println("Error: La película con ID " + filmId + " no está disponible en la tienda.");
                return;
            }

            if (!existCustomerStore(storeId, customerId)) {
                System.out.println("Error: El cliente con ID " + customerId + " no pertenece a la tienda.");
                return;
            }

            makeRental(entityManager, staffId, filmId, customerId);
            System.out.println("Alquiler realizado con éxito.");
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }

    //Método que obtiene la tienda del empleado
    private static int getTiendaDelEmpleado(int staffId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Staff staff = entityManager.find(Staff.class, (short) staffId);
        return staff.getStoreId();
    }

    //Método que verifica si el Empleado existe en la tienda
    private static boolean existCustomerStore(int storeId, int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Store store = entityManager.find(Store.class, storeId);
        Customer customer = entityManager.find(Customer.class, customerId);

        if (store == null || customer == null) {
            System.out.println("Error: La tienda o el cliente no existen.");
            return false;
        }

        return store.getCustomersByStoreId().contains(customer);
    }

    //Método que realiza el alquiler de la película para un cliente específico por un empleado
    private static void makeRental(EntityManager em, int employeeId, int filmId, int customerId) {
        Customer customer = em.find(Customer.class, (short) customerId);
        Staff staff = em.find(Staff.class, (short) employeeId);
        Film film = em.find(Film.class,filmId);

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Query insertQuery = em.createNativeQuery("INSERT INTO rental(inventory_id, customer_id, staff_id) VALUES (?, ?, ?)");
            insertQuery.setParameter(1, film.getInventoryId());
            insertQuery.setParameter(2, customer.getCustomerId());
            insertQuery.setParameter(3, staff.getStaffId());
            insertQuery.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error al realizar el alquiler.", e);
        }
    }

    //Método que verifica que hay existencias de la película en la tienda del empleado
    public static boolean existInventoryForFilm(EntityManager entityManager, int filmId, int storeId) {
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

        return inventoryCount > 0;
    }
}
