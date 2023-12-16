/*Потребно е да се направи компјутерска апликација со која ќе се забрза работењето на една аптека. Притоа апликацијата треба да му овозможи на корисникот (фармацевтот) брзо да пребарува низ огромното множество со лекови кои се внесени во системот. Начинот на кој тој треба да пребарува е следен: доволно е да ги внесе првите 3 букви од името на лекот за да може да му се излиста листа од лекови кои ги има во системот. Работата на фармацевтот е да провери дали внесениот лек го има во системот и да му даде информација на клиентот. Информацијата што треба да му ја даде на клиентот е дали лекот се наоѓа на позитивната листа на лекови, која е цената и колку парчиња од лекот има на залиха. Доколку лекот постои клиентот го нарачува со што кажува колку парчиња ќе купи. Оваа акција фармацевтот треба да ја евидентира на системот (односно да ја намали залихата на лекови за онолку парчиња колку што му издал на клиентот). Доколку нарачката на клиентот е поголема од залихата на лекот што ја има во системот, не се презема никаква акција.
Влез: Од стандарден влез прво се дава број N кој претставува број на лекови кои ќе бидат внесени во системот. Во наредните N реда се дадени имињата на лековите, дали ги има на позитивната листа (1/0), цената и број на парчиња, сите разделени со по едно празно место. Потоа се даваат редови со имиња на лекови и број на парчиња нарачани од клиентот. За означување на крај се дава зборот KRAJ.
Излез: На стандарден излез треба да се испечати за секој од влезовите следната информација: IME POZ/NEG CENA BR_LEKOVI. Доколку лекот не е најден се печати Nema takov lek. Доколку нарачката на клиентот е поголема од залихата се печати Nema dovolno lekovi инаку Napravena naracka.
Забелешка: Задачата да се реши со хeш табела. Функцијата со која се врши мапирање на имињата на лековите во број е следна: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 каде зборот w=c1c2c3c4c5…. е составен од сите големи букви.
Исто така за лековите да се направи посебна класа која како атрибути ќе ги има наведените карактеристики на лекот во системот.
Име на класата: Apteka.
-----------

It is necessary to make a computer application that will speed up the operation of a pharmacy. The application should enable the user (pharmacist) to quickly search through the huge set of drugs entered into the system. The way he should search is as follows: it is enough to enter the first 3 letters of the name of the drug so that a list of the drugs available in the system can be displayed. The job of the pharmacist is to check if the drug is in the system and to give information to the client. The information he should give to the customer is whether the drug is on the positive list of drugs, what is the price and how many pieces of the drug are in stock. If the drug exists, the customer orders it, stating how many pieces he will buy. The pharmacist should record this action on the system (that is, reduce the stock of drugs by as many pieces as he dispensed to the client). If the customer's order is greater than the drug stock in the system, no action is taken.
Input: From the standard input, a number N is first given which represents the number of drugs that will be entered into the system. In the next N lines are given the names of the drugs, whether they are on the positive list (1/0), the price and number of pieces, all separated by a space. Lines are then given with drug names and number of pieces ordered by the customer. The word is given to indicate the end KRAJ.
Output: The following information should be printed on the standard output for each of the inputs: IME POZ/NEG CENA BR_LEKOVI. If the drug is not found, Nema takov lek. is printed. If the customer's order is larger than the stock, it is printed  Nema dovolno lekovi, otherwise Napravena naracka.
Note: The problem should be solved with a hash table. The function that maps drug names to numbers is as follows: h(w)=(29∗(29∗(29∗0+ASCII(c1))+ASCII(c2))+ASCII(c3))%102780 where the word w=c1c2c3c4c5…. is composed of all capital letters.
Also, for the drugs, a separate class should be made which will have the specified characteristics of the drug in the system as attributes.
Class name: Apteka.*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class MapEntry<K extends Comparable<K>,E> implements Comparable<K> {

    // Each MapEntry object is a pair consisting of a key (a Comparable 
    // object) and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry (K key, E val) {
        this.key = key;
        this.value = val;
    }
    
    public int compareTo (K that) {
    // Compare this map entry to that map entry.
        @SuppressWarnings("unchecked")
		MapEntry<K,E> other = (MapEntry<K,E>) that;
        return this.key.compareTo(other.key);
    }

    public String toString () {
        return "<" + key + "," + value + ">";
    }
}

class SLLNode<E> {
	protected E element;
	protected SLLNode<E> succ;

	public SLLNode(E elem, SLLNode<E> succ) {
		this.element = elem;
		this.succ = succ;
	}

	@Override
	public String toString() {
		return element.toString();
	}
}

class CBHT<K extends Comparable<K>, E> {

	// An object of class CBHT is a closed-bucket hash table, containing
	// entries of class MapEntry.
	private SLLNode<MapEntry<K,E>>[] buckets;

	@SuppressWarnings("unchecked")
	public CBHT(int m) {
		// Construct an empty CBHT with m buckets.
		buckets = (SLLNode<MapEntry<K,E>>[]) new SLLNode[m];
	}

	private int hash(K key) {
		// Translate key to an index of the array buckets.
		int h = (29*(29*(29*0 + ((String) key).charAt(0)) + ((String) key).charAt(1)) + ((String) key).charAt(2)) % 102780;
		return  h % buckets.length;
	}

	public SLLNode<MapEntry<K,E>> search(K targetKey) {
		// Find which if any node of this CBHT contains an entry whose key is
		// equal
		// to targetKey. Return a link to that node (or null if there is none).
		int b = hash(targetKey);
		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
			if (targetKey.equals(((MapEntry<K, E>) curr.element).key))
				return curr;
		}
		return null;
	}

	public void insert(K key, E val) {		// Insert the entry <key, val> into this CBHT.
		MapEntry<K, E> newEntry = new MapEntry<K, E>(key, val);
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> curr = buckets[b]; curr != null; curr = curr.succ) {
			if (key.equals(((MapEntry<K, E>) curr.element).key)) {
				// Make newEntry replace the existing entry ...
				curr.element = newEntry;
				return;
			}
		}
		// Insert newEntry at the front of the SLL in bucket b ...
		buckets[b] = new SLLNode<MapEntry<K,E>>(newEntry, buckets[b]);
	}

	public void delete(K key) {
		// Delete the entry (if any) whose key is equal to key from this CBHT.
		int b = hash(key);
		for (SLLNode<MapEntry<K,E>> pred = null, curr = buckets[b]; curr != null; pred = curr, curr = curr.succ) {
			if (key.equals(((MapEntry<K,E>) curr.element).key)) {
				if (pred == null)
					buckets[b] = curr.succ;
				else
					pred.succ = curr.succ;
				return;
			}
		}
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < buckets.length; i++) {
			temp += i + ":";
			for (SLLNode<MapEntry<K,E>> curr = buckets[i]; curr != null; curr = curr.succ) {
				temp += curr.element.toString() + " ";
			}
			temp += "\n";
		}
		return temp;
	}

}

class Lek implements Comparable<Lek>{
	String ime;
	int pozLista;
	int cena;
	int kolicina;
	
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public int getCena() {
		return cena;
	}
	public void setCena(int cena) {
		this.cena = cena;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public int getPozLista() {
		return pozLista;
	}

	public Lek(String ime, int pozLista, int cena, int kolicina) {
		this.ime = ime.toUpperCase();
		this.pozLista = pozLista;
		this.cena = cena;
		this.kolicina = kolicina;
	}
	@Override
	public boolean equals(Object obj) {
		Lek pom = (Lek) obj;
		return this.ime.equals(pom.ime);
	}
	@Override
	public String toString() {
		if(pozLista==1)
		 return ime+"\n"+"POZ\n"+cena+"\n"+kolicina;
		else
		 return ime+"\n"+"NEG\n"+cena+"\n"+kolicina;
	}
	public int compareTo(Lek arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}

public class Apteka {

	public static void main(String[] args) throws Exception, IOException {
		// TODO Auto-generated method stub
		CBHT<String,Lek> tabela;
		BufferedReader br = new BufferedReader(new InputStreamReader(
				System.in));
		int N = Integer.parseInt(br.readLine());
		
		tabela = new CBHT<String,Lek>(50);
		for(int i=1;i<=N;i++){
			String linija = br.readLine();
			String[] vlez = linija.split(" ");
			Lek lek = new Lek(vlez[0], Integer.parseInt(vlez[1]), Integer.parseInt(vlez[2]), Integer.parseInt(vlez[3]));
			//System.out.println(lek.toString());
			tabela.insert(vlez[0].toUpperCase(),lek);
		}
		
		//System.out.println(tabela);
		
		String naracka = (br.readLine()).toUpperCase();
		while(naracka.compareTo("KRAJ")!=0){
			int kol = Integer.parseInt(br.readLine());
			SLLNode<MapEntry<String,Lek>> rez = tabela.search(naracka);
			if(rez==null){
				System.out.println("Nema takov lek");
				naracka = (br.readLine()).toUpperCase();
			}
			else if(rez.element.value.getIme().equals(naracka)){
				System.out.println(rez.element.value.toString());
				if(rez.element.value.getKolicina() < kol)
					System.out.println("Nema dovolno lekovi");
				else {
					int old = rez.element.value.getKolicina();
					rez.element.value.setKolicina(old - kol);
					tabela.insert(naracka, rez.element.value);
					System.out.println("Napravena naracka");
				}
				naracka = (br.readLine()).toUpperCase();
			}
			else{
				naracka = br.readLine();
			}	
		}
	}
}
