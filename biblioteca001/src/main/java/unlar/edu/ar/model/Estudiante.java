package unlar.edu.ar.model;

public class Estudiante {

// Atributos de la clase Estudiante
    private String legajo;
    private String nombre;
    private String dni;
    private String carrera;
    private String email;
    

// Constructor de la clase Estudiante
    public Estudiante(String legajo, String nombre, String dni, String carrera, String email) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.dni = dni;
        this.carrera = carrera;
        this.email = email; 

    }

// Métodos getters y setters para los atributos de la clase Estudiante
    public String getLegajo() {
        return legajo;
    }
    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getCarrera() {
        return carrera;
    }
    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
// Método para mostrar el nombre completo del estudiante en mayúsculas
//    public String mostrarNombreMayuscula() {
//     return this.nombre.toUpperCase() + " " + this.apellido.toUpperCase();
//    }

// Método toString para mostrar el estado del objeto Estudiante
    @Override
    public String toString() {
        return "Estudiante{" +
                "legajo='" + legajo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", carrera='" + carrera + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
