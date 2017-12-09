package com.mygdx.game;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AndroidLauncher extends AndroidApplication implements firebase{
	private ArrayList<User> users;
	private User validUser;
	DatabaseReference databaseUser;
		@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new GearUp(), config);
			users=new ArrayList<>();
			databaseUser= FirebaseDatabase.getInstance().getReference("users");
	}

	protected void onStart() {
		super.onStart();

		databaseUser.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				users.clear();
				for(DataSnapshot userSnapshot: dataSnapshot.getChildren())
				{
					User user=userSnapshot.getValue(User.class);
					users.add(user);
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}
	public void onclicklogin(String name,String password)
	{
		boolean found=false;

		for(User u:users)
		{
			if(u.getUserName().equals(name) && u.getUserpassword().equals(password )) {
				validUser = new User(u.getUserId(), u.getUserName(), u.getUserpassword(), u.getHighScore(), u.getScore());
				found = true;
			}
		}
		if(found)
		{
			Toast.makeText(this,"Valid User",Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(this,"InValid User.Please register",Toast.LENGTH_LONG).show();
		}

	}

	public void onclicknewuser(String name,String password)
	{
		if(!TextUtils.isEmpty(name))
		{
			String id=databaseUser.push().getKey();
			User user=new User(id,name,password);
			databaseUser.child(id).setValue(user);
			Toast.makeText(this,"New User Added",Toast.LENGTH_LONG).show();
		}else
		{
			Toast.makeText(this,"U should enter a name",Toast.LENGTH_LONG).show();
		}
	}
	}


