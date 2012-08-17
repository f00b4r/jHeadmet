package jheadmet;

/**
 *
 * @author Felix
 */
public class Templates {
    /* sablony */
    public static String meta = "<meta name=\"%s\" content=\"%s\"%s>";
    public static String meta_og = "<meta property=\"%s\" content=\"%s\"%s>";
    public static String title = "<title>%s</title>";
    public static String favicon = "<link rel=\"shortcut icon\" href=\"%s\" type=\"image/x-icon\"%s>";
    public static String rss = "<link rel=\"alternate\" type=\"application/rss+xml\" title=\"%s\" href=\"%s\"%s>";
    public static String charset = "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=%s\"%s>";
    public static String comments = "<!-- %s -->";
    public static String xhtml_tag = " /";
    public static String styles = "<link rel=\"stylesheet\" href=\"%s\" type=\"text/css\" media=\"%s\"%s>";
    public static String scripts = "<script type=\"text/javascript\" src=\"%s\"></script>";
    public static String skeleton_begin = "<html>\n<head>";
    public static String skeleton_end = "</head>\n<body>\n</body>\n</html>";
}
