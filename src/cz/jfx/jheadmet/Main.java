package cz.jfx.jheadmet;

import cz.jfx.jheadmet.app.gui.MainWindow;
import javax.swing.UIManager;

/**
 *
 * @author Felix
 */
public class Main {

    public static void main(String[] args) {

        // nastavi spravnej vzhled
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        // zalozi instanci
        final Application app = new Application();

        // spusti okno
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                MainWindow f = new MainWindow();
                f.meta = app;
                f.setVisible(true);
            }
        });

    }
}
