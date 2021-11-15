package menus;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import fretes.Frete;
import fretes.ItemFrete;
import pessoas.Cliente;
import util.Situacao;

public class Menu {
	public static void main(String[] args) {
		ArrayList<Frete> fretes = new ArrayList();
		while(true) {
			switch(montaMenu()) {
			case 1://Cadastrar Frete

				// Armazena os dados do Cliente
				// 
				String nome = JOptionPane.showInputDialog("Informe o nome do Cliente:");
				String endereco = JOptionPane.showInputDialog("Informe o endere�o do Cliente:");
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
				//perd�o pela falta de sensibilidade em iniciar o contador em 1 na linha abaixo mas mostrar para o 
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
						//com esse cont-- caso o peso n�o esteja entre os limites, o usuario ir� retornar ao item 
						//e coloca-lo novamente com o peso correto
						cont--;

					}
				}
				//Apresenta as op��es para a suitua��o usando a enumera�ao
				//
				String enum_situacao = "";
				enum_situacao+= ("Escolha uma das seguintes op��es (digite apenas o n�mero):\n");
				for (Situacao value : Situacao.values()) {
					enum_situacao += value.getDescricao() +"\n";
				}
				//armazena em um inteiro a situa��o.   
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

			case 2://Pesquisar Frete usando a origem e o destino

				boolean flag = false;
				if(fretes.isEmpty()) { //caso a lista de fretes esteja vazia, informa e volta para o menu
					JOptionPane.showMessageDialog(null, "Sem fretes cadastrados!");
					break;
				}
				else {
					String cidPesq = JOptionPane.showInputDialog("Informe a cidade:");
					for (Frete FretePesq: fretes) {
						if(FretePesq.getCidadeOrigem().equals(cidPesq) | FretePesq.getCidadeDestino().equals(cidPesq)) {
							JOptionPane.showMessageDialog(null, "Frete localizado: \n" + FretePesq.toString());
							flag = true;
						}		
					}
				}
				if(flag==false) 
					JOptionPane.showMessageDialog(null, "N�o exite frete para a cidade informada!"); 
				break;  

			case 3: //Pesquisar se um cliente possui algum frete cadastrado

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
					JOptionPane.showMessageDialog(null, "O Ciente n�o possui frete cadastrado!"); 
				break;  

			case 4://Pesquisar Frete usando a situacao

				flag = false;
				if(fretes.isEmpty()) {//caso a lista de fretes esteja vazia, informa e volta para o menu
					JOptionPane.showMessageDialog(null, "Sem fretes cadastrados!");
					break;
				}
				else {
					enum_situacao = "";
					enum_situacao+= ("Pesquisar por fretes em qual situa��o (digite apenas o n�mero):\n");
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

						JOptionPane.showMessageDialog(null, "N�o existe frete com a situa��o informada!"); 
				}  	
				break;

			case 5:System.exit(0);
			break;

			default: 
				JOptionPane.showMessageDialog(null, "Escolha uma das op��es anteriores!"); 

			}
		}			   
	}
	public static int montaMenu() {
		String menu = "";
		menu+= ("Escolha uma das seguintes op��es:\n");
		for (OpcoesMenu value : OpcoesMenu.values()) {
			menu += value.getDescricao() +"\n";
		}
		return Integer.parseInt(JOptionPane.showInputDialog(menu)); 
	}
}
