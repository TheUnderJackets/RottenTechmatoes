package theunderjackets.com.rottentechmatoes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import theunderjackets.com.rottentechmatoes.R;

/**
 * An activity for the "Manage Users" page for the admin.
 * The page displays a list of usernames and the users' statuses, and options
 * to unlock or ban the users.
 */
public class ManageUsersActivity extends AppCompatActivity {
    //get the set of users from Userlist and convert to a list
    Set<User> userSet = UserList.getUsers();
    List<User> userList = new ArrayList<User>(userSet);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Manage Users");

        View recyclerView = findViewById(R.id.user_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        Button unlockButton = (Button) findViewById(R.id.unlockButton);
        unlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: set action for UNLOCK button
            }
        });

        Button banButton = (Button) findViewById(R.id.banButton);
        banButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: set action for BAN button
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
                    .inflate(R.layout.movie_list_content1, parent, false);
            return new ViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder2 holder, int position) {
            holder.uItem = userValues.get(position);
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
            public final View uView;
            public final TextView uNameView;
            public final TextView uStatusView;
            public User uItem;

            /**
             * The constructor tells the view holder to place the usernames
             * and statuses in their respective locations.
             * @param view The view.
             */
            public ViewHolder2(View view) {
                super(view);
                uView = view;
                uNameView = (TextView) view.findViewById(R.id.user);
                uStatusView = (TextView) view.findViewById(R.id.status);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + uStatusView.getText() + "'";
            }
        }
    }

}
