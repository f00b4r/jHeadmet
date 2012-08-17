package jheadmet;

import java.util.ArrayList;
import javax.swing.UIManager;

/**
 *
 * @author Felix
 */
public class Meta {

    /* program */
    final String VERSION = "2.0a";
    final String UPDATE = "15.6. 2011 - 20:00";

    /* array listy */
    public ArrayList<String[]> settings = new ArrayList<String[]>();
    public ArrayList<String[]> bots = new ArrayList<String[]>();
    public ArrayList<String[]> scripts = new ArrayList<String[]>();
    public ArrayList<String[]> styles = new ArrayList<String[]>();
    public ArrayList<String[]> meta = new ArrayList<String[]>();
    public ArrayList<String[]> og = new ArrayList<String[]>();
    public ArrayList<String[]> og_video = new ArrayList<String[]>();
    public ArrayList<String[]> og_audio = new ArrayList<String[]>();
    public ArrayList<String[]> og_location = new ArrayList<String[]>();
    public ArrayList<String[]> og_contact = new ArrayList<String[]>();
    public ArrayList<String> output = new ArrayList<String>();

    /* sablony */
    public String template_meta = "<meta name=\"%s\" content=\"%s\"%s>";
    public String template_meta_og = "<meta property=\"%s\" content=\"%s\"%s>";
    public String template_title = "<title>%s</title>";
    public String template_favicon = "<link rel=\"shortcut icon\" href=\"%s\" type=\"image/x-icon\"%s>";
    public String template_charset = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=%s\"%s>";
    public String template_comments = "<!-- %s -->";
    public String template_xhtml_tag = " /";
    public String template_styles = "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\" media=\"%s\"%s>";
    public String template_scripts = "<script type=\"text/javascript\" src=\"%s\"></script>";
    public String template_page = "<html>\n<head>\n%s\n</head>";

    /* doctype */
    public final String HTML_4_STRICT = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">";
    public final String HTML_4_TRANSITIONAL = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
    public final String HTML_4_FRAMESET = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">";
    public final String HTML_5 = "<!DOCTYPE html>";
    public final String XHTML_1_STRICT = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">";
    public final String XHTML_1_TRANSITIONAL = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
    public final String XHTML_1_FRAMESET = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">";

    /* formaty */
    public final int FORMAT_CHARSET = 1;
    public final int FORMAT_TITLE = 2;
    public final int FORMAT_META = 3;
    public final int FORMAT_META_OG = 4;
    public final int FORMAT_FAVICON = 5;
    public final int FORMAT_STYLES = 50;
    public final int FORMAT_SCRIPTS = 51;
    public final int FORMAT_COMMENTS = 99;

    /* pracovni */
    public boolean xhtml = false;
    public boolean relative_path = true;
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

    /* zpracuje nastaveni */
    public void processSettings() {
        for (String[] value : this.settings) {
            // doctype
            if ("doctype".equals(value[0])) {
                // html5
                if ("HTML 5".equals(value[1])) {
                    this.output.add(this.HTML_5);
                    // html4 strict
                } else if ("HTML 4 STRICT".equals(value[1])) {
                    this.output.add(this.HTML_4_STRICT);
                    // html4 transional
                } else if ("HTML 4 TRANSITIONAL".equals(value[1])) {
                    this.output.add(this.HTML_4_TRANSITIONAL);
                    // html4 frameset
                } else if ("HTML 4 FRAMESET".equals(value[1])) {
                    this.output.add(this.HTML_4_FRAMESET);
                    // xhtml1 strict
                } else if ("XHTML 1 STRICT".equals(value[1])) {
                    this.output.add(this.XHTML_1_STRICT);
                    // xhtml1 transional
                } else if ("XHTML 1 TRANSITIONAL".equals(value[1])) {
                    this.output.add(this.XHTML_1_TRANSITIONAL);
                    // xhtml1 frameset
                } else if ("XHTML 1 FRAMESET".equals(value[1])) {
                    this.output.add(this.XHTML_1_FRAMESET);
                }
                // charset
            } else if ("charset".equals(value[0])) {
                this.output.add(this.parse("charset", value[1], this.FORMAT_CHARSET));
                // favicon
            } else if ("favicon".equals(value[0])) {
                this.output.add(this.parse("favicon", this.isRelativePath() + value[1], this.FORMAT_FAVICON));
            }
        }
    }

