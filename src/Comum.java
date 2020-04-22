public class Comum extends Campo {
	protected Dama dama;
	
	public Comum(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean movimento(int x, int y, Campo [][] camp) {
		boolean muv = false;
		int fator = 0;
		
		if (camp[this.x][this.y].getComum() != null) {
			//Agora, a gente verifica se a peca sobe ou desce
			System.out.println(muv); 
			if(getPreta() == false) {
				fator = 1;
				muv = verificaMovimento (x, y, camp, fator);
			}
			else {
				fator = -1;
				muv = verificaMovimento (x, y, camp, fator);
			}
		}
		System.out.println(muv);
		return muv;
	}
	
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int lado) {
		boolean muv = false;
		
		// verifica se movimento de 1 casa é verdadeiro
		if(y==(this.y+lado)) {
			if(x==(this.x+1) || x==(this.x-1) ) {
				if(camp[x][y].getComum() == null) {
					muv=true;
				}
			}
		}
		
		//verifica se o movimento de 2 espaço é possivel e se entre eles tem uma peça != da propria			
		if(y==(this.y+(2*lado))) {
			if(x==(this.x+2) ) {
				if(camp[x][y].getComum() == null) {
					if(camp[this.x+1][this.y+lado].getComum() != null && camp[this.x+1][this.y+lado].preta != preta){
						muv=true;
					}
				}
			}
			if(x==(this.x-2) ) {
				if(camp[x][y].getComum() == null) {
					if(camp[this.x-1][this.y+lado].getComum() != null && camp[this.x-1][this.y+lado].preta != preta){
						muv=true;
					}	
				}
			}
		}
		
		return muv;
	}
}