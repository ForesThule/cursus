package forest.les.cursus.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.Currency;
import java.util.Locale;

/**
 * Created by root on 16.05.17.
 */

@Root(name = "Valute")
public class Valute {
    @Attribute(name="ID")
    public String id;

    @Element(name = "NumCode")
    public Integer numcode;

    @Element(name = "CharCode")
    public String charcode;

    @Element(name = "Nominal")
   public Integer nominal;

    @Element(name = "Name")
    public String name;

    @Element(name = "Value")
    public String value;

    public Valute() {}

    @Override
    public String toString() {
        return "Valute{" +
                "numcode=" + numcode +
                ", charcode='" + charcode + '\'' +
                ", nominal=" + nominal +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
