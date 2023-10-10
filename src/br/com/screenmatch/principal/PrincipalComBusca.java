package br.com.screenmatch.principal;

// Importa os arquivos locais
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Importa os arquivos Gson
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.screenmatch.modelos.Titulo;
import br.com.screenmatch.modelos.TituloOmdb;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        // cria um metodo Scanner
        Scanner leitura = new Scanner(System.in);
        String busca = " ";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();
        // fecha o metodo Scanner

         while(!busca.equalsIgnoreCase("Sais")) {
            System.out.println("Digite um filme para busca: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("Sair")) {
                break;
            }

            // Usa o metodo Scanner para concatenar com a API e conseguir buscar o filme
            String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + "&apikey=9dd352ea"; // procura pelo filme
        
            try {
            // Faz um require para o filme informado na busca
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

            // Tras a resposta do filme informado na busca apos o require
            HttpResponse<String> response = client
                .send(request, BodyHandlers.ofString());

            String json = response.body();
            System.out.println(json);
            
            TituloOmdb meuTituloOmdb = gson.fromJson(json,TituloOmdb.class);
            System.out.println(meuTituloOmdb);
            // Tenta executar um a instrução para a execução do programa
            //try {
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Titulo já convertido");
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);
            } catch (NumberFormatException e) { // Contém o código para ser exibido caso ocorra uma exceção
                System.out.println("Aconteceu erro :");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Algun erro de argumento na busca, verifique o endereço !");
            } catch (ErroDeConversaoDeAnoException e) { // Utilizando a Exceção personalizada
                System.out.println(e.getMessage());
            }
        }
        leitura.close();
        System.out.println(titulos);
        
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("O programa finalizou corretamente!");
    }
}
