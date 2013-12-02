package com.periplanisi.library.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import com.periplanisi.library.ui.view.util.FontUtil;

/**
 * Custom TextView to facilitate usage of custom fonts
 * 
 * @author Ioannis
 * 
 */
public class FontTextView extends TextView {
	private String font;

	public FontTextView(final Context context) {
		this(context, null);
	}

	public FontTextView(final Context context, final AttributeSet attrs) {
		this(context, attrs, R.attr.fontTextViewStyle);
	}

	public FontTextView(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);

		final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FontTextView, defStyle, 0);
		font = a.getString(R.styleable.FontTextView_font);
		a.recycle();

		setFont(font);
	}

	/**
	 * Set the font, by providing the string resource id indicating the font filename (located in /assets)
	 * 
	 * @param fontResId
	 */
	public void setFont(final int fontResId) {
		setFont(getResources().getString(fontResId));
	}

	/**
	 * Set custom font, by providing the font filename (located in /assets)
	 * 
	 * @param font
	 */
	public void setFont(final String font) {
		this.font = font;

		// http://stackoverflow.com/questions/10952894/custom-font-in-android-renders-differently-in-different-apis
		// using subpixel rendering to fix rendering issues in ICS
		setPaintFlags(getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);

		setTypeface(FontUtil.getTypeface(getContext(), font));
	}

	/**
	 * Get the current font filename, or null if not defined
	 * 
	 * @return
	 */
	public String getFont() {
		return font;
	}
}
