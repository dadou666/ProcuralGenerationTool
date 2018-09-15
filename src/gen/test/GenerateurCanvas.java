package gen.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import gen.Generateur;
import ihm.swing.SwingBuilder;

public class GenerateurCanvas extends SwingBuilder implements ListSelectionListener, ActionListener {
	JList<String> compositions;
	CompositionCanvas cc;
	Generateur gen;
	JTextArea texte;
	JButton recharger;
	String fichier;

	public static void main(String args[]) throws ParserConfigurationException, SAXException, IOException {
		GenerateurCanvas gc = new GenerateurCanvas();
		gc.ouvrir("src/gen.xml");

	}

	public void ouvrir(String fichier) throws ParserConfigurationException, SAXException, IOException {

		gen = Generateur.lireFichierXML(fichier);
		this.fichier = fichier;
		String src = new String(Files.readAllBytes(Paths.get(fichier)));
		beginY();
		beginX();
		this.setSize(401, 321);
		cc = new CompositionCanvas(400, 320);
		add(cc);
		beginY();
		this.setSize(250, 20);
		JLabel label = new JLabel("compositions");
		add(label);
		this.setSize(250, 300);
		compositions = new JList<String>();
		Vector<String> vector = new Vector<>();
		vector.addAll(gen.compositions.keySet());
		compositions.setListData(vector);
		compositions.addListSelectionListener(this);
		add(compositions);
		end();
		end();
		this.setSize(651, 20);
		recharger = new JButton("Recharger");
		recharger.addActionListener(this);
		add(recharger);
		this.setSize(651, 321);

		texte = new JTextArea();
		texte.setText(src);
		//add(new JScrollPane(texte));

		end();
		this.open("Generateur");

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		Object sel = this.compositions.getSelectedValue();
		if (sel == null) {
			return;
		}
		this.cc.composition = gen.compositions.get(sel);
		this.cc.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			//Files.write(Paths.get(this.fichier), this.texte.getText().getBytes());
			gen = Generateur.lireFichierXML(fichier);
			Vector<String> vector = new Vector<>();
			vector.addAll(gen.compositions.keySet());
			compositions.setListData(vector);
		} catch (IOException e1) {
		
			e1.printStackTrace();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
