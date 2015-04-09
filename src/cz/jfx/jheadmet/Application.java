package cz.jfx.jheadmet;

import cz.jfx.jheadmet.app.Processor;
import cz.jfx.jheadmet.app.Templates;
import cz.jfx.jheadmet.app.gui.Output;

/**
 *
 * @author Felix
 */
public class Application extends Processor {

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
        } else if ("rss".equals(list)) {
            this.rss.add(data);
        } else {
            this.meta.add(data);
        }
    }

    /* zobrazi vsechno */
    public void printAll() {

        // zacatek html skeletonu
        if (this.skeleton) {
            if (!"null".equals(this.processDoctype())) {
                this.outputWindow.add(this.processDoctype());
            }
            this.outputWindow.add(Templates.skeleton_begin);
        }

        // renderovani..
        for (String d : this.output) {
            this.outputWindow.add((this.skeleton ? "\t" : "") + d);
        }

        // konec html skeletonu
        if (this.skeleton) {
            this.outputWindow.add(Templates.skeleton_end);
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
        // facebook znacky
        this.processOgAudio();
        this.processOgVideo();
        this.processOgLocation();
        this.processOgContact();
        this.processOg();
        // boty a overeni
        this.processBots();
        // meta znacky
        this.processMeta();
        // rss
        this.processRss();
        // styly
        this.processStyles();
        // scripty
        this.processScripts();
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
        this.rss.clear();
    }

    // otevre okno
    public void openOutput() {
        this.outputWindow.setVisible(true);
    }
}
