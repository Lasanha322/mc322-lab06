public class Campo {
	int x;
	int y;
	boolean peca; //false se for um espaco vazio, true se for um espaco pra peca
	boolean comida; //false quanto a peça nesse espaco nao foi comida, true quando foi comida
	boolean cor;// false se for branca B, true se for preta P
	
	Campo (int x, int y, boolean peca,boolean cor) {
		this.x = x;
		this.y = y;
		this.peca = peca;
		this.comida = false;
		this.cor = cor;
	}
	
	void colocarPeca(boolean cor) {
		peca = true;
		comida = false;
		this.cor = cor;
	}
	
	void comerPeca() {
		comida = true;
	}
	
	void mostrarCampo() {
		if (peca == false) {
			System.out.print("  ");
		} else if (comida == false) {
			if(cor == false) {
				System.out.print("B ");
			}
			else {
				System.out.print("P ");
			}
		} else {
			System.out.print("- ");
		}
	}
}
