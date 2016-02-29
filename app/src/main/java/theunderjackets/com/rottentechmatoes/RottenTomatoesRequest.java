package theunderjackets.com.rottentechmatoes;

/**
 * Created by Andrew Suh on 2/18/2016.
 */
public enum RottenTomatoesRequest {

    MOVIE_KEYWORD_SEARCH("http://api.rottentomatoes.com/api/public/v1.0/movies.json"),
    TOP_RENTALS("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/top_rentals.json"),
    NEW_RELEASES_DVD("http://api.rottentomatoes.com/api/public/v1.0/lists/dvds/new_releases.json"),
    NEW_RELEASES_MOVIES("http://api.rottentomatoes.com/api/public/v1.0/lists/movies/opening.json");

    private String requestTypeURL;
    RottenTomatoesRequest(String requestType) {
        this.requestTypeURL = requestType;
    }

    /**
     * Getter method for the url of the request.
     * @return the url of the request
     */
    public String getRequestURL() {
        return requestTypeURL;
    }
}
