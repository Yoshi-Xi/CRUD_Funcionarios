package modelo;
import java.sql.Date;

public class Funcionario {
    private int id;
    private String tipoId, numeroId, nombres, apellidos, estadoCivil, sexo, direccion, telefono;
    private Date fechaNacimiento;

    public Funcionario() {}
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTipoId() { return tipoId; }
    public void setTipoId(String tipoId) { this.tipoId = tipoId; }
    public String getNumeroId() { return numeroId; }
    public void setNumeroId(String numeroId) { this.numeroId = numeroId; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(String estadoCivil) { this.estadoCivil = estadoCivil; }
    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}