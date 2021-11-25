package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import fretes.Frete;

public class Arquivo {
	private String nomeArq;


	public Arquivo(String nomeArq) {
		super();
		this.nomeArq = nomeArq;
	}

	public Arquivo() {
	}
	public String getNomeArq() {
		return nomeArq;
	}

	public void setNomeArq(String nomeArq) {
		this.nomeArq = nomeArq;
	}

	public boolean escreverArquivo (Conjunto conjunto) {
		//this.setNomeArq("arquivoObjetos.txt");
		boolean flag = false;
		try {
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
			JOptionPane.showMessageDialog(null, "Falha na operação de Entrada ou Saída!");
			e.printStackTrace();

		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Exceção escrever, objeto null!");
			e.printStackTrace();
		} 
		finally {flag = true;}
		return flag;
	}
	
	public Conjunto lerArquivo (String nomeArq) {
				
	try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArq));){
		
		
		Conjunto<Frete> conjuntinho = new Conjunto();
		conjuntinho = (Conjunto) in.readObject();
		return conjuntinho;
				
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null, "Exceção ao ler ou escrever objeto!");
		e.printStackTrace();
	} 
	return null;
	}
}
