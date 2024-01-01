package org.example.informes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.*;

import java.util.List;
import java.util.Scanner;

public class InformeCliente {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int idCustomer;

        do {
            System.out.print("Introduce el ID del cliente (introduce 0 para salir): ");
            idCustomer = scanner.nextInt();
            if (idCustomer != 0) {
                System.out.println("-----DETALLES DEL CLIENTE-----");
                showCustomerDetails(idCustomer);
                System.out.println("-----DETALLES DE LA TIENDA ASOCIADA AL CLIENTE-----");
                showStoreDetails(idCustomer);
                System.out.println("-----DETALLES DE ALQUILERES-----");
                showRentalsDetails(idCustomer);
                System.out.println("-----DETALLES DE LOS PAGOS REALIZADOS-----");
                showPaymentsDetails(idCustomer);
            }
        } while (idCustomer != 0);
    }

    public static void showCustomerDetails(int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Customer customer = entityManager.find(Customer.class, customerId);

            if (customer != null) {
                System.out.println("Nombre del cliente: " + customer.getFirstName());
                System.out.println("Apellido del cliente: " + customer.getLastName());
                System.out.println("Email del cliente: " + customer.getEmail());

                if (customer.getAddressByAddressId() != null) {
                    System.out.println("Dirección del cliente: " + customer.getAddressByAddressId().toString());
                } else {
                    System.out.println("El cliente no tiene una dirección asociada.");
                }
                // Falta información de la tienda asociada, lista de alquileres y pagos.
            } else {
                System.out.println("No se encontró un cliente con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }
    public static void showStoreDetails(int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Customer customer = entityManager.find(Customer.class, customerId);

            if (customer != null) {
                Store store = customer.getStoreByStoreId();
                if (store != null) {
                    int idAddressStore = store.getAddressId();
                    Address address = entityManager.find(Address.class, idAddressStore);
                    System.out.println("Tienda asociada: " + address.toString());
                    // Puedes agregar más detalles de la tienda según sea necesario
                } else {
                    System.out.println("El cliente no está asociado a ninguna tienda.");
                }
            } else {
                System.out.println("No se encontró un cliente con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    public static void showRentalsDetails(int customerId){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Customer customer = entityManager.find(Customer.class, customerId);
        List<Rental> rentals = (List<Rental>) customer.getRentals();

        if (rentals != null && !rentals.isEmpty()) {
            System.out.println("Alquileres realizados por el cliente:");
            for (Rental rental : rentals) {
                Inventory inventory = rental.getInventoryByInventoryId();
                Film film = inventory.getFilmByFilmId();
                System.out.println("Película alquilada: " + film+". El día: "+rental.getRentalDate());
            }
        } else {
            System.out.println("El cliente no ha realizado alquileres.");
        }
    }
    public static void showPaymentsDetails(int customerId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Customer customer = entityManager.find(Customer.class, customerId);

            if (customer != null) {
                List<Payment> payments = (List<Payment>) customer.getPayments();

                if (payments != null && !payments.isEmpty()) {
                    System.out.println("Pagos realizados por el cliente:");
                    for (Payment payment : payments) {
                        System.out.println("Fecha del pago: " + payment.getPaymentDate());
                        System.out.println("Cantidad pagada: " + payment.getAmount());
                        // Agrega más detalles según sea necesario
                    }
                } else {
                    System.out.println("El cliente no ha realizado pagos.");
                }
            } else {
                System.out.println("No se encontró un cliente con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }
}
