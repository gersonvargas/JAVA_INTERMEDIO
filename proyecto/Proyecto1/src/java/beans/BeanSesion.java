
package beans;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BeanSesion implements Serializable {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String email;
    private String id;
    private String pagina;
    private boolean admin;
    private boolean valido;

    public BeanSesion() {
        
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public String getEmail() {
        return email;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public boolean getValido() {
        return valido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

}
