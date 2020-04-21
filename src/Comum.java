package lab06;

public class Comum extends Campo {

	public Comum(int x, int y, boolean peca, boolean preta) {
		super(x, y, peca, preta);
		// TODO Auto-generated constructor stub
	}
	public boolean movimento(int x, int y, Campo [][] camp) {
		boolean muv =false;
		int fator;
		if(peca== true && comida == false) {
			// verifica se a peca sobe ou desce 
			if(getPreta() == false) {
				fator =1;
				muv = verificaMovimento (x, y, camp, fator);
			}
			else {
				fator = -1;
				muv = verificaMovimento (x, y, camp, fator);
			}
		}
		return muv;
	}
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int fator) {
		boolean muv=false;
		int lado ;
		// verifica se movimento de 1 casa é verdadeiro
		lado = 1*fator;
		if(y==(this.y+lado)) {
			if(x==(this.x+1) || x==(this.x-1) ) {
				if(camp[x][y].comida == true) {
					muv=true;
				}
			}
		}
		//verifica se o movimento de 2 espaço é possivel e se entre eles tem uma peça != da propria
			
		if(y==(this.y+(2*lado))) {
			if(x==(this.x+2) ) {
				if(camp[x][y].comida == true) {
					if(camp[this.x+1][this.y+lado].comida==false && camp[this.x+1][this.y+lado].preta !=preta){
						muv=true;
					}
				}
			}
			if(x==(this.x-2) ) {
				if(camp[x][y].comida == true) {
					if(camp[this.x-1][this.y+lado].comida==false && camp[this.x-1][this.y+lado].preta !=preta){
						muv=true;
					}	
				}
			}
		}
		return muv;
	}
}
