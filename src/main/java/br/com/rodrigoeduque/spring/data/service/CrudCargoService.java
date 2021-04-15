package br.com.rodrigoeduque.spring.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.rodrigoeduque.spring.data.orm.Cargo;
import br.com.rodrigoeduque.spring.data.repository.CargoRepository;

@Service
public class CrudCargoService {

	private final CargoRepository cargoRepository;
	private boolean system = true;
	private Integer opcao;

	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("--------------------------------------------------");
			System.out.println("| 0 - Sair                                       |");
			System.out.println("| 1 - Cadastrar Novo Cargo                       |");
			System.out.println("| 2 - Atualizar Descrição Cargo Existente        |");
			System.out.println("| 3 - Deletar Cargo Existente                    |");
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

	private void salvar(Scanner scanner) {
		System.out.println("Descrição do Cargo : ");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();

		cargo.setDescricao(descricao);

		cargoRepository.save(cargo);
		System.out.println("--------------------------------------------------");
		System.out.println("|          Salvo com Sucesso                     |");
		System.out.println("--------------------------------------------------");

	}

	private void atualizar(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();

		System.out.print("Inserir nova descrição: ");
		String descricao = scanner.next();

		Cargo cargo = new Cargo();

		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);

		System.out.println("--------------------------------------------------");
		System.out.println("|          Atualizado com Sucesso                |");
		System.out.println("--------------------------------------------------");
	}

	private void deletar(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();

		Cargo cargo = new Cargo();
		cargo.setId(id);

		cargoRepository.delete(cargo);

		System.out.println("--------------------------------------------------");
		System.out.println("|       Deletado com Sucesso                     |");
		System.out.println("--------------------------------------------------");

	}
	
	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		System.out.println("--------------------------------------------------");
		System.out.println("|           Relatório Cargos                     |");
		System.out.println("--------------------------------------------------");
		cargos.forEach(cargo -> System.out.println("|           " + cargo.toString() ));
		System.out.println("--------------------------------------------------");
	}
	
	
	private void visualizarPorId(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();
		
		Object retornaCargo = cargoRepository.findById(id);

		
		Cargo cargo = new Cargo();
		cargo.setId(opcao);
		
		System.out.println("--------------------------------------------------");
		System.out.println("|           Relatório Cargos                     |");
		System.out.println("| Identificador: " + id +                       "|");
		System.out.println("| Descrição do Cargo : " + retornaCargo +"|");
		System.out.println("--------------------------------------------------");

	}

}
