package jheadmet;

import jheadmet.GUI.Output;
import jheadmet.GUI.MainWindow;
import java.util.ArrayList;
import javax.swing.UIManager;

/**
 *
 * @author Felix
 */
public class Headmet extends Processor{

    /* promenne */
    public Output outputWindow = new Output();

    /* prida do seznamu */
    public void addTo(String list, String[] data) {
        if ("settings".equals(list)) {
            this.settings.add(data);
        } else if ("bots".equals(list)) {
            this.bots.add(data);
        } else if ("scripts".equals(list)) {
            this.scripts.add(data);
        } else if ("styles".equals(list)) {
            this.styles.add(data);
        } else if ("og".equals(list)) {
            this.og.add(data);
        } else if ("og_video".equals(list)) {
            this.og_video.add(data);
        } else if ("og_audio".equals(list)) {
            this.og_audio.add(data);
        } else if ("og_location".equals(list)) {
            this.og_location.add(data);
        } else if ("og_contact".equals(list)) {
            this.og_contact.add(data);
        } else {
            this.meta.add(data);
        }
    }

    /* zobrazi vsechno */
    public void printAll() {
        for (String d : this.output) {
            this.outputWindow.add(d);
        }

        // vycisti vsechno
        this.clearAll();
    }

    /* zpracuje vse */
    public void processAll() {
        // vymaze output
        this.outputWindow.clear();
        // nastaveni
        this.processSettings();
        // meta znacky
        this.processMeta();
        // facebook znacky
        this.processOg();
        // boty a overeni
        this.processBots();
        // styly
        this.processStyles();
        // scripty
        this.processScripts();
    }
    
    // generuje komentare v html
    public void comments(String msg) {
        this.output.add(Parser.parse("comment", msg, Settings.FORMAT_COMMENTS));
    }

    // vycisti ArrayListy
    public void clearAll() {
        this.settings.clear();
        this.bots.clear();
        this.scripts.clear();
        this.styles.clear();
        this.meta.clear();
        this.og.clear();
        this.og_audio.clear();
        this.og_contact.clear();
        this.og_location.clear();
        this.og_video.clear();
        this.output.clear();
    }

    // otevre okno
    public void openOutput() {
        this.outputWindow.setVisible(true);
    }

    public static void main(String[] args) {

        // nastavi spravnej vzhled
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        Meta app = new Meta();
        app.addTo("settings", new String[]{"doctype", "XHTML_1_FRAMESET"});
        app.addTo("settings", new String[]{"charset", "UTF-8"});
        
        app.addTo("meta", new String[]{"title", "MUJ WEBIK"});
        app.addTo("meta", new String[]{"author", "ASDASD"});
        app.addTo("settings", new String[]{"favicon", "test.ico"});
        
        app.addTo("og", new String[]{"title", "hahahah"});
        app.addTo("og", new String[]{"admins", "123132"});
        
        app.addTo("og_video", new String[]{"title", "sdasd"});
        app.addTo("og_video", new String[]{"admins", "xdasd"});
        
        app.addTo("scripts", new String[]{"test.js"});
        app.addTo("styles", new String[]{"theme.css", "screen"});
        
        app.processAll();
         */
        // zalozi instanci
        final Headmet app = new Headmet();

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
