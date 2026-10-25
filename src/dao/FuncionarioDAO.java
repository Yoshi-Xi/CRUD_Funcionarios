package dao;

import modelo.Funcionario;
import modelo.GrupoFamiliar;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public boolean insertar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (tipo_identificacion, numero_identificacion, nombres, apellidos, estado_civil, sexo, direccion, telefono, fecha_nacimiento) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getTipoId()); pstmt.setString(2, f.getNumeroId());
            pstmt.setString(3, f.getNombres()); pstmt.setString(4, f.getApellidos());
            pstmt.setString(5, f.getEstadoCivil()); pstmt.setString(6, f.getSexo());
            pstmt.setString(7, f.getDireccion()); pstmt.setString(8, f.getTelefono());
            pstmt.setDate(9, f.getFechaNacimiento());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) throw new SQLException("El número de identificación ya existe.");
            throw e;
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT * FROM funcionarios";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setId(rs.getInt("id_funcionario"));
                f.setTipoId(rs.getString("tipo_identificacion"));
                f.setNumeroId(rs.getString("numero_identificacion"));
                f.setNombres(rs.getString("nombres"));
                f.setApellidos(rs.getString("apellidos"));
                f.setEstadoCivil(rs.getString("estado_civil"));
                f.setSexo(rs.getString("sexo"));
                f.setDireccion(rs.getString("direccion"));
                f.setTelefono(rs.getString("telefono"));
                f.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                lista.add(f);
            }
        }
        return lista;
    }

    public boolean actualizar(Funcionario f) throws SQLException {
        String sql = "UPDATE funcionarios SET tipo_identificacion=?, numero_identificacion=?, nombres=?, apellidos=?, estado_civil=?, sexo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE id_funcionario=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, f.getTipoId()); pstmt.setString(2, f.getNumeroId());
            pstmt.setString(3, f.getNombres()); pstmt.setString(4, f.getApellidos());
            pstmt.setString(5, f.getEstadoCivil()); pstmt.setString(6, f.getSexo());
            pstmt.setString(7, f.getDireccion()); pstmt.setString(8, f.getTelefono());
            pstmt.setDate(9, f.getFechaNacimiento()); pstmt.setInt(10, f.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id_funcionario=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    // DEMOSTRACIÓN DE LA RELACIÓN
    public List<GrupoFamiliar> listarFamiliares(int idFuncionario) throws SQLException {
        List<GrupoFamiliar> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupo_familiar WHERE id_funcionario = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idFuncionario);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    GrupoFamiliar gf = new GrupoFamiliar();
                    gf.setIdFamiliar(rs.getInt("id_familiar"));
                    gf.setIdFuncionario(rs.getInt("id_funcionario"));
                    gf.setNombreCompleto(rs.getString("nombre_completo"));
                    gf.setRol(rs.getString("rol"));
                    gf.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                    gf.setNumeroIdentificacion(rs.getString("numero_identificacion"));
                    gf.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    gf.setSexo(rs.getString("sexo"));
                    lista.add(gf);
                }
            }
        }
        return lista;
    }
}