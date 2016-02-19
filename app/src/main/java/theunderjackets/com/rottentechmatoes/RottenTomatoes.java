package theunderjackets.com.rottentechmatoes;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Andrew Suh on 2/18/2016.
 */
public final class RottenTomatoes {

    private static String apiKey = "yedukp76ffytfuy24zsqk7f5";

    private RottenTomatoes() {
    }

    /**
     * Makes a REST request from RottenTomatoes and returns a JsonObjectRequest
     * @param urlRequest the type of request in url form
     * @param limit the limit on number of movies in the list (maximum)
     * @param query the query of the search if of type keyword search
     * @return the list of movies queried for
     */
    public static JsonObjectRequest getMovies(RottenTomatoesRequest urlRequest, int limit, String query) {
    String url = urlRequest.getRequestURL() + "?";
    if (urlRequest.equals(RottenTomatoesRequest.MOVIE_KEYWORD_SEARCH)) {
        url += "q=" + query + "&";
    }
    url += "limit=" + limit;
    url += "&country=us&apikey=" + apiKey;
    return makeCall(url);
    }

    /**
     * Makes a REST request from RottenTomatoes and gets a list of movies
     * @param urlRequest the type of request in url form
     * @param limit the limit on number of movies in the list (maximum)
     * @return a request for the query made
     */
    public static JsonObjectRequest getMovies(RottenTomatoesRequest urlRequest, int limit) {
        return getMovies(urlRequest, limit, null);
    }
    // Don't worry about this.
    private static JsonObjectRequest makeCall(String url) {
        return new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject resp) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
    }

    /**
     * Makes a REST request from RottenTomatoes and returns a JsonObjectRequest
     * @param urlRequest enum request
     * @param id id of the movie
     * @return a request for the query made
     */
    public static JsonObjectRequest getMovieInfo(RottenTomatoesRequest urlRequest, String id) {
        String url = urlRequest.getRequestURL() + "?";
        url += "id=" + id;
        url += "&country=us&apikey=" + apiKey;
        return makeCall(url);
    }

    private void goToResultsScreen(List<Movie> movies) {

    }

    private void goToMovieScreen(Movie movie) {

    }

}
