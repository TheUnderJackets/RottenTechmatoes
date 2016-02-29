package theunderjackets.com.rottentechmatoes;

import android.content.Context;

/**
 * Created by Andrew Suh on 2/23/2016.
 */
public interface RTCallBack {
    void fireIntent(MovieList list, Context thisActivityContext, Class goalClass);
}
