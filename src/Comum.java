public class Comum extends Campo {
	
	public Comum(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	//retorna true se o movimento requerido é possivel e compativel com o tipo de peça comum
	public boolean movimento(int x, int y, Campo[][] camp) {
		boolean muv = false;
		int fator = 0;
		//pela cor da peça definimos o sentindo que ela pode movimentar
		if (camp[this.x][this.y].getComum() != null) {
			if (getPreta() == true) {
				fator = 1;
				muv = verificaMovimento (x, y, camp, fator);
			} else {
				fator = -1;
				muv = verificaMovimento (x, y, camp, fator);
			}
		}
		return muv;
	}
	//retorna se o movimento da peça foi possivel
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int lado) {
		boolean muv = false;
		// verifica se movimento de 1 casa é verdadeiro
		if(x==(this.x+lado)) {
			if(y==(this.y+1) || y==(this.y-1) ) {
				if(camp[x][y].getComum() == null) {
					muv=true;
				}
			}
		}
		//verifica se o movimento de 2 casas e se entre eles tem uma peça != da propria			
		if(x==(this.x+(2*lado))) {
			if(y==(this.y+2) ) {
				if(camp[x][y].getComum() == null) {
					if(camp[this.x+lado][this.y+1].getComum() != null && camp[this.x+lado][this.y+1].comum.getPreta() != this.preta
					|| camp[this.x+lado][this.y+1].getDama() != null && camp[this.x+lado][this.y+1].dama.getPreta() != this.preta){
						muv=true;
					}
				}
			}
			if(y==(this.y-2) ) {
				if(camp[x][y].getComum() == null) {
					if(camp[this.x+lado][this.y-1].getComum() != null && camp[this.x+lado][this.y-1].comum.getPreta() != this.preta
					|| camp[this.x+lado][this.y-1].getDama() != null && camp[this.x+lado][this.y-1].dama.getPreta() != this.preta){
						muv=true;
					}	
				}
			}
		}
		
		return muv;
	}
}
