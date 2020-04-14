public class Campo {
	int x;
	int y;
	boolean peca; //false se for um espaco vazio, true se for um espaco pra peca
	boolean comida; //false quanto a peça nesse espaco nao foi comida, true quando foi comida
	
	Campo (int x, int y, boolean peca) {
		this.x = x;
		this.y = y;
		this.peca = peca;
		this.comida = false;
	}
	
	void colocarPeca() {
		peca = true;
		comida = false;
	}
	
	void comerPeca() {
		comida = true;
	}
	
	void mostrarCampo() {
		if (peca == false) {
			System.out.print("  ");
		} else if (comida == false) {
			System.out.print("P ");
		} else {
			System.out.print("- ");
		}
	}
}
