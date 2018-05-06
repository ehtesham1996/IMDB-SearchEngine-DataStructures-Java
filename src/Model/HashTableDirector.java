package Model;

public class HashTableDirector {
	public static final int length=27;
	DirectorAvlTree[] table;
	
	HashTableDirector(){
		table=new DirectorAvlTree[length];
	}
	void insert(Director current){
		if(table[HashFunction(current.name)]==null)
			table[HashFunction(current.name)]=new DirectorAvlTree();
		table[HashFunction(current.name)].insertDirector(current);
	}

	//Hashfuntion to get avltree index of charat(i)
	int HashFunction(String string){
		int index=26;
		if(!string.isEmpty()){
		switch(string.charAt(0)){
		case 'a':case 'A': index=0; break;
		case 'b':case 'B': index=1; break;
		case 'c':case 'C': index=2; break;
		case 'd':case 'D': index=3; break;
		case 'e':case 'E': index=4; break;
		case 'f':case 'F': index=5; break;
		case 'g':case 'G': index=6; break;
		case 'h':case 'H': index=7; break;
		case 'i':case 'I': index=8; break;
		case 'j':case 'J': index=9; break;
		case 'k':case 'K': index=10;break;
		case 'l':case 'L': index=11;break;
		case 'm':case 'M': index=12;break;
		case 'n':case 'N': index=13;break;
		case 'o':case 'O': index=14;break;
		case 'p':case 'P': index=15;break;
		case 'q':case 'Q': index=16;break;
		case 'r':case 'R': index=17;break;
		case 's':case 'S': index=18;break;
		case 't':case 'T': index=19;break;
		case 'u':case 'U': index=20;break;
		case 'v':case 'V': index=21;break;
		case 'w':case 'W': index=22;break;
		case 'x':case 'X': index=23;break;
		case 'y':case 'Y': index=24;break;
		case 'z':case 'Z': index=25;break;
		default : index=26;

	}}
		return index;
	}
	void print(){
		for(int i=0;i<27;i++){
			table[i].display();
		}
	}
	Director search(String name){
		if(table[HashFunction(name)]!=null)
				return table[HashFunction(name)].search(name);
		else
			return null;
		
		
	}
}
