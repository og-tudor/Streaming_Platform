package movies;

import Input.ActionInput;
import Input.Filter;
import Input.MovieInput;
import Input.Sort;
import pageStructure.Details;
import pageStructure.HomeAuth;
import pageStructure.HomeUnauth;
import pageStructure.Movies;
import users.User;

import java.util.*;

public class MovieDataBase {
    ArrayList<Movie> movies = new ArrayList<>();
    private static MovieDataBase instance = null;

    public static MovieDataBase getInstance() {
        return instance;
    }

    public static MovieDataBase getInstance(ArrayList<MovieInput> movieInputs) {
        if (instance == null) {
            instance = new MovieDataBase(movieInputs);
        }
        return instance;
    }

    public MovieDataBase(ArrayList<MovieInput> moviesInput) {
        for (int i = 0; i < moviesInput.size(); i++) {
            MovieInput movieInput = moviesInput.get(i);
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(), movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(), movieInput.getCountriesBanned(), 0, 0 ,0 );
            movies.add(movie);
        }
    }

    public MovieDataBase(MovieDataBase movieDataBase, User user) {
        if (user != null) {
            for (int i = 0; i < movieDataBase.getMovies().size(); i++) {
                if (!movieDataBase.getMovies().get(i).getCountriesBanned().contains(user.getCredentials().getCountry())) {
                    Movie movie = new Movie(movieDataBase.getMovies().get(i));
                    movies.add(movie);
                }
            }
        } else {
            this.movies.clear();
        }
    }



    public void search(String startWith) {
        this.movies.removeIf(x -> !(x.getName().contains(startWith)));
//        for (int i = 0; i < this.movies.size(); i++) {
////            String movieName = this.movies.get(i).getName();
////            if (!movieName.contains(startWith)) {
////
////            }
////        }
    }

    public Movie find(String name) {
        for (int i = 0; i < this.movies.size(); i++) {
            Movie movie = this.movies.get(i);
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    public  void filter(ActionInput actionInput) {
        Filter filter = actionInput.getFilters();

        // Filter movies by actor
//        if (filter.getContains() != null) {
//            ArrayList<String> actorFilter = filter.getContains().getActors();
//            for (int i = 0; i < actorFilter.size(); i++) {
//                movies.removeIf(x -> !(x.getActors().containsAll(actorFilter)));
//            }
//        }

        // Sort
        Sort sort = filter.getSort();
        if (sort.getRating().equals("decreasing") && sort.getDuration().equals("decreasing")) {
            this.movies.sort(Comparator.comparingDouble(Movie::getRating).thenComparingInt(Movie::getDuration));
            Collections.reverse(movies);
        }
    }
    public void insertMovie(Movie movie) {
        movies.add(movie);
    }
    public MovieDataBase() {
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieDataBase{" +
                "movies=" + movies +
                '}';
    }

}
