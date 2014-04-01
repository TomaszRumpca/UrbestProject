package lemur.urbest.urbestproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TasksList extends Activity implements OnItemClickListener {

	public TasksList() {

	}

	private ListView tasksList;

	static public String TASK_CROSSWORD = "Murale";
	static public String TASK_2 = "Heksagony";
	static public String TASK_RIDDLE = "K�adka";
	static public String TASK_4 = "Ogr�dki dzia�kowe";
	static public String TASK_SCULPTURES = "Rze�by";
	static public String TASK_AIRPORT = "Lotnisko";

	static public int MAX_POINTS_CROSSWORD = 7;
	static public int MAX_POINTS_2 = 6;
	static public int MAX_POINTS_RIDDLE = 6;
	static public int MAX_POINTS_4 = 6;
	static public int MAX_POINTS_SCULPTURES = 6;
	static public int MAX_POINTS_AIRPORT = 5;

	static private String DESC_1 = "Dopasuj has�a do malowide� na wej�ciach do bloku i rozwi�� zagadki. "
			+ "Odpowiedzi wpisz do krzy��wki. Pami�taj, �e ka�de has�o odpowiada jednemu wej�ciu, "
			+ "ale hase� jest mniej ni� wej��.";

	static private String DESC_2 = "Masz narysowan� siatk� heksagonaln�. "
			+ "Zamaluj te odcinki, kt�re s� budynkami.";

	static private String DESC_3 = "Odgadnij zagadk�!";

	static private String DESC_4 = "Oszacuj warto�� grunt�w pod ogr�dkami dzia�kowymi na granicy Zaspy i Przymorza "
			+ "(wzd�u� SKM) na podstawie warto�ci 1m2 inwestycji, kt�re by�y realizowane w pobli�u w ostatnim czasie.";

	static private String DESC_SCULPTURES = "Odnajd� w przestrzeni rze�by!";

	static private String DESC_AIRPORT = "Wprowadzenie do historii tego miejsca, opowiedzenie o dawnych hangarach, kt�rych "
			+ "znaczenie zmieni�o si� przez lata. Co by�o w tym miejscu przed lotniskiem?";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tasks_list);
		tasksList = (ListView) findViewById(R.id.list);

		String[] titles = { TASK_CROSSWORD, TASK_2, TASK_RIDDLE, TASK_4,
				TASK_SCULPTURES, TASK_AIRPORT };
		String[] descriptions = { DESC_1, DESC_2, DESC_3, DESC_4,
				DESC_SCULPTURES, DESC_AIRPORT };
		int[] points = new int[] { MAX_POINTS_CROSSWORD, MAX_POINTS_2,
				MAX_POINTS_RIDDLE, MAX_POINTS_4, MAX_POINTS_SCULPTURES,
				MAX_POINTS_AIRPORT };

		TaskAdapter adapter = new TaskAdapter(this, titles, descriptions,
				points);

		tasksList.setAdapter(adapter);
		tasksList.setOnItemClickListener(this);
	}

	@Override
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

		}
	}

}
