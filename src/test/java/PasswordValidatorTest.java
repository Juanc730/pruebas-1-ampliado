import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.example.PasswordValidator;

class PasswordValidatorTest {
    
    // Caso 1: Contraseña nula o vacía
    @Test
    void ContraseñaNulaOVacía() {
        assertAll(
            () -> {
                IllegalArgumentException exception = assertThrows(
                        IllegalArgumentException.class,
                        () -> PasswordValidator.isValid(null)
                );
                assertEquals("La contraseña no puede ser nula o vacía", exception.getMessage());
            },
            () -> {
                IllegalArgumentException exception = assertThrows(
                        IllegalArgumentException.class,
                        () -> PasswordValidator.isValid("")
                );
                assertEquals("La contraseña no puede ser nula o vacía", exception.getMessage());
            }
        );
    }

    // Caso 2: Contraseña válida estándar
    @Test
    void ContraseñaValida() {
        assertTrue(PasswordValidator.isValid("Secure123!"));
    }

    // Caso 3: Contraseña demasiado corta
    @Test
    void ContraseñaDemasiadoCorta() {
        assertFalse(PasswordValidator.isValid("Ab1!"));
    }

    // Caso 4: Contraseña demasiado larga
    @Test
    void ContraseñaDemasiadoLarga() {
        String longPassword = "A".repeat(65) + "1!a";
        assertFalse(PasswordValidator.isValid(longPassword));
    }

    // Caso 5: Contraseña sin mayúsculas
    @Test
    void ContraseñaSinMayusculas() {
        assertFalse(PasswordValidator.isValid("secure123!"));
    }

    // Caso 6: Contraseña sin minúsculas
    @Test
    void ContraseñaSinMinusculas() {
        assertFalse(PasswordValidator.isValid("SECURE123!"));
    }

    // Caso 7: Contraseña sin números
    @Test
    void ContraseñaSinNumeros() {
        assertFalse(PasswordValidator.isValid("Secure!@#"));
    }

    // Caso 8: Contraseña sin caracteres especiales
    @Test
    void ContraseñaSinCaracteresEspeciales() {
        assertFalse(PasswordValidator.isValid("Secure1234"));
    }

    // Caso 9: Contraseña con espacio inicial o final
    @Test
    void ContraseñaConEspacioInicioOFinal() {
        assertAll(
            () -> assertFalse(PasswordValidator.isValid(" Secure123!")),
            () -> assertFalse(PasswordValidator.isValid("Secure123! "))
        );
    }

    // Caso 10: Contraseña con espacios consecutivos
    @Test
    void ContraseñaConEspaciosConsecutivos() {
        assertFalse(PasswordValidator.isValid("Secure  123!"));
    }

    // Caso 11: Contraseña válida con espacio único entre caracteres
    @Test
    void ContraseñaConEspacioEntreCaracteres() {
        assertTrue(PasswordValidator.isValid("Secure 123!"));
    }
}
