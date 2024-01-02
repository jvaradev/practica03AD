package org.example.informes;

import jakarta.persistence.*;
import org.example.entities.Staff;

import org.example.entities.*;

import java.sql.*;
import java.util.*;

public class InformeRental {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sakila";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
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
            System.out.println(existeEnInventario(getTiendaDelEmpleado(staffId),filmId));
            System.out.println(existeClienteTienda(getTiendaDelEmpleado(staffId),customerId));
            System.out.println(getInventoryCountForFilm(filmId,getTiendaDelEmpleado(staffId)));
            //System.out.println(hayCopiasDisponibles(getTiendaDelEmpleado(staffId),filmId));
            crearNuevoAlquiler(entityManager,staffId,filmId,customerId);
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

    private static boolean existeEnInventario(int tiendaId, int filmId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Store store = entityManager.find(Store.class, tiendaId);
        Film film = entityManager.find(Film.class, filmId);

        if (store == null || film == null) {
            System.out.println("Error: La tienda o la película no existen.");
            return false;
        }

        Collection<Inventory> inventories =store.getInventoriesByStoreId();

        for (Inventory inventory : inventories) {
            if (inventory.getFilm().equals(film)) {
                return true;
            }
        }

        return false;
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

    /*no funciona
    private static int hayCopiasDisponibles(int tiendaId, int peliculaId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Buscar la tienda, la película y el inventario
        Store tienda = entityManager.find(Store.class, tiendaId);
        Film pelicula = entityManager.find(Film.class, peliculaId);
        Inventory inventory = entityManager.find(Inventory.class, peliculaId);

        return inventory.getStoreId();
    }*/

    private static void crearNuevoAlquiler(EntityManager entityManager,int staffId, int filmId, int customerId) {
        System.out.println("Insertamos una nueva categoría con SQL nativo.");

        // Obtenemos transacción
        EntityTransaction transaction = entityManager.getTransaction();

        // Hacemos la operación dentro de try/catch para poder hacer rollback si algo va mal
        try {
            // Iniciamos transacción
            transaction.begin();

            // Creamos un objeto Query, con la sentencia insert con parámetros
            Query insertQuery = entityManager.createNativeQuery("INSERT INTO rental (rental_date, customer_id, staff_id, last_update) " +
                    "VALUES (CURRENT_TIMESTAMP, :inventoryId, :customerId, :staffId, CURRENT_TIMESTAMP)");
            insertQuery.setParameter("inventoryId", 100000);
            insertQuery.setParameter("customerId", customerId);
            insertQuery.setParameter("staffId", staffId);
            insertQuery.executeUpdate();

            // Así no podremos saber el id generado. En otro ejemplo se ve cómo recuperar el id.

            // Commit de la transacción
            transaction.commit();
        } finally {
            // Si la transacción sigue abierta al llegar aquí, es que tenemos
            // problemas, y hay que deshacer los cambios
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
    public static int getInventoryCountForFilm(int filmId, int storeId) {
        int inventoryCount = 0;

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_INVENTORY)) {
                preparedStatement.setInt(1, filmId);
                preparedStatement.setInt(2, storeId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        inventoryCount = resultSet.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryCount;
    }

}
