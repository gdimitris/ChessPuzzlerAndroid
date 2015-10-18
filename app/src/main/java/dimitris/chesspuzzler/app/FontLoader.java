package dimitris.chesspuzzler.app;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

public class FontLoader {
    public static final String FONT_DIRECTORY = "Fonts/";
    public static final String DEFAULT_FONT = "ChessCases.ttf";

    public static Typeface loadDefaultFont(Context context) {
        return load(context, DEFAULT_FONT);
    }

    public static Typeface loadFont(Context context, String filename) {
        return load(context, filename);
    }

    private static Typeface load(Context context, String filename) {
        Typeface fontToLoad = Typeface.createFromAsset(context.getAssets(), FONT_DIRECTORY + filename);
        if (fontToLoad == null)
            Log.e("Font Loader", "Font" + filename + " not found");
        return fontToLoad;
    }
}
