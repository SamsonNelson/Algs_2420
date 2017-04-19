package wordNet;

import java.util.Map;
import java.util.Set;

public class WordNet {
	
//	private final SAP sap;
    private final Map<Integer, String> idToSynset;
    private final Map<String, Set<Integer>> nounToIds;
	
	// How am I going to read in two data files
	// Turn into Digraph - Hypernyms.txt - Make sure it is a valid graph - Are there no Cycles? / Single Ancestor
	// Synsets.txt - Ancestor Query - Lookups from 0 to juvenile juvenile_person
	// Two different symbol tables - What kind?
	// 
	public WordNet(String synsets, String hypernyms) {
		
	}
	
//	public Iterable<String> nouns() {
//		
//	}
	
//	public boolean isNoun(String word) {
//		
//	}
	
//	public int distance(String nounA, String nounB) {
//		
//	}
	
//	public String sap(String nounA, String nounB) {
//		
//	}
	

	public static void main(String[] args) {
		
	}

}
