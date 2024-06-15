package com.br.barberq.barberq.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;

public class NotificaçãoMensagem {

    public static void main(String[] args) throws Exception {
        int porta = 8080; 
        String mensagem = "Agendamento realizado com sucesso!"; 

        ServerSocket servidorSocket = new ServerSocket(porta);
        servidorSocket.close();
        System.out.println("Servidor escutando na porta " + porta);

        while (true) {
            Socket clienteSocket = servidorSocket.accept();
            System.out.println("Cliente conectado!");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            BufferedWriter saida = new BufferedWriter(new OutputStreamWriter(clienteSocket.getOutputStream()));

            String requisicao = entrada.readLine();
            System.out.println("Requisição: " + requisicao);

            saida.write("HTTP/1.1 200 OK\r\n");
            saida.write("Content-Type: text/html\r\n");
            saida.write("\r\n");
            saida.write("<html><body>");
            saida.write("<h1>" + mensagem + "</h1>");
            saida.write("</body></html>");
            saida.flush();

            saida.close();
            entrada.close();
            clienteSocket.close();
        }
    }
}

