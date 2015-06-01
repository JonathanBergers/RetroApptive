package com.saxion.nl.retroapptive.communication.data.gatherer;

import android.os.AsyncTask;
import android.util.Log;

import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.ROClient;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.RORequest;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.ConnectionException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.InvalidCredentialException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.exceptions.UnknownErrorException;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.JsonRepr;
import com.saxion.nl.retroapptive.communication.data.gatherer.isis.applib.representation.Link;

public class IsisTask<T extends JsonRepr> extends AsyncTask<Link, Void, T> {

	private Class<T> typeClass;

	public IsisTask(Class<T> typeClass) {
		this.typeClass = typeClass;
	}

	int error = 0;
	private static final int INVALID_CREDENTIAL = -1;
	private static final int CONNECTION_ERROR = -2;
	private static final int UNKNOWN_ERROR = -3;

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected T doInBackground(Link... params) {
		Link elementLink = params[0];
		ROClient client = ROClient.getInstance();
		RORequest request = client.RORequestTo(elementLink.getHref());
		try {
			T result = client.executeT(typeClass, elementLink.getMethod(), request, null);
			Log.d("TRY", "WORK");
			return result;
		} catch (ConnectionException e) {
			error = CONNECTION_ERROR;
			e.printStackTrace();
		} catch (InvalidCredentialException e) {
			error = INVALID_CREDENTIAL;
			e.printStackTrace();
		} catch (UnknownErrorException e) {
			error = UNKNOWN_ERROR;
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.d("TRY", "FAIL");
		return null;

	}

}

