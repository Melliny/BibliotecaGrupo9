
package Modelo;

/**
 *
 * @author bianc
 */
public class Livro {
    private final String titulo;
    private final String autor;
    private boolean disponivel;

    public Livro(String titulo, String autor, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + (disponivel ? " (Disponível)" : " (Emprestado)");
    }
}