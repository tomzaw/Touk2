package ticketbooking.converter;

import java.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

    public LocalDateConverter() {
    }

    @Override
    public LocalDate convert(String s) {

        return LocalDate.parse(s);
    }
}
