package god.cna.wordsgame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        LevelAdapter levelAdapter = new LevelAdapter(GamePlayUtil.createLevels(), new OnRVItemClickListener<Level>() {
            @Override
            public void onItemClick(Level item, int pos) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Level", item);
                Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_gameFragment, bundle);
            }
        });
        recyclerView.setAdapter(levelAdapter);
    }
}
