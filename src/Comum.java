package lab06;

public class Comum extends Campo {

	public Comum(int x, int y, boolean peca, boolean preta) {
		super(x, y, peca, preta);
		// TODO Auto-generated constructor stub
	}
	public boolean movimento(int x, int y, Campo [][] camp) {
		boolean muv=false;
		// verifica se movimento de uma casa é verdadeiro
			if(y==(this.y+1)) {
				if(x==(this.x+1) || x==(this.x-1) ) {
					if(camp[x][y].comida == true) {
						muv=true;
					}
				}
			}
		//verifica se o movimento de 2 espaço é possivel e se entre eles tem uma peça != da propria
			if(y==(this.y+2)) {
				if(x==(this.x+2) ) {
					if(camp[x][y].comida == true) {
						if(camp[x-1][y-1].comida==false && camp[x-1][y-1].preta !=preta) {
							muv=true;
						}
						
					}
				}
				if(x==(this.x-2) ) {
					if(camp[x][y].comida == true) {
						if(camp[x+1][y-1].comida==false && camp[x+1][y-1].preta !=preta) {
							muv=true;
						}
						
					}
				}
			}
		return muv;
	}
}
