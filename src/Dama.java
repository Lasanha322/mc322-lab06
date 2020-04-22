
public class Dama extends Campo {

	public Dama(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}
	
	public boolean MovimentoDama(int x, int y, Campo [][] camp) {
		boolean muv = false;
		int fatorX;//direção em x
		int fatorY;//direção em y
		//definindo a direção do movimento
		//ideia de quadrante, pense que a peça esta no centro e 
		//o x e y vão indicar para que direção esta indo a peça
		if(y > this.y) {
			if(x > this.x) {
				fatorX=1;
				fatorY=1;
				muv = verificaMovimento(x,y,camp,fatorX,fatorY);
			}
			if(x<this.x) {
				fatorX=-1;
				fatorY=1;
				muv = verificaMovimento(x,y,camp,fatorX,fatorY);
			}
		}
		if(y < this.y) {
			if(x > this.x) {
				fatorX=1;
				fatorY=-1;
				muv = verificaMovimento(x,y,camp,fatorX,fatorY);
			}
			if(x < this.x) {
				fatorX=-1;
				fatorY=-1;
				muv = verificaMovimento(x,y,camp,fatorX,fatorY);
			}
		}
		return muv;
	}
	public boolean verificaMovimento (int x, int y, Campo [][] camp, int fatorX, int fatorY) {
		boolean muv = false;
		int somadorX, somadorY;
		if(fatorX<0) {
			somadorX = -1*(fatorX/fatorX);//somador para ir para o proximo campo	
		}else somadorX = fatorX/fatorX;
		
		if(fatorY<0){
			somadorY= -1*(fatorY/fatorY);
		}else somadorY = fatorY/fatorY;
		
		//verifica cada campo ate chegar na posição final desejada
		while(x!=fatorX && y!=fatorY ) {
			// Se o a proximo campo estiver vazio ele chama a função novamente
			if(camp[this.x+(fatorX)][this.y+(fatorY)].getComum()==null) {
				verificaMovimento(x,y,camp,fatorX+somadorX,fatorY+somadorY);
			}
			//se não estiver vazio verifica se é peca adversaria
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
		//se apoś passar por todos os campos e a posição destino ser igual a 
		//verificada, verificasse se o espaço esta disponivel
		if(x==fatorX && y==fatorY){
			if(camp[x][y].getComum()==null) {
				muv=true;
			}
		
		}
		return muv;
	}
}
