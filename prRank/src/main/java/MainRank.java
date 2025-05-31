import rank.Web;

public class MainRank {
	public static void main(String[] args) {
		String[] links = {"I->C",
				"J->C",
				"A->C",
				"A->D",
				"B->C",
				"B->F",
				"D->F",
				"E->B",
				"E->H",
				"F->G",
				"F->H",
				"G->E",
				"G->H"};

		Web web = new Web();
		for (String arc: links){
			web.addLink(arc);
		}
		System.out.println(web);
		web.simulateClick(4000);
		System.out.println("Pages sorted alphabetically");
		System.out.println(web.getSitesByName());
		System.out.println("Pages sorted by rank");
		System.out.println(web.getSitesByRank());

		System.out.println();

	}
}