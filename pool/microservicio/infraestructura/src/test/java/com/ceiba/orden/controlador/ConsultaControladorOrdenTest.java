package com.ceiba.orden.controlador;


import com.ceiba.ApplicationMock;
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


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorOrden.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaControladorOrdenTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deberia listar las ordenes de un cliente")
    void deberiaListarLasOrdenesDeUnCliente() throws Exception {

        // Arrange
        var identificacionCliente = "123";

        // Act - Assert
        mockMvc.perform(get("/ordenes/cliente/{identificacion}", identificacionCliente)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].tiempoExtra", is(1)))
                .andExpect(jsonPath("$[0].ticket").exists())
                .andExpect(jsonPath("$[0].ticket.id", is(1)))
                .andExpect(jsonPath("$[0].ticket.costoTotal", is(5000.0)))
                .andExpect(jsonPath("$[0].ticket.fechaVencimiento").exists())
                .andExpect(jsonPath("$[0].cliente").exists())
                .andExpect(jsonPath("$[0].cliente.identificacion", is("123")))
                .andExpect(jsonPath("$[0].cliente.fechaNacimiento").exists());
    }

}
