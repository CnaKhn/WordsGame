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
    private List<CharacterPlaceHolder> characterPlaceHolderList = new ArrayList<>();

    public CharacterAdapter(List<CharacterPlaceHolder> characterPlaceHolders) {
        this.characterPlaceHolderList = characterPlaceHolders;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_char, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.bind(characterPlaceHolderList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterPlaceHolderList.size();
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
        }
    }
}
