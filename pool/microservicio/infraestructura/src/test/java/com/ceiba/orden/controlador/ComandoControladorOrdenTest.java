package com.ceiba.orden.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.cliente.puerto.dao.DaoCliente;
import com.ceiba.cliente.servicio.testDataBuilder.ComandoClienteTestDataBuilder;
import com.ceiba.orden.puerto.dao.DaoOrden;
import com.ceiba.orden.servicio.testDataBuilder.ComandoOrdenTestDataBuilder;
import com.ceiba.ticket.modelo.dto.DtoTicket;
import com.ceiba.ticket.puerto.dao.DaoTicket;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorOrden.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoControladorOrdenTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DaoCliente daoCliente;
    @Autowired
    private DaoOrden daoOrden;
    @Autowired
    private DaoTicket daoTicket;


    @Test
    @DisplayName("Deberia procesar una orden")
    void deberiaProcesarUnaOrden() throws Exception {

        // Arrange
        var cliente = new ComandoClienteTestDataBuilder().conIdentificacion("1007").build();
        var orden = new ComandoOrdenTestDataBuilder().conCliente(cliente).build();

        // Act - Assert
        var resultadoCrearOrden = mockMvc.perform(post("/ordenes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orden)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.fechaVencimiento").exists())
                .andExpect(jsonPath("$.costoTotal").exists())
                .andReturn();

        var resultado = objectMapper.readValue(resultadoCrearOrden.getResponse().getContentAsString(), DtoTicket.class);

        assertNotNull(daoCliente.obtenerPorIdentificacion(orden.getCliente().getIdentificacion()));
        assertNotNull(daoTicket.obtenerPorId(resultado.getId()));
        assertEquals(1, daoOrden.listarPorCliente(orden.getCliente().getIdentificacion()).size());
    }

    @Test
    @DisplayName("Deberia eliminar una orden")
    void deberiaEliminarUnaOrden() throws Exception {

        // Arrange
        var idOrden = 1L;
        var idTicket = 1L;
        var identificacionCliente = "123";


        // Act - Assert
        mockMvc.perform(delete("/ordenes/{id}", idOrden)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        assertNull(daoOrden.obtenerPorId(idOrden));
        assertNull(daoTicket.obtenerPorId(idTicket));
        assertNotNull(daoCliente.obtenerPorIdentificacion(identificacionCliente));
    }
}
