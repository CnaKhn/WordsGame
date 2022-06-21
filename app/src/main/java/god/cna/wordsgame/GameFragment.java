package god.cna.wordsgame;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {
    private static final String TAG = "GameFragment";
    private Level level;
    private View guessActionsContainer, btnAccept, btnCancel;
    private CharacterAdapter guessCharsAdapter, wordsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getArguments().getParcelable("Level");
        Log.i(TAG, "onCreate: ");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        guessActionsContainer = view.findViewById(R.id.frame_game_guessActionsContainer);
        btnAccept = view.findViewById(R.id.btn_game_acceptAction);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = guessCharsAdapter.getWord();
                for (int i = 0; i < level.getWords().size(); i++) {
                    if (word.equals(level.getWords().get(i))) {
                        Toast.makeText(getContext(), "درست است. " + word, Toast.LENGTH_SHORT).show();
                        wordsAdapter.makeWordVisible(word);
                        btnCancel.performClick();
                        return;
                    }
                }

                btnCancel.performClick();
                Toast.makeText(getContext(), "صحیح نبود.", Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel = view.findViewById(R.id.btn_game_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guessActionsContainer.setVisibility(View.GONE);
                guessCharsAdapter.clear();
            }
        });
        RecyclerView rvCharacters = view.findViewById(R.id.rv_game_characters);
        rvCharacters.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        List<Character> uniqueChars = GamePlayUtil.extractUniqueChar(level.getWords());
        List<CharacterPlaceHolder> characterPlaceHolders = new ArrayList<>();
        for (int i = 0; i < uniqueChars.size(); i++) {
            CharacterPlaceHolder characterPlaceHolder = new CharacterPlaceHolder();
            characterPlaceHolder.setVisible(true);
            characterPlaceHolder.setCharacter(uniqueChars.get(i));
            characterPlaceHolders.add(characterPlaceHolder);
        }
        CharacterAdapter characterAdapter = new CharacterAdapter(characterPlaceHolders);
        rvCharacters.setAdapter(characterAdapter);
        characterAdapter.setOnRVItemClickListener(new OnRVItemClickListener<CharacterPlaceHolder>() {
            @Override
            public void onItemClick(CharacterPlaceHolder item, int pos) {
                guessActionsContainer.setVisibility(View.VISIBLE);
                guessCharsAdapter.add(item);
            }
        });
        RecyclerView rvGuessChar = view.findViewById(R.id.rv_game_guess);
        rvGuessChar.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        guessCharsAdapter = new CharacterAdapter();
        rvGuessChar.setAdapter(guessCharsAdapter);

        int maxLength = 0;
        for (int i = 0; i < level.getWords().size(); i++) {
            if (level.getWords().get(i).length() > maxLength) {
                maxLength = level.getWords().get(i).length();
            }
        }

        RecyclerView rvWords = view.findViewById(R.id.rv_game_words);
        rvWords.setLayoutManager(new GridLayoutManager(getContext(), maxLength, LinearLayoutManager.VERTICAL, false));
        List<CharacterPlaceHolder> wordsCharactersPlaceHolders = new ArrayList<>();
        for (int i = 0; i < level.getWords().size(); i++) {
            for (int j = 0; j < maxLength; j++) {

                CharacterPlaceHolder characterPlaceHolder = new CharacterPlaceHolder();
                if (j < level.getWords().get(i).length()) {
                    characterPlaceHolder.setCharacter(level.getWords().get(i).charAt(j));
                    characterPlaceHolder.setNull(false);
                    characterPlaceHolder.setVisible(false);
                    characterPlaceHolder.setTag(level.getWords().get(i));
                } else {
                    characterPlaceHolder.setNull(true);
                }
                wordsCharactersPlaceHolders.add(characterPlaceHolder);
            }
        }

        wordsAdapter = new CharacterAdapter(wordsCharactersPlaceHolders);
        rvWords.setAdapter(wordsAdapter);
    }
}
