package god.cna.wordsgame;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Level implements Parcelable {
    private int id;
    private List<String> words = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeStringList(this.words);
    }

    public Level() {}

    protected Level(Parcel in) {
        this.id = in.readInt();
        this.words = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Level> CREATOR = new Parcelable.Creator<Level>() {
        @Override
        public Level createFromParcel(Parcel source) {
            return new Level(source);
        }

        @Override
        public Level[] newArray(int i) {
            return new Level[0];
        }
    };

}
