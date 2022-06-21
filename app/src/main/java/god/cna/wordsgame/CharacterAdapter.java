package god.cna.wordsgame;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<CharacterPlaceHolder> characterPlaceHolders = new ArrayList<>();
    private OnRVItemClickListener<CharacterPlaceHolder> onRVItemClickListener;

    public void setOnRVItemClickListener(OnRVItemClickListener<CharacterPlaceHolder> onRVItemClickListener) {
        this.onRVItemClickListener = onRVItemClickListener;
    }

    public CharacterAdapter() {}

    public CharacterAdapter(List<CharacterPlaceHolder> characterPlaceHolders) {
        this.characterPlaceHolders = characterPlaceHolders;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characterPlaceHolders.get(position));
    }

    public void add(CharacterPlaceHolder characterPlaceHolder) {
        this.characterPlaceHolders.add(characterPlaceHolder);
        notifyItemInserted(characterPlaceHolders.size() - 1);
    }
    public void clear() {
        this.characterPlaceHolders.clear();
        notifyDataSetChanged();
    }
    public String getWord() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < characterPlaceHolders.size(); i++) {
            stringBuilder.append(characterPlaceHolders.get(i).getCharacter());
        }
        return stringBuilder.toString();
    }

    @Override
    public int getItemCount() {
        return characterPlaceHolders.size();
    }

    public class CharacterViewHolder extends RecyclerView.ViewHolder {

        private TextView tvChar;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            tvChar = itemView.findViewById(R.id.tv_char);
        }

        public void bind(CharacterPlaceHolder characterPlaceHolder) {
            if (characterPlaceHolder.isVisible()) {
                tvChar.setText(characterPlaceHolder.getCharacter().toString());
                tvChar.setVisibility(View.VISIBLE);
            } else {
                tvChar.setVisibility(View.INVISIBLE);
            }

            if (characterPlaceHolder.isNull()) {
                itemView.setBackground(null);
            } else {
                itemView.setBackgroundResource(R.drawable.background_rv_item);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRVItemClickListener != null) {
                        onRVItemClickListener.onItemClick(characterPlaceHolder, getAdapterPosition());
                    }
                }
            });
        }
    }

    public void makeWordVisible(String word) {

        for (int i = 0; i < characterPlaceHolders.size(); i++) {
            if (characterPlaceHolders.get(i).getTag() != null && characterPlaceHolders.get(i).getTag().equals(word)) {
                characterPlaceHolders.get(i).setVisible(true);
                notifyItemChanged(i);
            }
            
        }
    }
}
