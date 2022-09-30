package com.example.requisicaoemprestimo.unitarios;

import com.example.requisicaoemprestimo.domain.models.ResultadoAnalise;
import com.example.requisicaoemprestimo.domain.models.ResultadoTesouraria;
import org.junit.jupiter.api.Test;
import com.example.requisicaoemprestimo.domain.models.Emprestimo;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoTests {
    private final EmprestimoTestsFixture fixture = new EmprestimoTestsFixture();
    private Emprestimo emprestimo;
    @Test
    public void testParcelas(){
        // ARRANGE
        //TODO: FAÇA USO DO FIXTURE
        //TODO: Crie uma classe Emprestimo com valor 100 e quantidade de parcelas 12

        // ACT
        //TODO: Recupere as parcelas do emprestimo
        Emprestimo emprestimo = fixture.emprestimoAprovado(100, 12);
        // ASSERTS
        //TODO: Validar se existe parcelas
        //TODO: Validar o valor total de empréstimo é 106.50
        //TODO: Validar se o número de parcelas é 12
        assertTrue(emprestimo.getParcelas().isPresent());
        assertEquals(106.50, emprestimo.getValorTotalEmprestimo());
        assertEquals(12, emprestimo.getQuantidadeParcelasSolicitadas());
    }

    @Test
    public void testeAnaliseDeCreditoInvalida(){
        // ARRANGE
        //TODO: FAÇA USO DO FIXTURE
        //TODO: Crie uma classe Emprestimo com valor 100 e quantidade de parcelas 12

        // ACT
        //TODO: Crie uma análise de crédito rejeitando a proposta
        //TODO: Associe or resulado ao emprestimo
        emprestimo = fixture.emprestimoAprovado( 100.00, 12);
        ResultadoAnalise resultadoAnalise = new ResultadoAnalise();
        emprestimo.setResultadoAnalise(resultadoAnalise);

        // ASSERTS
        //TODO: Validar que o  emprestimo NÃO está aprovado
        ResultadoAnalise result = emprestimo.getResultadoAnalise();
        assertFalse(result.isAprovado());

    }

    @Test
    public void testeResultadoDaTesourariaInvalida(){
        // ARRANGE
        //TODO: FAÇA USO DO FIXTURE
        //TODO: Crie uma classe Emprestimo com valor 100 e quantidade de parcelas 12
        emprestimo = fixture.emprestimoAprovado( 100.00, 12);
        ResultadoTesouraria resultadoTesouraria = new ResultadoTesouraria();
        emprestimo.setResultadoTesouraria(resultadoTesouraria);
        // ACT
        //TODO: Crie uma solicitação para tesouraria rejeitando a proposta
        //TODO: Associe o resultado ao emprestimo
       //  Emprestimo emprestimo = fixture.emprestimoTesourariaInvalida(100, 12);


        // ASSERTS
        //TODO: Validar que o  emprestimo NÃO está aprovado
        ResultadoTesouraria result = emprestimo.getResultadoTesouraria();
        assertFalse(result.isAprovado());
    }

}
