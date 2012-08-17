package jheadmet;

/**
 *
 * @author Felix
 */
public class Parser {

    /* xhtml */
    public static boolean isXhtml = false;
    /* relativni cesta */
    public static boolean isRelativePath = false;
    /* nazev webu */
    public static String url_base;

    /* vraci xhtml */
    public static boolean isXhtml() {
        return Parser.isXhtml;
    }

    /* nastavuje xhtml */
    public static void setXhtml(boolean xhtml) {
        Parser.isXhtml = xhtml;
    }

    /* vraci / nebo "" */
    public static String getRelativePath() {
        if (Parser.isRelativePath) {
            return "/";
        } else {
            return "";
        }
    }

    /* nastavuje relativni oddelovac (ano/ne) */
    public static void setRelativePath(boolean path) {
        Parser.isRelativePath = path;
    }

     /* vraci url */
    public static String getUrl() {
        return Parser.url_base;
    }

    /* nastavuje url */
    public static void setUrl(String url_base) {
        Parser.url_base = url_base;
    }
    
    /* zkontroluje url */
    public static String rightUrl(String url) {
        // TODO
        return url;
    }

    /* zkontroluje odkaz */
    public static String rightLink(String link) {
        if (link.startsWith("/http")) { // zpetna kompatibilita
            return link.substring(1);
        } else if (link.indexOf("http") != -1) {
            return link;
        } else {
            return Parser.getRelativePath() + link;
        }
    }

    /* zkontroluje JAVASCRIPT koncovku */
    public static String rightJsExt(String file) {
        return Parser.rightExtension(file, "js");
    }

    /* zkontroluje CSS koncovku */
    public static String rightCssExt(String file) {
        return Parser.rightExtension(file, "css");
    }

    /* zkontroluje ICO koncovku */
    public static String rightIcoExt(String file) {
        return Parser.rightExtension(file, "ico");
    }

    /* zkontroluje RSS koncovku */
    public static String rightRssExt(String file) {
        return Parser.rightExtension(file, "rss");
    }
    
    /* zkontroluje obecne koncovku */
    public static String rightExtension(String file, String ext) {
        if (file.endsWith("." + ext)) {
            return file;
        } else {
            return file + "." + ext;
        }
    }

    /* vycisti retezec */
    public static String cleanString(String str){
        str = str.trim();
        return str;
    }
    
    // naplni sablonu
    public static String parse(String name, String content, int format) {
        String template, temp;
        
        // odstraneni mezer apod
        name = Parser.cleanString(name);
        content = Parser.cleanString(content);

        // ma se pouzit xhtml tag?
        String tag = Parser.isXhtml() ? Templates.xhtml_tag : "";

        // vybere sablonu
        switch (format) {
            // sablona znakovy sady
            case Settings.FORMAT_CHARSET:
                template = Templates.charset;
                temp = String.format(template, content, tag);
                break;
            // sablona titulku
            case Settings.FORMAT_TITLE:
                template = Templates.title;
                temp = String.format(template, content);
                break;
            // sablona ogt znacek
            case Settings.FORMAT_META_OG:
                template = Templates.meta_og;
                temp = String.format(template, name, content, tag);
                break;
            // sablona favicony
            case Settings.FORMAT_FAVICON:
                template = Templates.favicon;
                temp = String.format(template, Parser.rightLink(content), tag);
                break;
            // sablona css stylu
            case Settings.FORMAT_STYLES:
                template = Templates.styles;
                temp = String.format(template, Parser.rightLink(Parser.rightCssExt(name)), content, tag);
                break;
            // sablona javascriptu
            case Settings.FORMAT_SCRIPTS:
                template = Templates.scripts;
                temp = String.format(template, Parser.rightLink(Parser.rightJsExt(content)));
                break;
            // sablona rss
            case Settings.FORMAT_RSS:
                template = Templates.rss;
                temp = String.format(template, name, Parser.rightLink(Parser.rightRssExt(content)), tag);
                break;
            // sablona komentaru
            case Settings.FORMAT_COMMENTS:
                template = Templates.comments;
                temp = String.format(template, content);
                break;
            // defaultni sablona - meta znacek
            default:
                template = Templates.meta;
                temp = String.format(template, name, content, tag);
        }

        return temp;
    }
}
