public class Dama extends Campo {

	public Dama(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean movimento(int x, int y, Campo [][] camp) {
		boolean muv = false;
		int fatorX;//direcao em x
		int fatorY;//direcao em y
		//definindo a direcao do movimento
		//ideia de quadrante, pense que a peca esta no centro e 
		//o x e y vao indicar para que direcao esta indo a peca
		
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
		int somadorX, somadorY;
		if (fatorX < 0)
			somadorX = -1; //somador para ir para o proximo campo
		else 
			somadorX = 1;
		
		if(fatorY < 0)
			somadorY= -1;
		else 
			somadorY = 1;
		
		//verifica cada campo ate chegar na posicao final desejada
		while(x != this.x+fatorX && y != this.y+fatorY) {
			// Se o a proximo campo estiver vazio, ele chama a funcao novamente
			if (camp[this.x+fatorX][this.y+fatorY].getComum()==null && camp[this.x+fatorX][this.y+fatorY].getDama() == null) {
				fatorX = fatorX + somadorX;
				fatorY = fatorY + somadorY;
			} else {
				 //se nao estiver vazio, verifica se eh peca adversaria 
				if(camp[this.x+fatorX][this.y+fatorY].getComum() != null && 
					camp[this.x+fatorX][this.y+fatorY].comum.getPreta() != this.preta ||
					camp[this.x+fatorX][this.y+fatorY].getDama() != null &&
					camp[this.x+fatorX][this.y+fatorY].dama.getPreta() != this.preta) {
					fatorX = fatorX + somadorX;
					fatorY = fatorY + somadorY;					
					//se for peca adversaria, verifica se o campo apos o adversario esta vazio
					if(camp[this.x+fatorX][this.y+fatorY].getComum() == null &&
							camp[this.x+fatorX][this.y+fatorY].getDama() == null ) {
						//se este campo for o destino final, retorna que eh jogada valida
						if(this.x+fatorX == x && this.y+fatorY == y) {
							muv=true;
						}
					}
				}
			}
		}
		
		//se a peca passar por todos os campos e a posicao destino ser igual a 
		//verificada, verifica se o espaco esta disponivel
		if(x== this.x + fatorX && y==this.y + fatorY){
			if(camp[x][y].getComum() == null && camp[x][y].getDama() == null) {
				muv=true;
			}		
		}
		
		return muv;
	}
}
