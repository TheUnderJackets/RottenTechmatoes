package theunderjackets.com.rottentechmatoes;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Acts as an interface between the app and the RottenTomatoes API. A utility class.
 * Created by Andrew Suh on 2/18/2016.
 */
public final class RottenTomatoes {

    private static final String apiKey = "yedukp76ffytfuy24zsqk7f5";

    /**
     * Private default constructor to make sure this class is not instantiated.
     */
    private RottenTomatoes() {
    }

    /**
     * Makes a REST request to RottenTomatoes and fills a MovieList with the results, which is passed along through an Intent.
     *
     * @param urlRequest      the type of request in url form
     * @param limit           the limit on number of movies in the list (maximum)
     * @param activityContext the activity context
     * @param goalClass       the class to fire the intent to
     * @param callback        the code to execute once the results are back
     * @param query           the query of the search if of type keyword search
     */
    public static void getMovies(RottenTomatoesRequest urlRequest, int limit, Context activityContext, Class goalClass, RTCallBack callback, String query) {
        String url = urlRequest.getRequestURL() + "?";
        if (urlRequest.equals(RottenTomatoesRequest.MOVIE_KEYWORD_SEARCH) && query != null && !activityContext.getResources().getString(R.string.text_blank).equals(query)) {
            url += "q=" + query + "&";
            url += "page_limit=" + limit;
            url += "&apikey=" + apiKey;
        } else {
            url += "limit=" + limit;
            url += "&country=us&apikey=" + apiKey;
        }
        RequestQueueSingleton.getInstance(activityContext.getApplicationContext()).add(makeCall(url, callback, activityContext, goalClass, limit));
    }

    /**
     * Makes a REST request to RottenTomatoes and fills a MovieList with the results, which is passed along through an Intent.
     *
     * @param urlRequest the type of request in url form
     * @param limit      the limit on number of movies in the list (maximum)
     * @param context    the activity context
     * @param goalClass  the goal class
     * @param callback   the code that executes once results are returned
     */
    public static void getMovies(RottenTomatoesRequest urlRequest, int limit, Context context, Class goalClass, RTCallBack callback) {
        if (urlRequest.equals(RottenTomatoesRequest.MOVIE_KEYWORD_SEARCH)) {
            throw new IllegalArgumentException("You cannot have an empty query parameter for keyword search.");
        }
        getMovies(urlRequest, limit, context, goalClass, callback, null);
    }

    /**
     * Makes a REST request to RottenTomatoes and parses the information using the url given. It also
     * executes the code passed in as a callback method.
     *
     * @param url             url for REST request
     * @param callback        code that executes after the results are returned
     * @param activityContext context of the current activity
     * @param goalClass       class of the class to fire the intent to
     * @param limit           the number of queries to limit to
     * @return JsonObjectRequest to be added to the queue
     */
    private static JsonObjectRequest makeCall(String url, final RTCallBack callback, final Context activityContext, final Class goalClass, final int limit) {
        return new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            /**
             * This does need to be this long since this is one request to RottenTomatoes, async.
             */
            public void onResponse(JSONObject resp) {
                JSONArray arrMain = null;
                final MovieList list = new MovieList();
                try {
                    arrMain = resp.getJSONArray("movies");
                } catch (JSONException e) {
                    Log.e(activityContext.getResources().getString(R.string.text_blank), e.toString());
                }
                assert arrMain != null;
                int bound = limit;
                if (limit > arrMain.length()) {
                    bound = arrMain.length();
                }
                for (int i = 0; i < bound; i++) {
                    String id = activityContext.getResources().getString(R.string.text_default_nothing), title = activityContext.getResources().getString(R.string.text_default_nothing), theaterReleaseDate = activityContext.getResources().getString(R.string.text_default_nothing), synopsis = activityContext.getResources().getString(R.string.text_default_nothing), thumbnailURL = activityContext.getResources().getString(R.string.text_default_nothing);
                    String apiRating = "0";
                    int year = 0;
                    int runtime = 0;
                    JSONObject obj = null;
                    try {
                        obj = arrMain.getJSONObject(i);
                        id = obj.getString("id");
                        title = obj.getString("title");
                    } catch (JSONException e) {
                        Log.e(activityContext.getResources().getString(R.string.text_blank), e.toString());
                    }
                    if (obj == null) {
                        throw new IllegalStateException("RottenTomatoes didn't find anything.");
                    }
                    try {
                        if (!obj.getString("synopsis").equals(activityContext.getResources().getString(R.string.text_blank))) {
                            synopsis = obj.getString("synopsis");
                        }
                        year = Integer.parseInt(obj.getString("year"));
                        runtime = Integer.parseInt(obj.getString("runtime"));
                    } catch (JSONException e) {
                        Log.e(activityContext.getResources().getString(R.string.text_blank), e.toString());
                    }
                    try {
                        apiRating = obj.getJSONObject("ratings").getString("audience_score");
                        if ("-1".equals(apiRating) || apiRating.equals(activityContext.getResources().getString(R.string.text_blank))) {
                            apiRating = obj.getJSONObject("ratings").getString("critics_score");
                        }
                        if ("-1".equals(apiRating) || apiRating.equals(activityContext.getResources().getString(R.string.text_blank))) {
                            apiRating = "Not Rated Yet";
                        }
                        thumbnailURL = obj.getJSONObject("posters").getString("thumbnail");
                        theaterReleaseDate = obj.getJSONObject("release_dates").getString("theater");
                    } catch (JSONException e) {
                        Log.e(activityContext.getResources().getString(R.string.text_blank), e.toString());
                    }
                    final Movie movie = new Movie(id, title, year, runtime, theaterReleaseDate, apiRating, synopsis, thumbnailURL);
                    list.addMovie(movie);
                }
                callback.fireIntent(list, activityContext, goalClass);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                throw new IllegalStateException("Could not connect to the server.");
            }

        });
    }

    /**
     * Makes a REST request from RottenTomatoes and returns a JsonObjectRequest
     * @param urlRequest enum request
     * @param id id of the movie
     * @return a request for the query made
     */
    /*
    public static JsonObjectRequest getMovieInfo(RottenTomatoesRequest urlRequest, String id) {
        String url = urlRequest.getRequestURL() + "?";
        url += "id=" + id;
        url += "&country=us&apikey=" + apiKey;
        return makeCall(url);
    }
    */
}

