package jkvillavo.co.com.paymentapp.util;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;

import java.text.DecimalFormat;

import jkvillavo.co.com.paymentapp.R;

public class UiUtils {

    public static class Formats {
        public static final String DECIMAL = "###,###,###";
    }

    public static class Pages {
        public final static int PAGE_PAYMENTMETHOD = 0;
        public final static int PAGE_ISSUER = 1;
        public final static int PAGE_INSTALLMENTS = 2;
    }

    /**
     * Validate the text in edittext, if text is empty its show error in textInput and send false
     *
     * @param editText
     * @param textInput
     * @param context
     */
    public static int validadEditTextWithTextInput(TextInputEditText editText, TextInputLayout textInput, Context context) {

        if ("".equalsIgnoreCase(editText.getText().toString())) {
            textInput.setError(context.getString(R.string.msg_fieldMandatory));
            textInput.setErrorEnabled(true);
            return 0;
        } else {
            textInput.setError(null);
            textInput.setErrorEnabled(false);
            return 1;
        }

    }

    /**
     * @param value   valur to format
     * @param pattern pattern to aply in Format
     * @return Format string
     */
    public static String formatDecimal(long value, String pattern) {

        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(value);

    }
}
