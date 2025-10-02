package tests.game;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import game.InputManager;

public class InputManagerTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayInputStream testIn;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @AfterEach
    /*
     * Restaura a entrada e saida padrao. 
     * Boa pratica que todo e qualquer teste que mude System.in ou System.out deve fazer,
     * pois evita problemas em outros testes.
     */
    void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalOut);
    }

    /*
     * Simula a entrada do usuario
     * @param data String representando a entrada a ser testada.
     */
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testLerInteiro_ValidInput() {
        provideInput("5\n");

        int resultado = InputManager.readInteger("Digite um numero", 1, 10);
        assertEquals(5, resultado);
    }

    @Test
    public void testLerInteiro_InvalidInput_nao_inteiro() {
        System.setOut(new PrintStream(outContent));

        provideInput("palavras\n");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> 
        InputManager.readInteger("Digite um numero", 1, 10));

        // Entrada invalida faz com que o InputManager espere nova entrada (nao existe entao da erro)
        assertEquals("Entrada nao disponivel.", exception.getMessage());

        // Verifica se a mensagem de entrada errada foi exibida
        assertEquals("Digite um numero (1 - 10): Valor invalido. Digite um numero inteiro.\n"+
                    "Digite um numero (1 - 10): No line found\n", outContent.toString());
        
    }
}