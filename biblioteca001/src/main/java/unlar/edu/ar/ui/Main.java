package unlar.edu.ar.ui;

import unlar.edu.ar.model.*;
import unlar.edu.ar.service.BibliotecaServicio;
import unlar.edu.ar.exception.*;

public class Main {

    public static void main(String[] args) {
        BibliotecaServicio bibliotecaServicio = new BibliotecaServicio();

        // crear libros (5)
        Libro l1 = new Libro("1", "El Quijote", "Autor A", 2020, true);
        Libro l2 = new Libro("2", "Cervantes", "Autor B", 2019, true);
        Libro l3 = new Libro("3", "Don Quijote", "Autor C", 2021, true);
        Libro l4 = new Libro("4", "La vida de Cervantes", "Autor D", 2018, true);
        Libro l5 = new Libro("5", "El ingenioso hidalgo Don Quijote de la Mancha", "Autor E", 2022, true);
        Libro l6 = new Libro("6", "El Quijote de la Mancha", "Autor F", 2023, true);

        // agregar libros a la biblioteca
        bibliotecaServicio.agregarLibro(l1);
        bibliotecaServicio.agregarLibro(l2);
        bibliotecaServicio.agregarLibro(l3);
        bibliotecaServicio.agregarLibro(l4);
        bibliotecaServicio.agregarLibro(l5);
        bibliotecaServicio.agregarLibro(l6);
        // crear estudiantes (3)
        Estudiante e1 = new Estudiante("A1", "Juan", "5855999","Bioquimica", "juan.perez@email.com");
        Estudiante e2 = new Estudiante("A2", "María", "6215549","Farmacologia", "maria.gomez@email.com");
        Estudiante e3 = new Estudiante("A3", "Carlos", "4722035","Medicina", "carlos.lopez@email.com");

        // agregar estudiantes a la biblioteca
        bibliotecaServicio.agregarEstudiante(e1);
        bibliotecaServicio.agregarEstudiante(e2);
        bibliotecaServicio.agregarEstudiante(e3);

        // registrar préstamos
        try {
            bibliotecaServicio.registrarPrestamo("A1", "1");
            bibliotecaServicio.registrarPrestamo("A2", "2");
            bibliotecaServicio.registrarPrestamo("A3", "3");

            System.out.println("Préstamos registrados exitosamente.");

        } catch (EstudianteNoEncontradoException | LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println(e.getMessage());
        }

        // probar excepciones

        // registrar un préstamo con un estudiante que no existe
        try {
            bibliotecaServicio.registrarPrestamo("A4", "4"); // Estudiante no existe
        } catch (EstudianteNoEncontradoException | LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // registrar un préstamo con un libro que no existe
        try {
            bibliotecaServicio.registrarPrestamo("A1", "9"); // Libro no existe
        } catch (EstudianteNoEncontradoException | LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // registrar un préstamo con un libro que no está disponible
        try {
            bibliotecaServicio.registrarPrestamo("A1", "1"); // Libro no disponible
        } catch (EstudianteNoEncontradoException | LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // registrar más de 3 préstamos para un mismo estudiante
        try {
            bibliotecaServicio.registrarPrestamo("A1", "4");
            bibliotecaServicio.registrarPrestamo("A1", "5");
            bibliotecaServicio.registrarPrestamo("A1", "6"); // Límite de préstamos excedido
        } catch (EstudianteNoEncontradoException | LibroNoDisponibleException | LimitePrestamosExcedidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Listar préstamos registrados
        System.out.println("\nPréstamos del estudiante A1:");
        bibliotecaServicio.listarPrestamosPorEstudiante("A1");

        // Busqueda de libros
        System.out.println("\nBuscar libros con 'Quijote':");
        bibliotecaServicio.buscarLibrosPorTitulo("Quijote");

        // Calculo de multa (15 dias de retraso)
        double multa= bibliotecaServicio.calcularMulta(15, 1000); // 15 días de retraso, costo por día
        System.out.println("\nMulta por 15 días de retraso(1% diario): $" + multa);
    }
}
