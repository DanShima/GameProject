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

//Game starts
public class AndroidLauncher extends AndroidApplication implements firebase {

    //For Firebase - user validation
    private ArrayList<User> users;
 	private User validUser;
 	DatabaseReference databaseUser;

 	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Firebase
		PlayerLogin.fb=this;
		MenuScreen.fb=this;
		GameOverScreen.fb=this;
        users=new ArrayList<>();
        databaseUser= FirebaseDatabase.getInstance().getReference("users");

        //To load LoginScreen
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new LoginScreen(), config);

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

    /**
     * Login Validation
     * @param name
     * @param password
     * @return boolen
     */
	@Override
	public boolean onclicklogin(String name, String password) {
		boolean found=false;
		for(User u:users)
		{
			if(u.getUserName().equals(name) && u.getUserpassword().equals(password )) {
			validUser = new User(u.getUserId(), u.getUserName(), u.getUserpassword(), u.getHighScore(), u.getScore());
			found = true;
			}
		}
		return found;
	}

    /**
     * Register new User
     * @param name
     * @param password
     * @return boolean
     */
	@Override
	public boolean onclicknewuser(String name, String password) {
	    boolean registered=false;
		if(!TextUtils.isEmpty(name)){
			String id=databaseUser.push().getKey();
			User user=new User(id,name,password);
			databaseUser.child(id).setValue(user);
			registered=true;
        }
        return registered;
    }

	@Override
	public void setHighScore(int Maxscore, String name) {
		for (User u : users) {
			if (u.getUserName().equals(name)) {
				//lidUser = new User(u.getUserId(), u.getUserName(), u.getUserpassword(), u.getHighScore(), u.getScore());
				u.setHighScore(Maxscore);
				databaseUser.child(u.getUserId()).setValue(u);
			}
		}
	}

	@Override
	public int getHighScore(String name) {
		int score=0;
			for(User u:users) {
				if (u.getUserName().equals(name)) {
					score = u.getHighScore();
				}
			}
		return score;
	}

}

