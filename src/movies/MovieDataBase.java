package movies;

import input.ActionInput;
import input.Filter;
import input.MovieInput;
import input.Sort;
import users.User;
import users.UserDataBase;

import java.awt.desktop.UserSessionListener;
import java.util.ArrayList;
import java.util.Comparator;

//import java.util.*;

public class MovieDataBase {
    protected ArrayList<Movie> movies = new ArrayList<>();
    private static MovieDataBase instance = null;
    /** Makes the instance of MovieDataBase null */
    public static void clearInstance() {
        instance = null;
    }
    public static MovieDataBase getInstance() {
        return instance;
    }
    /** Returns the database instance */
    public static MovieDataBase getInstance(final ArrayList<MovieInput> movieInputs) {
        if (instance == null) {
            instance = new MovieDataBase(movieInputs);
        }
        return instance;
    }

    public MovieDataBase(final ArrayList<MovieInput> moviesInput) {
        for (int i = 0; i < moviesInput.size(); i++) {
            MovieInput movieInput = moviesInput.get(i);
            Movie movie = new Movie(movieInput.getName(), Integer.toString(movieInput.getYear()),
                                    movieInput.getDuration(), movieInput.getGenres(),
                                    movieInput.getActors(), movieInput.getCountriesBanned(),
                                    0, 0.00, 0);
            movies.add(movie);
        }
    }

    public MovieDataBase(final MovieDataBase movieDataBase, final User user) {
        if (user == null) {
            return;
        }
        // Copy constructor
        for (int i = 0; i < movieDataBase.getMovies().size(); i++) {
            Movie movie = movieDataBase.getMovies().get(i);
            if (!movie.getCountriesBanned().contains(user.getCredentials().getCountry())) {
                Movie movieCopy = new Movie(movieDataBase.getMovies().get(i));
                movies.add(movieCopy);
            }
        }
    }
    /** Populates a movieDatabase such as currentMovies,
     *  only with movies not banned in the user's country */
    public void populate(final User user) {
        this.movies.clear();
        if (user == null) {
            return;
        }
        // Going through each movie
        for (int i = 0; i < instance.getMovies().size(); i++) {
            Movie movie = instance.getMovies().get(i);
            boolean contains = false;
            // Checking evey country banned from seeing movie
            for (int j = 0; j < movie.getCountriesBanned().size(); j++) {
                if (movie.getCountriesBanned().get(j).equals(user.getCredentials().getCountry())) {
                    contains = true;
                }
            }
            // If the user's country isn't banned for the movie add it to the list
            if (!contains) {
                Movie movieIn = instance.getMovies().get(i);
                movies.add(movieIn);
            }
        }
    }

    /** Searches for a movie in a database, and eliminates all other movies from it */
    public void search(final String startWith) {
        this.movies.removeIf(x -> !(x.getName().startsWith(startWith)));
    }
    /** Searches for a movie in a database and returns the movie object */
    public Movie find(final String name) {
        for (int i = 0; i < this.movies.size(); i++) {
            Movie movie = this.movies.get(i);
            if (movie.getName().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    public boolean insertMovie(final MovieInput movieInput) {
        if (find(movieInput.getName()) != null) {
            return true;
        }
        Movie movie = new Movie(movieInput.getName(), Integer.toString(movieInput.getYear()),
                movieInput.getDuration(), movieInput.getGenres(),
                movieInput.getActors(), movieInput.getCountriesBanned(),
                0, 0.00, 0);
        movies.add(movie);
        UserDataBase userDataBase = UserDataBase.getInstance();
//        userDataBase.
        // TODO notify users
        return false;
    }

    /** Filters a movieDatabase based on actors and genres
     * and sorts it either by duration or rating or both */
    public  void filter(final ActionInput actionInput) {
        Filter filter = actionInput.getFilters();

        // Filter movies by actors
        if (filter.getContains() != null) {
            ArrayList<String> actorFilter = filter.getContains().getActors();
            for (int i = 0; i < actorFilter.size(); i++) {
                movies.removeIf(x -> !(x.getActors().containsAll(actorFilter)));
            }
        }
        // Filter genre
        if (filter.getContains() != null) {
            ArrayList<String> genreFilter = filter.getContains().getGenre();
            for (int i = 0; i < genreFilter.size(); i++) {
                movies.removeIf(x -> !(x.getGenres().containsAll(genreFilter)));
            }
        }
        /** The following is a list of comparators used to sort movies by duration then rating */

        // Decreasing && Decreasing
        Comparator<Movie> movieComparator1 = new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                // comparing duration (desc)
                if (o1.getDuration() == o2.getDuration()) {
                    // comparing rating (desc)
                    return Double.compare(o1.getRating(), o2.getRating());
                } else if (o1.getDuration() < o2.getDuration()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        // Increasing && Decreasing
        Comparator<Movie> movieComparator2 = new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                // comparing duration (inc)
                if (o1.getDuration() == o2.getDuration()) {
                    // comparing rating (desc)
                    return Double.compare(o1.getRating(), o2.getRating());
                } else if (o1.getDuration() < o2.getDuration()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        // Decreasing && Increasing
        Comparator<Movie> movieComparator3 = new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                // comparing duration (desc)
                if (o1.getDuration() == o2.getDuration()) {
                    // comparing rating (inc)
                    return Double.compare(o2.getRating(), o1.getRating());
                } else if (o1.getDuration() < o2.getDuration()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        // Increasing && Increasing
        Comparator<Movie> movieComparator4 = new Comparator<Movie>() {
            @Override
            public int compare(final Movie o1, final Movie o2) {
                // comparing duration (inc)
                if (o1.getDuration() == o2.getDuration()) {
                    // comparing rating (inc)
                    return Double.compare(o2.getRating(), o1.getRating());
                } else if (o1.getDuration() < o2.getDuration()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };

        // Sorting the movies after the genre and actor filters
        Sort sort = filter.getSort();
        if (sort == null) {
            return;
        }
        String ratingOrder = sort.getRating();
        String durationOrder = sort.getDuration();
        // Sorting just by rating
        if (durationOrder == null) {
            if (ratingOrder.equals("increasing")) {
                this.movies.sort((m1, m2) -> Double.compare(m1.getRating(), m2.getRating()));
            } else {
                this.movies.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
            }
            return;
        // Sorting just by duration
        } else if (ratingOrder == null) {
            if (durationOrder.equals("increasing")) {
                this.movies.sort((m1, m2) -> Double.compare(m1.getDuration(), m2.getDuration()));
            } else {
                this.movies.sort((m1, m2) -> Double.compare(m2.getDuration(), m1.getDuration()));
            }
            return;
        }
        // Sorting by both duration and rating
        if (ratingOrder.equals("decreasing") && durationOrder.equals("decreasing")) {
            this.movies.sort(movieComparator1);
        } else if (ratingOrder.equals("increasing") && durationOrder.equals("decreasing")) {
            this.movies.sort(movieComparator2);
        } else if (ratingOrder.equals("decreasing") && durationOrder.equals("increasing")) {
            this.movies.sort(movieComparator3);
        } else if (ratingOrder.equals("increasing") && durationOrder.equals("increasing")) {
            this.movies.sort(movieComparator4);
        }
    }
    public MovieDataBase() {
    }
    /** Getter */
    public ArrayList<Movie> getMovies() {
        return movies;
    }
    /** Setter */
    public void setMovies(final ArrayList<Movie> movies) {
        this.movies = movies;
    }
    /** Print the movieDatabase */
    @Override
    public String toString() {
        return "MovieDataBase{"
                +
                "movies=" + movies
                +
                '}';
    }

}
