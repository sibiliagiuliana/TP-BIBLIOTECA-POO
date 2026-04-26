package unlar.edu.ar.service;

import unlar.edu.ar.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import unlar.edu.ar.exception.EstudianteNoEncontradoException;
import unlar.edu.ar.exception.LibroNoDisponibleException;   
import unlar.edu.ar.exception.LimitePrestamosExcedidoException;


public class BibliotecaServicio {

// Atributos de la clase BibliotecaServicio    
    private HashMap<String, Estudiante> estudiantes;
    private ArrayList<Libro> catalogo;
    private HashSet<Prestamo> prestamos;

// Constructor de la clase BibliotecaServicio
    public BibliotecaServicio() {
        estudiantes = new HashMap<>();
        catalogo = new ArrayList<>();
        prestamos = new HashSet<>();
    }

// Métodos para agregar estudiantes, libros y préstamos a la biblioteca
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.put(estudiante.getLegajo(), estudiante);
    }
    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
    }

    public Estudiante buscarEstudiante(String legajo) {
        return estudiantes.get(legajo);
    }
    public Libro buscarLibro(String isbn) {
        for (Libro libro : catalogo) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }


    public void registrarPrestamo(String legajo, String isbn) throws EstudianteNoEncontradoException, LibroNoDisponibleException, LimitePrestamosExcedidoException {
        Estudiante estudiante = buscarEstudiante(legajo);
        Libro libro = buscarLibro(isbn);

         if (estudiante == null) {
            throw new EstudianteNoEncontradoException("El estudiante no existe.");
        }

        if (libro == null) {
            throw new LibroNoDisponibleException("El libro no está disponible.");
        }
        if (!libro.isDisponible()) {
            throw new LibroNoDisponibleException("El libro no está disponible.");
        }

        int contador = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getEstudiante().getLegajo().equals(legajo)) {
                contador++;
            }
        }
        if (contador >= 3) {
            throw new LimitePrestamosExcedidoException("El estudiante ha excedido el límite de préstamos.");
        }

        Prestamo prestamo = new Prestamo(estudiante, libro, java.time.LocalDate.now(), null);
        prestamos.add(prestamo);
        libro.setDisponible(false);
        } 


         public void registrarDevolucion(String legajo, String isbn) {
            Estudiante estudiante = buscarEstudiante(legajo);
            Libro libro = buscarLibro(isbn);

            if (libro == null || estudiante == null) {
                System.out.println("El libro o el estudiante no existe.");
            } else {
                Prestamo prestamoEncontrado = null;
                for (Prestamo prestamo : prestamos) {
                    if (prestamo.getEstudiante().getLegajo().equals(legajo) && prestamo.getLibro().getIsbn().equals(isbn)) {
                        prestamoEncontrado = prestamo;
                        break;
                    }
                }
                if (prestamoEncontrado != null) {
                    prestamoEncontrado.setFechaDevolucion(java.time.LocalDate.now());
                    libro.setDisponible(true);
                    System.out.println("Devolución realizada con éxito.");
                } else {
                    System.out.println("No se encontró el préstamo para este estudiante y libro.");
                }
            }
        }

        public void buscarLibrosPorTitulo(String texto) {
            System.out.println("Libros encontrados con el título '" + texto + "':");
            for (Libro libro : catalogo) {
                if (libro.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                    System.out.println(libro);
                }
            }
        }

        public void listarPrestamosPorEstudiante(String legajo) {
            System.out.println("Préstamos del estudiante con legajo '" + legajo + "':");
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getEstudiante().getLegajo().equals(legajo)) {
                    System.out.println(prestamo);
                }
            }
        }

        public double calcularMulta(int diasRetraso, double valorLibro){
            //caso base 
            if (diasRetraso <= 0) {
                return 0;
            }
            // Limite de 30 dias
            if (diasRetraso > 30) {
                diasRetraso = 30; // Limitar el retraso a 30 días
            }
            // caso recursivo
            return valorLibro * 0.01 * diasRetraso + calcularMulta(diasRetraso - 1, valorLibro);

        }

     

      

    }



