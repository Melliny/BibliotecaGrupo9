
package Visao;

/**
 *
 * @author bianc
 */
import Modelo.Biblioteca;
import Modelo.Livro;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaView extends JFrame {
    private final JTextArea textAreaDisponiveis;
    private final JTextArea textAreaEmprestados;
    private final JButton btnAdicionar;
    private final JButton btnRemover;
    private final JButton btnEmprestar;
    private final JButton btnDevolver;
    private final JTextField txtTitulo;
    private final JTextField txtAutor;
    private final Biblioteca biblioteca;

    public BibliotecaView(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;

        setTitle("Biblioteca");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panelDisponiveis = new JPanel(new BorderLayout());
        textAreaDisponiveis = new JTextArea();
        textAreaDisponiveis.setEditable(false);
        panelDisponiveis.add(new JScrollPane(textAreaDisponiveis), BorderLayout.CENTER);
        tabbedPane.addTab("Disponíveis", panelDisponiveis);
        
        JPanel panelEmprestados = new JPanel(new BorderLayout());
        textAreaEmprestados = new JTextArea();
        textAreaEmprestados.setEditable(false);
        panelEmprestados.add(new JScrollPane(textAreaEmprestados), BorderLayout.CENTER);
        tabbedPane.addTab("Emprestados", panelEmprestados);

        JPanel panelInputs = new JPanel();
        panelInputs.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtTitulo = new JTextField(15);
        txtAutor = new JTextField(15);
        btnAdicionar = new JButton("Adicionar Livro");
        btnRemover = new JButton("Remover Livro");
        btnEmprestar = new JButton("Emprestar Livro");
        btnDevolver = new JButton("Devolver Livro");

        gbc.gridx = 0; gbc.gridy = 0; panelInputs.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1; panelInputs.add(txtTitulo, gbc);
        gbc.gridx = 0; gbc.gridy = 1; panelInputs.add(new JLabel("Autor:"), gbc);
        gbc.gridx = 1; panelInputs.add(txtAutor, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; panelInputs.add(btnAdicionar, gbc);
        gbc.gridx = 1; panelInputs.add(btnRemover, gbc);
        gbc.gridx = 0; gbc.gridy = 3; panelInputs.add(btnEmprestar, gbc);
        gbc.gridx = 1; panelInputs.add(btnDevolver, gbc);

        add(tabbedPane, BorderLayout.CENTER);
        add(panelInputs, BorderLayout.SOUTH);

        atualizarListas();

        btnAdicionar.addActionListener(e -> adicionarLivro());
        btnRemover.addActionListener(e -> removerLivro());
        btnEmprestar.addActionListener(e -> emprestarLivro());
        btnDevolver.addActionListener(e -> devolverLivro());

        setVisible(true);
    }

    private void adicionarLivro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        if (!titulo.isEmpty() && !autor.isEmpty()) {
            Livro livro = new Livro(titulo, autor, true);
            biblioteca.adicionarLivro(livro);
            txtTitulo.setText("");
            txtAutor.setText("");
            atualizarListas();
        }
    }

    private void removerLivro() {
        String titulo = txtTitulo.getText();
        for (Livro livro : biblioteca.listarLivros()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                biblioteca.removerLivro(livro);
                txtTitulo.setText("");
                atualizarListas();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado!");
    }

    private void emprestarLivro() {
        String titulo = txtTitulo.getText();
        for (Livro livro : biblioteca.listarLivros()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                biblioteca.emprestarLivro(livro);
                txtTitulo.setText("");
                atualizarListas();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não disponível ou não encontrado!");
    }

    private void devolverLivro() {
        String titulo = txtTitulo.getText();
        for (Livro livro : biblioteca.listarLivros()) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                biblioteca.devolverLivro(livro);
                txtTitulo.setText("");
                atualizarListas();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Livro não encontrado!");
    }

    private void atualizarListas() {
        StringBuilder listaDisponiveis = new StringBuilder();
        StringBuilder listaEmprestados = new StringBuilder();
        for (Livro livro : biblioteca.listarLivros()) {
            String infoLivro = livro.getTitulo() + " - " + livro.getAutor();
            if (livro.isDisponivel()) {
                listaDisponiveis.append(infoLivro).append("\n");
            } else {
                listaEmprestados.append(infoLivro).append("\n");
            }
        }
        textAreaDisponiveis.setText(listaDisponiveis.toString());
        textAreaEmprestados.setText(listaEmprestados.toString());
    }
}