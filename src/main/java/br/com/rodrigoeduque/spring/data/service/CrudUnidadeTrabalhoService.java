package br.com.rodrigoeduque.spring.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.rodrigoeduque.spring.data.orm.Cargo;
import br.com.rodrigoeduque.spring.data.orm.UnidadeTrabalho;
import br.com.rodrigoeduque.spring.data.repository.CargoRepository;
import br.com.rodrigoeduque.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class CrudUnidadeTrabalhoService {

	private final UnidadeTrabalhoRepository unidadeRepository;
	private boolean system = true;
	private Integer opcao;

	public CrudUnidadeTrabalhoService(UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.unidadeRepository = unidadeTrabalhoRepository;
	}

	public void inicial(Scanner scanner) {

		while (system) {

			System.out.println("--------------------------------------------------");
			System.out.println("| 0 - Sair                                       |");
			System.out.println("| 1 - Cadastrar Nova Unidade de Trabalho         |");
			System.out.println("| 2 - Atualizar Descrição Unidade de Trabalho    |");
			System.out.println("| 3 - Deletar Registro                           |");
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
		System.out.println("Descrição da Unidade de Trabalho : ");
		String descricao = scanner.next();
		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

		unidadeTrabalho.setDescricao(descricao);
		System.out.println("Endereço : ");
		String endereco = scanner.next();
		unidadeTrabalho.setEndereco(endereco);

		unidadeRepository.save(unidadeTrabalho);

		System.out.println("--------------------------------------------------");
		System.out.println("|          Salvo com Sucesso                     |");
		System.out.println("--------------------------------------------------");

	}

	private void atualizar(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();

		System.out.print("Inserir nova descrição: ");
		String descricao = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();

		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(descricao);
		

		System.out.print("Inserir novo Endereço: ");
		String endereco = scanner.next();
		unidadeTrabalho.setEndereco(endereco);

		unidadeRepository.save(unidadeTrabalho);
		System.out.println("--------------------------------------------------");
		System.out.println("|          Atualizado com Sucesso                |");
		System.out.println("--------------------------------------------------");
	}

	private void deletar(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);

		unidadeRepository.delete(unidadeTrabalho);

		System.out.println("--------------------------------------------------");
		System.out.println("|       Deletado com Sucesso                     |");
		System.out.println("--------------------------------------------------");

	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeRepository.findAll();
		System.out.println("--------------------------------------------------");
		System.out.println("|           Relatório Cargos                     |");
		System.out.println("--------------------------------------------------");
		unidades.forEach(unidade -> System.out.println("|           " + unidade));
		System.out.println("--------------------------------------------------");
	}

	private void visualizarPorId(Scanner scanner) {
		System.out.print("Informe o id : ");
		int id = scanner.nextInt();

		Object retornaUnidade = unidadeRepository.findById(id);

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(opcao);

		System.out.println("--------------------------------------------------");
		System.out.println("|           Relatório Cargos                     |");
		System.out.println("| Identificador: " + id + "                      |");
		System.out.println("| Descrição do Cargo : " + retornaUnidade + "    |");
		System.out.println("--------------------------------------------------");

	}

}
