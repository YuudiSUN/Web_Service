
package test.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.5.7
 * Sat Apr 13 11:32:10 CEST 2024
 * Generated source version: 3.5.7
 */

@XmlRootElement(name = "getCountryCodeByCityNameResponse", namespace = "http://www.com.computer")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCountryCodeByCityNameResponse", namespace = "http://www.com.computer")

public class GetCountryCodeByCityNameResponse {

    @XmlElement(name = "return")
    private java.lang.String _return;

    public java.lang.String getReturn() {
        return this._return;
    }

    public void setReturn(java.lang.String new_return)  {
        this._return = new_return;
    }

}

