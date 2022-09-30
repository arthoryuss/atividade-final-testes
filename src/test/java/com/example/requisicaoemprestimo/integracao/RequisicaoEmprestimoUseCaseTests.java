package com.example.requisicaoemprestimo.integracao;

import com.example.requisicaoemprestimo.domain.models.Emprestimo;
import com.example.requisicaoemprestimo.domain.models.ResultadoAnalise;
import com.example.requisicaoemprestimo.domain.models.ResultadoTesouraria;
import com.example.requisicaoemprestimo.domain.ports.IAnaliseProxy;
import com.example.requisicaoemprestimo.domain.ports.ITesourariaProxy;
import com.example.requisicaoemprestimo.domain.usecases.RequisicaoEmprestimoUseCase;
import org.junit.jupiter.api.BeforeEach;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

//TODO: Use o mokito para realizar testes TOP-DOWN para criar dublês das interfaces IAnaliseProxy e ITesourariaProxy
public class RequisicaoEmprestimoUseCaseTests {

    //TODO: Setup das classes Mocks e Instância real da classe RequisicaoEmprestimoUseCase
    private IAnaliseProxy analiseProxy;
    private ITesourariaProxy tesourariaProxy;
    private RequisicaoEmprestimoUseCase requisicao;

    @BeforeEach
    public void Setup(){
        analiseProxy = mock(IAnaliseProxy.class);
        tesourariaProxy = mock(ITesourariaProxy.class);
        when(analiseProxy.solicitarAnaliseDeCredito(any(Emprestimo.class))).thenReturn(new ResultadoAnalise(false, new String[0]));
        when(tesourariaProxy.solicitarLiberacaoDaTesouraria(any(Emprestimo.class))).thenReturn(new ResultadoTesouraria(true, "OK"));

        requisicao = new RequisicaoEmprestimoUseCase(analiseProxy, tesourariaProxy);
    }
    @Test
    public void test1(){
        //TODO Fazer um teste caminho Feliz (TUDO FUNCIONA BEM)
        Emprestimo emprestimo = requisicao.executar(UUID.randomUUID(), 100, 12);

        assertTrue(emprestimo.isEmprestimoFoiAprovado());
    }

    @Test
    public void test2(){
        //TODO Fazer um teste caminho INFELIZ IAnaliseProxy retornando uma Análise reprovada
        Emprestimo emprestimo = requisicao.executar(UUID.randomUUID(), 100, 12);

        assertEquals(false, emprestimo.isEmprestimoFoiAprovado());
    }

    @Test
    public void test3(){
        //TODO Fazer um teste caminho INFELIZ ITesourariaProxy retornando resultado reprovado
        Emprestimo emprestimo = requisicao.executar(UUID.randomUUID(), 100, 12);

        assertEquals(false, emprestimo.isEmprestimoFoiAprovado());
    }
}
