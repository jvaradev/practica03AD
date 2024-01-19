package org.example.informes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.*;

import java.util.Scanner;

public class InformeStaff {

    private static final Scanner scanner = new Scanner(System.in);
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("SAKILA_PERSISTENCE_UNIT");

    public static void main(String[] args) {
        int staffId;

        do {
            System.out.print("Introduce el ID del empleado (introduce 0 para salir): ");
            staffId = scanner.nextInt();
            if (staffId != 0) {
                System.out.println("-----DETALLES DEL EMPLEADO-----");
                showStaffDetails(staffId);
                showRentals(staffId);
            }
        } while (staffId != 0);
    }

    //Método que muestra los detalles del empleado
    public static void showStaffDetails(int staffId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Staff staff = entityManager.find(Staff.class, (byte) staffId);

            if (staff != null) {
                System.out.println("ID del Empleado: " + staff.getStaffId());
                System.out.println("Nombre: " + staff.getFirstName() + " " + staff.getLastName());
                System.out.println("Correo Electrónico: " + staff.getEmail());
                System.out.print("Tienda del Empleado: " + staff.getStoreId() + ". ");
                showStoreAddress(staff.getStoreId());
                System.out.println("Dirección Empleado: " + staff.getAddressByAddressId().toString());
                System.out.println("Última Actualización: " + staff.getLastUpdate());

            } else {
                throw new NullPointerException("No se encontró un empleado con el ID proporcionado.");
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    //Método que muestra la dirección de la tienda
    public static void showStoreAddress(Short storeId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Store store = entityManager.find(Store.class, storeId);
            if (store != null) {
                System.out.println("Dirección de la Tienda: " + store.getAddressByAddressId());
            } else {
                System.out.println("No se encontró la tienda con ID: " + storeId);
            }
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            entityManager.close();
        }
    }

    //Método que muestra los alquileres que ha hecho el empleado
    public static void showRentals(int staffId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Staff staff = entityManager.find(Staff.class, (byte) staffId);
        System.out.println("-----LISTA DE ALQUILERES-----");
        for (Rental rental : staff.getRentalsByStaffId()) {
            System.out.println("ID de Alquiler: " + rental.getRentalId());
            System.out.println("Fecha de Alquiler: " + rental.getRentalDate());

            if (rental.getInventoryByInventoryId() != null && rental.getInventoryByInventoryId().getFilm() != null) {
                System.out.println("Título de la Película: " + rental.getInventoryByInventoryId().getFilm().getTitle());
            } else {
                System.out.println("Título de la Película: No disponible");
            }
        }

    }
}
