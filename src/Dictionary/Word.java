package Dictionary;


import java.util.Objects;

public class Word {
    private String name;
    private String meaning;
    private int freq;

    public Word() {
    }

    public Word(String name, String meaning, int freq) {
        this.name = name;
        this.meaning = meaning;
        this.freq = freq;
    }


    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return freq == word.freq && Objects.equals(name, word.name) && Objects.equals(meaning, word.meaning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, meaning, freq);
    }

    @Override
    public String toString() {
        return "Word{" +
                "name='" + name + '\'' +
                ", meaning='" + meaning + '\'' +
                ", freq=" + freq +
                '}';
    }
}
