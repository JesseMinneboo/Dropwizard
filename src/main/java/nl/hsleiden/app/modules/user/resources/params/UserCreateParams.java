package nl.hsleiden.app.modules.user.resources.params;

import com.google.common.base.Strings;
import nl.hsleiden.app.filters.services.ExceptionService;
import nl.hsleiden.app.modules.user.resources.PasswordEncryptService;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class UserCreateParams {
    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String password;



    public String getEmail() {
        return email;
    }

    @FormParam("email")
    public void setEmail(String email) {
        this.email = Strings.nullToEmpty(email).trim();
    }

    public String getName() {
        return name;
    }

    @FormParam("name")
    public void setName(String name) {
        this.name = Strings.nullToEmpty(name).trim();
    }

    public String getSurname() {
        return surname;
    }

    @FormParam("surname")
    public void setSurname(String surname) {
        this.surname = Strings.nullToEmpty(surname).trim();
    }

    public String getPassword() {
        return password;
    }

    @FormParam("password")
    public void setPassword(String password) {
        try {
            this.password = PasswordEncryptService.generateStrongPasswordHash(password);
        } catch (NoSuchAlgorithmException e) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "User Create Params: Password could not be encrypted!",
                    "User Create Params: Password encryption algorithm not found!",
                    Response.Status.BAD_REQUEST
            );
        } catch (InvalidKeySpecException e) {
            ExceptionService.throwIlIllegalArgumentException(
                    this.getClass(),
                    "User Create Params: Password invalid key...",
                    "User Create Params: Password invalid key...",
                    Response.Status.BAD_REQUEST
            );
        }
    }
}
