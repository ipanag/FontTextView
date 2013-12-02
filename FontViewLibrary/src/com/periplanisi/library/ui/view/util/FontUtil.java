package com.periplanisi.library.ui.view.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Utility to create Typeface from custom fonts, using cache
 * 
 * @author Ioannis
 * 
 */
public final class FontUtil {

	private static final Map<String, Typeface> TYPEFACES = new HashMap<String, Typeface>();

	private FontUtil() {
	}

	/**
	 * Get custom font from assets. Uses cache to avoid recreating object.<br>
	 * Not thread-safe. Call this from the one thread only
	 * 
	 * @param ctx
	 * @param font
	 * @param style
	 * @return the typeface or null if not found
	 */
	public static Typeface getTypeface(final Context context, final String font) {
		Typeface typeface;

		// check if resource string empty
		if (font == null || font.length() == 0) {
			typeface = null;

		} else {

			// check if already created and stored (avoid creating continuously the same typeface object)
			if(TYPEFACES.containsKey(font)) {
				typeface = TYPEFACES.get(font);

				// check if cache contains valid object
				if (typeface == null) {
					TYPEFACES.remove(font);

					typeface = createTypeface(context, font);
				}
			} else {
				typeface = createTypeface(context, font);
			}

		}

		return typeface;
	}

	/**
	 * Creates new typeface and adds it in cache
	 * @param context
	 * @param font
	 * @return
	 */
	private static Typeface createTypeface(final Context context, final String font) {
		final Typeface typeface = Typeface.createFromAsset(context.getAssets(), font);

		// put in cache
		if (typeface != null) {
			TYPEFACES.put(font, typeface);
		}

		return typeface;
	}

}
