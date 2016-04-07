package theunderjackets.com.rottentechmatoes;

import android.content.Context;

/**
 * Interface for a callback once a response is received from FireBase.
 * Created by Andrew Suh on 3/28/2016.
 */
public interface FireBaseCallBack {
    /**
     * Method to be passed into async call from FireBase, post method execute.
     * @param retValue what would be returned if call weren't asynchronous
     * @param exitCode tells, if any control flow happened, what happened in the method
     * @param cont context of the the activity called from
     */
    void onPostExecute(Object retValue, int exitCode, Context cont);
}
