package theunderjackets.com.rottentechmatoes;

import android.content.Context;

/**
 * Interface to provide a callback method once async call to RT is completed.
 * Created by Andrew Suh on 2/23/2016.
 */
public interface RTCallBack {
    /**
     * Fires the intent to go to the Search results screen once call is completed.
     * @param list list to be passed by intent
     * @param thisActivityContext current activity context
     * @param goalClass the class to go to
     */
    void fireIntent(MovieList list, Context thisActivityContext, Class goalClass);
}
