package Persona;

import java.time.LocalDate;
import java.time.Period;

public class Persona {
    
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String contraseña;

    public Persona(String nombre, String apellidos, String fechaNacimiento, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.contraseña = contrasena;
        System.out.println("Hola");
    }


    
    public String toString() {
        return "Nombre: " + nombre + " | Apellidos: " + apellidos + " | Edad: " + calcularEdad();
    }

    //Este método calcula la edad según la fecha de nacimiento

    public int calcularEdad() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaNac = LocalDate.parse(fechaNacimiento);
        Period periodo = Period.between(fechaNac, fechaActual);
        int edad = periodo.getYears();
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

        

}
