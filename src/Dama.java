public class Dama extends Campo {

	public Dama(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean MovimentoDama(int x, int y, Campo [][] camp) {
		boolean muv = false;
		int fatorX;//direcao em x
		int fatorY;//direcaoo em y
		//definindo a direcao do movimento
		//ideia de quadrante, pense que a peca esta no centro e 
		//o x e y vao indicar para que direcao esta indo a peca
		
		if (y > this.y) {
			if(x > this.x) {
				fatorX = 1;
				fatorY = 1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			} else if (x < this.x) {
				fatorX = -1;
				fatorY = 1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			}
		}
		
		if (y < this.y) {
			if(x > this.x) {
				fatorX = 1;
				fatorY = -1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			} else if(x < this.x) {
				fatorX = -1;
				fatorY = -1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			}
		}
		
		return muv;
	}
	
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int fatorX, int fatorY) {
		boolean muv = false;
		int somadorX, somadorY;
		
		if (fatorX < 0)
			somadorX = -1*(fatorX/fatorX); //somador para ir para o proximo campo
		else 
			somadorX = fatorX/fatorX;
		
		if(fatorY < 0)
			somadorY= -1*(fatorY/fatorY);
		else 
			somadorY = fatorY/fatorY;
		
		//verifica cada campo ate chegar na posicao final desejada
		while(x!=fatorX && y!=fatorY ) {
			// Se o a proximo campo estiver vazio ele chama a funcao novamente
			if(camp[this.x+(fatorX)][this.y+(fatorY)].getComum()==null) {
				verificaMovimento(x,y,camp,fatorX+somadorX,fatorY+somadorY);
			}
			//se nao estiver vazio, verifica se eh peca adversaria
			else {
				if(camp[this.x+(fatorX)][this.y+(fatorY)].preta!=preta) {
					//se for peca adversaria verica se o campo apos o adversario esta vazio
					if(camp[this.x+(fatorX+somadorX)][this.y+(fatorY+somadorY)].getComum()==null) {
						//se este campo for destido final retorna jogada valida
						if(this.x+(fatorX+somadorX)==x && this.y+(fatorY+somadorY) ==y) {
							muv=true;
						}
					}
				}
			}
		}
		//se a peca passar por todos os campos e a posicao destino ser igual a 
		//verificada, verifica se o espaco esta disponivel
		if(x==fatorX && y==fatorY){
			if(camp[x][y].getComum()==null && camp[x][y].getDama() == null) {
				muv=true;
			}		
		}
		
		return muv;
	}
}
