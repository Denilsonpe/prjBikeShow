/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bikeshow.telas;

/**
 *
 * @author Denilson Petronio de Almeida
 */
import java.sql.*;
import br.com.bikeshow.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    //variaveis p conexão com banco de dados
    // Prepared statement e resultSet são frameworks do pacote java.sql
    //e servem p preparar e executar as instruções SQL
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //construtor
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //metodo de consulta de usuários
    private void consultar() {
        String sql = "select * from tbusuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsuMatricula.setText(rs.getString(4));
                txtUsuFuncao.setText(rs.getString(5));
                txtUsuLogin.setText(rs.getString(7));
                txtUsuSenha.setText(rs.getString(8));
                //as linhas abaixo se refere ao combobox
                cboUsuSetor.setSelectedItem(rs.getString(6));
                cboUsuPerfil.setSelectedItem(rs.getString(9));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado!");
                //as linhas abaixo "limpam" os campos
                txtUsuNome.setText(null);
                txtUsuFone.setText(null);
                txtUsuMatricula.setText(null);
                txtUsuFuncao.setText(null);
                txtUsuLogin.setText(null);
                txtUsuSenha.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Metodo para adicionar usuários
    private void adicionar() {
        String sql = "insert into tbusuarios(iduser,usuario,fone,matricula,funcao,setor,login,senha,perfil) values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsuNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsuMatricula.getText());
            pst.setString(5, txtUsuFuncao.getText());
            pst.setString(6, cboUsuSetor.getSelectedItem().toString());
            pst.setString(7, txtUsuLogin.getText());
            pst.setString(8, txtUsuSenha.getText());
            pst.setString(9, cboUsuPerfil.getSelectedItem().toString());
            //Validação dos campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                //a linha abaixo atualiza a tabela com os dados do formulário
                //a linha abaixo é usada para confirmar a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio p verificar o valor da variável adicionado
                //e entendimento da lógica
                //System.out.println(adicionado);
                if (adicionado > 0) {//verifica se a variavel e maior que zero
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso!");
                    //as linhas abaixo "limpam" os campos
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuMatricula.setText(null);
                    txtUsuFuncao.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //criando o metodo para alterar os dados do usuário
    private void alterar() {
        String sql = "update tbusuarios set usuario=?,fone=?,matricula=?,funcao=?,setor=?,login=?,senha=?,perfil=? where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuFone.getText());
            pst.setString(3, txtUsuMatricula.getText());
            pst.setString(4, txtUsuFuncao.getText());
            pst.setString(5, cboUsuSetor.getSelectedItem().toString());
            pst.setString(6, txtUsuLogin.getText());
            pst.setString(7, txtUsuSenha.getText());
            pst.setString(8, cboUsuPerfil.getSelectedItem().toString());
            pst.setString(9, txtUsuId.getText());
            //Validação dos campos obrigatórios
            if ((txtUsuId.getText().isEmpty()) || (txtUsuNome.getText().isEmpty()) || (txtUsuLogin.getText().isEmpty()) || (txtUsuSenha.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
            } else {

                //a linha abaixo atualiza a tabela com os dados do formulario
                //a linha abaixo é usada para confirmar a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                //a linha abaixo serve de apoio p verificar o valor da variavel adicionado
                //e entendimento da logica
                //System.out.println(adicionado);
                if (adicionado > 0) {//verifica se a variavel e maior que zero
                    JOptionPane.showMessageDialog(null, "Dados do Usuário alterados com sucesso!");
                    //as linhas abaixo "limpam" os campos
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuMatricula.setText(null);
                    txtUsuFuncao.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //Método responsável pela remoção de usuários.
    private void remover(){
        //A estrutura abaixo confirma a remoção do usuário
        int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover esse usuário?","Atenção",JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            String sql = "delete from tbusuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1,txtUsuId.getText());
                int removido = pst.executeUpdate();
                if(removido > 0){
                    JOptionPane.showMessageDialog(null, "Dados do Usuário removidos com sucesso!");
                    //as linhas abaixo "limpam" os campos
                    txtUsuId.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuMatricula.setText(null);
                    txtUsuFuncao.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                }
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUsuNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsuFone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUsuMatricula = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtUsuFuncao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboUsuSetor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cboUsuPerfil = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtUsuLogin = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtUsuSenha = new javax.swing.JPasswordField();
        btnUsuCriar = new javax.swing.JButton();
        btnUsuEditar = new javax.swing.JButton();
        btnUsuDeletar = new javax.swing.JButton();
        btnUsuPesquizar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), null, null, new java.awt.Color(0, 204, 204)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuarios\n");
        setFont(new java.awt.Font("Alef", 1, 10)); // NOI18N
        setPreferredSize(new java.awt.Dimension(771, 502));

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel1.setText("*Id:");
        jLabel1.setToolTipText("insira um numero para id ");

        txtUsuId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel2.setText("*Usuário:");
        jLabel2.setToolTipText("Insira o nome do Usuário");

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel3.setText("Fone:");
        jLabel3.setToolTipText("Insira um telefone para contato");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel4.setText("Matrícula: ");
        jLabel4.setToolTipText("Insira um numero para matricula");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel5.setText("Função: ");
        jLabel5.setToolTipText("Insira a função");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel6.setText("Setor: ");
        jLabel6.setToolTipText("Escolha uma opção");

        cboUsuSetor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vendas", "Oficina", "Administração", "TI" }));
        cboUsuSetor.setToolTipText("escolha uma opção");
        cboUsuSetor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsuSetorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel7.setText("*Perfil: ");
        jLabel7.setToolTipText("Escolha uma opção");

        cboUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Atendente", "Mecanico", "Gerente", "Administrador", "admin", " " }));
        cboUsuPerfil.setToolTipText("escolha uma opção");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("*Login: ");
        jLabel8.setToolTipText("insira um nome para logar");

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel9.setText("*Senha: ");
        jLabel9.setToolTipText("Informe uma senha");

        btnUsuCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/criar.png"))); // NOI18N
        btnUsuCriar.setToolTipText("Adicionar");
        btnUsuCriar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCriar.setMaximumSize(new java.awt.Dimension(80, 80));
        btnUsuCriar.setMinimumSize(new java.awt.Dimension(80, 80));
        btnUsuCriar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCriarActionPerformed(evt);
            }
        });

        btnUsuEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/Editar.png"))); // NOI18N
        btnUsuEditar.setToolTipText("Editar");
        btnUsuEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuEditarActionPerformed(evt);
            }
        });

        btnUsuDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/deletar.png"))); // NOI18N
        btnUsuDeletar.setToolTipText("Deletar");
        btnUsuDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuDeletarActionPerformed(evt);
            }
        });

        btnUsuPesquizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/pesquizar.png"))); // NOI18N
        btnUsuPesquizar.setToolTipText("Pesquizar");
        btnUsuPesquizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuPesquizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquizarActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel10.setText("*  CAMPOS OBRIGATÓRIOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnUsuCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuPesquizar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuNome)
                                .addGap(12, 12, 12)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cboUsuSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 3, Short.MAX_VALUE)))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUsuMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtUsuFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtUsuMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(txtUsuFuncao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(cboUsuSetor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsuCriar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuPesquizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuDeletar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        setBounds(0, 0, 772, 510);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuIdActionPerformed

    private void cboUsuSetorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsuSetorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsuSetorActionPerformed

    private void btnUsuPesquizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquizarActionPerformed
        // chamando o metodo consultar
        consultar();
    }//GEN-LAST:event_btnUsuPesquizarActionPerformed

    private void btnUsuCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCriarActionPerformed
        // chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnUsuCriarActionPerformed

    private void btnUsuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuEditarActionPerformed
        // Chamando o metodo alterar:
        alterar();
    }//GEN-LAST:event_btnUsuEditarActionPerformed

    private void btnUsuDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuDeletarActionPerformed
        // chamando o método remover.
        remover();
    }//GEN-LAST:event_btnUsuDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuCriar;
    private javax.swing.JButton btnUsuDeletar;
    private javax.swing.JButton btnUsuEditar;
    private javax.swing.JButton btnUsuPesquizar;
    private javax.swing.JComboBox<String> cboUsuPerfil;
    private javax.swing.JComboBox<String> cboUsuSetor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuFuncao;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuMatricula;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JPasswordField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
