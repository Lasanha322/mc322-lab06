public class Dama extends Campo {

	public Dama(int x, int y, boolean preta) {
		super(x, y);
		this.preta = preta;
	}

	public boolean movimento(int x, int y, Campo[][] camp) {
		boolean muv = false;
		int fatorX;// direcao em x
		int fatorY;// direcaoo em y
		// definindo a direcao do movimento
		// ideia de quadrante, pense que a peca esta no centro e
		// o x e y vao indicar para que direcao esta indo a peca
		if (y > this.y) {
			if (x > this.x) {
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
			if (x > this.x) {
				fatorX = 1;
				fatorY = -1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			} else if (x < this.x) {
				fatorX = -1;
				fatorY = -1;
				muv = verificaMovimento(x, y, camp, fatorX, fatorY);
			}
		}
		return muv;
	}

	public boolean verificaMovimento(int x, int y, Campo[][] camp, int fatorX, int fatorY) {
		boolean muv = false;
		int somadorX, somadorY;

		if (fatorX < 0)
			somadorX = -1 * (fatorX / fatorX); // somador para ir para o proximo campo
		else
			somadorX = fatorX / fatorX;

		if (fatorY < 0)
			somadorY = -1 * (fatorY / fatorY);
		else
			somadorY = fatorY / fatorY;

		// verifica cada campo ate chegar na posicao final desejada
		while (x != (this.x + fatorX) && y != (this.y + fatorY)) {
			// Se o a proximo campo estiver vazio ele chama a funcao novamente
			if (camp[this.x + (fatorX)][this.y + (fatorY)].getComum() != null
					&& camp[this.x + (fatorX)][this.y + (fatorY)].comum.preta != preta
					|| camp[this.x + (fatorX)][this.y + (fatorY)].getDama() != null
							&& camp[this.x + (fatorX)][this.y + (fatorY)].dama.preta != preta) {
				if (camp[this.x + (fatorX + somadorX)][this.y + (fatorY + somadorY)].getComum() == null
						&& camp[this.x + (fatorX)][this.y + (fatorY)].getDama() == null) {
					muv = true;
				}
			}
			fatorX = fatorX + somadorX;
			fatorY = fatorY + somadorY;
		}
		// se a peca passar por todos os campos e a posicao destino ser igual a
		// verificada, verifica se o espaco esta disponivel
		if (x == (this.x + fatorX) && y == (this.y + fatorY)){
			if (camp[x][y].getComum() == null && camp[x][y].getDama() == null) {
				muv = true;
			}
		}
		return muv;
	}
}
