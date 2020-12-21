/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bikeshow.telas;

import java.sql.*;
import br.com.bikeshow.dal.ModuloConexao;
import javax.swing.JOptionPane;
//A linha abaixo importa recursos da biblioteca rs2xml.jar para criar uma pesquisa avançada
//junto com a tabela.
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Denilson Petrônio de Almeida
 */
public class TelaClientes extends javax.swing.JInternalFrame {

    //Variáveis para conexão com o banco de dados,
    // Prepared statement e resultSet são frameworks do pacote java.sql
    //elas servem para preparar e executar as instruções SQL.
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    //Construtor
    public TelaClientes() {
	initComponents();
	conexao = ModuloConexao.conector();
    }

    //Método para adicionar clientes.
    private void adicionar() {
	String sql = "insert into tbclientes(nome,fone,Rg,endereco,complemento,bairro,cidade,estado) values(?,?,?,?,?,?,?,?)";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliFone.getText());
	    pst.setString(3, txtCliRg.getText());
	    pst.setString(4, txtCliEndereco.getText());
	    pst.setString(5, txtCliComplemento.getText());
	    pst.setString(6, txtCliBairro.getText());
	    pst.setString(7, txtCliCidade.getText());
	    pst.setString(8, txtCliEstado.getText());
	    //pst.setString(9, cboUsuPerfil.getSelectedItem().toString());
	    //Validação dos campos obrigatórios.
	    if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) || (txtCliRg.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {

		//A linha abaixo atualiza a tabela com os dados do formulário.
		//A linha abaixo é usada para confirmar a inserção dos dados na tabela.
		int adicionado = pst.executeUpdate();
		//A linha abaixo serve de apoio para verificar o valor da variável adicionado
		//e entendimento da lógica.
		//System.out.println(adicionado);
		if (adicionado > 0) {//verifica se a variável e maior que zero
		    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
		    //As linhas abaixo "limpam" os campos.
		    txtCliNome.setText(null);
		    txtCliFone.setText(null);
		    txtCliRg.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliComplemento.setText(null);
		    txtCliBairro.setText(null);
		    txtCliCidade.setText(null);
		    txtCliEstado.setText(null);
		}
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para pesquisar clientes pelo nome com filtro.
    private void pesquisar_cliente() {
	String sql = "select * from tbclientes where nome like ?";
	try {
	    pst = conexao.prepareStatement(sql);
	    //passando o conteúdo da caixa de pesquisa para o ?
	    //Atenção ao "%" - continuação da string SQL.
	    pst.setString(1, txtcliPesquisar.getText() + "%");
	    rs = pst.executeQuery();
	    //A linha abaixo usa a biblioteca rs2xml.jar para preencher os campos da tabela.
	    tblClientes.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Criando o método para alterar os dados do cliente.
    private void alterar() {
	String sql = "update tbclientes set nome=?,fone=?,Rg=?,endereco=?,complemento=?,bairro=?,cidade=?,estado=? where idcli=?";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtCliNome.getText());
	    pst.setString(2, txtCliFone.getText());
	    pst.setString(3, txtCliRg.getText());
	    pst.setString(4, txtCliEndereco.getText());
	    pst.setString(5, txtCliComplemento.getText());
	    pst.setString(6, txtCliBairro.getText());
	    pst.setString(7, txtCliCidade.getText());
	    pst.setString(8, txtCliEstado.getText());
	    pst.setString(9, txtCliId.getText());

	    //Validação dos campos obrigatórios.
	    if ((txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) || (txtCliRg.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {

		//A linha abaixo atualiza a tabela com os dados do formulário
		//A linha abaixo é usada para confirmar a alteração dos dados na tabela.
		int adicionado = pst.executeUpdate();
		//A linha abaixo serve de apoio para verificar o valor da variável adicionado
		//e entendimento da lógica.
		System.out.println(adicionado);
		if (adicionado > 0) {//Verifica se a variável é maior que zero.
		    JOptionPane.showMessageDialog(null, "Dados do Cliente alterados com sucesso!");
		    //As linhas abaixo "limpam" os campos.
		    txtCliNome.setText(null);
		    txtCliFone.setText(null);
		    txtCliRg.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliComplemento.setText(null);
		    txtCliBairro.setText(null);
		    txtCliCidade.setText(null);
		    txtCliEstado.setText(null);

		    btnAdicionar.setEnabled(true);
		}
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para setar os campos do formulário com o conteúdo da tabela.
    public void setar_campos() {
	int setar = tblClientes.getSelectedRow();
	txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
	txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
	txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
	txtCliRg.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
	txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
	txtCliComplemento.setText(tblClientes.getModel().getValueAt(setar, 5).toString());
	txtCliBairro.setText(tblClientes.getModel().getValueAt(setar, 6).toString());
	txtCliCidade.setText(tblClientes.getModel().getValueAt(setar, 7).toString());
	txtCliEstado.setText(tblClientes.getModel().getValueAt(setar, 8).toString());

	// A linha abaixo desabilita o botão adicionar.
	btnAdicionar.setEnabled(false);
    }

    //Método responsável pela remoção de usuários.
    private void remover() {
	//A estrutura abaixo confirma a remoção do usuário.
	int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Cliente?", "Atenção", JOptionPane.YES_NO_OPTION);
	if (confirma == JOptionPane.YES_OPTION) {
	    String sql = "delete from tbclientes where idcli=?";
	    try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, txtCliId.getText());
		int removido = pst.executeUpdate();
		if (removido > 0) {
		    JOptionPane.showMessageDialog(null, "Dados do Cliente removidos com sucesso!");
		    //As linhas abaixo "limpam" os campos.
		    txtCliNome.setText(null);
		    txtCliFone.setText(null);
		    txtCliRg.setText(null);
		    txtCliEndereco.setText(null);
		    txtCliComplemento.setText(null);
		    txtCliBairro.setText(null);
		    txtCliCidade.setText(null);
		    txtCliEstado.setText(null);
		    
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtCliRg = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliComplemento = new javax.swing.JTextField();
        txtCliBairro = new javax.swing.JTextField();
        txtCliCidade = new javax.swing.JTextField();
        txtCliEstado = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtcliPesquisar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 255), new java.awt.Color(0, 102, 255), null, null));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela de clientes");
        setPreferredSize(new java.awt.Dimension(772, 510));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setText("*Nome:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel3.setText("*fone:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setText("*Rg:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel5.setText("Endereço:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel6.setText("complemento:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel7.setText("bairro:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel8.setText("cidade:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel9.setText("estado:");

        txtCliNome.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 204, 255), null));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("* campos Obrigatórios");

        txtCliRg.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 204, 255), null));

        txtCliFone.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        txtCliEndereco.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        txtCliComplemento.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        txtCliBairro.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        txtCliCidade.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        txtCliEstado.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 204, 255), null));

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/criar.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicione um Cliente");
        btnAdicionar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/Editar.png"))); // NOI18N
        btnAlterar.setToolTipText("Altera os dados do Cliente");
        btnAlterar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/deletar.png"))); // NOI18N
        btnExcluir.setToolTipText("Deleta um Cliente");
        btnExcluir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/search_p.png"))); // NOI18N
        jLabel11.setToolTipText("");

        txtcliPesquisar.setToolTipText("Digite um nome para pesquizar");
        txtcliPesquisar.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(102, 204, 255), null));
        txtcliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcliPesquisarKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setText("Id:");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(txtCliBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtCliRg, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addGap(10, 10, 10)
                                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtcliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(10, 10, 10)
                                .addComponent(txtCliComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel9)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                            .addComponent(btnAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCliId))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                                    .addComponent(txtCliRg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtcliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(100, 100, 100))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
	// Metodo para adicionar clientes.
	adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed
//O evento abaixo é do tipo "Enquanto for digitando".
    private void txtcliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcliPesquisarKeyReleased
	// Chamando o método pesquisar clientes.
	pesquisar_cliente();
    }//GEN-LAST:event_txtcliPesquisarKeyReleased
// Evento que será usado para setar os campos da tabela(com o click do mouse).
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
	//Chamando o método.
	setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
	// Chamando o método alterar.
	alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // Chamando o método remover.
	remover();
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliBairro;
    private javax.swing.JTextField txtCliCidade;
    private javax.swing.JTextField txtCliComplemento;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliEstado;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliRg;
    private javax.swing.JTextField txtcliPesquisar;
    // End of variables declaration//GEN-END:variables
}
