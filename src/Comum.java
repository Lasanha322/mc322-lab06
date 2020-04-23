public class Comum extends Campo {
	
	public Comum(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean movimento(int x, int y, Campo[][] camp) {
		boolean muv = false;
		int fator = 0;
		
		if (camp[this.x][this.y].getComum() != null) {
			//Agora, a gente verifica se a peca sobe ou desce
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
	
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int lado) {
		boolean muv = false;
		// verifica se movimento de 1 casa � verdadeiro
		if(x==(this.x+lado)) {
			if(y==(this.y+1) || y==(this.y-1) ) {
				if(camp[x][y].getComum() == null) {
					muv=true;
				}
			}
		}
		
		//verifica se o movimento de 2 espa�o � possivel e se entre eles tem uma pe�a != da propria			
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