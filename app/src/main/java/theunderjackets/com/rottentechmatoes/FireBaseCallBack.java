package theunderjackets.com.rottentechmatoes;

import android.content.Context;

/**
 * Created by Andrew Suh on 3/28/2016.
 */
public interface FireBaseCallBack {
    void onPostExecute(Object retValue, int exitCode, Context cont);
}
