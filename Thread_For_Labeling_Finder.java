import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.math.BigInteger;
import java.math.*;
import java.util.Locale;
import java.text.NumberFormat;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Thread_For_Labeling_Finder extends Thread{
	private List<Integer> previous_valid_permutation;
	private List<Integer> current_working_list;
	private int[] array_to_permute;
	private boolean[] used_indexes;
	private int number_to_use;
	private Edge_Relation input_edge_relation;
	private int input_modulo;
	private Big_Integer_Counter loop_counter;
	private int fixed_label;
	private String output_path;

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
	public void set_fixed_label(int input){
		this.fixed_label = input;
	}
	public void set_output_path(String input){
		this.output_path = input;
	}

	public void run(){
		System.out.printf("%nForcing vertex [%s] to have value [%s]. ", String.valueOf(number_to_use), String.valueOf(fixed_label));
		/*
			First we must set up our labeling set. This means that we remove the fixed_label from the set
		*/
		array_to_permute = Labeling_Finder_Utilities.remove_value_from_int_array(array_to_permute, fixed_label);
		number_to_use--;
		/*
			Now we set up our loop counter
		*/
		BigInteger unique_cases_to_try = Labeling_Finder_Utilities.factorial(array_to_permute.length).divide(Labeling_Finder_Utilities.factorial(array_to_permute.length - number_to_use));
		Big_Integer_Counter new_loop_counter = new Big_Integer_Counter(unique_cases_to_try);
		set_loop_counter(new_loop_counter);
		/*
			Now we run the function that permutes everything that's left :)
		*/
		Labeling_Finder_Utilities.test_all_labelings(previous_valid_permutation, current_working_list, array_to_permute, used_indexes, number_to_use, input_edge_relation, input_modulo, fixed_label, loop_counter, output_path, this);
		/*
			Push the fact that we didn't find any labelings to a file
		*/
		try{
			File failure_file = new File(String.format("%s_failure.txt", output_path));
			failure_file.createNewFile();
			try(FileWriter output_failure_stream = new FileWriter(String.format("%s_failure.txt", output_path))){
				String failure_text = String.format("No labelings found in %d iterations%n", loop_counter.get_counter());
				output_failure_stream.write(failure_text);
				output_failure_stream.close();
			}catch(java.io.FileNotFoundException e){
				System.out.println("Printing stack trace... (couldn't find the txt file for failure)");
			}
		}catch(IOException e){
			System.out.println("Printing stack trace... (problem creating the txt file for failure)");
			e.printStackTrace();
		}
	}
}