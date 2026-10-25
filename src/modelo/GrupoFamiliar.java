package modelo;

public class GrupoFamiliar {
    private int idFamiliar;
    private int idFuncionario;
    private String nombreCompleto;
    private String rol;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private java.sql.Date fechaNacimiento;
    private String sexo;

    
    public void setIdFamiliar(int idFamiliar) { this.idFamiliar = idFamiliar; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public void setRol(String rol) { this.rol = rol; }
    public void setTipoIdentificacion(String tipoIdentificacion) { this.tipoIdentificacion = tipoIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }
    public void setFechaNacimiento(java.sql.Date fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setSexo(String sexo) { this.sexo = sexo; }


    public int getIdFamiliar() { return idFamiliar; }
    public int getIdFuncionario() { return idFuncionario; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getRol() { return rol; }
    public String getTipoIdentificacion() { return tipoIdentificacion; }
    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public java.sql.Date getFechaNacimiento() { return fechaNacimiento; }
    public String getSexo() { return sexo; }
}