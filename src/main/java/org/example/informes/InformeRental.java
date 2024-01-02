package org.example.informes;

import jakarta.persistence.*;
import org.example.entities.Staff;

import org.example.entities.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class InformeRental {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");
    private static final String COUNT_INVENTORY = "SELECT COUNT(*) AS inventory_count FROM inventory WHERE film_id = ? and store_id=?";

    public static void main(String[] args) {
        // Configurar el EntityManager

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        try {
            // Solicitar al usuario los datos necesarios
            System.out.print("Ingrese el ID del empleado que realiza el alquiler: ");
            int staffId = scanner.nextInt();

            System.out.print("Ingrese el ID de la película a alquilar: ");
            int filmId = scanner.nextInt();

            System.out.print("Ingrese el ID del cliente que realiza el alquiler: ");
            int customerId = scanner.nextInt();

            // Verificar la existencia del empleado
            Staff empleado = entityManager.find(Staff.class, staffId);
            if (empleado == null) {
                System.out.println("Error: El empleado con ID " + staffId + " no existe.");
                return;
            }
            System.out.println(getTiendaDelEmpleado(staffId));
            System.out.println(existInventoryCountForFilm(entityManager,filmId,getTiendaDelEmpleado(staffId)));
            System.out.println(existeClienteTienda(getTiendaDelEmpleado(staffId),customerId));

            //System.out.println(hayCopiasDisponibles(getTiendaDelEmpleado(staffId),filmId));
            realizarAlquiler(entityManager,staffId,filmId,customerId);
        } finally {
            // Cerrar el EntityManager al finalizar
            entityManager.close();
            entityManagerFactory.close();
        }
    }
    private static int getTiendaDelEmpleado(int staffId) {
        // Puedes acceder a la tienda directamente desde la relación en la entidad Staff
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Staff staff = entityManager.find(Staff.class, (short) staffId);
        return staff.getStoreId();
    }
    private static boolean existeClienteTienda(int tiendaId, int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Store store = entityManager.find(Store.class, tiendaId);
        Customer customer = entityManager.find(Customer.class, customerId);

        if (store == null || customer == null) {
            System.out.println("Error: La tienda o el cliente no existen.");
            return false;
        }

        Collection<Customer> customers = store.getCustomersByStoreId();

        for (Customer customerByStore : customers) {
            if (customerByStore.equals(customer)) {
                return true;
            }
        }

        return false;
    }


    private static void realizarAlquiler(EntityManager em, int empleadoId, int peliculaId, int clienteId) {
        Customer customer = em.find(Customer.class, (short) clienteId);
        Staff staff = em.find(Staff.class, (short) empleadoId);
        Inventory inventory = em.find(Inventory.class, peliculaId);
        Film film = em.find(Film.class, peliculaId);

        // Crear un nuevo alquiler (rental)
        Rental nuevoAlquiler = new Rental();
        nuevoAlquiler.setRentalDate(Timestamp.valueOf(LocalDateTime.now()));
        nuevoAlquiler.setInventoryByInventoryId(inventory); // Asignar el inventario directamente
        nuevoAlquiler.setCustomerId((short) clienteId);
        nuevoAlquiler.setStaff(staff);

        // Asignar el nuevo alquiler al inventario y viceversa
        inventory.getRentals().add(nuevoAlquiler);
        film.getInventories().add(inventory);

        em.getTransaction().begin();
        em.persist(nuevoAlquiler);
        em.getTransaction().commit();

        System.out.println("Alquiler realizado con éxito. Detalles del alquiler:");
        System.out.println("ID del alquiler: " + nuevoAlquiler.getRentalId());
        System.out.println("Fecha del alquiler: " + nuevoAlquiler.getRentalDate());
        System.out.println("Película alquilada: " + nuevoAlquiler.getInventoryByInventoryId().getFilm().getTitle());
    }

    public static boolean existInventoryCountForFilm (EntityManager entityManager,int filmId, int storeId){
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
        if (inventoryCount>0){
            return true;
        }

        return false;
    }

}
