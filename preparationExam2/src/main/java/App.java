import banish.*;

public class App {
	public static void main(String[] args) {
		TextBanisher tb = new TextBanisher();

		// Зчитуємо заборонені слова
		try {
			tb.readBanishedWords("D:\\КПІ\\Adv Programming 1\\full git\\preparationExam2\\src\\main\\java\\banished_words.txt");

			System.out.println("Banished words list:");
			System.out.println(tb);

			// Знаходимо слова, що зустрічаються в тексті
			System.out.println("\nFound banished words:");
			tb.findBanishedWords("D:\\КПІ\\Adv Programming 1\\full git\\preparationExam2\\src\\main\\java\\text.txt");

			// Замінюємо їх згідно зі стратегією UppercasedWord
			System.out.println("\n (UppercasedWord):");
			tb.findAndReplaceBanishedWords("D:\\КПІ\\Adv Programming 1\\full git\\preparationExam2\\src\\main\\java\\text.txt", new UppercasedWord());

			// Замінюємо згідно зі стратегією StarredWord
			System.out.println("\n(StarredWord):");
			tb.findAndReplaceBanishedWords("D:\\КПІ\\Adv Programming 1\\full git\\preparationExam2\\src\\main\\java\\text.txt", new StarredWord());

		} catch (BanishException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}

