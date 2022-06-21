package god.cna.wordsgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {

    private List<Level> levels = new ArrayList<>();
    private OnRVItemClickListener<Level> onRVItemClickListener;

    public LevelAdapter(List<Level> levels, OnRVItemClickListener<Level> onRVItemClickListener) {
        this.levels = levels;
        this.onRVItemClickListener = onRVItemClickListener;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LevelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_level, parent, false));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder holder, int position) {
        holder.bindLevel(levels.get(position));
    }

    public class LevelViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public LevelViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_level_num);
        }

        public void bindLevel(Level level) {
            textView.setText(String.valueOf(level.getId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRVItemClickListener.onItemClick(level, getAdapterPosition());
                }
            });
        }
    }
}
