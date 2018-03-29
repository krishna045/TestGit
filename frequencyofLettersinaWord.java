
public class frequencyofLettersinaWord {
public static void main(String[] args){
	String s="naveen";
	char[] c=s.toCharArray();
	int sz=c.length;
	int i=0;
	int j=0;
	
	for(i=0;i<sz;i++){
		int counter=0;
		for(j=0;j<sz;j++){
			if(j<i && c[i]==c[j]){
				break;
			}
			if(c[i]==c[j]){
				counter++;
			}
			if(j==sz-1){
				System.out.println("the character "+ c[i]+" is present"+counter + "times");
			}
		}
	}
}
}
