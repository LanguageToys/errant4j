package edu.guym.errantj.lang.en.utils.lemmatize;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.dictionary.Dictionary;

import java.util.HashSet;
import java.util.Set;

public class WordNetLemmatizer implements Lemmatizer {
    private final Dictionary wordnet;

    private WordNetLemmatizer(Dictionary wordnet) {
        this.wordnet = wordnet;
    }

    public static WordNetLemmatizer create() {
        try {
            Dictionary wordnet = Dictionary.getDefaultResourceInstance();
            return new WordNetLemmatizer(wordnet);
        } catch (JWNLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> lemmas(String word) {
        try {
            Set<String> lemmas = new HashSet<>();
            for (POS pos : POS.getAllPOS()) {
                lemmas.addAll(wordnet.getMorphologicalProcessor().lookupAllBaseForms(pos, word.toLowerCase()));
            }
            return lemmas;
        } catch (JWNLException e) {
            throw new RuntimeException(e);
        }
    }
}
