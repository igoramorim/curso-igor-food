package com.mycompany.igorfood.domain.service;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.mycompany.igorfood.domain.model.Cozinha;
import com.mycompany.igorfood.domain.repository.CozinhaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class CadastroCozinhaServiceTests {
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private CadastroCozinhaService service;
	
	@MockBean
	private CozinhaRepository repository;
	
	@SuppressWarnings("unchecked")
	@Test
    public void shouldReturnDefaultMessage() throws Exception {
        when(service.teste()).thenReturn("Teste");
        this.mockMvc.perform(get("/cozinhas/teste")).andExpect(status().isOk()).andExpect(content().string("Teste"));
    }
	
	@Test
    public void shouldReturnNotFound() throws Exception {
        when(service.teste()).thenReturn("Teste");
        this.mockMvc.perform(get("/teste")).andExpect(status().isNotFound());
    }
	
	@Test
    public void shouldReturnCozinhasList() throws Exception {
        when(repository.listar()).thenReturn(getMockCozinhas());
        this.mockMvc.perform(get("/cozinhas")).andExpect(status().isOk());
    }
	
	@Test
    public void shouldReturnCozinha() throws Exception {
        when(repository.buscar(1L)).thenReturn(getMockCozinha());
        this.mockMvc.perform(get("/cozinhas/1")).andExpect(status().isOk());
    }
	
	private List<Cozinha> getMockCozinhas() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		Cozinha c2 = Cozinha.builder().id(2L).nome("Japonesa").build();
		List<Cozinha> cozinhas = Stream.of(c1, c2).collect(Collectors.toList());
		
		return cozinhas;
	}
	
	private Cozinha getMockCozinha() {
		Cozinha c1 = Cozinha.builder().id(1L).nome("Italiana").build();
		return c1;
	}

}
