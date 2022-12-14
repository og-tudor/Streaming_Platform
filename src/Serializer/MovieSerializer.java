package Serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import movies.Movie;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Formatter;

public class MovieSerializer extends StdSerializer<Movie> {
    public MovieSerializer() {
        this(null);
    }

    public MovieSerializer(Class<Movie> t) {
        super(t);
    }

    public static Double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
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
//        Double newValue = value.getRating() + 0.0001;
//        BigDecimal bd = new BigDecimal(newValue).setScale(2, RoundingMode.FLOOR);
        jgen.writeNumberField("rating", value.getRating());
//        DecimalFormat df = new DecimalFormat("###.###");
//        jgen.writeNumberField("rating", Double.parseDouble(df.format(newValue)));
//        jgen.writeNumberField("rating", roundAvoid(1.0045, 2).floatValue());
//        jgen.writePOJO("rating", value.getRating());
//        POJONode pojoNode = new POJONode(value.getRating());
//        jgen.writePOJO(pojoNode);
//        jgen.writeNumberField("rating", rou);
//        Formatter formatter = new Formatter();
//        formatter.format("%.2f", value.getRating());

//        jgen.writeNumberField("rating", value.getRating());
//        jgen.writeNumber
//        writer.WriteRawValue($"{value:0.00}");
//        jgen.writeRawValue($"{rating:0.00}");
        jgen.writeNumberField("numRatings", value.getNumRatings());
        jgen.writeEndObject();
    }
}
