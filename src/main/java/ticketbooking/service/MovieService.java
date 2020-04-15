package ticketbooking.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ticketbooking.model.Movie;
import ticketbooking.repository.MovieRepository;

@Transactional
@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {

        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public Movie find(Long id) {

        Movie movie = movieRepository.findById(id).get();
        return movie;
    }

    public Movie find(String title) {

        Movie movie = movieRepository.findByTitle(title).get();
        return movie;
    }

    public void save(Movie movie) {

        movieRepository.save(movie);
    }

    public void delete(Long id) {

        movieRepository.deleteById(id);
    }
}