    /* zpracuje meta znacky */
    public void processMeta() {
        for (String[] value : this.meta) {
            // titulek
            if ("title".equals(value[0])) {
                this.output.add(this.parse(value[0], value[1], this.FORMAT_META));
                this.output.add(this.parse(value[0], value[1], this.FORMAT_TITLE));
            } else {
                // znacka
                this.output.add(this.parse(value[0], value[1], this.FORMAT_META));
            }
        }
    }

    /* zpracuje facebook znacky */
    public void processOg() {
        // klasicky
        for (String[] value : this.og) {
            // pro admins a app_id je fb:
            if ("admins".equals(value[0]) | "app_id".equals(value[0])) {
                this.output.add(this.parse("fb:" + value[0], value[1], this.FORMAT_META_OG));
            } else {
                this.output.add(this.parse("og:" + value[0], value[1], this.FORMAT_META_OG));
            }
        }
        // video
        for (String[] value : this.og_video) {
            if ("video".equals(value[0])) {
                this.output.add(this.parse("og:video", value[1], this.FORMAT_META_OG));
            } else {
                this.output.add(this.parse("og:video:" + value[0], value[1], this.FORMAT_META_OG));
            }
        }
        // audio
        for (String[] value : this.og_audio) {
            if ("audio".equals(value[0])) {
                this.output.add(this.parse("og:audio", value[1], this.FORMAT_META_OG));
            } else {
                this.output.add(this.parse("og:audio:" + value[0], value[1], this.FORMAT_META_OG));
            }
        }
        // kontakt
        for (String[] value : this.og_contact) {
            this.output.add(this.parse("og:" + value[0], value[1], this.FORMAT_META_OG));
        }
        // pozice
        for (String[] value : this.og_location) {
            this.output.add(this.parse("og:" + value[0], value[1], this.FORMAT_META_OG));
        }
    }

    /* zpracuje boty */
    public void processBots() {
        for (String[] value : this.bots) {
            // znacka
            this.output.add(this.parse(value[0], value[1], this.FORMAT_META));
        }
    }

    /* zpracuje styly */
    public void processStyles() {
        for (String[] value : this.styles) {
            // znacka
            this.output.add(this.parse(this.isRelativePath() + value[0], value[1], this.FORMAT_STYLES));
        }
    }

    /* zpracuje javascripty */
    public void processScripts() {
        for (String[] value : this.scripts) {
            // znacka
            this.output.add(this.parse("script", this.isRelativePath() + value[0], this.FORMAT_SCRIPTS));
        }
    }

    // naplni sablonu
    public String parse(String name, String content, int format) {
        String template, temp;

        // ma se pouzit xhtml tag?
        String tag = this.isXhtml() ? this.template_xhtml_tag : "";

        // vybere sablonu
        switch (format) {
            // sablona znakovy sady
            case 1:
                template = this.template_charset;
                temp = String.format(template, content, tag);
                break;
            // sablona titulku
            case 2:
                template = this.template_title;
                temp = String.format(template, content);
                break;
            // sablona ogt znacek
            case 4:
                template = this.template_meta_og;
                temp = String.format(template, name, content, tag);
                break;
            // sablona favicony
            case 5:
                template = this.template_favicon;
                temp = String.format(template, content, tag);
                break;
            // sablona css stylu
            case 50:
                template = this.template_styles;
                temp = String.format(template, name, content, tag);
                break;
            // sablona javascriptu
            case 51:
                template = this.template_scripts;
                temp = String.format(template, content);
                break;
            // sablona komentaru
            case 99:
                template = this.template_comments;
                temp = String.format(template, content);
                break;
            // defaultni sablona - meta znacek
            default:
                template = this.template_meta;
                temp = String.format(template, name, content, tag);
        }

        return temp;
    }

    // generuje komentare v html
    public void comments(String msg) {
        this.output.add(this.parse("comment", msg, this.FORMAT_COMMENTS));
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

    // je stranka xhtml?
    public boolean isXhtml() {
        if (xhtml) {
            return true;
        } else {
            return false;
        }
    }

    // nastavi xhtml
    public void setXhtml(boolean xhtml) {
        this.xhtml = xhtml;
    }

    // je nastavena relativni cesta?
    public String isRelativePath() {
        if (this.relative_path) {
            return "/";
        } else {
            return "";
        }
    }

    // nastavi relativni cestu
    public void setRelativePath(boolean relative_path) {
        this.relative_path = relative_path;
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
        final Meta app = new Meta();

        // spusti okno
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Frame f = new Frame();
                f.meta = app;
                f.setVisible(true);
            }
        });

    }
}
