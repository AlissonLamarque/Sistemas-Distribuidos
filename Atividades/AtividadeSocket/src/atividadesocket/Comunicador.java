
package atividadesocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicador {

    public static Pessoa solicitarCadastro(String host, int porta, String nome) throws Exception{
        try (Socket socket = new Socket(host, porta)) {
            //Envia o nome para o servidor
            ObjectOutputStream saida = new ObjectOutputStream(socket.getOutputStream());
            saida.writeObject(nome);
            saida.flush();

            // Recebe a resposta do servidor
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream());
            Pessoa pessoaRecebida = (Pessoa) entrada.readObject();
            
            return pessoaRecebida;
        }
    }

    public static String receberNome(Socket cliente) throws Exception{
        ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
        return (String) entrada.readObject();
    }

    public static void enviarPessoa(Socket cliente, Pessoa p) throws Exception{
        ObjectOutputStream saida = new ObjectOutputStream(cliente.getOutputStream());
        saida.writeObject(p);
        saida.flush();
    }
}
