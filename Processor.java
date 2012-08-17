package jheadmet;

import java.util.ArrayList;

/**
 *
 * @author Felix
 */
public class Processor {

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
    
    protected ArrayList<String> output = new ArrayList<String>();
    
    /* zpracuje nastaveni */
    public void processSettings() {
        for (String[] value : this.settings) {
            // doctype
            if ("doctype".equals(value[0])) {
                // html5
                if ("HTML 5".equals(value[1])) {
                    this.output.add(Settings.HTML_5);
                    // html4 strict
                } else if ("HTML 4 STRICT".equals(value[1])) {
                    this.output.add(Settings.HTML_4_STRICT);
                    // html4 transional
                } else if ("HTML 4 TRANSITIONAL".equals(value[1])) {
                    this.output.add(Settings.HTML_4_TRANSITIONAL);
                    // html4 frameset
                } else if ("HTML 4 FRAMESET".equals(value[1])) {
                    this.output.add(Settings.HTML_4_FRAMESET);
                    // xhtml1 strict
                } else if ("XHTML 1 STRICT".equals(value[1])) {
                    this.output.add(Settings.XHTML_1_STRICT);
                    // xhtml1 transional
                } else if ("XHTML 1 TRANSITIONAL".equals(value[1])) {
                    this.output.add(Settings.XHTML_1_TRANSITIONAL);
                    // xhtml1 frameset
                } else if ("XHTML 1 FRAMESET".equals(value[1])) {
                    this.output.add(Settings.XHTML_1_FRAMESET);
                }
                // charset
            } else if ("charset".equals(value[0])) {
                this.output.add(Parser.parse("charset", value[1], Settings.FORMAT_CHARSET));
                // favicon
            } else if ("favicon".equals(value[0])) {
                this.output.add(Parser.parse("favicon", value[1], Settings.FORMAT_FAVICON));
            }
        }
    }

    /* zpracuje meta znacky */
    public void processMeta() {
        for (String[] value : this.meta) {
            // titulek
            if ("title".equals(value[0])) {
                this.output.add(Parser.parse(value[0], value[1], Settings.FORMAT_META));
                this.output.add(Parser.parse(value[0], value[1], Settings.FORMAT_TITLE));
            } else {
                // znacka
                this.output.add(Parser.parse(value[0], value[1], Settings.FORMAT_META));
            }
        }
    }

    /* zpracuje facebook znacky */
    public void processOg() {
        if(this.og.size()>0) this.comments("FACEBOOK");
        // klasicky
        for (String[] value : this.og) {
            // pro admins a app_id je fb:
            if ("admins".equals(value[0]) | "app_id".equals(value[0]) | "page_id".equals(value[0])) {
                this.output.add(Parser.parse("fb:" + value[0], value[1], Settings.FORMAT_META_OG));
            } else {
                this.output.add(Parser.parse("og:" + value[0], value[1], Settings.FORMAT_META_OG));
            }
        }
        if(this.og.size()>0) this.comments("/FACEBOOK");
    }

    /* zpracuje facebook:video znacky */
    public void processOgVideo() {
        // video
        for (String[] value : this.og_video) {
            if ("video".equals(value[0])) {
                this.output.add(Parser.parse("og:video", value[1], Settings.FORMAT_META_OG));
            } else {
                this.output.add(Parser.parse("og:video:" + value[0], value[1], Settings.FORMAT_META_OG));
            }
        }
    }

    /* zpracuje facebook:audio znacky */
    public void processOgAudio() {
        // audio
        for (String[] value : this.og_audio) {
            if ("audio".equals(value[0])) {
                this.output.add(Parser.parse("og:audio", value[1], Settings.FORMAT_META_OG));
            } else {
                this.output.add(Parser.parse("og:audio:" + value[0], value[1], Settings.FORMAT_META_OG));
            }
        }
    }

    /* zpracuje facebook:kontakt znacky */
    public void processOgContact() {
        // kontakt
        for (String[] value : this.og_contact) {
            this.output.add(Parser.parse("og:" + value[0], value[1], Settings.FORMAT_META_OG));
        }
    }

    /* zpracuje facebook:lokalita znacky */
    public void processOgLocation() {
        // pozice
        for (String[] value : this.og_location) {
            this.output.add(Parser.parse("og:" + value[0], value[1], Settings.FORMAT_META_OG));
        }
    }

    /* zpracuje boty */
    public void processBots() {
        for (String[] value : this.bots) {
            // znacka
            this.output.add(Parser.parse(value[0], value[1], Settings.FORMAT_META));
        }
    }

    /* zpracuje styly */
    public void processStyles() {
        for (String[] value : this.styles) {
            // znacka
            this.output.add(Parser.parse(value[0], value[1], Settings.FORMAT_STYLES));
        }
    }

    /* zpracuje javascripty */
    public void processScripts() {
        for (String[] value : this.scripts) {
            // znacka
            this.output.add(Parser.parse("script", value[1], Settings.FORMAT_SCRIPTS));
        }
    }
    
    // generuje komentare v html
    public void comments(String msg) {
        this.output.add(Parser.parse("comment", msg, Settings.FORMAT_COMMENTS));
    }    
}
