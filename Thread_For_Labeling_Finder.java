import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigInteger;
import java.math.*;
import java.util.Locale;
import java.text.NumberFormat;

public class Thread_For_Labeling_Finder extends Thread{
	private List<Integer> previous_valid_permutation;
	private List<Integer> current_working_list;
	private int[] array_to_permute;
	private boolean[] used_indexes;
	private int number_to_use;
	private Edge_Relation input_edge_relation;
	private int input_modulo;
	private Big_Integer_Counter loop_counter;

	public void set_previous_valid_permutation(List<Integer> input){
		this.previous_valid_permutation = input;
	}
	public void set_current_working_list(List<Integer> input){
		this.current_working_list = input;
	}
	public void set_array_to_permute(int[] input){
		this.array_to_permute = input;
	}
	public void set_used_indexes(boolean[] input){
		this.used_indexes = input;
	}
	public void set_number_to_use(int input){
		this.number_to_use = input;
	}
	public void set_input_edge_relation(Edge_Relation input){
		this.input_edge_relation = input;
	}
	public void set_input_modulo(int input){
		this.input_modulo = input;
	}
	public void set_loop_counter(Big_Integer_Counter input){
		this.loop_counter = input;
	}

	public void run(){
		System.out.printf("%nTesting %s iterations", loop_counter.get_max().toString());
		Graph test = Labeling_Finder_Utilities.test_all_labelings(previous_valid_permutation, current_working_list, array_to_permute, used_indexes, number_to_use, input_edge_relation, input_modulo, loop_counter);
		System.out.printf("%nNo labelings found in %d iterations%n", loop_counter.get_counter());
	}
}