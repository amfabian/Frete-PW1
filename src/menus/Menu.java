package menus;

import java.io.*;
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
		Frete frete1 = new Frete(100.0, "poa", "canoas", null, Situacao.EM_ANDAMENTO, new Cliente("Alexandre", "Rua", "999", "123"));
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

				if (conjunto.listarTodos().equals("")) 	
					JOptionPane.showMessageDialog(null, "Nenhum frete cadastrado");
				else 
					JOptionPane.showMessageDialog(null, "Imprimindo treeset: \n" + conjunto.listarTodos());
				break;  

			case 3: //Listar fretes de um cliente (pesquisar pelo nome)

				JOptionPane.showMessageDialog(null, "Opção 03!");
				JOptionPane.showMessageDialog(null, conjunto.procurarNome(JOptionPane.showInputDialog("Informe o nome do cliente:")));
				break;  

			case 4://Remover Frete usando a situacao
				JOptionPane.showMessageDialog(null, "Opção 04!");


				flag = false;

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


				Frete frete_remove = conjunto.excluir(situacao);
				if(frete_remove!=null) {
					JOptionPane.showMessageDialog(null, "Excluindo: " + frete_remove);
					conjunto.remover(frete_remove);
				}
				else JOptionPane.showMessageDialog(null, "Nenhum frete nessa situação localizado");	
				break;

			case 5:System.exit(0);
			break;

			case 6: 
				JOptionPane.showMessageDialog(null, "Opção 06 - Gravar arquivo!");
				try {
					String nomeArq = "arquivoObjetos.txt";
					ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArq)); 
					out.writeObject(conjunto);
					out.flush();
					out.close();
					System.out.println("salvando objeto no arquivo!\n");

				} catch(FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Exceção arquivo não encontrado!");
					e.printStackTrace();
				} catch (SecurityException e) {
					JOptionPane.showMessageDialog(null, "Exceção de segurança (sem permissão de escrita)!");
					e.printStackTrace();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Falha na opeação de Entrada ou Saída!");
					e.printStackTrace();

				} catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null, "Exceção escrever, objeto null!");
					e.printStackTrace();
				}
				break;

			case 7:
				JOptionPane.showMessageDialog(null, "Opção 07 - Ler arquivo!");

				try {
					String nomeArq = "arquivoObjetos.txt";
					ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArq));
					Conjunto<Frete> conjuntinho = new Conjunto();
					conjuntinho = (Conjunto) in.readObject();
					JOptionPane.showMessageDialog(null, "Objeto lido do arquivo:\n" + conjuntinho.listarTodos());

					in.close();

				}catch(Exception e) {
					JOptionPane.showMessageDialog(null, "Exceção ao ler ou escrever objeto!");
					e.printStackTrace();
				}
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
