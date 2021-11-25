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
			JOptionPane.showMessageDialog(null, "Exce��o arquivo n�o encontrado!");
			e.printStackTrace();
		} catch (SecurityException e) {
			JOptionPane.showMessageDialog(null, "Exce��o de seguran�a (sem permiss�o de escrita)!");
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Falha na opera��o de Entrada ou Sa�da!");
			e.printStackTrace();

		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Exce��o escrever, objeto null!");
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
		JOptionPane.showMessageDialog(null, "Exce��o ao ler ou escrever objeto!");
		e.printStackTrace();
	} 
	return null;
	}
}
