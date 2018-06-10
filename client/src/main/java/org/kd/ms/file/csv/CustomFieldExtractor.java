package org.kd.ms.file.csv;

import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomFieldExtractor<T> implements FieldExtractor<T>, InitializingBean {

    private String[] names;

    private Map<String, Format> formats;

    @Override
    public Object[] extract(final T item) {
        List<Object> values = new ArrayList<>();
        BeanWrapper bw = new BeanWrapperImpl(item);

        for (String propName : this.names) {
            Object propVal = bw.getPropertyValue(propName);
            if (propVal == null) {
                values.add("");
            } else {
                Format format = this.formats.get(propName);
                if (format == null) {
                    values.add(propVal);
                } else {
                    values.add(format.format(propVal));
                }
            }
        }

        return values.toArray();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(names, "names can not be null");
    }

    public void setNames(String[] names) {
        this.names = names;
    }

    public void setFormats(Map<String, Format> formats) {
        this.formats = formats;
    }
}
