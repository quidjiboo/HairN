package ru.akov.hairn.test_switch_frame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.akov.hairn.R;
import ru.akov.hairn.chooser_service_to_date.Callback_for_Fragments;
import ru.akov.hairn.chooser_service_to_date.ItemClickSupport;
import ru.akov.hairn.chooser_service_to_date.ListContentFragment;
import ru.akov.hairn.chooser_service_to_date.Single_simple;
import ru.akov.hairn.chooser_service_to_date.myRecyclAdapter;

/**
 * Example about replacing fragments inside a ViewPager. I'm using
 * android-support-v7 to maximize the compatibility.
 * 
 * @author Dani Lao (@dani_lao)
 * 
 */
public class ShopTypeSelectFragment extends Fragment implements Callback_for_Fragments {
	private  myRecyclAdapter adapter;
	private static final String TAG = "ShopTypeSelectFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.recycler_view, container, false);

		//Создаём список
		RecyclerView recyclerView = (RecyclerView) inflater.inflate(
				R.layout.recycler_view, container, false);
		adapter   = new myRecyclAdapter();
		recyclerView.setAdapter(adapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

		ItemClickSupport.addTo(recyclerView).setOnItemClickListener(
				new ItemClickSupport.OnItemClickListener() {
					@Override
					public void onItemClicked(RecyclerView recyclerView, int position, View v) {
						Single_simple.getInstance().unregisterCallBack1();


						Log.d("dfgdfgdf","ОЛОЛОЛОЛО position = " +    adapter.getItem(position));
						FragmentTransaction trans = getFragmentManager()
								.beginTransaction();
						trans.replace(R.id.root_frame, new ListContentFragment());


						trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
						trans.addToBackStack(null);

						trans.commit();
					}
				}
		);

		Single_simple.getInstance().registerCallBack1(this);


		return recyclerView;
	}

	@Override
	public void addtolist(String obj) {
		adapter.add(obj);
	}

	@Override
	public void removefromlist(String obj) {

	}
}
