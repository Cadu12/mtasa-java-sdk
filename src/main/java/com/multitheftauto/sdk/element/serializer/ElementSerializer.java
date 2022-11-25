package com.multitheftauto.sdk.element.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.multitheftauto.sdk.element.Element;

import java.io.IOException;

public class ElementSerializer extends StdSerializer<Element> {
    public ElementSerializer() {
        this(null);
    }

    public ElementSerializer(Class<Element> t) {
        super(t);
    }

    @Override
    public void serialize(
            Element value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException {
        gen.writeString(Element.SERVER_PREFIX + value.getId());
    }
}
