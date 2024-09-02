package org.example;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {

    private Evento evento;

    @BeforeEach
    public void setup() {
        evento = new Evento(10, 20);
    }

    @Test
    public void testAgregarPersona() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        assertEquals("Juan", evento.matriz[0][0]);
        assertEquals("25", evento.matriz[0][1]);
        assertEquals("VIP", evento.matriz[0][2]);
        assertEquals("2", evento.matriz[0][3]);
        assertEquals("False", evento.matriz[0][4]);
    }

    @Test
    public void testVerificarEdadMayorDeEdad() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        assertTrue(evento.verificarEdad(0));
    }

    @Test
    public void testVerificarEdadMenorDeEdad() {
        evento.agregarPersona(0, "Juan", 17, "VIP", 2);
        assertFalse(evento.verificarEdad(0));
    }

    @Test
    public void testVerificarBoletoVIP() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        assertEquals("VIP", evento.verificarBoleto(0));
    }

    @Test
    public void testVerificarBoletoGeneral() {
        evento.agregarPersona(0, "Juan", 25, "General", 2);
        assertEquals("General", evento.verificarBoleto(0));
    }

    @Test
    public void testValidarInvitadosVIP() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        assertTrue(evento.validarInvitados(0));
    }

    @Test
    public void testValidarInvitadosGeneralConInvitados() {
        evento.agregarPersona(0, "Juan", 25, "General", 2);
        assertFalse(evento.validarInvitados(0));
    }

    @Test
    public void testValidarInvitadosGeneralSinInvitados() {
        evento.agregarPersona(0, "Juan", 25, "General", 0);
        assertTrue(evento.validarInvitados(0));
    }

    @Test
    public void testAforoDisponibleVIP() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        evento.ingresarPersona(0);
        assertEquals(7, evento.aforoDisponible("VIP"));
    }

    @Test
    public void testAforoDisponibleGeneral() {
        evento.agregarPersona(0, "Juan", 25, "General", 2);
        evento.ingresarPersona(0);
        assertEquals(19, evento.aforoDisponible("General"));
    }

    @Test
    public void testIngresarPersona() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        evento.ingresarPersona(0);
        assertEquals("True", evento.matriz[0][4]);
    }

    @Test
    public void testPermitirEntradaVIP() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        assertTrue(evento.permitirEntrada(0));
    }

    @Test
    public void testPermitirEntradaGeneral() {
        evento.agregarPersona(0, "Juan", 25, "General", 2);
        assertTrue(evento.permitirEntrada(0));
    }

    @Test
    public void testPermitirEntradaMenorDeEdad() {
        evento.agregarPersona(0, "Juan", 17, "VIP", 2);
        assertFalse(evento.permitirEntrada(0));
    }

    @Test
    public void testRemoverPersona() {
        evento.agregarPersona(0, "Juan", 25, "VIP", 2);
        evento.ingresarPersona(0);
        evento.removerPersona(0);
        assertEquals("False", evento.matriz[0][4]);
    }
}