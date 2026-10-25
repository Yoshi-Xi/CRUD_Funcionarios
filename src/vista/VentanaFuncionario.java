package vista;

import dao.FuncionarioDAO;
import modelo.Funcionario;
import modelo.GrupoFamiliar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaFuncionario extends JFrame {
    private JTextField txtTipoId, txtNumId, txtNombres, txtApellidos, txtDireccion, txtTelefono, txtFechaNac;
    private JComboBox<String> cbEstadoCivil, cbSexo;
    private JTable tabla;
    private DefaultTableModel modelo;
    private FuncionarioDAO dao;
    private JButton btnGuardar, btnActualizar;
    private int idSeleccionado = -1;

    public VentanaFuncionario() {
        dao = new FuncionarioDAO();
        setTitle("Gestión de Funcionarios - IUDigital");
        setSize(950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        cargarTabla();
    }

    private void initComponents() {
        JPanel panelForm = new JPanel(new GridLayout(10, 2, 5, 5)); 
        panelForm.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20)); 
        
        panelForm.add(new JLabel("Tipo Identificación:")); txtTipoId = new JTextField(); panelForm.add(txtTipoId);
        panelForm.add(new JLabel("Número Identificación:")); txtNumId = new JTextField(); panelForm.add(txtNumId);
        panelForm.add(new JLabel("Nombres:")); txtNombres = new JTextField(); panelForm.add(txtNombres);
        panelForm.add(new JLabel("Apellidos:")); txtApellidos = new JTextField(); panelForm.add(txtApellidos);
        panelForm.add(new JLabel("Estado Civil:")); cbEstadoCivil = new JComboBox<>(new String[]{"Soltero", "Casado", "Divorciado", "Viudo"}); panelForm.add(cbEstadoCivil);
        panelForm.add(new JLabel("Sexo:")); cbSexo = new JComboBox<>(new String[]{"M", "F"}); panelForm.add(cbSexo);
        panelForm.add(new JLabel("Dirección:")); txtDireccion = new JTextField(); panelForm.add(txtDireccion);
        panelForm.add(new JLabel("Teléfono:")); txtTelefono = new JTextField(); panelForm.add(txtTelefono);
        panelForm.add(new JLabel("Fecha Nac (YYYY-MM-DD):")); txtFechaNac = new JTextField(); panelForm.add(txtFechaNac);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        btnGuardar = new JButton("Guardar Nuevo");
        btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnGuardar); panelBotones.add(btnActualizar); panelBotones.add(btnEliminar); panelBotones.add(btnLimpiar);
        btnActualizar.setEnabled(false);

        modelo = new DefaultTableModel(new String[]{"ID", "Tipo", "Número", "Nombres", "Apellidos", "Estado Civil", "Sexo", "Dirección", "Teléfono", "F. Nac"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

        setLayout(new BorderLayout());
        add(panelForm, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardar());
        btnActualizar.addActionListener(e -> actualizar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
        
        tabla.getSelectionModel().addListSelectionListener(e -> {
            if (tabla.getSelectedRow() != -1) {
                int fila = tabla.getSelectedRow();
                idSeleccionado = Integer.parseInt(modelo.getValueAt(fila, 0).toString());
                txtTipoId.setText(modelo.getValueAt(fila, 1).toString());
                txtNumId.setText(modelo.getValueAt(fila, 2).toString());
                txtNombres.setText(modelo.getValueAt(fila, 3).toString());
                txtApellidos.setText(modelo.getValueAt(fila, 4).toString());
                cbEstadoCivil.setSelectedItem(modelo.getValueAt(fila, 5).toString());
                cbSexo.setSelectedItem(modelo.getValueAt(fila, 6).toString());
                txtDireccion.setText(modelo.getValueAt(fila, 7).toString());
                txtTelefono.setText(modelo.getValueAt(fila, 8).toString());
                txtFechaNac.setText(modelo.getValueAt(fila, 9).toString());
                btnActualizar.setEnabled(true); btnGuardar.setEnabled(false);

                try {
                    List<GrupoFamiliar> familia = dao.listarFamiliares(idSeleccionado);
                    if(!familia.isEmpty()){
                        System.out.println("--- FAMILIARES DE " + txtNombres.getText() + " ---");
                        for(GrupoFamiliar gf : familia){
                            System.out.println("- " + gf.getNombreCompleto() + " (" + gf.getRol() + ")");
                        }
                    }
                } catch (Exception ex) { System.out.println("Error: " + ex.getMessage()); }
               
            }
        });
    }

    private void guardar() {
        try {
            Funcionario f = new Funcionario();
            f.setTipoId(txtTipoId.getText()); f.setNumeroId(txtNumId.getText());
            f.setNombres(txtNombres.getText()); f.setApellidos(txtApellidos.getText());
            f.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString()); f.setSexo(cbSexo.getSelectedItem().toString());
            f.setDireccion(txtDireccion.getText()); f.setTelefono(txtTelefono.getText());
            try { f.setFechaNacimiento(java.sql.Date.valueOf(txtFechaNac.getText())); } 
            catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD"); return; }

            dao.insertar(f);
            JOptionPane.showMessageDialog(this, "Guardado exitosamente.");
            cargarTabla(); limpiar();
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE); }
    }

    private void actualizar() {
        if (idSeleccionado == -1) return;
        try {
            Funcionario f = new Funcionario();
            f.setId(idSeleccionado); f.setTipoId(txtTipoId.getText()); f.setNumeroId(txtNumId.getText());
            f.setNombres(txtNombres.getText()); f.setApellidos(txtApellidos.getText());
            f.setEstadoCivil(cbEstadoCivil.getSelectedItem().toString()); f.setSexo(cbSexo.getSelectedItem().toString());
            f.setDireccion(txtDireccion.getText()); f.setTelefono(txtTelefono.getText());
            try { f.setFechaNacimiento(java.sql.Date.valueOf(txtFechaNac.getText())); } 
            catch (IllegalArgumentException ex) { JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use YYYY-MM-DD"); return; }

            dao.actualizar(f);
            JOptionPane.showMessageDialog(this, "Actualizado exitosamente.");
            cargarTabla(); limpiar();
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE); }
    }

    private void eliminar() {
        if (tabla.getSelectedRow() != -1) {
            if (JOptionPane.showConfirmDialog(this, "¿Eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try { dao.eliminar(idSeleccionado); cargarTabla(); limpiar(); } 
                catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage()); }
            }
        }
    }

    private void cargarTabla() {
        try {
            modelo.setRowCount(0);
            for (Funcionario f : dao.listar()) {
                modelo.addRow(new Object[]{ f.getId(), f.getTipoId(), f.getNumeroId(), f.getNombres(), f.getApellidos(), f.getEstadoCivil(), f.getSexo(), f.getDireccion(), f.getTelefono(), f.getFechaNacimiento() });
            }
        } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error al cargar: " + ex.getMessage()); }
    }

    private void limpiar() {
        txtTipoId.setText(""); txtNumId.setText(""); txtNombres.setText(""); txtApellidos.setText("");
        txtDireccion.setText(""); txtTelefono.setText(""); txtFechaNac.setText("");
        tabla.clearSelection(); idSeleccionado = -1; btnActualizar.setEnabled(false); btnGuardar.setEnabled(true);
    }
}