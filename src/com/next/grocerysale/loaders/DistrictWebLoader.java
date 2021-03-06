package com.next.grocerysale.loaders;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;

import com.next.core.exception.AppException;
import com.next.grocery.client.DistrictWeb;
import com.next.grocerysale.server.services.DataServices;
import com.next.grocerysale.services.impl.DataServiceFactory;

@SuppressLint("NewApi")
public class DistrictWebLoader extends AsyncTaskLoader<List<DistrictWeb>> {
	Context context;
	Long itemid;
	private DataServices dataServices;

	public DistrictWebLoader(Context context, Long id) {
		super(context);
		this.context = context;
		dataServices = DataServiceFactory.getDataServices();

		itemid = id;
	}

	@Override
	public List<DistrictWeb> loadInBackground() {
		List<DistrictWeb> districts = null;
		for (int i = 0; i < 3; i++) {
		try {
			districts =  dataServices.getAllDistrictOfState(itemid);
			break;
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return districts;
	}

	@Override
	public void deliverResult(List<DistrictWeb> data) {
		// TODO Auto-generated method stub
		super.deliverResult(data);
	}

	private void onReleaseResources(Object cachedSaleItems2) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}

	@Override
	protected void onStopLoading() {
		cancelLoad();
	}

	@Override
	protected void onReset() {
		super.onReset();
		onStopLoading();
	}

	public void onCanceled(List<DistrictWeb> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled((List<DistrictWeb>) data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		onReleaseResources(data);
	}

	@Override
	public void forceLoad() {
		// TODO Auto-generated method stub
		super.forceLoad();
	}

}
