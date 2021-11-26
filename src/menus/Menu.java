package menus;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import fretes.*;
import pessoas.Cliente;
import util.*;

public class Menu {
	public static void main(String[] args) {
		Arquivo arquivo = new Arquivo("arquivoObjetos.txt");
		Conjunto<Frete> conjunto = new Conjunto();
		if(arquivo.lerArquivo("arquivoObjetos.txt")!=null) {
			conjunto = arquivo.lerArquivo("arquivoObjetos.txt");
			JOptionPane.showMessageDialog(null, "Arquivo lido com sucesso.");
		}
		while(true) {
			boolean flag;
			switch(montaMenu()) {
			case 1://Cadastrar Frete
				// Armazena os dados do Cliente
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
				String enum_situacao = "";
				enum_situacao+= ("Escolha uma das seguintes opções (digite apenas o número):\n");
				for (Situacao value : Situacao.values()) {
					enum_situacao += value.getDescricao() +"\n";
				}
				//armazena em um inteiro a situação.   
				int situacao_num = Integer.parseInt(JOptionPane.showInputDialog(enum_situacao)); 
				Situacao situacao = null;
				switch(situacao_num) {
				case 1: 
					situacao = Situacao.EM_ANDAMENTO;
					break;
				case 2: 
					situacao = Situacao.CANCELADO;
					break;
				case 3:
					situacao = Situacao.ENCERRADO;
					break;   
				}
				//Finalmente cria o frete e guarda os valores.
				Frete frete = new Frete(valor, cidadeOrigem, cidadeDestino, itens, situacao, cliente);
				JOptionPane.showMessageDialog(null, "Frete: " + frete.toString());
				//add o frete no Conjunto de fretes.
				conjunto.cadastrar(frete);
				break;

			case 2://Listar todos os fretes
				try {		
					if (conjunto.listarTodos().equals("")) 	
						JOptionPane.showMessageDialog(null, "Cadastre um frete utilizando a opção 1 do Menu");
					else { 
						JOptionPane.showMessageDialog(null, "Imprimindo treeset: \n" + conjunto.listarTodos());
					} 
				}catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Nenhum frete cadastrado.\nCadastre um frete utilizando a opção 1 do Menu");
				}				
				break;  

			case 3: //Listar fretes de um cliente (pesquisar pelo nome)
				JOptionPane.showMessageDialog(null, conjunto.pesquisar(JOptionPane.showInputDialog("Informe o nome do cliente:")));
				break;  

			case 4://Remover Frete usando a situacao
				enum_situacao = "";
				enum_situacao+= ("Remover fretes em qual situação (digite apenas o número):\n");
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

				Frete frete_remove = conjunto.procurarFreteSit(situacao);
				if(frete_remove!=null) {
					JOptionPane.showMessageDialog(null, "Excluindo: " + frete_remove);
					conjunto.remover(frete_remove);
				}
				else JOptionPane.showMessageDialog(null, "Nenhum frete nessa situação localizado");	
				break;

			case 5:
				arquivo.escreverArquivo(conjunto);
				JOptionPane.showMessageDialog(null, "Arquivo salvo!");
				System.exit(0);
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
