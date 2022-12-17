package serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import movies.Movie;

import java.io.IOException;


public class MovieSerializer extends StdSerializer<Movie> {
    public MovieSerializer() {
        this(null);
    }

    public MovieSerializer(final Class<Movie> t) {
        super(t);
    }
    /** Round a double value to a certain number of decimal places */
    public static Double roundAvoid(final double value, final int places) {
        final int powerTen = 10;
        double scale = Math.pow(powerTen, places);
        return Math.round(value * scale) / scale;
    }
    /** overides Json serialization for Movies */
    @Override
    public void serialize(final Movie value, final JsonGenerator jgen,
                          final SerializerProvider provider)
                          throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("name", value.getName());
        jgen.writeNumberField("year", value.getYear());
        jgen.writeNumberField("duration", value.getDuration());
        jgen.writeArrayFieldStart("genres");
        for (int i = 0; i < value.getGenres().size(); i++) {
            jgen.writeString(value.getGenres().get(i));
        }
        jgen.writeEndArray();

        jgen.writeArrayFieldStart("actors");
        for (int i = 0; i < value.getActors().size(); i++) {
            jgen.writeString(value.getActors().get(i));
        }
        jgen.writeEndArray();

        jgen.writeArrayFieldStart("countriesBanned");
        for (int i = 0; i < value.getCountriesBanned().size(); i++) {
            jgen.writeString(value.getCountriesBanned().get(i));
        }
        jgen.writeEndArray();
        jgen.writeNumberField("numLikes", value.getNumLikes());
        jgen.writeNumberField("rating", value.getRating());
        jgen.writeNumberField("numRatings", value.getNumRatings());
        jgen.writeEndObject();
    }
}
