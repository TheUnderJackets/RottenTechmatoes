package theunderjackets.com.rottentechmatoes;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by Andrew Suh on 2/18/2016.
 */
public class RequestQueueSingleton {
    private static RequestQueueSingleton queueInstance;
    private RequestQueue requestQueue;
    private List<Movie> resultsMovies;
    private Movie resultsMovie;
    private static Context cont;

    private RequestQueueSingleton(Context context) {
        cont = context;
        requestQueue = getRequestQueue();
    }

    /**
     * Singleton instance accessor
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
     * Getter method for the request queue.
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
     * @param req request to be added
     */
    public <T> void add(Request<T> req) {
        getRequestQueue().add(req);
    }

    public List<Movie> getMovies() {
        return resultsMovies;
    }

    public Movie getMovie() {
        return resultsMovie;
    }
}
