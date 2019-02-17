package client;

import util.Mensagem;
import util.Status;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Cliente {

    private Socket socket;

    public static void main(String[] args) {
//        String ip = JOptionPane.showInputDialog(null, "Informe o ip do servidor");
        String ip = "localhost";
        do{
//            String porta = JOptionPane.showInputDialog(null, "Informe a porta do servidor");
            String porta = "8000";
            try {
                if (Integer.parseInt(porta) > 0 && Integer.parseInt(porta) < 80000){
                    new Cliente(ip, Integer.parseInt(porta));
                    break;
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Informe uma porta válida. Error: " + e.getMessage());
            }
        }while (true);
    }

    public Cliente(String ip, int porta) {
        try {
            System.out.println("Estabelendo conexão com servidor...");
            socket = new Socket(ip, porta);
            System.out.println("Conexão com servidor estabelecida.");

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enviando mensagem para o servidor.");

            Mensagem mensagem = new Mensagem("teste");
            mensagem.setStatus(Status.SOLICITACAO);
            mensagem.setParam("nome", "Eduardo");
            mensagem.setParam("sobrenome", "Dipp");

            objectOutputStream.writeObject(mensagem);
            objectOutputStream.flush();
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Erro de conexão no cliente. Error: " + e.getMessage());
        }
    }
}
