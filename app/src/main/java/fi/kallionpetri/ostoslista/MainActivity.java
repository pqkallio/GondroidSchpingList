package fi.kallionpetri.ostoslista;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fi.kallionpetri.ostoslista.domain.ShoppingList;

public class MainActivity extends AppCompatActivity {
    private ShoppingList shoppingList;
    private ListFragment listFragment;
    private AddProductFragment addProductFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.shoppingList = new ShoppingList("Kauppalista");
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.header);
        textView.setText(this.shoppingList.getName());
        this.listFragment = new ListFragment();
        this.addProductFragment = new AddProductFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    // .add(R.id.activity_main, listFragment)
                    .add(R.id.activity_main, addProductFragment)
                    .commit();
        }
    }

    public ShoppingList getShoppingList() {
        return this.shoppingList;
    }

    public void addProduct(View view) {
        this.addProductFragment.addProduct(view);
    }

    public static class AddProductFragment extends Fragment {
        public AddProductFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_add_product, container, false);
            final Button button = (Button) view.findViewById(R.id.button_add);
            button.setClickable(false);
            EditText editText = (EditText) view.findViewById(R.id.add_product);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    buttonActivation(button, charSequence.length() != 0);
                }

                @Override
                public void afterTextChanged(Editable editable) {}
            });
            return view;
        }

        public void addProduct(View view) {
            EditText editText = (EditText) view.findViewById(R.id.add_product);
            String product = editText.getText().toString();
            editText.setText("");
            Button button = (Button) view.findViewById(R.id.button_add);
            buttonActivation(button, false);
        }

        private void buttonActivation(Button button, boolean active) {
            if (!active) {
                button.setClickable(false);
                button.setBackgroundColor(getResources().getColor(R.color.button_inactive));
            } else {
                button.setClickable(true);
                button.setBackgroundColor(getResources().getColor(R.color.button_active));
            }
        }

    }

    public static class ListFragment extends Fragment {
        private ShoppingList shoppingList;
        public ListFragment() {
            // Required empty public constructor
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);

            String[] tuotteet = {   "banana",
                    "omena",
                    "haapana",
                    "kana",
                    "lakana",
                    "aluslakana",
                    "possu raakana"};

            List<String> productList = new ArrayList<>(Arrays.asList(tuotteet));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                    R.layout.fragment_list, R.id.list_item_textview, productList);
            ListView listView = (ListView) rootView.findViewById(R.id.list_view);
            listView.setAdapter(adapter);

            return rootView;
        }
    }

}
