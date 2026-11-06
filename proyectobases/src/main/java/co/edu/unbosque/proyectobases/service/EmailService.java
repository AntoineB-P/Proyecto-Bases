package co.edu.unbosque.proyectobases.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import co.edu.unbosque.proyectobases.exception.ServicioCorreoException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remitente;

    public void enviarCodigoVerificacion(String destino, String usuario, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(remitente);
        mensaje.setTo(destino);
        mensaje.setSubject("Verifica tu cuenta - Liga BetPlay");
        mensaje.setText("Hola " + usuario + ",\n\n"
                + "Tu código de verificación es: " + codigo + "\n\n"
                + "Es válido por 15 minutos. Si no creaste esta cuenta, ignora este correo.");
        try {
            mailSender.send(mensaje);
        } catch (MailException e) {
            throw new ServicioCorreoException(
                    "No se pudo enviar el correo de verificación. Pide un nuevo código en unos minutos");
        }
    }
}