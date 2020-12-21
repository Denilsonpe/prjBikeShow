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
import net.proteanit.sql.DbUtils;

public class TelaFornecedores extends javax.swing.JInternalFrame {

    //variaveis p conexão com banco de dados
    // Prepared statement e resultSet são frameworks do pacote java.sql
    //e servem p preparar e executar as instruções SQL
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaFornecedores
     */
    public TelaFornecedores() {
	initComponents();
	conexao = ModuloConexao.conector();
    }

    //Método para adicionar clientes
    private void adicionar() {
	String sql = "insert into tbfornecedor(idforn,nome,fone,cnpj,email,endereco,complemento,bairro,cidade,estado,cep,contato) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(1, txtfornId.getText());
	    pst.setString(2, txtfornNome.getText());
	    pst.setString(3, txtfornfone.getText());
	    pst.setString(4, txtfornCnpj.getText());
	    pst.setString(5, txtfornEmail.getText());
	    pst.setString(6, txtfornEndereco.getText());
	    pst.setString(7, txtfornComplemento.getText());
	    pst.setString(8, txtfornBairro.getText());
	    pst.setString(9, txtfornCidade.getText());
	    pst.setString(10, txtfornEstado.getText());
	    pst.setString(11, txtfornCep.getText());
	    pst.setString(12, txtfornContato.getText());
	    //pst.setString(9, cboUsuPerfil.getSelectedItem().toString());
	    //Validação dos campos obrigatórios
	    if ((txtfornNome.getText().isEmpty()) || (txtfornfone.getText().isEmpty()) || (txtfornCnpj.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {

		//a linha abaixo atualiza a tabela com os dados do formulário
		//a linha abaixo é usada para confirmar a inserção dos dados na tabela
		int adicionado = pst.executeUpdate();
		//a linha abaixo serve de apoio p verificar o valor da variável adicionado
		//e entendimento da lógica
		//System.out.println(adicionado);
		if (adicionado > 0) {//verifica se a variável e maior que zero
		    JOptionPane.showMessageDialog(null, "Fornecedor adicionado com sucesso!");
		    //as linhas abaixo "limpam" os campos
		    txtfornId.setText(null);
		    txtfornNome.setText(null);
		    txtfornfone.setText(null);
		    txtfornCnpj.setText(null);
		    txtfornEmail.setText(null);
		    txtfornEndereco.setText(null);
		    txtfornComplemento.setText(null);
		    txtfornBairro.setText(null);
		    txtfornCidade.setText(null);
		    txtfornEstado.setText(null);
		    txtfornCep.setText(null);
		    txtfornContato.setText(null);
		}
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para pesquisar clientes pelo nome com filtro
    private void pesquisar_fornecedor() {
	String sql = "select * from tbfornecedor where nome like ?";
	try {
	    pst = conexao.prepareStatement(sql);
	    //passando o conteudo da caixa de pesquisa para o ?
	    //Atençõa ao "%" - continuação da string sql
	    pst.setString(1, txtfornNome.getText() + "%");
	    rs = pst.executeQuery();
	    //a linha abaixo usa a biblioteca rs2xml.jar para preencher os campos da tabela
	    tblFornecedor.setModel(DbUtils.resultSetToTableModel(rs));

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //criando o metodo para alterar os dados do cliente
    private void alterar() {
	String sql = "update tbfornecedor set nome=?,fone=?,cnpj=?,email=?,endereco=?,complemento=?,bairro=?,cidade=?,estado=?,cep=?,contato=? where idforn=?";
	try {
	    pst = conexao.prepareStatement(sql);
	    pst.setString(12, txtfornId.getText());
	    pst.setString(1, txtfornNome.getText());
	    pst.setString(2, txtfornfone.getText());
	    pst.setString(3, txtfornCnpj.getText());
	    pst.setString(4, txtfornEmail.getText());
	    pst.setString(5, txtfornEndereco.getText());
	    pst.setString(6, txtfornComplemento.getText());
	    pst.setString(7, txtfornBairro.getText());
	    pst.setString(8, txtfornCidade.getText());
	    pst.setString(9, txtfornEstado.getText());
	    pst.setString(10, txtfornCep.getText());
	    pst.setString(11, txtfornContato.getText());

	    //Validação dos campos obrigatórios
	    if ((txtfornNome.getText().isEmpty()) || (txtfornfone.getText().isEmpty()) || (txtfornCnpj.getText().isEmpty())) {
		JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!");
	    } else {

		//a linha abaixo atualiza a tabela com os dados do formulario
		//a linha abaixo é usada para confirmar a alteração dos dados na tabela
		int adicionado = pst.executeUpdate();
		//a linha abaixo serve de apoio p verificar o valor da variavel adicionado
		//e entendimento da logica
		System.out.println(adicionado);
		if (adicionado > 0) {//verifica se a variavel e maior que zero
		    JOptionPane.showMessageDialog(null, "Dados do Fornecedor alterados com sucesso!");
		    //as linhas abaixo "limpam" os campos
		    txtfornId.setText(null);
		    txtfornNome.setText(null);
		    txtfornfone.setText(null);
		    txtfornCnpj.setText(null);
		    txtfornEmail.setText(null);
		    txtfornEndereco.setText(null);
		    txtfornComplemento.setText(null);
		    txtfornBairro.setText(null);
		    txtfornCidade.setText(null);
		    txtfornEstado.setText(null);
		    txtfornCep.setText(null);
		    txtfornContato.setText(null);

		    btnAdicionar.setEnabled(true);
		}
	    }

	} catch (Exception e) {
	    JOptionPane.showMessageDialog(null, e);
	}
    }

    //Método para setar os campos do formulário com o conteúdo da tabela
    public void setar_campos() {
	int setar = tblFornecedor.getSelectedRow();
	txtfornId.setText(tblFornecedor.getModel().getValueAt(setar, 0).toString());
	txtfornNome.setText(tblFornecedor.getModel().getValueAt(setar, 1).toString());
	txtfornfone.setText(tblFornecedor.getModel().getValueAt(setar, 2).toString());
	txtfornCnpj.setText(tblFornecedor.getModel().getValueAt(setar, 3).toString());
	txtfornEmail.setText(tblFornecedor.getModel().getValueAt(setar, 4).toString());
	txtfornEndereco.setText(tblFornecedor.getModel().getValueAt(setar, 5).toString());
	txtfornComplemento.setText(tblFornecedor.getModel().getValueAt(setar, 6).toString());
	txtfornBairro.setText(tblFornecedor.getModel().getValueAt(setar, 7).toString());
	txtfornCidade.setText(tblFornecedor.getModel().getValueAt(setar, 8).toString());
	txtfornEstado.setText(tblFornecedor.getModel().getValueAt(setar, 9).toString());
	txtfornCep.setText(tblFornecedor.getModel().getValueAt(setar, 10).toString());
	txtfornContato.setText(tblFornecedor.getModel().getValueAt(setar, 11).toString());

	// alinha abaixo desabilita o botao adicionar
	btnAdicionar.setEnabled(false);
    }

    //Método responsável pela remoção de usuários.
    private void remover() {
	//A estrutura abaixo confirma a remoção do usuário
	int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse fornecedor?", "Atenção", JOptionPane.YES_NO_OPTION);
	if (confirma == JOptionPane.YES_OPTION) {
	    String sql = "delete from tbfornecedor where idforn=?";
	    try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, txtfornId.getText());
		int removido = pst.executeUpdate();
		if (removido > 0) {
		    JOptionPane.showMessageDialog(null, "Dados do fornecedor removidos com sucesso!");
		    //as linhas abaixo "limpam" os campos
		    txtfornId.setText(null);
		    txtfornNome.setText(null);
		    txtfornfone.setText(null);
		    txtfornCnpj.setText(null);
		    txtfornEmail.setText(null);
		    txtfornEndereco.setText(null);
		    txtfornComplemento.setText(null);
		    txtfornBairro.setText(null);
		    txtfornCidade.setText(null);
		    txtfornEstado.setText(null);
		    txtfornCep.setText(null);
		    txtfornContato.setText(null);

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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFornecedor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtfornId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtfornNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtfornfone = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtfornCnpj = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfornEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfornEndereco = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfornComplemento = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtfornBairro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfornCep = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtfornCidade = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtfornEstado = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtfornContato = new javax.swing.JTextField();
        btnAlterar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnAdicionar = new javax.swing.JButton();
        btnPequisar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 153), new java.awt.Color(0, 255, 102), null, new java.awt.Color(0, 255, 102)));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Tela Fornecedores");
        setPreferredSize(new java.awt.Dimension(772, 510));

        tblFornecedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFornecedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFornecedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFornecedor);

        jLabel1.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("Id :");

        txtfornId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfornIdActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Nome :");

        txtfornNome.setMaximumSize(new java.awt.Dimension(59, 22));

        jLabel3.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 255));
        jLabel3.setText("fone :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("CNPJ :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Email :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Endereco :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Complemento :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Bairro :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 255));
        jLabel9.setText("Cep :");

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 255));
        jLabel10.setText("Cidade :");

        jLabel11.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 255));
        jLabel11.setText("Estado :");

        jLabel12.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 255));
        jLabel12.setText("Contato :");

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/Editar.png"))); // NOI18N
        btnAlterar.setText("jButton1");
        btnAlterar.setPreferredSize(new java.awt.Dimension(65, 65));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/deletar.png"))); // NOI18N
        btnDeletar.setText("jButton2");
        btnDeletar.setPreferredSize(new java.awt.Dimension(65, 65));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/criar.png"))); // NOI18N
        btnAdicionar.setText("jButton3");
        btnAdicionar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(51, 51, 255), null, null));
        btnAdicionar.setPreferredSize(new java.awt.Dimension(65, 65));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnPequisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bikeshow/icones/pesquizar.png"))); // NOI18N
        btnPequisar.setText("jButton4");
        btnPequisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPequisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfornId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfornNome, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(jLabel3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtfornfone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(17, 17, 17)
                                            .addComponent(jLabel4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtfornCnpj))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)
                                                .addComponent(txtfornEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtfornEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtfornComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(txtfornBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtfornCep)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtfornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtfornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtfornContato, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnAdicionar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnPequisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDeletar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfornId, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtfornNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtfornCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                .addComponent(txtfornfone)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                            .addComponent(txtfornEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(txtfornEndereco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfornComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtfornBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtfornCep, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfornCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtfornEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(txtfornContato, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPequisar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfornIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfornIdActionPerformed
	// TODO add your handling code here:
    }//GEN-LAST:event_txtfornIdActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
	// chamndo o método adicionar
	adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
     alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnPequisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPequisarActionPerformed
	// chamando o método pequisar fornecedor
	pesquisar_fornecedor();
    }//GEN-LAST:event_btnPequisarActionPerformed

    private void tblFornecedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFornecedorMouseClicked
        // TODO add your handling code here:
	setar_campos();
    }//GEN-LAST:event_tblFornecedorMouseClicked

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        // TODO add your handling code here:
	remover();
    }//GEN-LAST:event_btnDeletarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnPequisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblFornecedor;
    private javax.swing.JTextField txtfornBairro;
    private javax.swing.JTextField txtfornCep;
    private javax.swing.JTextField txtfornCidade;
    private javax.swing.JTextField txtfornCnpj;
    private javax.swing.JTextField txtfornComplemento;
    private javax.swing.JTextField txtfornContato;
    private javax.swing.JTextField txtfornEmail;
    private javax.swing.JTextField txtfornEndereco;
    private javax.swing.JTextField txtfornEstado;
    private javax.swing.JTextField txtfornId;
    private javax.swing.JTextField txtfornNome;
    private javax.swing.JTextField txtfornfone;
    // End of variables declaration//GEN-END:variables
}
