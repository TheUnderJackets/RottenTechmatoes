package theunderjackets.com.rottentechmatoes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * An activity for the "Manage Users" page for the admin.
 * The page displays a list of usernames and the users' statuses, and options
 * to unlock or ban the users.
 */
public class ManageUsersActivity extends AppCompatActivity {
    //get the set of users from Userlist and convert to a list
    private List<User> userList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Manage Users");
        userList = getIntent().getParcelableArrayListExtra(LoginActivity.USERSEXTRA);
        final View recyclerView = findViewById(R.id.user_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        Button unlockButton = (Button) findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // After this button is clicked, if a user is selected,
                // then unlock that user
                // See ".isSelected()" method in User.java
                for(User u: userList) {
                    if(u.isSelected()) {
                        u.setLocked(false);
                    }
                    u.setSelected(false);
                }
                ((RecyclerView) recyclerView).getAdapter().notifyDataSetChanged();

            }
        });

        Button banButton = (Button) findViewById(R.id.banButton);
        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // After this button is clicked, if a user is selected,
                // then ban that user
                // See ".isSelected()" method in User.java

                for(User u: userList) {
                    if(u.isSelected()) {
                        u.setBanned(true);
                    }
                    u.setSelected(false);

                }
                ((RecyclerView) recyclerView).getAdapter().notifyDataSetChanged();
            }

        });

        final CheckBox selectAll = (CheckBox) findViewById(R.id.selectAllBox);
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(User u: userList) {
                    if (selectAll.isChecked()) {
                        u.setSelected(true);
                    }
                    else {
                        u.setSelected(false);
                    }

                }

                ((RecyclerView) recyclerView).getAdapter().notifyDataSetChanged();
            }


        });

    }


    /**
     * Sets up the recycler view for the list of users.
     * @param recyclerView The recycler view for the lsit of users.
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setAdapter(new RecyclerViewAdapter(userList));
    }

    /**
     * A class for the RecyclerViewAdapter, which binds the user data to views
     * that are displayed in the RecyclerView.
     */
    public class RecyclerViewAdapter
            extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder2> {

        private final List<User> userValues;

        /**
         * The constructor takes in the list of users.
         * @param items The list of users
         */
        public RecyclerViewAdapter(List<User> items) {
            userValues = items;
        }

        @Override
        public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.user_list_content, parent, false);
            return new ViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder2 holder, int position) {

            final int pos = position;

            holder.uItem = userValues.get(position);

            holder.chkSelected.setChecked(userValues.get(position).isSelected());

            holder.chkSelected.setTag(userValues.get(position));

            holder.chkSelected.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    User contact = (User) cb.getTag();

                    contact.setSelected(cb.isChecked());
                    userValues.get(pos).setSelected(cb.isChecked());

                }
            });

            holder.uNameView.setText(userValues.get(position).getUserName());

            String status;
            if (userValues.get(position).getBanned()) {
                status = "Banned";
            } else if (userValues.get(position).getLocked()) {
                status = "Locked";
            } else {
                status = "Active";
            }
            holder.uStatusView.setText(status);
        }

        @Override
        public int getItemCount() {
            return userValues.size();
        }

        /**
         * This ViewHolder class determines what data (username and status) is
         * placed where in the RecyclerView.
         */
        public class ViewHolder2 extends RecyclerView.ViewHolder {
            //
            protected final View uView;
            protected final TextView uNameView;
            protected final TextView uStatusView;
            protected User uItem;
            protected CheckBox chkSelected;

            /**
             * The constructor tells the view holder to place the usernames
             * and statuses in their respective locations.
             * @param view The view.
             */
            public ViewHolder2(View view) {
                super(view);
                uView = view;
                chkSelected = (CheckBox) view.findViewById(R.id.checkBox);
                uNameView = (TextView) view.findViewById(R.id.user);
                uStatusView = (TextView) view.findViewById(R.id.status);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + uStatusView.getText() + "'";
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.adminLogout) {
            CurrentUser.getInstance().setUser(null);
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
