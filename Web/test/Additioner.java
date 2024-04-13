package test;
import javax.jws.WebService; import javax.jws.WebMethod; import javax.jws.WebParam;
@WebService(targetNamespace="http://www.com.computer")
public class Additioner { @WebMethod
public int add(@WebParam(name="a") int a, @WebParam(name="b") int b) {
return a+b; }
}