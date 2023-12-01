import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Conta {
	
	/**
	 * @author Marcus Vinicius - 210332 
	 * @para arg1 Este é o primeiro parametro
	 * @throws Throwable
	 * */
	
	private RuntimeException exception; // Variavel para exeções
	private double saldo; // Variavel de saldo da conta
	
	
	
	// Em caso de cliente especial com inicialmente saldo negativo
	@Given("Um cliente especial com saldo atual de -(\\d+) reais$")
	public void um_cliente_especial_com_saldo_atual_de_reais (int sI) throws Throwable{
		saldo = -sI;
		throw new PendingException();
	}
	
	// Quando feito um saque
	@When("for solicitado um saque no valor de (\\d+) reaiss")
	public void for_solicitado_um_saque_no_valor_de_reais(int Vs) throws Throwable { 
		 if (Vs <= 0) {
			 
	            exception = new RuntimeException("Valor de saque inv�lido."); // Em caso especial de uma Exeção
	        } else if (saldo < Vs) {
	        	
	            exception = new RuntimeException("Saldo insuficiente para o saque."); // Em caso especial de uma Exeção
	        } else {
	            saldo -= Vs; // Para realizar o saque
	        }
		throw new PendingException();
	}
	
	// Confirmação de saque 
	@Then("deve efetuar o saque e atualizar o saldo da conta para -(\\d+) reaiss")
	public void deve_efetuar_o_saque_e_atualizar_o_saldo_da_conta_para_reais(int saldoNovo) throws Throwable {
		if (saldo == -saldoNovo) {
            System.out.println("Saque feito com exito !: " + saldo);
        } else {
            System.out.println("Erro inesperado !.");
        }
		throw new PendingException();
	}
	
	//Em caso de não haver salfo suficiente
	@Then("check more outcomes$")
	public void check_more_outcomes() throws Throwable {
		
		 // Confere sem existe alguma exeção
        if (exception != null) {
        	
            // Caso a exeção seja sem saldo"
            if ("Você não tem saldo o sunficiente para efetuar seu saque.".equals(exception.getMessage())) {
                System.out.println("Não foi possivel fazer seu saque");
            } else {
                System.out.println("Erro inesperado !");
            }
        } else {
            System.out.println("Saque feito com exito !");
        }
		throw new PendingException();
	}
}