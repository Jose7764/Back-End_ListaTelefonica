package org.example;

import org.example.dao.ContatoDao;
import org.example.model.Contato;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {

        inicio();


    }

    public static void inicio(){
        Scanner input = new Scanner(System.in);

        boolean sair = false;

        System.out.println("================ Lista Telefonica ====================\n" +
                "1. Cadastrar contato: \n" +
                "2. Listar todos os contatos cadastrados.\n" +
                "3. Buscar contato por nome.\n" +
                "4. Atualizar dados de um contato \n" +
                "5. Remover contato\n" +
                "6. Sair do sistema");

            int opcao = input.nextInt();
            input.nextLine();

        switch (opcao){

            case 1:

                CadastrarContato();
                break;

            case 2:
                listarContatos();

                break;

            case 3:
                buscarContatoPorNome();
                break;

            case 4:
                atualizarContato();
                break;

            case 6:
                sair = true;
                break;

        }

        if (!sair) {
            inicio();
        }
    }

    private static void atualizarContato() {

        var dao = new ContatoDao();

        try {
            List<Contato> contatos = dao.listarContatos();
        }catch(SQLException e){
            System.out.println("Erro");
        }
        System.out.println("Digite o id do contato que deseja alterar: ");
    }


    private static void buscarContatoPorNome() {

        System.out.println("============  Buscar Contato por nome ===========");

            List<Contato> contatos = new ArrayList<>();
        for (Contato contato : contatos){
            System.out.println("==========================================");
            System.out.println("ID: "+ contato.getId());
            System.out.println("NOME: "+ contato.getNome());
            System.out.println("TELEFONE: "+ contato.getTelefone());
            System.out.println("EMAIL: "+ contato.getEmail());
            System.out.println("OBSEVAÇÂO: "+ contato.getObservacao());
            System.out.println("==========================================");

        }

    }

    private static void listarContatos() {
        System.out.println("================== Contatos ====================");
        var dao = new ContatoDao();

        try{

            List<Contato> contatos = dao.listarContatos();

            for (Contato contato : contatos){
                System.out.println("==========================================");
                System.out.println("ID: "+ contato.getId());
                System.out.println("NOME: "+ contato.getNome());
                System.out.println("TELEFONE: "+ contato.getTelefone());
                System.out.println("EMAIL: "+ contato.getEmail());
                System.out.println("OBSEVAÇÂO: "+ contato.getObservacao());
                System.out.println("==========================================");

            }

        }catch (SQLException e){
            System.out.println("Erro conexao");

        }

    }

    public static void CadastrarContato(){
        Scanner input = new Scanner(System.in);

        System.out.println("    ====   Cadastro de Contato  ====    ");
        System.out.println("Digite o nome do Contato: ");
        String nome = input.nextLine();

        System.out.println("Digite o telefone do Contato: ");
        String telefone = input.nextLine();

        System.out.println("Digite o email do Contato: ");
        String email = input.nextLine();

        System.out.println("Digite o observação do Contato: ");
        String observacao = input.nextLine();

        var contato =  new Contato(nome, telefone, email, observacao);
        var dao = new ContatoDao();

        try{
            dao.inserirContato(contato);
            System.out.println("Contato inserido");
        }catch (SQLException e){
            System.out.println("Erro ");
            e.printStackTrace();

        }
    }
}