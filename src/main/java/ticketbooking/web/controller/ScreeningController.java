package ticketbooking.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ticketbooking.model.Screening;
import ticketbooking.service.MovieService;
import ticketbooking.service.ScreeningService;
import ticketbooking.service.RoomService;
import ticketbooking.web.CreateScreeningRequest;
import ticketbooking.web.ScreeningRequest;
import ticketbooking.web.ScreeningResponse;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScreeningController.class);

    private ScreeningService screeningService;

    private MovieService movieService;

    private RoomService roomService;

    public ScreeningController(ScreeningService screeningService, MovieService movieService, RoomService roomService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
        this.roomService = roomService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateScreeningRequest csr) {

        Screening screening = new Screening(csr.getDate(), movieService.find(csr.getMovieId()), roomService.find(csr.getRoomId()));
        screeningService.save(screening);
        LOGGER.info("Screening created.");
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ScreeningResponse read(@PathVariable Long id) {

        Screening screening = screeningService.find(id);
        return new ScreeningResponse(screening.getId(), screening.getDate(), screening.getMovie().getId(), screening.getRoom().getId());
    }

    //Rturns all screenings for a given date sorted by movie title and then by date.
    @GetMapping(params = "date", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ScreeningResponse> readAllByDate(@RequestParam LocalDate date) {

        List<Screening> screenings = screeningService.findAllByDateSortedByMovieTitleAndDate(date);
        List<ScreeningResponse> screeningsResponses = new ArrayList<>();
        for (int i = 0; i < screenings.size(); i++) {
            screeningsResponses.add(new ScreeningResponse(screenings.get(i).getId(), screenings.get(i).getDate(), screenings.get(i).getMovie().getId(), screenings.get(i).getRoom().getId()));

        }
        return screeningsResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody ScreeningRequest sr) {

        Screening screening = new Screening(sr.getDate(), movieService.find(sr.getMovieId()), roomService.find(sr.getRoomId()));
        screening.setId(sr.getId());
        screeningService.save(screening);
        LOGGER.info("Screening" + sr.getId() + "updated.");
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        screeningService.delete(id);
        LOGGER.info("Screening" + id + "deleted.");
    }
}
