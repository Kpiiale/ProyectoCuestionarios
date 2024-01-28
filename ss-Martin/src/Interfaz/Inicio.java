package Interfaz;

import Clases.ListaUsuarios;
import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JButton iniciarSesiónButton;
    private JPasswordField txtInicioContrasena;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtCedula;
    private JTextField txtUsuario;
    private JButton registrarEstudiante;
    private JPasswordField txtContrasena;
    private JComboBox cboHorario;
    private JTextField txtInicioUsuario;
    private JButton salir;
    private JButton administrarCuestionariosButton;
    private JButton registrarNuevoEstudianteButton;
    ListaUsuarios listaUsuario = new ListaUsuarios();


    public static void main(String[] args) {
        JFrame frame = new JFrame("Inicio");
        frame.setContentPane(new Inicio().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public Inicio() {

        iniciarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try {
                    if(listaUsuario.verificarContra(txtInicioUsuario.getText(),txtInicioContrasena.getText())){
                        JOptionPane.showMessageDialog(null,"INICIO DE SESION EXITOSO");
                    }else{
                        JOptionPane.showMessageDialog(null,"ERROR EN INICIO DE SESION");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        registrarEstudiante.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre=txtNombre.getText() ;
                String apellido=txtApellido.getText();
                int cedula=Integer.parseInt(txtCedula.getText());
                String usuario1=txtUsuario.getText();
                String contrasena=txtContrasena.getText();
                String horario=cboHorario.getSelectedItem().toString();
                String rol="Estudiante";
                if(listaUsuario.parametroVacio(nombre)&&listaUsuario.parametroVacio(apellido)&&
                        listaUsuario.parametroVacio(txtCedula.getText())&&
                        listaUsuario.parametroVacio(rol)&&listaUsuario.parametroVacio(usuario1)&&
                        listaUsuario.parametroVacio(contrasena)){
                    if (listaUsuario.esUsuarioUnico(usuario1)){
                        if (listaUsuario.validarUsuario(usuario1)){
                            if(listaUsuario.validarContrasena(contrasena)){
                                if(listaUsuario.verificarID(cedula)){
                                    try {
                                        listaUsuario.agregarUsuarioSiEsUnico(new Usuario(nombre, apellido,cedula,usuario1,contrasena,horario,rol));
                                        JOptionPane.showMessageDialog(null, "nuevo registro");
                                    } catch (Exception ex) {
                                        JOptionPane.showMessageDialog(null, ex.getMessage());
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,"El numero de cedula se repite");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Formato de la contrasena invalida");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Formato de usuario incorrecto");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Usuario ya registrada");
                    }
                    }else{
                        JOptionPane.showMessageDialog(null,"ALGUNO DE LOS PARAMETROS ESTA VACIO");
                    }



                }
        });
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
