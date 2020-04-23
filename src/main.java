public class main {
	public static void main(String[] args) {
		//Recebemos, na entrada, a sequencia de movimentos
		CSVReader csv = new CSVReader();
		csv.setDataSource("src/db/invalidGame.csv");
		String commands[] = csv.requestCommands();
		
		//Geramos o tabuleiro e imprimimos seu estado inicial
		Tabuleiro tabuleiro = new Tabuleiro();
		tabuleiro.gerarTabuleiro();
		System.out.println("Tabuleiro inicial:");
		tabuleiro.imprimirTabuleiro();
		
		//Enquanto houver movimentos, aplicamos o seguinte loop
		for (int i = 0; i < commands.length; i++) {
			//Imprimimos o movimento a ser feito, aplicamos no tabuleiro e o imprimimos
			System.out.println("Source: " + commands[i].substring(0, 2));
			System.out.println("Target: " + commands[i].substring(3));
			tabuleiro.aplicarMovimento(commands[i]);
			tabuleiro.imprimirTabuleiro();
		}		
	}
}