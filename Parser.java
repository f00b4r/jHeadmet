package jheadmet;

/**
 *
 * @author Felix
 */
public class Parser {
    
    public static boolean isXhtml = false;
    public static boolean isRelativePath = false;
    
    public static boolean isXhtml(){
        return Parser.isXhtml;
    }

    public static void setXhtml(boolean xhtml){
        Parser.isXhtml = xhtml;
    }

    public static String getRelativePath(){
        if(Parser.isRelativePath){
            return "/";
        }else{
            return "";
        }
    }
 
    public static void setRelativePath(boolean path){
        Parser.isRelativePath = path;
    }    
    
    /* zkontroluje url */
    public static String rightUrl(String url){
        if(url.startsWith("/http")){
            return url.substring(1);
        }else{
            return url;
        }
    }
    
    // naplni sablonu
    public static String parse(String name, String content, int format) {
        String template, temp;

        // ma se pouzit xhtml tag?
        String tag = Parser.isXhtml() ? Templates.template_xhtml_tag : "";

        // vybere sablonu
        switch (format) {
            // sablona znakovy sady
            case Settings.FORMAT_CHARSET:
                template = Templates.template_charset;
                temp = String.format(template, content, tag);
                break;
            // sablona titulku
            case Settings.FORMAT_TITLE:
                template = Templates.template_title;
                temp = String.format(template, content);
                break;
            // sablona ogt znacek
            case Settings.FORMAT_META_OG:
                template = Templates.template_meta_og;
                temp = String.format(template, name, content, tag);
                break;
            // sablona favicony
            case Settings.FORMAT_FAVICON:
                template = Templates.template_favicon;
                temp = String.format(template, Parser.rightUrl(content), tag);
                break;
            // sablona css stylu
            case Settings.FORMAT_STYLES:
                template = Templates.template_styles;
                temp = String.format(template, Parser.rightUrl(name), content, tag);
                break;
            // sablona javascriptu
            case Settings.FORMAT_SCRIPTS:
                template = Templates.template_scripts;
                temp = String.format(template, Parser.rightUrl(content));
                break;
            // sablona komentaru
            case Settings.FORMAT_COMMENTS:
                template = Templates.template_comments;
                temp = String.format(template, content);
                break;
            // defaultni sablona - meta znacek
            default:
                template = Templates.template_meta;
                temp = String.format(template, name, content, tag);
        }

        return temp;
    }

}
