package View;

import java.awt.Choice;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import Model.Actor;
import Model.IMDB;
import Model.MovieList;
import Model.MovieNode;



public class Search extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField textField;
	Choice choice;
	JRadioButton b1;
	JRadioButton b2;
	JRadioButton b3;
	JRadioButton b4;
	JRadioButton b5;
	JRadioButton b6;
	JRadioButton rdbtnByTitle;
	JRadioButton rdbtnByDirector;
	JRadioButton rdbtnByActor;
	JRadioButton rdbtnByGenre;
	JRadioButton rdbtnYear;
	JRadioButton rdbtnRatingRange;
	JButton btnNewButton;
	JLabel l;
	private JLabel label;

	public Search() {
		IMDB db = new IMDB();
		db.read();

		setSize(850, 500);
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.decode("#000000"));

		setSize(850, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		textField = new JTextField();
		textField.setBounds(55, 173, 465, 39);
		getContentPane().add(textField);
		textField.setColumns(10);

		btnNewButton = new JButton("Search");

		JRadioButton rAn = new JRadioButton("Actor By Name");
		rAn.setBackground(Color.BLACK);
		rAn.setForeground(Color.WHITE);
		rAn.setBounds(201, 219, 109, 23);
		getContentPane().add(rAn);
		
		JRadioButton rAac = new JRadioButton("Actor All CoActors");
		rAac.setBackground(Color.BLACK);
		rAac.setForeground(Color.WHITE);
		rAac.setBounds(201, 245, 127, 23);
		getContentPane().add(rAac);
		
		JRadioButton rAcm = new JRadioButton("Actor CoActor In Movie");
		rAcm.setBackground(Color.BLACK);
		rAcm.setForeground(Color.WHITE);
		rAcm.setBounds(201, 271, 158, 23);
		getContentPane().add(rAcm);
		
		ButtonGroup b1=new ButtonGroup();
		b1.add(rAn);
		b1.add(rAac);
		b1.add(rAcm);
		rAn.setVisible(false);
		rAac.setVisible(false);
		rAcm.setVisible(false);
		btnNewButton.setBounds(624, 173, 101, 39);
		getRootPane().setDefaultButton(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (choice.getSelectedItem().equals("Actor")) {
					if(rAn.isSelected()){
					String s=db.searchActorByName(textField.getText());
					if (s!="") {
						DisplayMovieListInGui(s);
					} else {
						JOptionPane.showMessageDialog(null, "Actor not found");
					}}
					if(rAac.isSelected()){
						if(db.Actor_tree.search(textField.getText())!=null){
						String s=db.Actor_tree.search(textField.getText()).printCoActors();
						if(s!=""){
							DisplayMovieListInGui(s);
						}}
						 else {
								JOptionPane.showMessageDialog(null, "Actor not found");
							}
					}
					if(rAcm.isSelected()){
						if(db.Actor_tree.search(textField.getText())!=null){
						Actor temp=db.Actor_tree.search(textField.getText());
						MovieList  mv=temp.getMoviesDone();
						String[] moviechoice=getchoices(mv);
						String input = (String) JOptionPane.showInputDialog(null, "Select Movie To Display CO Actors",
						        String.format("%s Movie List", temp.name), JOptionPane.QUESTION_MESSAGE, null, 
						        moviechoice, 
						        moviechoice[0]); 
						String s=temp.printCoActorInMovie(input);
						JOptionPane.showMessageDialog(null,s);
					}
					else{
						JOptionPane.showMessageDialog(null, "Actor not found");
					}
					}
				}
				if (choice.getSelectedItem().equals("Director")) {
					String s=db.searchDirectorByName(textField.getText());
					if (s!="") {
						DisplayMovieListInGui(s);
					} else {
						JOptionPane.showMessageDialog(null, "No Such Director");
					}
				}
				if (choice.getSelectedItem().equals("Movies")) {
					if (rdbtnByTitle.isSelected()) {
						String s=db.searchMovieByTitle(textField.getText());
						if (s!="") {
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie not found");
						}
					}
					 else if (rdbtnByGenre.isSelected()) {
						String s=db.searchMovieByGenre(textField.getText());
						if (s!="") {
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie not found");
						}
					} else if (rdbtnByActor.isSelected()) {
						String s=db.searchMovieByActor(textField.getText());
						if (s!="") {
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie Not Found");
						}
					} else if (rdbtnYear.isSelected()) {
						String s=db.searchMovieByYear(textField.getText());
						if(s!=""){
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie not found");
						}

					} else if (rdbtnByDirector.isSelected()) {
						String s=db.searchMovieByDirector(textField.getText());
						if (s!="") {
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie not found");
						}

					}else if(rdbtnRatingRange.isSelected()){
						String s=db.searchMovieByRating(textField.getText());
						if (s!="") {
							DisplayMovieListInGui(s);
						} else {
							JOptionPane.showMessageDialog(null, "Movie not found");
						}
					}
				}
			}
		}
		

		);
		getContentPane().add(btnNewButton);

		ButtonGroup b = new ButtonGroup();

		choice = new Choice();
		choice.setBounds(526, 173, 92, 39);
		choice.add("Movies");
		choice.add("Actor");
		choice.add("Director");

		getContentPane().add(choice);

		rdbtnByTitle = new JRadioButton("By Title");
		rdbtnByTitle.setBounds(55, 219, 92, 23);
		rdbtnByTitle.setBackground(Color.decode("#000000"));
		rdbtnByTitle.setSelected(true);
		rdbtnByTitle.setForeground(Color.white);
		getContentPane().add(rdbtnByTitle);

		rdbtnByDirector = new JRadioButton("By Director");
		rdbtnByDirector.setBounds(55, 245, 92, 23);
		rdbtnByDirector.setBackground(Color.decode("#000000"));
		rdbtnByDirector.setForeground(Color.white);
		getContentPane().add(rdbtnByDirector);

		rdbtnByActor = new JRadioButton("By Actor");
		rdbtnByActor.setBounds(55, 271, 92, 23);
		rdbtnByActor.setBackground(Color.decode("#000000"));
		rdbtnByActor.setForeground(Color.white);
		getContentPane().add(rdbtnByActor);

		rdbtnByGenre = new JRadioButton("By Genre");
		rdbtnByGenre.setBounds(55, 297, 92, 23);
		rdbtnByGenre.setBackground(Color.decode("#000000"));
		rdbtnByGenre.setForeground(Color.white);
		getContentPane().add(rdbtnByGenre);

		rdbtnYear = new JRadioButton("Year");
		rdbtnYear.setBounds(55, 323, 72, 23);
		rdbtnYear.setBackground(Color.decode("#000000"));
		rdbtnYear.setForeground(Color.white);
		getContentPane().add(rdbtnYear);

		rdbtnRatingRange = new JRadioButton("Rating Range");
		rdbtnRatingRange.setBounds(55, 349, 118, 23);
		rdbtnRatingRange.setBackground(Color.decode("#000000"));
		rdbtnRatingRange.setForeground(Color.white);
		getContentPane().add(rdbtnRatingRange);

		b.add(rdbtnByActor);
		b.add(rdbtnByDirector);
		b.add(rdbtnByGenre);
		b.add(rdbtnRatingRange);
		b.add(rdbtnYear);
		b.add(rdbtnByTitle);

		label = new JLabel("");
		label.setBackground(Color.BLACK);
		label.setBounds(114, 0, 548, 162);
		label.setIcon(new ImageIcon(getClass().getResource("bkg.jpg")));
		getContentPane().add(label);
		
		
		

		choice.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getItem().equals("Movies")) {
					rdbtnByActor.setVisible(true);
					rdbtnByDirector.setVisible(true);
					rdbtnByGenre.setVisible(true);
					rdbtnByTitle.setVisible(true);
					rdbtnRatingRange.setVisible(true);
					rdbtnYear.setVisible(true);
					rAn.setEnabled(false);
					rAac.setEnabled(false);
					rAcm.setEnabled(false);
					rAn.setVisible(false);
					rAac.setVisible(false);
					rAcm.setVisible(false);

				}

				else if(e.getItem().equals("Actor")) {
					
					rdbtnByActor.setVisible(false);
					rdbtnByDirector.setVisible(false);
					rdbtnByGenre.setVisible(false);
					rdbtnByTitle.setVisible(false);
					rdbtnRatingRange.setVisible(false);
					rdbtnYear.setVisible(false);
					rAn.setEnabled(true);
					rAac.setEnabled(true);
					rAcm.setEnabled(true);
					rAn.setVisible(true);
					rAac.setVisible(true);
					rAcm.setVisible(true);
					rAn.setSelected(true);
					
				}
				else{
					rdbtnByActor.setVisible(false);
					rdbtnByDirector.setVisible(false);
					rdbtnByGenre.setVisible(false);
					rdbtnByTitle.setVisible(false);
					rdbtnRatingRange.setVisible(false);
					rdbtnYear.setVisible(false);
					rAn.setEnabled(false);
					rAac.setEnabled(false);
					rAcm.setEnabled(false);
					rAn.setVisible(false);
					rAac.setVisible(false);
					rAcm.setVisible(false);
				}
			}
		});

		setVisible(true);
	}


	void DisplayMovieListInGui(String s) {
		JTextArea textArea = new JTextArea(30, 70);
		textArea.setText(s);
		textArea.setEditable(false);
		JOptionPane.showMessageDialog(null, new JScrollPane(textArea));
	}
	String[] getchoices(MovieList m){
		int count=0;
		
		for(MovieNode temp=m.root;temp!=null;temp=temp.next){
			count++;
		}
		String[] s=new String[count];
		count=0;
		for(MovieNode temp=m.root;temp!=null;temp=temp.next){
			s[count]=temp.data.title;
			count++;
		}
		return s;
	}
}
