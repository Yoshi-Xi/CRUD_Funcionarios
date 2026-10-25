package modelo;

public class FormacionAcademica {
    private int idFormacion;
    private int idFuncionario;
    private String universidad;
    private String nivelEstudio;
    private String tituloEstudio;

    public int getIdFormacion() { return idFormacion; }
    public void setIdFormacion(int idFormacion) { this.idFormacion = idFormacion; }
    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }
    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }
    public String getNivelEstudio() { return nivelEstudio; }
    public void setNivelEstudio(String nivelEstudio) { this.nivelEstudio = nivelEstudio; }
    public String getTituloEstudio() { return tituloEstudio; }
    public void setTituloEstudio(String tituloEstudio) { this.tituloEstudio = tituloEstudio; }
}