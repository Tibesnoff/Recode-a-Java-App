import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.ArrayList;

public class runApp {
	FetchAPI fetch;
	Articles articles;
	Filter filter;
	PrintFilters printer;
	ReadFile fileReader;
	PredicateBuilder pBuilder;
	
	public runApp() {
		System.out.println("Running.......");
		fetch = new FetchAPI();
		articles = fetch.Fetch();
		filter = new Filter(articles);
		printer = new PrintFilters(filter);
		run();
	}
	
	public void run() {	
		if(articles == null) {
			System.out.println("No articles found");
			return;
		}
		
		//printer.PrintWriterFilter("Livia Albeck-Ripka");
		//printer.PrintPublishedFilter(new Date("2023-01-31"));
		
		fileReader = new ReadFile("filtersForBoss.txt");
		ArrayList<String> simone = fileReader.getFileContents();
		
		/*TODO Read in the file, use the file contents array as a stream to find articles,
		 *if any, that match the given line
		 *Needed:
		 *Check if the line is "all" then print all articles
		 *Call to printer.print
		 *Creation of a predicate using the PredicateBuilder class
		 */
//		for (String s: simone)
//			System.out.println(s);
//		
		
		
		PredicateBuilder chenGPT = new PredicateBuilder();
		simone.stream().forEach(s -> {
			if (!s.equals("all")) {
				String left = s.substring(0,s.indexOf(':'));
				String right = s.substring(s.indexOf(':')+1);
				//System.out.println(left + " " + right);
				Predicate<Article> zia = (Predicate<Article>) chenGPT.generalPredicate(left,right);
				printer.print(zia,right);
			}
		} );
		
		
		
		
		
	}
}
