
package n2.sistemadebiblioteca;

import Modelo.Biblioteca;
import Visao.BibliotecaView;

/**
 *
 * @author bianc
 */
public class SistemaDeBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        BibliotecaView view = new BibliotecaView(biblioteca);
    }
}
