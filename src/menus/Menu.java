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
		Conjunto<Frete> conjunto = new Conjunto();
		JOptionPane.showMessageDialog(null, "Criado CONJUNTO");
		Frete frete1 = new Frete(100.0, "poa", "canoas", null, Situacao.ENCERRADO, new Cliente("Alexandre", "Rua", "999", "123"));
		Frete frete3 = new Frete(90.0, "poa", "viamao", new ArrayList<ItemFrete>(), Situacao.ENCERRADO, new Cliente("Alexandre", "Avenida", "888", "4556"));
		Frete frete4 = new Frete(80.0, "poa", "guaiba", new ArrayList<ItemFrete>(), Situacao.ENCERRADO, new Cliente("Alexandre", "Estrada", "777", "8956"));
		Frete frete5 = new Frete(90.0, "poa", "viamao", new ArrayList<ItemFrete>(), Situacao.ENCERRADO, new Cliente("Eduardo", "Avenida", "888", "4556"));
		Frete frete6 = new Frete(80.0, "poa", "guaiba", new ArrayList<ItemFrete>(), Situacao.ENCERRADO, new Cliente("Leonardo", "Estrada", "777", "8956"));
		conjunto.cadastrar(frete1);
		conjunto.cadastrar(frete3);
		conjunto.cadastrar(frete4);
		conjunto.cadastrar(frete5);
		conjunto.cadastrar(frete6);

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
				double valor = Double.parseDouble(JOptionPane.showInputDialog("Informe o valor do frete:"));
					//	Integer.parseInt(JOptionPane.showInputDialog("Informe o valor do frete:"));
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
				
				//add o frete no Conjunto de fretes.
				conjunto.cadastrar(frete);
				JOptionPane.showMessageDialog(null, "Imprimindo treeset: \n" + conjunto.listarTodos());
				break;

			case 2://Listar todos os fretes

				JOptionPane.showMessageDialog(null, "Opção 02!");
				
				
				JOptionPane.showMessageDialog(null, "Imprimindo treeset: \n" + conjunto.listarTodos());
				
	/*			ArrayList<ItemFrete> itens1 = new ArrayList();

				ItemFrete item1 = new ItemFrete("descricao", 10.0);
				itens1.add(item1);
				JOptionPane.showMessageDialog(null, "Criado item para o Frete");

				Frete frete1 = new Frete(10.0, "poa", "canoas", itens1, Situacao.ENCERRADO, new Cliente("Alexandre", "Rua", "999", "123"));

				JOptionPane.showMessageDialog(null, "Frete: " + frete1.toString());

		
				
				Frete frete10 = new Frete(10.0, "canoas", "poa", itens1, Situacao.CANCELADO, new Cliente("Eduardo", "Avenida", "888", "456"));
				conjunto.cadastrar(frete10);
				JOptionPane.showMessageDialog(null, "Imprimindo treeset 2: " + conjunto.listarTodos());
				
				
				JOptionPane.showMessageDialog(null, "Pesquisando item 1 ");
				
				JOptionPane.showMessageDialog(null, "Item pesquisado 1: " + conjunto.pesquisar(frete1));
				
				conjunto.remover(frete1);
				JOptionPane.showMessageDialog(null, "Removendo item 1 ");

				JOptionPane.showMessageDialog(null, "Imprimindo treeset depois da remoção: " + conjunto.listarTodos());

				JOptionPane.showMessageDialog(null, "Pesquisando 1 apos remocao: " + conjunto.pesquisar(frete1));
*/
				break;  

			case 3: //Listar fretes de um cliente (pesquisar pelo nome)

				JOptionPane.showMessageDialog(null, "Opção 03!");
				JOptionPane.showMessageDialog(null, conjunto.procurarNome(JOptionPane.showInputDialog("Informe o nome do cliente:")));
				break;  

			case 4://Remover Frete usando a situacao
				JOptionPane.showMessageDialog(null, "Opção 04!");
				

				flag = false;
			//	if(fretes.isEmpty()) {//caso a lista de fretes esteja vazia, informa e volta para o menu
			//		JOptionPane.showMessageDialog(null, "Sem fretes cadastrados!");
			//		break;
			//	}
			//	else {
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

					
					
					if (conjunto.pesquisar(new Frete(10.0, "canoas", "poa", null, situacao, new Cliente("Eduardo", "Avenida", "888", "456")))) {
						//JOptionPane.showMessageDialog(null, "Frete localizado: \n" + conjunto.toString());
						JOptionPane.showMessageDialog(null, "Frete localizado");
						flag = true;
					}
					
			//		for (Frete FretePesq: conjunto) {
		//				if(FretePesq.getSituacao().equals(situacao)) {
			//			
			//				JOptionPane.showMessageDialog(null, "Frete localizado: \n" + FretePesq.toString());
			//				flag = true;
			//			}

			//		}
					if(flag==false) 

						JOptionPane.showMessageDialog(null, "Não existe frete com a situação informada!"); 
				  
		
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

				case 7://Listar todos os fretes
					JOptionPane.showMessageDialog(null, "Opção 07!");
					JOptionPane.showMessageDialog(null, conjunto.procurarNome(JOptionPane.showInputDialog("Informe o nome do cliente:")));
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
