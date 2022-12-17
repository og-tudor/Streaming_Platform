package movies;

import Input.MovieInput;

import java.util.ArrayList;

public class CurrentMovieList extends MovieDataBase {
    private static CurrentMovieList instance = null;

    public static void clearInstance() {
        instance = null;
    }
    public static CurrentMovieList getInstance() {
        return instance;
    }

    public static CurrentMovieList getInstance(ArrayList<MovieInput> movieInputs) {
        if (instance == null) {
            instance = new CurrentMovieList(movieInputs);
        }
        return instance;
    }
    public CurrentMovieList(ArrayList<MovieInput> moviesInput) {
        for (int i = 0; i < moviesInput.size(); i++) {
            MovieInput movieInput = moviesInput.get(i);
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(), movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(), movieInput.getCountriesBanned(), 0, 0.00,0 );
            movies.add(movie);
        }
    }

}
