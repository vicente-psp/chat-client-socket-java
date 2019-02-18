package client;

import util.Mensagem;
import util.Status;

import javax.swing.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private Socket socket;

    public static void main(String[] args) {
        new Cliente("localhost", 3322);
    }

    public Cliente(String local, int porta) {
        try {
            System.out.println("Estabelecendo conexão...");
            socket = new Socket(local, porta);
            System.out.println("Conexão estabelecida.");

            ObjectInputStream objInput = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());

            System.out.println("Enviando mensagem...");

            Mensagem mensagem = new Mensagem("CLIENT");
            mensagem.setStatus(Status.SOLICITACAO);
            mensagem.setParam("nome", "Eduardo");
            mensagem.setParam("sobrenome", "Dipp");
            objOutput.writeObject(mensagem);
            objOutput.flush();

            System.out.println("\nMensagem\n" + mensagem + " enviando.\n");

            mensagem = (Mensagem) objInput.readObject();

            if(mensagem.getStatus() == Status.OK){
                System.out.println("Resposta\n" + mensagem);
//                String resp = (String) mensagem.getParam("mensagem");
//                System.out.println("Mensagem: " + resp + "\n");
            }else{
                System.out.println("Erro: " + mensagem.getStatus() + "\n");
            }

            objInput.close();
            objOutput.close();
            socket.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no Cliente: " + e.getMessage());
        }
    }
}
