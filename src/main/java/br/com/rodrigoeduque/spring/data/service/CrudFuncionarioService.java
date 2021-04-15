package br.com.rodrigoeduque.spring.data.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.rodrigoeduque.spring.data.orm.Cargo;
import br.com.rodrigoeduque.spring.data.orm.Funcionario;
import br.com.rodrigoeduque.spring.data.orm.UnidadeTrabalho;
import br.com.rodrigoeduque.spring.data.repository.CargoRepository;
import br.com.rodrigoeduque.spring.data.repository.FuncionarioRepository;
import br.com.rodrigoeduque.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudFuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;
	private final FuncionarioRepository funcionarioRepository;

	public CrudFuncionarioService(CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository,
			FuncionarioRepository funcionarioRepository) {
		this.cargoRepository = cargoRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
		this.funcionarioRepository = funcionarioRepository;
	}

	public void inicial(Scanner scanner) {
		
		while (system) {

			System.out.println("--------------------------------------------------");
			System.out.println("| 0 - Sair                                       |");
			System.out.println("| 1 - Cadastrar Novo Funcionario                 |");
			System.out.println("| 2 - Atualizar                                  |");
			System.out.println("| 3 - Deletar                                    |");
			System.out.println("| 4 - Visualizar Todos                           |");
			System.out.println("| 5 - Buscar por ID                              |");

			System.out.println("--------------------------------------------------");

			System.out.print("Digite a Opção: ");
			int opcao = scanner.nextInt();

			switch (opcao) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				deletar(scanner);
				break;
			case 4:
				visualizar();
				break;
			case 5:
				visualizarPorId(scanner);
				break;
			default:
				system = false;
				break;
			}


	}
}

	private void visualizarPorId(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private void visualizar() {
		// TODO Auto-generated method stub
		
	}

	private void deletar(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private void atualizar(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}

	private void salvar(Scanner scanner) {
		System.out.print("Digite o nome: ");
		String nome = scanner.next();
		
		
		System.out.print("Digite o cpf");
		String cpf = scanner.next();
		
		System.out.print("Digite o salário: ");
		String salario = scanner.next();
		
		System.out.print("Digite a Data da Contratação: ");
		String dataContratacao = scanner.next();
		
		System.out.print("Digite o cargoId");
        Integer cargoId = scanner.nextInt();
        
        List<UnidadeTrabalho> unidades = unidade(scanner);
        
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(new BigDecimal(salario));
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao,formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidadeTrabalhos(unidades);
                

	funcionarioRepository.save(funcionario);
    System.out.println("Salvo");
}

private List<UnidadeTrabalho> unidade(Scanner scanner) {
    Boolean isTrue = true;
    List<UnidadeTrabalho> unidades = new ArrayList<>();

    while (isTrue) {
        System.out.println("Digite o unidadeId (Para sair digite 0)");
        Integer unidadeId = scanner.nextInt();

        if(unidadeId != 0) {
            Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
            unidades.add(unidade.get());
        } else {
            isTrue = false;
        }
    }

    return unidades;
}


}