
package atividadesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.SwingUtilities;


public class JFrameServidor extends javax.swing.JFrame {
    
    ServerSocket servidor;
    private ArrayList<Pessoa> lista;

    public JFrameServidor() {
        initComponents();
        lista = new ArrayList<>();
    }
    
    private void criaServerSocket() {
        try {
            int porta = Integer.parseInt(txtPorta.getText());
            servidor = new ServerSocket(porta);
            System.out.println("Server escutando na porta " + porta + "\n");
        } catch (Exception ex) {
            System.out.println("Erro ao criar ServerSocket: " + ex.getMessage() + "\n");
        }
    }
    
    private void aguardaClientes() {
        while (true) {
            // Variável local para cada conexão
            Socket cliente = null;
            try {
                // Aguarda conexão
                cliente = servidor.accept();
                System.out.println("Cliente conectado: " + cliente.getInetAddress());
                
                // Solicita ao Comunicador para receber a mensagem
                String nome = Comunicador.receberNome(cliente);
                
                Pessoa pessoa = null;
                boolean encontrado = false;
                
                // Verifica se a pessoa já foi cadastrada
                for (Pessoa p : lista){
                    if (p.getNome().equals(nome)){
                        encontrado = true;
                        break;
                    }
                }
                
                // Caso em que a pessoa ainda não foi cadastrada
                if (!encontrado){
                    String[] vetorNome = nome.split(" ");
                    String email = vetorNome[0].toLowerCase() + "." + vetorNome[vetorNome.length - 1].toLowerCase() + "@ufn.edu.br";
                    
                    Pessoa novaPessoa = new Pessoa(nome, email);
                    lista.add(novaPessoa);
                    pessoa = novaPessoa;
                    System.out.println("Novo cadastro: "+pessoa);
                }
                // Caso em que a pessoa já foi cadastrada
                else {
                    System.out.println("Pessoa já cadastrada: "+nome);
                }
                
                // Solicita ao Comunicador para enviar a resposta
                Comunicador.enviarPessoa(cliente, pessoa);
                
                // Atualiza a interface grafica na thread principal
                SwingUtilities.invokeLater(() -> atualizarListaClientes());
                
                cliente.close();
                
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }
    
    private void atualizarListaClientes() {
        // Cria um vetor de nomes para exibir na JList
        String[] nomesClientes = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            nomesClientes[i] = lista.get(i).getNome();
        }

        // Atualiza a JList na interface gráfica
        listCliente.setListData(nomesClientes);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        txtPorta = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        listCliente = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("SERVIDOR");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Porta:");

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(listCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addComponent(btnIniciar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        criaServerSocket();
        new Thread(() -> aguardaClientes()).start();
        btnIniciar.setEnabled(false);
    }//GEN-LAST:event_btnIniciarActionPerformed


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            JFrameServidor s = new JFrameServidor();
            s.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<String> listCliente;
    private javax.swing.JTextField txtPorta;
    // End of variables declaration//GEN-END:variables
}
