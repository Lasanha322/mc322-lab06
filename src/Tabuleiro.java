public class Tabuleiro {
	Campo[][] campos;
	int n;
	
	void gerarTabuleiro() {
		this.n = 7;
		campos = new Campo[n][n];
		
		//Começamos gerando o espaço total
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				campos[i][j] = new Campo(i, j, false);
			}
		}
		
		//Agora incluimos as pecas
		for (int i = 2; i < 5; i++) {
			for (int j = 0; j < n; j++) {
				campos[i][j].colocarPeca();
			}
		}
		for (int j = 2; j < 5; j++) {
			for (int i = 0; i < n; i++) {
				campos[i][j].colocarPeca();
			}
		}
		//Tiramos a peca do centro
		campos[3][3].comerPeca();
	}
	
	void aplicarMovimento(String commands) {
		//Processamos o comando de entrada
		char[] command = commands.toCharArray();
		int sourceX, sourceY, targetX, targetY, comidaX, comidaY;
		
		sourceX = 7 - (command[1] - '0');
		targetX = 7 - (command[4] - '0');
		if (targetX == sourceX)
			comidaX = targetX;
		else if (targetX > sourceX)
			comidaX = (targetX - sourceX)/2 + sourceX;
		else
			comidaX = (sourceX - targetX)/2 + targetX;
		
		sourceY = command[0] - 'a';
		targetY = command[3] - 'a';

		if (targetY == sourceY)
			comidaY = targetY;
		else if (targetY > sourceY)
			comidaY = (targetY - sourceY)/2 + sourceY;
		else
			comidaY = (sourceY - targetY)/2 + targetY;
		
		//Com a posicao de tudo definida, aplicamos as operacoes de comer ou colocar as pecas
		campos[sourceX][sourceY].comerPeca();
		campos[targetX][targetY].colocarPeca();
		campos[comidaX][comidaY].comerPeca();
		
	}
	
	void imprimirTabuleiro() {
		//Fazemos um loop para cada linha que sera impressa
		for (int i = 0; i < n; i++) {
			//Pra cada linha, imprimimos os elementos nas colunas
			System.out.print(n-i + " ");
			for (int j = 0; j < n; j++) {
				campos[i][j].mostrarCampo();
			}
			System.out.println();
		}
		//Na última linha, imprimimos as letras
		System.out.println("  a b c d e f g ");
		System.out.println();
	}	
}