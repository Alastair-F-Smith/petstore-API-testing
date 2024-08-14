package utils.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.function.Function;
import java.util.function.Predicate;

public class JacksonFilterFactory {

    /**
     * Define a new Jackson property filter that conditionally omits a property from serialization
     * based on a specified condition.
     * @param name The name of the property.
     * @param accessor A function that returns a value that is used to determine whether the property should be
     *                 serialized. This could be the value of the field, for example. It receives the POJO as an Object.
     * @param predicate Receives the output of the accessor function. If this evaluates to true, the property is
     *                  serialized.
     * @return A Jackson PropertyFilter, which can be added to an ObjectMapper instance.
     */
    public static <T> PropertyFilter getFilter(String name, Function<Object, T> accessor, Predicate<T> predicate) {
        return new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField
                    (Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer)
                    throws Exception {
                if (include(writer)) {
                    if (!writer.getName().equals(name)) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }
                    T property = accessor.apply(pojo);
                    if (predicate.test(property)) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                } else if (!jgen.canOmitFields()) { // since 2.3
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }
        };
    }
}
