package org.jss.tally.converter;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.jss.tally.domain.TallyResponse;

public class TallyResponseConverter implements Converter {
    @Override
    public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
        TallyResponse tallyResponse = (TallyResponse) context.currentObject();
        if (nodeNameIs("RESPONSE", reader)) {
            readTallyResponse(reader, tallyResponse);
        }
        return tallyResponse;
    }

    private boolean nodeNameIs(String nodeName, HierarchicalStreamReader reader) {
        return reader.getNodeName().equals(nodeName);
    }

    private void readTallyResponse(HierarchicalStreamReader reader, TallyResponse tallyResponse) {
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if (nodeNameIs("CREATED", reader)) {
                tallyResponse.setCreated(toBoolean(reader.getValue()));
            }
            if (nodeNameIs("ALTERED", reader)) {
                tallyResponse.setAltered(toBoolean(reader.getValue()));
            }
            if (nodeNameIs("LINEERROR", reader)) {
                tallyResponse.setError(reader.getValue());
            }
            reader.moveUp();
        }
    }

    private boolean toBoolean(String str) {
        return str != null && str.equals("0") ? false : true;
    }

    @Override
    public boolean canConvert(Class type) {
        return type.equals(TallyResponse.class);
    }
}
