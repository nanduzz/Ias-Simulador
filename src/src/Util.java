package src;

public class Util {
	
	public int type[];
	
	public boolean dataTransfer(int ir){
		return (ir == 10 ) || ( ir == 9 ) || ( ir == 33 )
				           || (( ir >= 1 )&& ( ir<=4 ) );  
	}
	
	public boolean uncondBranch(int ir){
		return ( ( ir == 13 ) || ( ir == 14) );
	}
	
	public boolean condBranch(int ir){
		return ( ( ir == 15 ) || ( ir == 16 ) );
	}
	
	public boolean arithmetic (int ir){
		return ( ( ir >= 5 ) && ( ir<=8 ) ) || ( ir == 11 ) || ( ir == 12)
				|| ( ir == 20 ) || ( ir == 21 );
	}
	
	public boolean addressModify(int ir){
		return ( ( ir == 18 ) || (ir == 19) );
	}
	
	public void instType(int ir){
		int t = -1;
		if(dataTransfer(ir)){
			t = 0;
		}else if ( uncondBranch(ir) ){
			t = 1;
		}else if ( condBranch(ir) ){
			t = 2;
		}else if ( arithmetic(ir) ){
			t = 3;
		}else if ( addressModify(ir) ){
			t = 4;
		}
		
		if(t > -1){
			type[t] = type[t] + 1;
		}
	}
	
	public long getOPL(long word){
		return (word & Base.OPL) >> 32;
	}
	
	public long getOPR(long word){
		return (word & Base.OPR) >> 12;
	}
	
	public long getADL(long word){
		return (word & Base.ADL) >> 20;
	}
	
	public long getADR(long word){
		return (word & Base.ADR);
	}
	
	public long getINSTR(long word){
		return (word & Base.INSTR);
	}
	
	public long getMAG(long word){
		return (word & Base.MAG) >> 39;
	}
	
	public long getNUM(long value){
		return (Base.NUM & value);
	}
	
	public long toComp2(long word){
		long num = getNUM(word);
		if(getMAG(word) == 1){
			num = -num;
		}
		return num;
	}
	public long toMag(long num){
		long word;
		if(num < 0){
			word = -num;
			word = getNUM(word) | Base.MAG;
		}else{
			word = getNUM(num);
		}
		return word;
	}
	
	public void printfDelim(){
		System.out.println("-------------------------------------------");
	}
	
	public void printfInstTypesQtts(){
		String labels[] = {"Data Transfer", "Unconditional Branch", "Conditional Branch", "Arithmetic",
				"Address Modify"};
		int i;
		System.out.println("Quantidade de instru��es executadas");
		for(i = 0; i < 5; i++){
			System.out.println(labels[i] + ": " + type[i]);
		}
		printfDelim();
	}
	
	public void printfMEM(int max, int b, int e){
		int i;
		int mo = 0;
		int mf = max;
		
		if(e<max){
			mf = e;
		}
		System.out.println("Memory(" + mo + " : " + mf + " )");
		for(i = mo; i <= mf; i++){
			//TODO Entender linha 121 do GIT para colocar aqui em baixo
			System.out.println("     " + i + ": " + readMEM(i));
		}
		printfDelim();
	}
	
	public static void main(String[] args) {
		System.out.println(Base.LCLEAN);
	}
	
	public void printPC(){
		System.out.println("PC : " + RegsFlags.getReg(RegsFlags.PC));
	}
	
	public static String printONOFF(boolean on){
		if(on){
			return "ON";
		}
		return "OFF";
	}
	
	public void printREGS(){
		int i;
		String labels[] = {"AC", "IBR", "IR", "MAR", "MBR", "MQ", "PC", "MSK"};
		System.out.println("Registradores");
		for(i = 0; i < labels.length; i++){
			System.out.println("       " + labels[i] + " : " + RegsFlags.getReg(i));
		}
		String fLabels[] = {"FETCH_FLAG", "JMPR_FLAG", "END_FLAG", "READMEM_FLAG", "WRITEMEM_FLAG"};
		for(i = 0; i < fLabels.length; i++){
			System.out.println("      " + fLabels[i] +": " + RegsFlags.isON(i));
		}
		printfDelim();
	}
	
	//TODO fazer essa funcao =)
	public void loqeMEM(){
		
	}
	// TODO fazer essa funcao
	public void printHelp(){
		
	}
	
	//TODO terminar essa funcao
	public void cpuStatus(int max, int ma, int mb){
		char c;
		printPC();
		System.out.print(" > ");
		
		
	}
}