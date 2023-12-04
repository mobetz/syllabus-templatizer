import javafx.collections.ListChangeListener;

public class Listener implements ListChangeListener<String> {

    @Override
    public void onChanged(Change<? extends String> changes) {
        while ( changes.next() ) {
            for ( String name : changes.getAddedSubList() ) {
                System.out.println("Observed adding: " + name);
            }

            for ( String name : changes.getRemoved() ) {
                System.out.println("Observed removing: " + name);
            }
        }
    }
}
