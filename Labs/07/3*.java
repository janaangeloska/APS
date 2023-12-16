/*Потребно е да се симулира рутирање преку хеш табела. Секој рутер претставува една кофичка од хеш табелата и притоа пакетите на влез ги прима преку еден интерфејс. Бидејќи рутерот, рутирањето на даден пакет го врши користејќи ги статичките рути што тој ги знае, кога ќе добие пакет преку влезниот интерфејс, тој треба да даде одговор дали може да го рутира пакетот до дадениот уред во таа мрежа (postoi или nepostoi). Важно е тоа што сите адреси имаат мрежна маска /24, што значи дека последните 8 бита се наменети за адресирање. Претпоставуваме дека сите адреси се зафатени во таа мрежа, така што до било кој уред од таа мрежа, доколку ја има во рутирачката табела, може да се достави пакетот. Така што доколку во рутирачката табела има 10.10.10.0, тоа значи дека рутерот може да го проследи пакетот до  сите уреди во таа мрежа (10.10.10.1- 10.10.10.254).
На влез најпрвин се внесува бројот на рутери, потоа најизменично IP адресата на влезниот интерфејс, па во следниот ред IP адресите на мрежите до кој рутерот има статички рути. Потоа се внесува бројот на обиди за рутирање на пакети. Во следните редови најизменично се внесува влезен интерфејс и IP адреса на уред за која треба да се даде одговор дали тој рутер ја познава или не. 
Име на класта :RoutingHashJava
--------------

You should simulate routing by using a hash table. Every router is one bucket in the hash table and it receives the input packets only through one interface. Since the router performs the routing of the packet by using the static routes it knows, when it receives a packet it should tell if it is possible to route the packet in the network (postoi or nepostoi). It is important that all addresses have network mask /24 which means that the last 8 bits are allocated for addressing. We assume that all addresses are busy and the packet can be transferred to any device in a network if the network exists in the routing table. For example, if the address 10.10.10.0 can be found in the routing table, it means that the router can transfer the packet to all devices in that network (10.10.10.1- 10.10.10.254).
The number of the routers is given in the first input line. In the next lines for each router the IP address of the interface and IP addresses of the networks to which it has static routes are given. Then the number of routing attempts are entered. In the next lines for each attempt an input interface and device IP address are given. The router should answer if it knows the address or not.
Class Name: RoutingHashJava*/
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
		return Math.abs(key.hashCode()) % buckets.length;
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
		// Insert newEntry at the front of the 1WLL in bucket b ...
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
public class RoutingHashJava {
	 public static void main (String[] args) throws IOException {
		 CBHT<String,String[]> tabela;
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	     int N = Integer.parseInt(br.readLine());
		 tabela=new CBHT<String,String[]>(2*N);
	        for(int i=1;i<=N;i++){
	        	String interfejs=br.readLine();
	        	String routing_table=br.readLine();
				String[] ruti = routing_table.split(",");
				tabela.insert(interfejs, ruti);
			}
	        int M = Integer.parseInt(br.readLine());
	        for (int i=1;i<=M;i++)
	        {
	        	String ip_adresa_interfejs=br.readLine();
	        	String mreza=br.readLine();
	        	SLLNode<MapEntry<String,String[]>> teme=tabela.search(ip_adresa_interfejs);
	            if(teme!=null)
	            {
	            	String [] rez=teme.element.value;
	            	String[] bajt_mreza=mreza.split("\\.");
	            	boolean postoi =false;
	            	for(int j=0;j<rez.length;j++)
	            	{
	            		String[] bajt_tabela=rez[j].split("\\.");
	            		if(bajt_tabela[0].compareTo(bajt_mreza[0])==0&&bajt_tabela[1].compareTo(bajt_mreza[1])==0&&bajt_tabela[2].compareTo(bajt_mreza[2])==0)
	            		{
	            			System.out.println("postoi");
	            			postoi=true;
	            			break;
	            		}
	            	}
	            	if(!postoi)
	            	System.out.println("ne postoi");
	            }
	            else
	            	System.out.println("ne postoi");
	        }
	 }
}
