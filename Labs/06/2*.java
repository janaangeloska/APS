/*Даден е некој модифициран XML код. Модифицираниот XML код ги користи симболите '[' и ']', за отварање и затворање на таг, соодветно, наместо стандардните '<' и '>'. Треба да се провери дали сите тагови во кодот се правилно вгнездени (дали кодот е валиден) т.е. дали секој отворен таг има соодветен затворен таг со истото име на соодветното место во кодот. За поедноставување, дадено е дека секој отворен таг мора да има свој затворен таг и дека таговите немаат атрибути.
На влез е даден бројот на редови во кодот и самиот XML со секој таг во посебен ред, а на излез треба да се испечати 1 или 0 за валиден или невалиден код, соодветно.
Објаснување: Во модифицираниот XML код секој отворен таг е во облик [imeNaTag], а соодветниот затворен таг е во облик [/imeNaTag].
Пример за правилно вгнездени тагови во XML e:
[tag1]
[tag2] 
Podatok
[/tag2] 
[/tag1] 
Пример за неправилно вгнездени тагови во XML e:
[tag1]
[tag2] 
Podatok
[/tag1]
[/tag2]
------------------

Some modified XML code is provided. The modified XML code uses the symbols '[' and ']', to open and close a tag, respectively, instead of the default '<' and '>'. It should be checked if all the tags in the code are nested correctly (if the code is valid) ie. whether each open tag has a corresponding closed tag with the same name at the appropriate place in the code. For simplicity, it is assumed that every open tag must have its own closing tag and that tags have no attributes.
The input is given the number of lines in the code and the XML itself with each tag in a separate line, and the output should print 1 or 0 for a valid or invalid code, respectively.
Explanation: In the modified XML code each open tag is of the form [tagName] and the corresponding closed tag is of the form [/tagName].
An example of properly nested tags in XML is:
[tag1]
[tag2]
Data
[/tag2]
[/tag1]
An example of improperly nested tags in XML is:
[tag1]
[tag2]
Data
[/tag1]
[/tag2]
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class CheckXML {	
   
	public static void main(String[] args) throws Exception{
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  
        String s = br.readLine();
		int n = Integer.parseInt(s);
		String [] redovi = new String[n];
	
		for(int i=0;i<n;i++)
			redovi[i] = br.readLine();
       
		int valid;
    	
        // Vasiot kod tuka
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        
        System.out.println(valid);
        
        br.close();
	}
}
