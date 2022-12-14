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
import users.UserDataBase;

import java.util.*;

public class MovieDataBase {
    ArrayList<Movie> movies = new ArrayList<>();
    private static MovieDataBase instance = null;

    public static void clearInstance() {
        instance = null;
    }
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
            Movie movie = new Movie(movieInput.getName(), movieInput.getYear(), movieInput.getDuration(), movieInput.getGenres(), movieInput.getActors(), movieInput.getCountriesBanned(), 0, 0.00,0 );
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

    public void populate(User user) {
        this.movies.clear();
        if (user != null) {
            MovieDataBase movieDataBase = MovieDataBase.getInstance();
            for (int i = 0; i < movieDataBase.getMovies().size(); i++) {
                Movie movie = movieDataBase.getMovies().get(i);
                boolean contains = false;
                for (int j = 0; j < movie.getCountriesBanned().size(); j++) {
                    if (movie.getCountriesBanned().get(j).equals(user.getCredentials().getCountry())) {
                        contains = true;
                    }
                }
                if (!contains) {
                    Movie movieIn = movieDataBase.getMovies().get(i);
                    movies.add(movieIn);
                }

//                if (!movieDataBase.getMovies().get(i).getCountriesBanned().contains(user.getCredentials().getCountry())) {
////                    Movie movie = new Movie(movieDataBase.getMovies().get(i));
//                    Movie movie = movieDataBase.getMovies().get(i);
//                    movies.add(movie);
//                }
            }
        } else {
            this.movies.clear();
        }
    }



    public void search(String startWith) {
        this.movies.removeIf(x -> !(x.getName().startsWith(startWith)));
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
                movies.removeIf(x -> !(x.getActors().containsAll(genreFilter)));
            }
        }

        // Decreasing && Decreasing
        Comparator<Movie> movieComparator1 = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
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
            public int compare(Movie o1, Movie o2) {
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
            public int compare(Movie o1, Movie o2) {
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
            public int compare(Movie o1, Movie o2) {
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

        // Sort
        Sort sort = filter.getSort();
        if (sort == null) {
            return;
        }
        String ratingOrder = sort.getRating();
        String durationOrder = sort.getDuration();
        if (durationOrder == null) {
            if (ratingOrder.equals("increasing")) {
                this.movies.sort((m1, m2) -> Double.compare(m1.getRating(), m2.getRating()));
            } else {
                this.movies.sort((m1, m2) -> Double.compare(m2.getRating(), m1.getRating()));
            }
            return;
        } else if (ratingOrder == null) {
            if (durationOrder.equals("increasing")) {
                this.movies.sort((m1, m2) -> Double.compare(m1.getDuration(), m2.getDuration()));
            } else {
                this.movies.sort((m1, m2) -> Double.compare(m2.getDuration(), m1.getDuration()));
            }
            return;
        }

        if (ratingOrder.equals("decreasing") && durationOrder.equals("decreasing")) {
//            this.movies.sort(Comparator.comparingDouble(Movie::getRating).thenComparingInt(Movie::getDuration).thenComparing(Movie::getName));
            this.movies.sort(movieComparator1);
        } else if (ratingOrder.equals("increasing") && durationOrder.equals("decreasing")) {
//            this.movies.sort(Comparator.comparingDouble(Movie::getRating).thenComparingInt(Movie::getDuration).thenComparing(Movie::getName));
            this.movies.sort(movieComparator2);
        } else if (ratingOrder.equals("decreasing") && durationOrder.equals("increasing")) {
            this.movies.sort(movieComparator3);
        } else if (ratingOrder.equals("increasing") && durationOrder.equals("increasing")) {
            this.movies.sort(movieComparator4);
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
