package jheadmet;

/**
 *
 * @author Felix
 */
public class Templates {
    /* sablony */
    public static String template_meta = "<meta name=\"%s\" content=\"%s\"%s>";
    public static String template_meta_og = "<meta property=\"%s\" content=\"%s\"%s>";
    public static String template_title = "<title>%s</title>";
    public static String template_favicon = "<link rel=\"shortcut icon\" href=\"%s\" type=\"image/x-icon\"%s>";
    public static String template_charset = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=%s\"%s>";
    public static String template_comments = "<!-- %s -->";
    public static String template_xhtml_tag = " /";
    public static String template_styles = "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\" media=\"%s\"%s>";
    public static String template_scripts = "<script type=\"text/javascript\" src=\"%s\"></script>";
    public static String template_page = "<html>\n<head>\n%s\n</head>";
}
