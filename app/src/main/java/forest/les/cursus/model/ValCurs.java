package forest.les.cursus.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * Created by root on 16.05.17.
 */

@Root(name = "ValCurs")
public class ValCurs {
    @Override
    public String toString() {
        return "ValCurs{" +
                "date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", valutes=" + valutes +
                '}';
    }

    @Attribute(name="Date")
    public String date;

    @Attribute(name="name")
    public String name;

    @ElementList(inline = true)
    public List<Valute> valutes;

    public ValCurs() {}

    public List<Valute> getConfigurations()
    {
        if (valutes == null)
        {
            valutes = new ArrayList<Valute>();
        }
        return this.valutes;
    }

    public void setConfigurations(List<Valute> configuration)
    {
        this.valutes = configuration;
    }
}
