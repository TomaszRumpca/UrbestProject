package lemur.urbest.urbestproject;

<<<<<<< HEAD
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
=======
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
>>>>>>> better
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TasksList extends Activity implements OnItemClickListener {

<<<<<<< HEAD
	
	public TasksList(){
		
	}
	
=======
	public TasksList() {

	}

>>>>>>> better
	private ListView tasksList;

	static public String TASK_CROSSWORD = "Murale";
	static public String TASK_2 = "Heksagony";
<<<<<<< HEAD
	static public String TASK_RIDDLE = "K³adka";
	static public String TASK_4 = "Ogródki dzia³kowe";
	static public String TASK_SCULPTURES = "RzeŸby";
	static public String TASK_AIRPORT = "Lotnisko";
	
=======
	static public String TASK_RIDDLE = "Kï¿½adka";
	static public String TASK_4 = "Ogrï¿½dki dziaï¿½kowe";
	static public String TASK_SCULPTURES = "Rzeï¿½by";
	static public String TASK_AIRPORT = "Lotnisko";

>>>>>>> better
	static public int MAX_POINTS_CROSSWORD = 7;
	static public int MAX_POINTS_2 = 6;
	static public int MAX_POINTS_RIDDLE = 6;
	static public int MAX_POINTS_4 = 6;
	static public int MAX_POINTS_SCULPTURES = 6;
	static public int MAX_POINTS_AIRPORT = 5;

<<<<<<< HEAD
	static private String DESC_1 = "Dopasuj has³a do malowide³ na wejœciach do bloku i rozwi¹¿ zagadki. "
			+ "Odpowiedzi wpisz do krzy¿ówki. Pamiêtaj, ¿e ka¿de has³o odpowiada jednemu wejœciu, "
			+ "ale hase³ jest mniej ni¿ wejœæ.";

	static private String DESC_2 = "Masz narysowan¹ siatkê heksagonaln¹. "
			+ "Zamaluj te odcinki, które s¹ budynkami.";

	static private String DESC_3 = "Odgadnij zagadkê!";
	
	static private String DESC_4 = "Oszacuj wartoœæ gruntów pod ogródkami dzia³kowymi na granicy Zaspy i Przymorza " +
			"(wzd³u¿ SKM) na podstawie wartoœci 1m2 inwestycji, które by³y realizowane w pobli¿u w ostatnim czasie.";

	static private String DESC_SCULPTURES = "OdnajdŸ w przestrzeni rzeŸby!";
	
	static private String DESC_AIRPORT = "Wprowadzenie do historii tego miejsca, opowiedzenie o dawnych hangarach, których " +
			"znaczenie zmieni³o siê przez lata. Co by³o w tym miejscu przed lotniskiem?";
	
=======
	static private String DESC_1 = "Dopasuj hasï¿½a do malowideï¿½ na wejï¿½ciach do bloku i rozwiï¿½ï¿½ zagadki. "
			+ "Odpowiedzi wpisz do krzyï¿½ï¿½wki. Pamiï¿½taj, ï¿½e kaï¿½de hasï¿½o odpowiada jednemu wejï¿½ciu, "
			+ "ale haseï¿½ jest mniej niï¿½ wejï¿½ï¿½.";

	static private String DESC_2 = "Masz narysowanï¿½ siatkï¿½ heksagonalnï¿½. "
			+ "Zamaluj te odcinki, ktï¿½re sï¿½ budynkami.";

	static private String DESC_3 = "Odgadnij zagadkï¿½!";

	static private String DESC_4 = "Oszacuj wartoï¿½ï¿½ gruntï¿½w pod ogrï¿½dkami dziaï¿½kowymi na granicy Zaspy i Przymorza "
			+ "(wzdï¿½uï¿½ SKM) na podstawie wartoï¿½ci 1m2 inwestycji, ktï¿½re byï¿½y realizowane w pobliï¿½u w ostatnim czasie.";

	static private String DESC_SCULPTURES = "Odnajdï¿½ w przestrzeni rzeï¿½by!";

	static private String DESC_AIRPORT = "Wprowadzenie do historii tego miejsca, opowiedzenie o dawnych hangarach, ktï¿½rych "
			+ "znaczenie zmieniï¿½o siï¿½ przez lata. Co byï¿½o w tym miejscu przed lotniskiem?";

>>>>>>> better
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks_list);
		tasksList = (ListView) findViewById(R.id.list);

<<<<<<< HEAD
		String[] titles = { TASK_CROSSWORD, TASK_2, TASK_RIDDLE, TASK_4, TASK_SCULPTURES, TASK_AIRPORT};
		String[] descriptions = { DESC_1, DESC_2, DESC_3, DESC_4, DESC_SCULPTURES, DESC_AIRPORT};
		int[] points = new int[] { MAX_POINTS_CROSSWORD, MAX_POINTS_2, MAX_POINTS_RIDDLE, MAX_POINTS_4, MAX_POINTS_SCULPTURES, MAX_POINTS_AIRPORT};

		TaskAdapter adapter = new TaskAdapter(this, titles, descriptions, points);
		
=======
		String[] titles = { TASK_CROSSWORD, TASK_2, TASK_RIDDLE, TASK_4,
				TASK_SCULPTURES, TASK_AIRPORT };
		String[] descriptions = { DESC_1, DESC_2, DESC_3, DESC_4,
				DESC_SCULPTURES, DESC_AIRPORT };
		int[] points = new int[] { MAX_POINTS_CROSSWORD, MAX_POINTS_2,
				MAX_POINTS_RIDDLE, MAX_POINTS_4, MAX_POINTS_SCULPTURES,
				MAX_POINTS_AIRPORT };

		TaskAdapter adapter = new TaskAdapter(this, titles, descriptions,
				points);

>>>>>>> better
		tasksList.setAdapter(adapter);
		tasksList.setOnItemClickListener(this);
	}

	@Override
<<<<<<< HEAD
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		switch (position) {
			case 0: {
				Intent intent = new Intent(getApplicationContext(),Crossword.class);
				startActivity(intent);
				break;
			}
			case 1: {
	
				break;
			}
			case 2: {
				Intent intent = new Intent(getApplicationContext(), Riddle.class);
				startActivity(intent);
				break;
			}
			case 3:{
				 Intent intent = new Intent(getApplicationContext(),MapActivity.class);
				 Bundle bundle = new Bundle();
				 bundle.putInt("mode",1);
				 intent.putExtras(bundle);
			     startActivity(intent);
			     break;
			}
			case 4:{
				Intent intent = new Intent(getApplicationContext(),MapActivity.class);
				 Bundle bundle = new Bundle();
				 bundle.putInt("mode",2);
				 intent.putExtras(bundle);
			     startActivity(intent);
				break;
			}
			case 5:{
				//TODO Lotnisko zadanie 8 
				break;
			}
			
			
			
=======
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (position) {
		case 0: {
			Intent intent = new Intent(getApplicationContext(), Crossword.class);
			startActivity(intent);
			break;
		}
		case 1: {

			break;
		}
		case 2: {
			Intent intent = new Intent(getApplicationContext(), Riddle.class);
			startActivity(intent);
			break;
		}
		case 3: {
			Intent intent = new Intent(getApplicationContext(),
					MapActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("mode", 1);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		}
		case 4: {
			Intent intent = new Intent(getApplicationContext(),
					MapActivity.class);
			Bundle bundle = new Bundle();
			bundle.putInt("mode", 2);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		}
		case 5: {
			// TODO Lotnisko zadanie 8
			break;
		}

>>>>>>> better
		}
	}

}
