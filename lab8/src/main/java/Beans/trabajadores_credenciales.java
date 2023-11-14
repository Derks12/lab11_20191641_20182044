package Beans;

public class trabajadores_credenciales {

    private String trabajadores_dni;
    private String password_hashed;
    private String email;

    public String getTrabajadores_dni() {
        return trabajadores_dni;
    }

    public void setTrabajadores_dni(String trabajadores_dni) {
        this.trabajadores_dni = trabajadores_dni;
    }

    public String getPassword_hashed() {
        return password_hashed;
    }

    public void setPassword_hashed(String password_hashed) {
        this.password_hashed = password_hashed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
