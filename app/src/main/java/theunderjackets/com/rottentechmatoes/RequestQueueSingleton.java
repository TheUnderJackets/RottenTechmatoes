package theunderjackets.com.rottentechmatoes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Implements the Singleton pattern in order to ensure a single queue of RottenTomatoes requests.
 * Created by Andrew Suh on 2/18/2016.
 */
public final class RequestQueueSingleton {
    private static RequestQueueSingleton queueInstance;
    private RequestQueue requestQueue;
    private static Context cont;

    /**
     * Private constructor to make sure only one instance ever exists.
     * @param context the context of the activity
     */
    private RequestQueueSingleton(Context context) {
        cont = context;
        requestQueue = getRequestQueue();
    }

    /**
     * Singleton instance accessor
     *
     * @param context current context in application
     * @return single instance of the class
     */
    public static RequestQueueSingleton getInstance(Context context) {
        if (queueInstance == null) {
            queueInstance = new RequestQueueSingleton(context);
        }
        return queueInstance;
    }

    /**
     * Getter method for the request queue. This should be public. The goal is to provide
     * global state for RT requests.
     *
     * @return request queue
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(cont.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * Add method for the request queue. For the purpose of this app, get a JsonObjectRequest
     * from the RottenTomatoes class and add it to this queue.
     *
     * @param <T> the generic type of the request
     * @param req request to be added
     */
    public <T> void add(Request<T> req) {
        getRequestQueue().add(req);
    }
}
