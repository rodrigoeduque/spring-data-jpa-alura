package br.com.rodrigoeduque.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rodrigoeduque.spring.data.service.CrudCargoService;
import br.com.rodrigoeduque.spring.data.service.CrudFuncionarioService;
import br.com.rodrigoeduque.spring.data.service.CrudUnidadeTrabalhoService;
import br.com.rodrigoeduque.spring.data.service.impressao.ImpressaoTela;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private boolean system = true;

	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	private final CrudFuncionarioService crudFuncionarioService;

	public SpringDataApplication(CrudCargoService cargoService, CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, CrudFuncionarioService crudFuncionarioService) {
		this.cargoService = cargoService;
		this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
		this.crudFuncionarioService = crudFuncionarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new java.util.Scanner(System.in);

		while (system) {

			System.out.println("--------------------------------");
			System.out.println("| Qual ação você deseja executar? |");
			System.out.println("| 0 - Sair                        |");
			System.out.println("| 1 - Cargos                      |");
			System.out.println("| 2 - Unidade Trabalho            |");
			System.out.println("| 3 - Funcionario                 |");

			System.out.println("--------------------------------");

			System.out.println("Digite a Opção: ");
			int action = scanner.nextInt();

			if (action == 1) {
				cargoService.inicial(scanner);

			} else if (action == 2) {
				crudUnidadeTrabalhoService.inicial(scanner);
			} else if (action == 3) {
				crudFuncionarioService.inicial(scanner);
			}

			else {

				system = false;
				System.out.println("FIM");

			}
		}

	}

}
