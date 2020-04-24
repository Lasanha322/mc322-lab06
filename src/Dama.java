public class Dama extends Campo {

	public Dama(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean movimento(int x, int y, Campo [][] camp) {
		boolean muv = false;
		int fatorX, fatorY; //Anotam a direcao do movimento
		
		if (x > this.x)
			fatorX = 1;
		else
			fatorX = -1;
		
		if (y > this.y)
			fatorY = 1;
		else
			fatorY = -1;
		
		muv = verificaMovimento(x, y, camp, fatorX, fatorY);		
		return muv;
	}
	
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int fatorX, int fatorY) {
		boolean muv = false;
		int somadorX, somadorY; //Anotam a direcao dos movimentos, permite mexer no fatorX e fatorY
		
		if (fatorX < 0)
			somadorX = -1;
		else 
			somadorX = 1;
		
		if (fatorY < 0)
			somadorY= -1;
		else 
			somadorY = 1;
		
		//Pro trajeto dado, verificamos todos os campos nesse caminho
		while(x != this.x+fatorX && y != this.y+fatorY) {
			//Se o campo em frente estiver vazio, incrementamos as variaveis
			if (camp[this.x+fatorX][this.y+fatorY].getComum()==null && camp[this.x+fatorX][this.y+fatorY].getDama() == null) {
				fatorX = fatorX + somadorX;
				fatorY = fatorY + somadorY;
			} else {
				 //Se tiver peca no campo, verificamos se eh peca adversaria
				if(camp[this.x+fatorX][this.y+fatorY].getComum() != null && 
					camp[this.x+fatorX][this.y+fatorY].comum.getPreta() != this.preta ||
					camp[this.x+fatorX][this.y+fatorY].getDama() != null &&
					camp[this.x+fatorX][this.y+fatorY].dama.getPreta() != this.preta) {
					fatorX = fatorX + somadorX;
					fatorY = fatorY + somadorY;					
					//Se for, verificamos se tem um campo livre depois da peca adversaria
					if(camp[this.x+fatorX][this.y+fatorY].getComum() == null &&
					   camp[this.x+fatorX][this.y+fatorY].getDama() == null ) {
						//Se o movimento acabar nesse espaco livre, validamos o movimento
						if(this.x+fatorX == x && this.y+fatorY == y) {
							muv=true;
						}
					}
				}
			}
		}
		
		//Apos verificamos todos os campos no trajeto, verificamos se o destino final esta livre
		if(camp[x][y].getComum() == null && camp[x][y].getDama() == null) {
			muv=true;
		}
		
		//Por fim, verificamos se o movimento eh numa diagonal valida
		if((x-this.x)/(y-this.y) != 1 && (x-this.x)/(y-this.y) != -1)
			muv = false;
		
		return muv;
	}
}
