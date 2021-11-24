package menus;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JOptionPane;


import fretes.Frete;
import fretes.ItemFrete;
import pessoas.Cliente;
import pessoas.Pessoa;
import util.Conjunto;
import util.Situacao;

public class Menu {
	public static void main(String[] args) {
		ArrayList<Frete> fretes = new ArrayList();
		//	Agenda<Pessoa> agenda = new Agenda();

		while(true) {
			boolean flag;
			switch(montaMenu()) {
			case 1://Cadastrar Frete

				// Armazena os dados do Cliente
				// 
				String nome = JOptionPane.showInputDialog("Informe o nome do Cliente:");
				String endereco = JOptionPane.showInputDialog("Informe o endereço do Cliente:");
				String telefone = JOptionPane.showInputDialog("Informe o telefone do Cliente:");
				String cpf = JOptionPane.showInputDialog("Informe o cpf do Cliente:");
				//Cria o cliente com os dados passados
				Cliente cliente = new Cliente(nome, endereco, telefone, cpf);


				//Armazena os dados do frete.
				//
				double valor = Integer.parseInt(JOptionPane.showInputDialog("Informe o valor do frete:"));
				String cidadeOrigem = JOptionPane.showInputDialog("Informe a cidade de Origem:");
				String cidadeDestino = JOptionPane.showInputDialog("Informe a cidade de Destino:");

				//criando a lista de itens de frete
				ArrayList<ItemFrete> itens = new ArrayList();
				int numItens = Integer.parseInt(JOptionPane.showInputDialog("Quantos itens:"));
				//perdão pela falta de sensibilidade em iniciar o contador em 1 na linha abaixo mas mostrar para o 
				//usuario "item 0" faz menos sentido ainda
				for(int cont =1; cont<=numItens; cont++) {
					String descricao = JOptionPane.showInputDialog("Informe a descricao do item " + cont + ": ");
					double peso = Integer.parseInt(JOptionPane.showInputDialog("Informe o peso do item:"));
					//cria cada item
					ItemFrete item = new ItemFrete(descricao, peso);
					//chamada para o validador
					if (item.validaPeso(peso)) {
						itens.add(item); //caso o peso esteja em ordem add item na lista itens
					} else {
						JOptionPane.showMessageDialog(null, "O peso do item deve estar entre 1kg e 30kg");
						//com esse cont-- caso o peso não esteja entre os limites, o usuario irá retornar ao item 
						//e coloca-lo novamente com o peso correto
						cont--;

					}
				}
				//Apresenta as opções para a suituação usando a enumeraçao
				//
				String enum_situacao = "";
				enum_situacao+= ("Escolha uma das seguintes opções (digite apenas o número):\n");
				for (Situacao value : Situacao.values()) {
					enum_situacao += value.getDescricao() +"\n";
				}
				//armazena em um inteiro a situação.   
				int situacao_num = Integer.parseInt(JOptionPane.showInputDialog(enum_situacao)); 
				Situacao situacao = null;
				switch(situacao_num) {
				case 1: situacao = Situacao.EM_ANDAMENTO;

				break;
				case 2: situacao = Situacao.CANCELADO;

				break;
				case 3:situacao = Situacao.ENCERRADO;

				break;   
				}


				//Finalmente cria o frete e guarda os valores.
				//


				Frete frete = new Frete(valor, cidadeOrigem, cidadeDestino, itens, situacao, cliente);
				JOptionPane.showMessageDialog(null, "Frete: " + frete.toString());
				//add o frete na lista de fretes.
				fretes.add(frete);
				break;

			case 2://Listar todos os fretes

				JOptionPane.showMessageDialog(null, "Opção 02!");
				Conjunto<Frete> conjunto = new Conjunto();
			
				JOptionPane.showMessageDialog(null, "Criado CONJUNTO");
				ArrayList<ItemFrete> itens1 = new ArrayList();

				ItemFrete item1 = new ItemFrete("descricao", 10.0);
				itens1.add(item1);
				JOptionPane.showMessageDialog(null, "Criado item para o Frete");

				Frete frete1 = new Frete(10.0, "poa", "canoas", itens1, Situacao.ENCERRADO, new Cliente("Alexandre", "Rua", "999", "123"));

				JOptionPane.showMessageDialog(null, "Frete: " + frete1.toString());

				conjunto.cadastrar(frete1);

			

				JOptionPane.showMessageDialog(null, "Imprimindo treeset: " + conjunto.listarTodos());
				
				Frete frete10 = new Frete(10.0, "canoas", "poa", itens1, Situacao.CANCELADO, new Cliente("Eduardo", "Avenida", "888", "456"));
				conjunto.cadastrar(frete10);
				JOptionPane.showMessageDialog(null, "Imprimindo treeset 2: " + conjunto.listarTodos());
				
				
				JOptionPane.showMessageDialog(null, "Pesquisando item 1 ");
				
				JOptionPane.showMessageDialog(null, "Item pesquisado 1: " + conjunto.pesquisar(frete1));
				
				conjunto.remover(frete1);
				JOptionPane.showMessageDialog(null, "Removendo item 1 ");

				JOptionPane.showMessageDialog(null, "Imprimindo treeset depois da remoção: " + conjunto.listarTodos());

				JOptionPane.showMessageDialog(null, "Pesquisando 1 apos remocao: " + conjunto.pesquisar(frete1));

				break;  

			case 3: //Listar fretes de um cliente (pesquisar pelo nome)

				JOptionPane.showMessageDialog(null, "Opção 03!");

				flag = false;
				if(fretes.isEmpty()) {//caso a lista de fretes esteja vazia, informa e volta para o menu
					JOptionPane.showMessageDialog(null, "Sem fretes cadastrados!");
					break;
				}
				else {
					String cpfPesq = JOptionPane.showInputDialog("Informe o cpf:");
					for (Frete FretePesq: fretes) {
						if(FretePesq.getCliente().getCpf().equals(cpfPesq)) {
							JOptionPane.showMessageDialog(null, "O Cliente possui frete: \n" + FretePesq.toString());
							flag = true;
						}
					}
				}
				if(flag==false) 
					JOptionPane.showMessageDialog(null, "O Ciente não possui frete cadastrado!"); 
				break;  

			case 4://Remover Frete usando a situacao

				flag = false;
				if(fretes.isEmpty()) {//caso a lista de fretes esteja vazia, informa e volta para o menu
					JOptionPane.showMessageDialog(null, "Sem fretes cadastrados!");
					break;
				}
				else {
					enum_situacao = "";
					enum_situacao+= ("Pesquisar por fretes em qual situação (digite apenas o número):\n");
					for (Situacao value : Situacao.values()) {
						enum_situacao += value.getDescricao() +"\n";
					}
					int sitPesq = Integer.parseInt(JOptionPane.showInputDialog(enum_situacao)); 

					situacao = null;
					switch(sitPesq)  {
					case 1: situacao = Situacao.EM_ANDAMENTO;
					break;
					case 2: situacao = Situacao.CANCELADO;
					break;
					case 3:situacao = Situacao.ENCERRADO;
					break;   
					}


					for (Frete FretePesq: fretes) {
						if(FretePesq.getSituacao().equals(situacao)) {

							JOptionPane.showMessageDialog(null, "Frete localizado: \n" + FretePesq.toString());
							flag = true;
						}

					}
					if(flag==false) 

						JOptionPane.showMessageDialog(null, "Não existe frete com a situação informada!"); 
				}  	
				break;

			case 5:System.exit(0);
			break;

			case 6://Listar todos os fretes

				JOptionPane.showMessageDialog(null, "Opção 02!");
				TreeSet<Frete> fretesTS2 = new TreeSet<>();
				JOptionPane.showMessageDialog(null, "Criado TreeSet");
				ArrayList<ItemFrete> itens2 = new ArrayList();

				ItemFrete item2 = new ItemFrete("descricao", 10.0);
				itens2.add(item2);
				JOptionPane.showMessageDialog(null, "Criado item para o Frete");

				Frete frete2 = new Frete(10.0, "poa", "canoas", itens2, Situacao.ENCERRADO, new Cliente("Alexandre", "Rua", "999", "123"));

				JOptionPane.showMessageDialog(null, "Frete: " + frete2.toString());

				fretesTS2.add(frete2);

				JOptionPane.showMessageDialog(null, "Criado TreeSet");

				JOptionPane.showMessageDialog(null, "Imprimindo treeset: " + fretesTS2.first());




				break;  

			default: 
				JOptionPane.showMessageDialog(null, "Escolha uma das opções anteriores!"); 

			}
		}			   
	}
	public static int montaMenu() {
		String menu = "";
		menu+= ("Escolha uma das seguintes opções:\n");
		for (OpcoesMenu value : OpcoesMenu.values()) {
			menu += value.getDescricao() +"\n";
		}
		return Integer.parseInt(JOptionPane.showInputDialog(menu)); 
	}
}
