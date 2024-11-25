
package Modelo;

/**
 *
 * @author bianc
 */
import java.io.*;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Livro> livros;
    private final String arquivoDados = "biblioteca.txt";

    public Biblioteca() {
        livros = new ArrayList<>();
        inicializarLivros(); 
        carregarDados();
    }

    private void inicializarLivros() {
        livros.add(new Livro("1984", "George Orwell", true));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", true));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", true));
        livros.add(new Livro("Orgulho e Preconceito", "Jane Austen", true));
        livros.add(new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", true));
        livros.add(new Livro("A Revolução dos Bichos", "George Orwell", true));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", true));
        livros.add(new Livro("O Morro dos Ventos Uivantes", "Emily Brontë", true));
        livros.add(new Livro("Moby Dick", "Herman Melville", true));
        livros.add(new Livro("A Metamorfose", "Franz Kafka", true));
        livros.add(new Livro("O Sol é Para Todos", "Harper Lee", true));
        livros.add(new Livro("Fahrenheit 451", "Ray Bradbury", true));
        livros.add(new Livro("O Estrangeiro", "Albert Camus", true));
        livros.add(new Livro("A Menina que Roubava Livros", "Markus Zusak", true));
        livros.add(new Livro("O Alquimista", "Paulo Coelho", true));
        livros.add(new Livro("Harry Potter e a Pedra Filosofal", "J.K. Rowling", true));
        livros.add(new Livro("A Sombra do Vento", "Carlos Ruiz Zafón", true));
        livros.add(new Livro("O Código Da Vinci", "Dan Brown", true));
        livros.add(new Livro("A Guerra dos Tronos", "George R.R. Martin", true));
        livros.add(new Livro("O Diário de Anne Frank", "Anne Frank", true));
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        salvarDados();
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
        salvarDados();
    }

    public void emprestarLivro(Livro livro) {
        if (livros.contains(livro) && livro.isDisponivel()) {
            livro.setDisponivel(false);
            salvarDados();
        }
    }

    public void devolverLivro(Livro livro) {
        if (livros.contains(livro) && !livro.isDisponivel()) {
            livro.setDisponivel(true);
            salvarDados();
        }
    }

    public ArrayList<Livro> listarLivros() {
        return livros;
    }

    private void carregarDados() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoDados))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";");
                boolean disponivel = Boolean.parseBoolean(partes[2]);
                livros.add(new Livro(partes[0], partes[1], disponivel));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void salvarDados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoDados))) {
            for (Livro livro : livros) {
                bw.write(livro.getTitulo() + ";" + livro.getAutor() + ";" + livro.isDisponivel());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }
}