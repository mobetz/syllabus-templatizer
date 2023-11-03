import java.util.Scanner;

public class NumberGuesser {

    private Integer number;


    public NumberGuesser() {

    }

    public void collectNumber() {
        Scanner in = new Scanner(System.in);
        System.out.print("Number Guesser says, 'Enter a number': ");
        this.number = Integer.parseInt(in.nextLine());
    }


    public int showOneHigher() {
        return this.number + 1;
    }



}
