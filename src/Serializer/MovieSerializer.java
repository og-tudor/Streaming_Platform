package Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import movies.Movie;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class MovieSerializer extends StdSerializer<Movie> {
    public MovieSerializer() {
        this(null);
    }

    public MovieSerializer(Class<Movie> t) {
        super(t);
    }

    @Override
    public void serialize(Movie value, JsonGenerator jgen, SerializerProvider provider)
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
        BigDecimal bd = new BigDecimal(value.getRating()).setScale(2, RoundingMode.FLOOR);
        jgen.writeNumberField("rating", bd.doubleValue());
        jgen.writeNumberField("numRatings", value.getNumRatings());
        jgen.writeEndObject();
    }
}
