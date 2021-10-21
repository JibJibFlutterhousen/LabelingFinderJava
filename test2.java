import java.io.*;
import java.util.*;
import java.math.BigInteger;
import java.util.Locale;
import java.text.NumberFormat;

public class test2{
	public static void main(String args[]){
		
		/*
			This is the labeling set, in array form, that we are testing for
		*/
		int[] U11 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] U15 = { 1, 2, 4, 7, 8, 11, 13, 14 };
		int[] U21 = { 1, 2, 4, 5, 8, 10, 11, 13, 16, 17, 19, 20 };
		int[] U32 = { 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31 };
		int[] U33 = { 1, 2, 4, 5, 7, 8, 10, 13, 14, 16, 17, 19, 20, 23, 25, 26, 28, 29, 31, 32 };
		
		/*
			This is a formality, and allows us to store all the previous edge relations elsewhere so as to not clutter up the main file
		*/
		Edge_Relation_Collection listing = new Edge_Relation_Collection();
		
		/*
			This is what changes run-to-run
				ie: our labeling set, the number of vertexes on our graph, and which edge relation we're tlaking about
		*/
		int[] labeling_set = U33 ;
		int number_of_vertexes = 9;
		Edge_Relation relation_to_use = listing.get_edge_relation("K_5 Snake");
		
		/*
			This is all  the stuff that's functionally dependent on the previous stuff that changes run-to-run
		*/
		int modulo = labeling_set[labeling_set.length-1]+1;
		BigInteger unique_cases_to_try = Labeling_Finder_Utilities.factorial(labeling_set.length).divide(Labeling_Finder_Utilities.factorial(labeling_set.length - number_of_vertexes));
		System.out.printf("Unique permutations we're trying: %s%n", NumberFormat.getNumberInstance(Locale.US).format(unique_cases_to_try));
		Big_Integer_Counter loop_counter = new Big_Integer_Counter(unique_cases_to_try);

		/*
			Now we set up each of our different threads with their respective information
		*/
		//Graph last_tested_graph = Labeling_Finder_Utilities.test_all_labelings(new ArrayList<>(), new ArrayList<>(), labeling_set, new boolean[labeling_set.length], number_of_vertexes, relation_to_use, modulo, loop_counter);
		Thread_For_Labeling_Finder[] threads = new Thread_For_Labeling_Finder[1];
		for(int i = 0; i < threads.length; i++){
			System.out.printf("Foo");
			threads[i] = new Thread_For_Labeling_Finder();
			threads[i].set_previous_valid_permutation(new ArrayList<>());
			threads[i].set_current_working_list(new ArrayList<>());
			threads[i].set_array_to_permute(labeling_set);
			threads[i].set_used_indexes(new boolean[labeling_set.length]);
			threads[i].set_number_to_use(number_of_vertexes);
			threads[i].set_input_edge_relation(relation_to_use);
			threads[i].set_input_modulo(modulo);
			threads[i].set_loop_counter(loop_counter);
		}
		/*
			Now we tell all the respective threads to run! :)
		*/
		for(int i = 0; i < threads.length; i++){
			threads[i].start();
		}
		
	}
}